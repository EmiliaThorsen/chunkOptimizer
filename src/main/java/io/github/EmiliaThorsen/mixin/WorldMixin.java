package io.github.EmiliaThorsen.mixin;


import io.github.EmiliaThorsen.ChunkAccessOptimizer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(World.class)
public abstract class WorldMixin {

	@Unique
	private int prevChunkX;
	@Unique
	private int prevChunkZ;

	@Shadow
	protected ChunkSource chunkSource;

	@Unique
	private WorldChunk prevChunk;

	/**
	 * @author Emilia
	 * @reason dumb test thing
	 */
	@Overwrite
	public WorldChunk getChunkAt(int chunkX, int chunkZ) {
		if (chunkX != prevChunkX || chunkZ != prevChunkZ || prevChunk.removed) {
			prevChunk = this.chunkSource.getChunkNow(chunkX, chunkZ);
			prevChunkX = chunkX;
			prevChunkZ = chunkZ;
			ChunkAccessOptimizer.logMisses();
		} else ChunkAccessOptimizer.logHits();
		return prevChunk;
	}


}
