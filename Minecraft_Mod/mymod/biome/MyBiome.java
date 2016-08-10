package mymod.biome;

import mymod.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.passive.EntityMooshroom;    
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class MyBiome extends BiomeGenBase
{
    public MyBiome(int par1)
    {
        super(par1);
        
        this.setBiomeName("My Awesome Biome");
        
        this.topBlock = (byte)Main.MyBlock_1.blockID;
        this.fillerBlock = (byte)Block.blockClay.blockID;
        
        this.theBiomeDecorator.bigMushroomsPerChunk = 10;
        this.theBiomeDecorator.treesPerChunk = 5;

        this.spawnableCreatureList.add(new SpawnListEntry(EntityGiantZombie.class, 25, 4, 8));
        //this.spawnableCreatureList.add(new SpawnListEntry(EntityWither.class, 25, 4, 8));
        
        this.setMinMaxHeight(0.1F, 0.5F);
        this.setTemperatureRainfall(1.5F, 0.2F);
   
    }
}