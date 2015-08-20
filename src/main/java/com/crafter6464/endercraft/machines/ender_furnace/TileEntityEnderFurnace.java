package com.crafter6464.endercraft.machines.ender_furnace;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;

import com.crafter6464.endercraft.handler.ConfigurationHandler;
import com.crafter6464.endercraft.machines.abstractmachines.ECTileEntity;
import com.crafter6464.endercraft.utility.Log;

public class TileEntityEnderFurnace extends ECTileEntity implements IUpdatePlayerListBox,ISidedInventory {
	public static boolean isPowered;
	public int burnTime;
	public int furnaceSpeed=ConfigurationHandler.enderFurnaceSpeed;
	public int currentItemBurnTime;
	public int cookTime;
	
	private int[] slotTop = new int[]{0};
	private int[] slotSide = new int[]{0};
	private int[] slotBottom = new int[]{0};

	public TileEntityEnderFurnace() {
		super(3,"EnderFurnace");
		
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return new ContainerEnderFurnace(playerInventory, this);
	}



	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		Log.info(side);
		return (side == side.UP)?slotTop:(side == side.DOWN)?slotBottom:slotSide;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn,
			EnumFacing direction) {
		/*
		if(index == 2)return false;
		if(index == 1 && this.isItemFuel(itemStackIn) && slots[index].stackSize + itemStackIn.stackSize<=64)return true;
		if(index == 0){
			
			if(FurnaceRecipes.instance().getSmeltingResult(itemStackIn)!=null){
				if(itemStackIn.stackSize + this.slots[index].stackSize <=64){
					return true;
				}
				else if(this.slots[0]==null)return true;
			}
		}
		*/
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack,
			EnumFacing direction) {
		return true;
	}
	public void doUpdate(){
		Ender_Furnace.updateState(this.isActive(), worldObj, this, pos);

	}
public void updateState(){

doUpdate();
	
	
}
public boolean checkPowered(){
	if(worldObj.isBlockPowered(pos)){
		return true;
	}
	return false;
}

	@Override
	public void update() {
		
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		if(this.slots[0]==null){
			doUpdate();
		}
		if(checkPowered()){
			this.cookTime=0;
			this.burnTime=0;
			
			 doUpdate();
		}
	if (this.isActive()) {
 this.burnTime--;
		}
		//doUpdate();
		
			
		
//	Log.info(burnTime);
		//check if burn time should be increased
		if(this.slots[0]==null){
			this.cookTime=0;
		}
		
if(canSmelt()&&!checkPowered()){
	if(this.burnTime<=0){
		this.cookTime=0;
		if(this.slots[1]!=null){
		
		if(this.slots[1].getItem() != Items.lava_bucket){
			this.slots[1].stackSize--;
		this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]); 
		flag1=true;
		if(this.slots[1].stackSize<=0)this.slots[1]=null;
		}
		else{
			this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]); 
			this.slots[1] =null;
			this.slots[1] =new ItemStack(Items.bucket,1);
			

		}
	}
}
	
	
	if(canSmelt()&&!checkPowered()){
		if(this.burnTime>=1){
			flag1=true;
		this.cookTime++;
	}
		else{
			this.cookTime=0;
		}
		
		if(this.cookTime == this.furnaceSpeed){
			flag1 = true;
			this.cookTime=0;
			if(canSmelt()){
		
					
				this.smeltItem();
			}
		}
		

	}		

			if (flag != this.isActive()) {
				flag1 = true;
				doUpdate();
				}
		
		if(flag1){
			this.markDirty();
	
	}
	}
	}

private int getItemBurnTime(ItemStack itemStack) {
		if(itemStack.getItem() instanceof ItemBlock){
			Item item = itemStack.getItem();
			if(Block.getBlockFromItem(item) != Blocks.air){
				Block block = Block.getBlockFromItem(item);
				
				if(block == Blocks.coal_block) return 16000;
				if(block == Blocks.sapling) return 100;
				if(block == Blocks.planks) return 400;
				if(block == Blocks.log || block == Blocks.log2) return 1600;
			}
		}else if(itemStack.getItem() instanceof Item){
			Item item = itemStack.getItem();
			if(item == Items.coal)return 8000;
			if(item == Items.lava_bucket)return 100000;
			if(item == Items.ender_pearl) return 150000;
			
			
		}
		return 0;
	}

public boolean canSmelt(){
	if(this.slots[0]==null)return false;
	if(FurnaceRecipes.instance().getSmeltingResult(this.slots[0])!=null){
	ItemStack resultStack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
	if(this.slots[2]==null)return true;
	if(this.slots[2].stackSize + resultStack.stackSize <=64 && this.slots[2].getItem().equals(resultStack.getItem()))return true;
}
	return false;
}
public boolean isActive(){
	return this.burnTime>0;
}

public void smeltItem(){
	ItemStack resultStack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
	if(this.slots[2]==null){
		this.slots[2] = resultStack.copy();
		this.slots[0].stackSize--;
		if(this.slots[0].stackSize<=0){
			this.slots[0]=null;
		}
	}
	else
	{
		this.slots[2].stackSize += resultStack.stackSize;
		this.slots[0].stackSize--;
		if(this.slots[0].stackSize<=0){
			this.slots[0]=null;
		}
	}
}
public int getBurnTimeRemainingScaled(int i) {
	return this.burnTime * i / this.currentItemBurnTime;
}
public int getCookTimeRemainingScaled(int modifier){
	return this.cookTime* modifier/this.furnaceSpeed ;
}
public boolean isItemFuel(ItemStack stack){
	return this.getItemBurnTime(stack) > 0;
}
@Override
public void writeToNBT(NBTTagCompound compound) {
	super.writeToNBT(compound);
	
	
	compound.setShort("cookTime", (short) this.cookTime);
	compound.setShort("burnTime", (short)this.burnTime);
	compound.setShort("currentItemBurnTime", (short) this.currentItemBurnTime);
	
	NBTTagList list = new NBTTagList();

	for(int i = 0;i<this.slots.length; ++i){
		NBTTagCompound compound1 = new NBTTagCompound();
		compound1.setByte("Slot", (byte) i);
		if(this.slots[i]!=null){
		this.slots[i].writeToNBT(compound1);
		
		list.appendTag(compound1);
		Log.info("Saved Slot: "+i);
		
	}
	}
	compound.setTag("Items", list);
	
}
@Override
public void readFromNBT(NBTTagCompound compound) {
	super.readFromNBT(compound);
	
	this.cookTime = compound.getShort("cookTime");
	this.burnTime = compound.getShort("burnTime");
	this.currentItemBurnTime = compound.getShort("currentItemBurnTime");
	
	this.slots = new ItemStack[getSizeInventory()];
	
	NBTTagList list = compound.getTagList("Items", 10);
	
	for(int i = 0; i < list.tagCount();++i){
	NBTTagCompound compound1 = (NBTTagCompound)list.getCompoundTagAt(i);
	byte b = compound1.getByte("Slot");
	if (b >= 0 && b < this.slots.length) {
		this.slots[b] = ItemStack.loadItemStackFromNBT(compound1);
	}
	}
			

}
}
