package com.prohitman.overthehorizons.common.item;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.prohitman.overthehorizons.common.network.MessageBreakBlock;
import com.prohitman.overthehorizons.common.network.MessageDecreaseBullets;
import com.prohitman.overthehorizons.common.network.MessageExtendedReachAttack;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.common.util.IExtendedReach;
import com.prohitman.overthehorizons.core.init.ModSounds;
import com.prohitman.overthehorizons.core.util.ExtendedReachUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.settings.KeyBindingMap;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HuntingRifleItem extends Item implements IExtendedReach {
    public int distance;

    public HuntingRifleItem(Properties pProperties, int attackDistance) {
        super(pProperties);
        this.distance = attackDistance;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemstack = user.getItemInHand(hand);
        if (user.isHolding(this)) {
            if (world.isClientSide) {
                CompoundTag tag = user.getMainHandItem().getOrCreateTag();

                int k = tag.getInt("AmmoCount");
                if (k != 0 || user.isCreative()) {

                    int j = distance;//((HuntingRifleItem) item).getMaterial().getBulletShootingRange();

                    ExtendedReachUtils.getMouseOverExtended(j);
                    switch (Minecraft.getInstance().hitResult.getType()) {
                        case ENTITY:
                            OTHPacketHandler.HANDLER.sendToServer(new MessageExtendedReachAttack(((EntityHitResult) Minecraft.getInstance().hitResult).getEntity().getId()));

                            break;
                        case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) Minecraft.getInstance().hitResult;
                            BlockPos blockPos = blockHitResult.getBlockPos();
                            Block block = world.getBlockState(blockPos).getBlock();

                            if (block instanceof AbstractGlassBlock || block instanceof StainedGlassPaneBlock || block == Blocks.GLASS_PANE) {
                                OTHPacketHandler.HANDLER.sendToServer(new MessageBreakBlock(((BlockHitResult) Minecraft.getInstance().hitResult).getBlockPos()));
                            }

                        case MISS:
                            break;

                    }
                    if (!user.isCreative()) {
                        --k;
                    }
                    int i = 5;//((HuntingRifleItem) item).getMaterial().getCooldownMultiplier();
                    user.getCooldowns().addCooldown(this, 4*i);
                    OTHPacketHandler.HANDLER.sendToServer(new MessageDecreaseBullets(k));

                    tag.putInt("AmmoCount", k);

                } else {
                    if (!user.isCreative()) {
                        world.playSound(user, user.getX(), user.getY(), user.getZ(), ModSounds.RIFLE_NO_AMMO.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        user.displayClientMessage((new TranslatableComponent("overthehorizons.message.no_ammo")).withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
                    }
                }
            }
        }

        return super.use(world, user, hand);
    }

    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        HuntingRifleItem rifleitem = (HuntingRifleItem) stack.getItem();
        CompoundTag tag = stack.getTag();
        int bullets = 0;
        if (tag != null) {
            bullets = tag.getInt("AmmoCount");
        }

        if (Key.isKeyPressed(Minecraft.getInstance().getWindow().get.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add((new TranslatableText("nwbase.tooltip.bullet_shooting_range")).formatted(Formatting.BLUE).append("\u00A77" + 20));
            tooltip.add((new TranslatableText("nwbase.tooltip.bullet_capacity")).formatted(Formatting.BLUE).append("\u00A77" + 20));
            tooltip.add((new TranslatableText("nwbase.tooltip.current_bullets")).formatted(Formatting.BLUE).append("\u00A77" + ((Integer) bullets).toString()));
        } else {
            tooltip.add((new TranslatableText("nwbase.tooltip.press_shift")).formatted(Formatting.GRAY));
        }
    }*/


    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public float getReach() {
        return distance;
    }
}
