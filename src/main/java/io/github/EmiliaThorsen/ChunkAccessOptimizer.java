package io.github.EmiliaThorsen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.ornithemc.osl.entrypoints.api.ModInitializer;

public class ChunkAccessOptimizer implements ModInitializer {

	private static int hits = 0;
	private static int misses = 0;

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("chunk optimizer");

	@Override
	public void init() {
		LOGGER.info("initializing chunk optimizer!");

		try {
			LOGGER.info(Class.forName("sun.util.logging.LoggingProxy", true, null).getClassLoader());
			LOGGER.info(Class.forName("java.util.logging.LoggingProxyImpl").getClassLoader());
			LOGGER.info(Class.forName("sun.util.logging.LoggingProxy").getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static public void logHits() {
		hits++;
		if(hits % 1000 == 0) {
			LOGGER.info("hits:" + hits);
		}
	}
	static public void logMisses() {
		misses++;
		if(misses % 1000 == 0) {
			LOGGER.info("misses:" + misses);
		}
	}
}
