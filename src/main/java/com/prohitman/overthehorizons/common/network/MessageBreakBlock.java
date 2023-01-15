package com.prohitman.overthehorizons.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageBreakBlock implements IMessage<MessageBreakBlock>{
    private BlockPos blockPos;

    public MessageBreakBlock() {
    }

    public MessageBreakBlock(BlockPos pos) {
        this.blockPos = pos;
    }
    @Override
    public void encode(MessageBreakBlock message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.blockPos);
    }

    @Override
    public MessageBreakBlock decode(FriendlyByteBuf buffer) {
        return new MessageBreakBlock(buffer.readBlockPos());
    }

    @Override
    public void handle(MessageBreakBlock message, Supplier<NetworkEvent.Context> ctx) {
// DEBUG
        System.out.println("Break Glass Message received");
        // Know it will be on the server so make it thread-safe
        final ServerPlayer thePlayer = ctx.get().getSender();
        final BlockPos blockPos = message.blockPos;
        Block block = thePlayer.getLevel().getBlockState(blockPos).getBlock();
        ctx.get().enqueueWork(() -> {
            if(block instanceof GlassBlock || block instanceof StainedGlassPaneBlock || block == Blocks.GLASS_PANE){
                thePlayer.getLevel().destroyBlock(blockPos, false);
            }
        });
        ctx.get().setPacketHandled(true);// no response message
    }
}
