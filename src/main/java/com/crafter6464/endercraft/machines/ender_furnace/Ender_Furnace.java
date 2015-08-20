package com.crafter6464.endercraft.machines.ender_furnace;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.crafter6464.endercraft.block.ECBlockTileEntity;
import com.crafter6464.endercraft.init.EnderCraftBlocks;
import com.crafter6464.endercraft.reference.GuiIDs;
import com.crafter6464.endercraft.reference.Reference;

public class Ender_Furnace extends ECBlockTileEntity {
private static boolean isPowered;
public boolean active;
public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public Ender_Furnace(boolean active,String name) {
		
		super(name);
		this.active=active;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityEnderFurnace();
	}
@Override
public boolean onBlockActivated(World worldIn, BlockPos pos,
		IBlockState state, EntityPlayer playerIn, EnumFacing side,
		float hitX, float hitY, float hitZ) {
	if(worldIn.getTileEntity(pos)instanceof TileEntityEnderFurnace){
		playerIn.openGui(Reference.MOD_ID,GuiIDs.Ender_FurnaceID,worldIn,pos.getX(),pos.getY(),pos.getZ());
	}
	return true;
}

@Override
	public int getRenderType() {
		// TODO Auto-generated method stub
		return 3 ;
	}
public IBlockState getStateFromMeta(int meta)
{
    EnumFacing enumfacing = EnumFacing.getFront(meta);

    if (enumfacing.getAxis() == EnumFacing.Axis.Y)
    {
        enumfacing = EnumFacing.NORTH;
    }

    return this.getDefaultState().withProperty(FACING, enumfacing);
}

/**
 * Convert the BlockState into the correct metadata value
 */
public int getMetaFromState(IBlockState state)
{
    return ((EnumFacing)state.getValue(FACING)).getIndex();
}

protected BlockState createBlockState()
{
    return new BlockState(this, new IProperty[] {FACING});
}

@SideOnly(Side.CLIENT)

static final class SwitchEnumFacing
    {
        static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];
        private static final String __OBFID = "CL_00002111";

        static
        {
            try
            {
                FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            try
            {
                FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }
public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
{

    this.setDefaultFacing(worldIn, pos, state);
}
@Override
public IBlockState onBlockPlaced(
      World worldIn, 
      BlockPos pos, 
      EnumFacing facing, 
      float hitX, 
      float hitY, 
      float hitZ, 
      int meta, 
      EntityLivingBase placer)
{
    return getDefaultState().withProperty(FACING, 
          placer.getHorizontalFacing().getOpposite());
}

private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
{
    if (!worldIn.isRemote)
    {
        Block block = worldIn.getBlockState(pos.north()).getBlock();
        Block block1 = worldIn.getBlockState(pos.south()).getBlock();
        Block block2 = worldIn.getBlockState(pos.west()).getBlock();
        Block block3 = worldIn.getBlockState(pos.east()).getBlock();
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
        {
            enumfacing = EnumFacing.SOUTH;
        }
        else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
        {
            enumfacing = EnumFacing.NORTH;
        }
        else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
        {
            enumfacing = EnumFacing.EAST;
        }
        else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
        {
            enumfacing = EnumFacing.WEST;
        }

        worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
    }
}


@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(EnderCraftBlocks.ender_furnace_off);
	}

	

@Override
	public void onBlockHarvested(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer player) {
		InventoryHelper helper = new InventoryHelper();
		TileEntity te = (TileEntityEnderFurnace) worldIn.getTileEntity(pos);
		helper.dropInventoryItems(worldIn, pos, (TileEntityEnderFurnace)te);
		super.onBlockHarvested(worldIn, pos, state, player);
	}
public static void updateState(boolean on,World world,TileEntityEnderFurnace te,BlockPos pos){
	TileEntityEnderFurnace enderfurnace = (TileEntityEnderFurnace) world.getTileEntity(pos);
	if(on){
	world.setBlockState(pos, EnderCraftBlocks.ender_furnace_on.getDefaultState());
	
	
	}else{
		world.setBlockState(pos, EnderCraftBlocks.ender_furnace_off.getDefaultState());

	}
	if(enderfurnace !=null ){
		enderfurnace.validate();
		world.setTileEntity(pos, enderfurnace);
		
	}
}

@SideOnly(Side.CLIENT)
public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
{
	
    if (this.active)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;

        switch (SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()])
        {
            case 1:
                worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case 2:
                worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case 3:
                worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                break;
            case 4:
                worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
}
}
