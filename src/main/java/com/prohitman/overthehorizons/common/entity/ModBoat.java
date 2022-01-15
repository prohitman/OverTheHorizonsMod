package com.prohitman.overthehorizons.common.entity;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModEntityTypes;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class ModBoat extends Boat {
    private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);

    public ModBoat(Level level, double x, double y, double z) {
        this(ModEntityTypes.MOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public ModBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Item getDropItem() {
        /*switch (this.getModBoatType()) {
            default -> ModItems.PINE_BOAT.get();
        }*/

        return ModItems.PINE_BOAT.get();
    }

    public Block getPlanks(){
        switch (this.getModBoatType()) {
            default -> ModBlocks.PINE_PLANKS.get();
        }
        return ModBlocks.PINE_PLANKS.get();
    }

    public ModType getModBoatType() {
        return ModType.byId(this.entityData.get(BOAT_TYPE));
    }

    public void setModBoatType(ModType type){
        this.entityData.set(BOAT_TYPE, type.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOAT_TYPE, ModType.PINE.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("ModType", this.getModBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("ModType", 8)) {
            this.setModBoatType(ModType.byName(compound.getString("ModType")));
        }
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
            if (pOnGround) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != Boat.Status.ON_LAND) {
                        this.resetFallDistance();
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && !this.isRemoved()) {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for(int i = 0; i < 3; ++i) {
                                this.spawnAtLocation(this.getPlanks());
                            }

                            for(int j = 0; j < 2; ++j) {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.resetFallDistance();
            } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && pY < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - pY);
            }

        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (!this.level.isClientSide && !this.isRemoved()) {
            if (pSource instanceof IndirectEntityDamageSource && pSource.getEntity() != null && this.hasPassenger(pSource.getEntity())) {
                return false;
            } else {
                this.setHurtDir(-this.getHurtDir());
                this.setHurtTime(10);
                this.setDamage(this.getDamage() + pAmount * 10.0F);
                this.markHurt();
                this.gameEvent(GameEvent.ENTITY_DAMAGED, pSource.getEntity());
                boolean flag = pSource.getEntity() instanceof Player && ((Player) pSource.getEntity()).getAbilities().instabuild;
                if (flag || this.getDamage() > 40.0F) {
                    if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                        this.spawnAtLocation(this.getDropItem());
                    }

                    this.discard();
                }
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }


    public enum ModType{

        PINE("pine");

        private final String name;

        ModType(String string) {
            this.name = string;
        }

        /**
         * Get a boat type by it's enum ordinal
         */
        public static ModType byId(int id) {
            ModType[] aboat$type = values();
            if (id < 0 || id >= aboat$type.length) {
                id = 0;
            }

            return aboat$type[id];
        }

        public static ModType byName(String pName) {
            ModType[] aboat$type = values();

            for (ModType modBoatTypes : aboat$type) {
                if (modBoatTypes.getName().equals(pName)) {
                    return modBoatTypes;
                }
            }

            return aboat$type[0];
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }
    }
}
