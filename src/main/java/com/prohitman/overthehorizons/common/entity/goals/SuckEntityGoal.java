package com.prohitman.overthehorizons.common.entity.goals;

import com.prohitman.overthehorizons.common.entity.CatFish;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class SuckEntityGoal extends MoveTowardsTargetGoal {
    private final Random random = new Random();
    private final CatFish catfish;
    private final float within;
    private int unseenTicks;
    protected int unseenMemoryTicks = 60;

    public SuckEntityGoal(CatFish pMob, double pSpeedModifier, float pWithin) {
        super(pMob, pSpeedModifier, pWithin);
        this.catfish = pMob;
        this.within = pWithin;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.catfish.getTarget();
        if (target == null) {
            return false;
        } else if (target.distanceToSqr(this.catfish) > (double)(this.within * this.within)) {
            return false;
        } else {
            Vec3 vec3 = DefaultRandomPos.getPosTowards(this.catfish, 8, 0, target.position(), (double)((float)Math.PI / 2F));
            if (vec3 == null) {
                return false;
            } else {
                return super.canUse();
            }
        }
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
        catfish.lookAt(target, 90.0F, 90.0F);
        if(this.catfish.distanceToSqr(target) < 0.5){
            ItemStack fish_bones = new ItemStack(ModItems.FISH_BONES.get());
            ItemEntity itemEntity = new ItemEntity(catfish.level(), target.getX(), target.getY(), target.getZ(), fish_bones);
            this.catfish.level().addFreshEntity(itemEntity);
            target.remove(Entity.RemovalReason.KILLED);
            this.catfish.playSound(SoundEvents.CHICKEN_EGG, 1,1);
        } else {
            Vec3 vector3d = (new Vec3(this.catfish.getX() - target.getX(), this.catfish.getY() - target.getY(), this.catfish.getZ() - target.getZ())).scale(0.03D + random.nextFloat(0.03F));//+ target.getRandom().nextFloat());//0.03F in nextFloat
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
        /*LivingEntity target = catfish.getTarget();
        assert target != null;
        catfish.lookAt(target, 90.0F, 90.0F);*/
        catfish.setIsSucking(true);
    }
}
