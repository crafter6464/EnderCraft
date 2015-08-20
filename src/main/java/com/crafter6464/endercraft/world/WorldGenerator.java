package com.crafter6464.endercraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {
Random rand = new Random();
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimensionId()){
		
		case -1: generateNether (world, chunkX,chunkZ );
		case 0: generateOverWorld (world, chunkX* 16,chunkZ *16, random );
		case 1: generateEnd (world, chunkX,chunkZ );
		}

	}

	private void generateNether(World world, int chunkX, int chunkZ) {
		
		
	}
	private void generateOverWorld(World world, int chunkX, int chunkZ,Random random) {
	
		
	}
	
	private void generateEnd(World world, int chunkX, int chunkZ) {
		
		
	}
/**
 * 
 * @param block the block you want to spawn
 * @param minOre the minimum amount in a vein
 * @param maxOre the max amouunt in a vein
 * @param minY minimum y of which the block can spawn
 * @param maxY max y of which the block can spawn
 * @param world world to spawn in
 * @param random random var
 * @param ChunkX chunkX coord
 * @param ChunkZ chunkZ coord
 * @param rarity how rare the block is (1 for rare blocks, 2 is recomended)
 */
private void addOreSpawn(Block block, int minOre,int maxOre,int minY,int maxY,World world,Random random,int ChunkX,int ChunkZ,int rarity){
	WorldGenMinable minable = new WorldGenMinable(block.getDefaultState(),rand.nextInt(maxOre)+minOre);
for(int i = 0; i<rarity;++i){
	int posX = ChunkX + random.nextInt(16);
	int posY = random.nextInt(maxY-minY) +minY; 
	int posZ =ChunkZ + random.nextInt(16);
	
	
	minable.generate(world, random, new BlockPos(posX,posY,posZ));
}
}



}
