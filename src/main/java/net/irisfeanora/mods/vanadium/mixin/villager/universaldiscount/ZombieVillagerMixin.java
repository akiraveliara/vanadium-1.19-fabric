package net.irisfeanora.mods.vanadium.mixin.villager.universaldiscount;

import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ZombieVillager.class)
public class ZombieVillagerMixin {

	@Shadow
	public CompoundTag tradeOffers;

	@Inject(
			method = "finishConversion(Lnet/minecraft/server/level/ServerLevel;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/npc/Villager;setVillagerXp(I)V"
			),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void universallyReducePrices(ServerLevel world, CallbackInfo ci, Villager entity)
	{
		if(this.tradeOffers == null) {
			return;
		}

		MerchantOffers offers = entity.getOffers();

		for(int i = 0; i < offers.size(); i++) {
			MerchantOffer offer = offers.get(i);

			ItemStack itemStack = offer.getBaseCostA();

			int newCount = itemStack.getCount() - 7;

			itemStack.setCount(Math.max(newCount, 1));

			((TradeOfferAccessorMixin)offer).setFirstBuyItem(itemStack);

			offers.set(i, offer);
		}
	}
}
