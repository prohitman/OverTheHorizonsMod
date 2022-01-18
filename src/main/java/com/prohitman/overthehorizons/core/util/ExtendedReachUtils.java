package com.prohitman.overthehorizons.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ExtendedReachUtils {
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
}
