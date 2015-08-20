package com.crafter6464.endercraft.machines.abstractmachines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;

public abstract class ECTileEntity extends TileEntityLockable  {
	protected ItemStack[] slots;
	String name;
	public ECTileEntity(int slotCount,String name){
		this.slots = new ItemStack[slotCount];
		this.name=name;
	}
	
	public ItemStack decrStackSize(int index, int count)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack;

            if (this.slots[index].stackSize <= count)
            {
                itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[index].splitStack(count);

                if (this.slots[index].stackSize == 0)
                {
                    this.slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return this.slots[index];
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
this.slots[index] = stack;		
	}
	@Override
	public void clear() {
for(int i = 0; i<this.slots.length;++i){
	this.slots[i]=null;
}		
	}
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.slots.length;
	}
	@Override
	public String getGuiID() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
