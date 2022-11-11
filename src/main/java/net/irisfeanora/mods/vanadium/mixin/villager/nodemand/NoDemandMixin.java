package net.irisfeanora.mods.vanadium.mixin.villager.nodemand;

import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MerchantOffer.class)
public class NoDemandMixin {

	@Shadow
	protected int demand;

	/**
	 * @author ExaInsanity
	 * @reason disable demand mechanics
	 */
	@Overwrite
	public void updateDemand() {
		this.demand = 0;
	}
}
