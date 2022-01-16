package com.prohitman.overthehorizons.common.network;

import com.prohitman.overthehorizons.common.util.IExtendedReach;
import com.prohitman.overthehorizons.core.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageDecreaseBullets implements IMessage<MessageDecreaseBullets> {
    private int newBulletNum;

    public MessageDecreaseBullets() {

    }

    public MessageDecreaseBullets(int newBulletNum) {
        this.newBulletNum = newBulletNum;
    }

    @Override
    public void encode(MessageDecreaseBullets c, FriendlyByteBuf buffer) {
        buffer.writeInt(c.newBulletNum);
    }

    @Override
    public MessageDecreaseBullets decode(FriendlyByteBuf buffer) {
        return new MessageDecreaseBullets(buffer.readInt());
    }

    @Override
    public void handle(MessageDecreaseBullets message, Supplier<NetworkEvent.Context> ctx) {
// DEBUG
        System.out.println("Decrease Bullets Message received");
        // Know it will be on the server so make it thread-safe
        final ServerPlayer thePlayer = ctx.get().getSender();
        int bullets = message.newBulletNum;
        ItemStack stack = thePlayer.getMainHandItem();
        CompoundTag tag = stack.getOrCreateTag();
        ctx.get().enqueueWork(() -> {

            if (!thePlayer.isCreative()) {
                stack.hurtAndBreak(1, thePlayer, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
            thePlayer.getLevel().playSound(null, thePlayer.getOnPos(), ModSounds.RIFLE_SHOOT_BULLET.get(), SoundSource.PLAYERS, 0.5f, 1f);
            tag.putInt("AmmoCount", bullets);
        });
        ctx.get().setPacketHandled(true);// no response message
    }
}
