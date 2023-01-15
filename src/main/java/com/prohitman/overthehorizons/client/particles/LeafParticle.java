package com.prohitman.overthehorizons.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class LeafParticle extends TextureSheetParticle {
    public int tickCounter = 0, i;
    private final Random random = new Random();

    protected LeafParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.gravity *= 0.01F;
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.5F;
        this.lifetime = 40;
        this.i = this.random.nextBoolean() ? 1 : -1;
    }

    public void tick() {
        super.tick();
        tickCounter++;
        if(!this.removed){
            double yVel = 1 + Math.cos(tickCounter/20d)*0.05D;
            this.yd -= this.gravity * yVel;
            double xVel = this.i * Math.cos(tickCounter / (20d + this.random.nextDouble(0.1))) * (this.random.nextDouble(0.03d) + 0.01D);//20
            this.setParticleSpeed(xVel, this.yd, this.zd);
            this.postMoveUpdate();
        }
    }

    protected void postMoveUpdate() {
        if (this.onGround) {
            this.remove();
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            LeafParticle leafParticle = new LeafParticle(pLevel, pX, pY, pZ);
            leafParticle.lifetime = 10000;
            leafParticle.gravity = 0.001F;
            leafParticle.pickSprite(this.sprite);
            return leafParticle;
        }
    }
}
