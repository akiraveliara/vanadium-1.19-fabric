package net.irisfeanora.mods.vanadium.mixin.player.oldelytra;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

// restore 1.15 elytra/levitation interactions

@Mixin(value = Player.class)
public abstract class OldElytraMechanicMixin extends LivingEntity {

	protected OldElytraMechanicMixin(EntityType<? extends LivingEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Shadow
	abstract void startFallFlying();

	@Shadow
	Abilities abilities;

	@Shadow
	public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	/**
	 * @author ExaInsanity
	 * @reason allow elytra to fly with levitation
	 */
	@Overwrite
	public boolean tryToStartFallFlying() {
		if(!this.onGround && !this.isFallFlying() && !this.isUnderWater()){
			ItemStack chestItem = this.getItemBySlot(EquipmentSlot.CHEST);

			if(chestItem.is(Items.ELYTRA) && ElytraItem.isFlyEnabled(chestItem)) {
				this.startFallFlying();
				return true;
			}
		}

		return false;
	}
}
