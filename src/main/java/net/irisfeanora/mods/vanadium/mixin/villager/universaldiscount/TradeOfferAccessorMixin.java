package net.irisfeanora.mods.vanadium.mixin.villager.universaldiscount;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MerchantOffer.class)
public interface TradeOfferAccessorMixin {

	@Accessor("baseCostA")
	public void setFirstBuyItem(ItemStack firstBuyItem);
}
