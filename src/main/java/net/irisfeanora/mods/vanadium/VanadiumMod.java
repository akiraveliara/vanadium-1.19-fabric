package net.irisfeanora.mods.vanadium;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanadiumMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("vanadium");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading vanadium. Your villagers are now less painful.");
	}
}
