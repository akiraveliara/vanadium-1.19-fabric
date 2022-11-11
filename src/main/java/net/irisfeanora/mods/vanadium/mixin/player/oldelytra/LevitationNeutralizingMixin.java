package net.irisfeanora.mods.vanadium.mixin.player.oldelytra;

import net.irisfeanora.mods.vanadium.VanadiumMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;

@Mixin(value = LivingEntity.class)
public abstract class LevitationNeutralizingMixin extends Entity {

    public LevitationNeutralizingMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    public abstract boolean isFallFlying();

    @Redirect(
        method = "travel(Lnet/minecraft/world/phys/Vec3;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z",
            ordinal = 2
        )
    )
    public boolean disableLevitationMovementWhenFlying(LivingEntity entity, MobEffect effect) {
        if(isFallFlying()) {
            return false;
        } else {
            return entity.hasEffect(effect);
        }
    }

    @Redirect(
        method = "updateFallFlying()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"
        )
    )
    public boolean suppressLevitationDetectionWhenFlying(LivingEntity entity, MobEffect effect) {
        return false;
    }
}
