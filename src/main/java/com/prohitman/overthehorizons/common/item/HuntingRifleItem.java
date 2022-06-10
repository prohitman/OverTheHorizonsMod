package com.prohitman.overthehorizons.common.item;

import com.prohitman.overthehorizons.client.renderers.ItemStackRenderer;
import com.prohitman.overthehorizons.common.network.MessageBreakBlock;
import com.prohitman.overthehorizons.common.network.MessageDecreaseBullets;
import com.prohitman.overthehorizons.common.network.MessageExtendedReachAttack;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.common.util.IExtendedReach;
import com.prohitman.overthehorizons.core.init.ModSounds;
import com.prohitman.overthehorizons.core.util.ExtendedReachUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.settings.KeyBindingMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class HuntingRifleItem extends Item implements IExtendedReach {
    public static int distance;

    public HuntingRifleItem(Properties pProperties, int attackDistance) {
        super(pProperties);
        distance = attackDistance;
    }

    /*@Override
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
        return InteractionResultHolder.fail(itemstack);
        //return super.use(world, user, hand);
    }*/

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new ItemStackRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        //HuntingRifleItem rifleitem = (HuntingRifleItem) stack.getItem();
        CompoundTag tag = stack.getTag();
        int bullets = 0;
        if (tag != null) {
            bullets = tag.getInt("AmmoCount");
        }

        //if (Minecraft.getInstance().options.keyShift.isDown()) {
        tooltip.add((new TranslatableComponent("overthehorizons.tooltip.rifle_shooting_range")).withStyle(ChatFormatting.BLUE).append("\u00A77" + 20));
        tooltip.add((new TranslatableComponent("overthehorizons.tooltip.bullet_capacity")).withStyle(ChatFormatting.BLUE).append("\u00A77" + 25));
        tooltip.add((new TranslatableComponent("overthehorizons.tooltip.current_bullets")).withStyle(ChatFormatting.BLUE).append("\u00A77" + ((Integer) bullets)));
        //} else {
        //    tooltip.add((new TranslatableComponent("overthehorizons.tooltip.press_shift")).withStyle(ChatFormatting.GRAY));
        //}
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public float getReach() {
        return distance;
    }
}
