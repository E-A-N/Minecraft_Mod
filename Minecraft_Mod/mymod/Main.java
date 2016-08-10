package mymod;

import java.awt.Color;

import mymod.biome.MyBiome;
import mymod.blocks.MyBlock;
import mymod.entity.chicken.MyEntityChicken;
import mymod.entity.chicken.MyModelChicken;
import mymod.entity.chicken.MyRenderChicken;
import mymod.items.MyItem;
import mymod.items.MyPickaxe;
import mymod.items.MySword;
import mymod.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


/* 	MOD INFO */
	@Mod( modid = "mymod", name = "My Awesome Mod", version = "1.0")
	@NetworkMod(clientSideRequired=true, serverSideRequired=false)	


public class Main {

/*	PROXY INFO */
	@SidedProxy(clientSide = "mymod.proxies.ClientProxy", serverSide = "mymod.proxies.CommonProxy")
	public static CommonProxy proxy;
		
	
/**	
 * DECLARATION SECTION 
 * *********************************************************** */

 //  DECLARE THE SWORD 
        public static Item MySword_1;
        
 //  DECLARE NEW TOOL MATERIAL                                                  //Material Name, Harvest, Efficiency, Damage, Enchantabiliity  
        public static EnumToolMaterial MyToolMaterial = EnumHelper.addToolMaterial("Ugly Stuff", 3, 1561, 8.0F, 9500.0F, 10);

//  DECLARE THE PICKAXE 
        public static Item MyPickaxe_1;

//  DECLARE THE ITEM
        public static Item MyItem_1;
        
 //  DECLARE THE BLOCK
        public static Block MyBlock_1;
        
  //  DECLARE THE BIOME
        public static  BiomeGenBase MyBiome_1;
        
  //  DECLARE THE MOB ID
        static int MyEntityID = 300;
    
    //  SEARCH FOR UNIQUE ID    
        public static int getUniqueEntityId() {
            do {
                MyEntityID++;
            }
            while (EntityList.getStringFromID(MyEntityID) != null);
            return MyEntityID++;
        }
    
    //  DECLARE A NEW EGG
        public static void registerEntityEgg(Class <? extends Entity> entity, int primaryColor, int secondaryColor) {
            int id = getUniqueEntityId();
            EntityList.IDtoClassMapping.put(id, entity);
            EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
        }
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	


@EventHandler	
	public  void preInit( FMLPreInitializationEvent event ) 
	{
/**	
 * LOAD SECTION 
 * *********************************************************** */ 

 //  LOAD THE SWORD
        MySword_1 = new MySword(2021, MyToolMaterial, "MySword_1");
        GameRegistry.registerItem(MySword_1, "MySword_1");
        LanguageRegistry.addName(MySword_1, "Ugly Face Sword");     

//  LOAD THE PICKAXE
        MyPickaxe_1 = new MyPickaxe(2022, EnumToolMaterial.IRON, "MyPickaxe_1");
        GameRegistry.registerItem(MyPickaxe_1, "MyPickaxe_1");
        LanguageRegistry.addName(MyPickaxe_1, "Ugly Axe");
        
 //  LOAD THE ITEM
        MyItem_1 = new MyItem(2030, "MyItem_1").setCreativeTab(CreativeTabs.tabMisc);
        GameRegistry.registerItem(MyItem_1, "MyItem_1");
        LanguageRegistry.addName(MyItem_1, "My Really Awesome Item"); 
        
 //  LOAD THE BLOCK 
        MyBlock_1 = new MyBlock(250, Material.rock, "MyBlock_1");
        GameRegistry.registerBlock(MyBlock_1, "MyBlock_1");
        LanguageRegistry.addName(MyBlock_1, "My Awesome Block"); 
		MinecraftForge.setBlockHarvestLevel(MyBlock_1, "pickaxe", 0);
		
 //  LOAD BIOME
        MyBiome_1 = new MyBiome(30);
        GameRegistry.addBiome(MyBiome_1);
        
  //  REMOVE OTHER BIOMES
        GameRegistry.removeBiome(BiomeGenBase.beach);
        GameRegistry.removeBiome(BiomeGenBase.desert);
        GameRegistry.removeBiome(BiomeGenBase.desertHills);
        GameRegistry.removeBiome(BiomeGenBase.extremeHills);
        GameRegistry.removeBiome(BiomeGenBase.extremeHillsEdge);
        GameRegistry.removeBiome(BiomeGenBase.forest);
        GameRegistry.removeBiome(BiomeGenBase.forestHills);
        GameRegistry.removeBiome(BiomeGenBase.frozenOcean);
        GameRegistry.removeBiome(BiomeGenBase.frozenRiver);
        GameRegistry.removeBiome(BiomeGenBase.iceMountains);
        GameRegistry.removeBiome(BiomeGenBase.icePlains);
        GameRegistry.removeBiome(BiomeGenBase.jungle);
        GameRegistry.removeBiome(BiomeGenBase.jungleHills);
        GameRegistry.removeBiome(BiomeGenBase.mushroomIsland);
        GameRegistry.removeBiome(BiomeGenBase.ocean);
        GameRegistry.removeBiome(BiomeGenBase.plains);
        GameRegistry.removeBiome(BiomeGenBase.river);
        GameRegistry.removeBiome(BiomeGenBase.swampland);
        GameRegistry.removeBiome(BiomeGenBase.taiga);
        GameRegistry.removeBiome(BiomeGenBase.taigaHills);
        
 //  REGISTER YOUR ENTITY
        EntityRegistry.registerGlobalEntityID(MyEntityChicken.class, "My Awesome Mob", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.addSpawn(MyEntityChicken.class, 50, 1, 5, EnumCreatureType.monster, MyBiome_1);     
        EntityRegistry.addSpawn(MyEntityChicken.class, 50, 1, 5, EnumCreatureType.monster, BiomeGenBase.icePlains);
        registerEntityEgg(MyEntityChicken.class, (new Color(0, 255, 0)).getRGB(), (new Color(255, 0, 0)).getRGB());
        RenderingRegistry.registerEntityRenderingHandler(MyEntityChicken.class, new MyRenderChicken(new MyModelChicken(), 0.3F));
        ModLoader.addLocalization("entity.My Awesome Mob.name", "My Awesome Mob");
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	}

@EventHandler
	public static void init( FMLInitializationEvent event ) 
	{
	
/**	
 * RECIPES SECTION 
 * *********************************************************** */

 //  SWORD RECIPE  
        GameRegistry.addRecipe(new ItemStack(MySword_1, 1), new Object[]
        {
                "XXX",
                "XXX",
                " S ",
            'S', Item.stick,
            'X', Item.blazeRod,
        }); 

	
//  PICKAXE RECIPE  
        GameRegistry.addRecipe(new ItemStack(MyPickaxe_1, 1), new Object[]
        {
                "ZZZ",
                "HSH",
                "HSH",
            'S', Item.axeStone,
            'Z', MyItem_1,
            'H', Item.swordDiamond,
        });
        
 //  ITEM RECIPE         
        GameRegistry.addRecipe(new ItemStack(MyItem_1, 1), new Object[]
        {
                "S S",
                " S ",
                "S S",
            'S', Item.stick,
        });
        
  //  SMELTING RECIPE
        GameRegistry.addSmelting(MyBlock_1.blockID, (new ItemStack(MyItem_1, 1)), 0.5F);
/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	
/**	
 * EXTRA METHODS SECTION 
 * *********************************************************** */


        


/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	

	
	}

@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) 
	{

	}
	
}
