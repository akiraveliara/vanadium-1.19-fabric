package net.irisfeanora.mods.vanadium.mixin.villager.oldrestock;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Villager.class)
public abstract class OldRestockMechanicMixin extends AbstractVillager {

	public OldRestockMechanicMixin(EntityType<? extends AbstractVillager> entityType, Level world) {
		super(entityType, world);
	}

	@Shadow
	public abstract void updateDemand();

	@Shadow
	public abstract VillagerData getVillagerData();

	@Shadow
	public long lastRestockGameTime;

	/**
	 * @author ExaInsanity
	 * @reason 1.14 restock mechanics
	 */
	@Overwrite
	public void restock() {
		this.updateDemand();

		for(MerchantOffer offer : this.getOffers()) {
			offer.resetUses();
		}

		this.lastRestockGameTime = this.level.getGameTime();
	}
}
