package com.prohitman.overthehorizons.common.item;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.prohitman.overthehorizons.common.network.MessageBreakBlock;
import com.prohitman.overthehorizons.common.network.MessageDecreaseBullets;
import com.prohitman.overthehorizons.common.network.MessageExtendedReachAttack;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.common.util.IExtendedReach;
import com.prohitman.overthehorizons.core.init.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
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

    //From GameRenderer
    public static void getMouseOverExtended(float dist) {
        Entity entity = Minecraft.getInstance().getCameraEntity();

        if (entity != null) {
            if (Minecraft.getInstance().level != null) {
                Minecraft.getInstance().getProfiler().push("pick");
                double d0 = dist;
                Minecraft.getInstance().hitResult = entity.pick(d0, 1.0F, false);
                Vec3 Vector3d = entity.getEyePosition(1.0F);
                boolean flag = false;
                double d1 = d0;

                {
                    if (d0 > 3.0D) {
                        flag = true;
                    }
                }

                if (Minecraft.getInstance().hitResult != null) {
                    d1 = Minecraft.getInstance().hitResult.getLocation().distanceToSqr(Vector3d);
                }

                d1 = d1 * d1;

                Vec3 Vector3d1 = entity.getViewVector(1.0F);
                Vec3 Vector3d2 = Vector3d.add(Vector3d1.x * d0, Vector3d1.y * d0, Vector3d1.z * d0);
                AABB axisalignedbb = entity.getBoundingBox().expandTowards(Vector3d1.scale(d0)).inflate(1.0D, 1.0D, 1.0D);
                EntityHitResult entityraytraceresult = ProjectileUtil.getEntityHitResult(entity, Vector3d, Vector3d2,
                        axisalignedbb, (Entity) -> !Entity.isSpectator() && Entity.isPickable(), d1);
                if (entityraytraceresult != null) {
                    Entity pointedEntity = entityraytraceresult.getEntity();
                    Vec3 Vector3d3 = entityraytraceresult.getLocation();
                    double d2 = Vector3d.distanceToSqr(Vector3d3);

                    if (pointedEntity != null && flag && d2 > d1) {
                        Minecraft.getInstance().hitResult = BlockHitResult.miss(Vector3d3,
                                Direction.getNearest(Vector3d1.x, Vector3d1.y, Vector3d1.z), new BlockPos(Vector3d3));
                    }

                    else if (pointedEntity != null && (d2 < d1 || Minecraft.getInstance().hitResult == null)) {
                        Minecraft.getInstance().hitResult = entityraytraceresult;

                        if (pointedEntity instanceof LivingEntity || pointedEntity instanceof ItemFrame) {
                            Minecraft.getInstance().crosshairPickEntity = pointedEntity;
                        }
                    }
                }

                Minecraft.getInstance().getProfiler().pop();
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        //Item item = user.getItemInHand(hand).getItem();
        if (user.isHolding(this)) {
                if (world.isClientSide) {
                    CompoundTag tag = user.getMainHandItem().getOrCreateTag();

                    int k = tag.getInt("AmmoCount");
                    if (k != 0 || user.isCreative()) {

                        int j = distance;//((HuntingRifleItem) item).getMaterial().getBulletShootingRange();

                        getMouseOverExtended(j);
                        switch (Minecraft.getInstance().hitResult.getType()) {
                            case ENTITY:
                                OTHPacketHandler.HANDLER.sendToServer(new MessageExtendedReachAttack(((EntityHitResult) Minecraft.getInstance().hitResult).getEntity().getId()));

                                //PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                               //passedData.writeInt(((EntityHitResult) MinecraftClient.getInstance().crosshairTarget).getEntity().getEntityId());
                               //ClientSidePacketRegistry.INSTANCE.sendToServer(GunPackets.GUN_ATTACK_ENTITY_PACKET_ID, passedData);

                                break;

                            case BLOCK:
                                BlockHitResult blockHitResult = (BlockHitResult) Minecraft.getInstance().hitResult;
                                BlockPos blockPos = blockHitResult.getBlockPos();
                                Block block = world.getBlockState(blockPos).getBlock();

                                if (block instanceof AbstractGlassBlock || block instanceof StainedGlassPaneBlock || block == Blocks.GLASS_PANE) {
                                    OTHPacketHandler.HANDLER.sendToServer(new MessageBreakBlock(((BlockHitResult) Minecraft.getInstance().hitResult).getBlockPos()));
                                }
                                break;
                            case MISS:
                                break;

                        }
                        if (!user.isCreative()) {
                            k = --k;
                        }
                        int i = 5;//((HuntingRifleItem) item).getMaterial().getCooldownMultiplier();
                        user.getCooldowns().addCooldown(this, 4*i);
                        OTHPacketHandler.HANDLER.sendToServer(new MessageDecreaseBullets(k));

                        tag.putInt("AmmoCount", k);
                    } else {
                        if (!user.isCreative()) {
                            world.playSound(user, user.getX(), user.getY(), user.getZ(), ModSounds.RIFLE_NO_AMMO.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                            user.displayClientMessage((new TextComponent("overthehorizons.message.no_ammo")).withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
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
