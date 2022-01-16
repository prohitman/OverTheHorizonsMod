package com.prohitman.overthehorizons.common.network;

import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.core.init.ModItems;
import com.prohitman.overthehorizons.core.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageReloadRifle implements IMessage<MessageReloadRifle> {

    public MessageReloadRifle(){
    }

    @Override
    public void encode(MessageReloadRifle message, FriendlyByteBuf buffer) {
    }

    @Override
    public MessageReloadRifle decode(FriendlyByteBuf buffer) {
        return new MessageReloadRifle();
    }

    @Override
    public void handle(MessageReloadRifle message, Supplier<NetworkEvent.Context> ctx) {
        // DEBUG
        System.out.println("Reload Message received");
        // Know it will be on the server so make it thread-safe
        final ServerPlayer thePlayer = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {

            if (thePlayer != null) {
                ItemStack stack = thePlayer.getMainHandItem();
                Item heldItem = stack.getItem();
                CompoundTag tag = stack.getOrCreateTag();

                if (heldItem instanceof HuntingRifleItem) {
                    HuntingRifleItem rifleItem = (HuntingRifleItem) heldItem;

                        for (int i = 0; i < thePlayer.getInventory().getContainerSize(); ++i) {
                            ItemStack bulletstack = thePlayer.getInventory().getItem(i);

                            int bulletstacknum = bulletstack.getCount();
                            if (!bulletstack.isEmpty()) {
                                if (bulletstack.getItem() == ModItems.COPPER_BULLET.get()) {
                                    int bulletnumber = tag.getInt("AmmoCount");
                                    int bulletcapacity = 20;//rifleItem.getMaterial().getBulletCapacity();
                                    int neededbullets = bulletcapacity - bulletnumber;

                                    if (neededbullets != 0) {
                                        if (neededbullets <= bulletstacknum) {
                                            bulletnumber = bulletnumber + neededbullets;

                                            bulletstack.setCount(bulletstacknum - neededbullets);
                                        } else {
                                            bulletnumber = bulletnumber + bulletstacknum;
                                            thePlayer.getInventory().removeItem(i, 1);//.removeOne(bulletstack);

                                        }

                                        tag.putInt("AmmoCount", bulletnumber);
                                        thePlayer.getCooldowns().addCooldown(rifleItem, 4*i);
                                        thePlayer.getLevel().playSound(null, thePlayer.getOnPos(), ModSounds.RIFLE_RELOAD.get(), SoundSource.PLAYERS, 0.5f, 1f);
                                    }
                                }
                        }
                    }
                }
            }

        });
        ctx.get().setPacketHandled(true);// no response message
    }
}
