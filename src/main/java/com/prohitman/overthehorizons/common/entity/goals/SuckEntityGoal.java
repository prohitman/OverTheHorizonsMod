package com.prohitman.overthehorizons.common.entity.goals;

import com.prohitman.overthehorizons.common.entity.CatFish;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.phys.Vec3;

public class SuckEntityGoal extends MoveTowardsTargetGoal {
    private final CatFish catfish;
    private int unseenTicks;
    protected int unseenMemoryTicks = 60;

    public SuckEntityGoal(CatFish pMob, double pSpeedModifier, float pWithin) {
        super(pMob, pSpeedModifier, pWithin);
        this.catfish = pMob;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.catfish.getTarget();
        if(target != null){
            if (this.catfish.getSensing().hasLineOfSight(target)) {
                this.unseenTicks = 0;
            } else if (++this.unseenTicks > reducedTickDelay(this.unseenMemoryTicks)) {
                return false;
            }
        }

        return super.canContinueToUse();

    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.catfish.getTarget();
        assert target != null;
        if(this.catfish.distanceToSqr(target) < 0.5){
            target.remove(Entity.RemovalReason.KILLED);
            this.catfish.playSound(SoundEvents.POLISHED_DEEPSLATE_PLACE, 1,1);
        } else {
            Vec3 vector3d = (new Vec3(this.catfish.getX() - target.getX(), this.catfish.getY() - target.getY(), this.catfish.getZ() - target.getZ())).scale(0.02D);
            target.setDeltaMovement(target.getDeltaMovement().add(vector3d));
        }

    }

    @Override
    public void stop() {
        super.stop();
        catfish.setIsSucking(false);
        this.unseenTicks = 0;
    }

    @Override
    public void start() {
        super.start();
        catfish.setIsSucking(true);
    }
}
