package com.crafter6464.endercraft.machines.abstractmachines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class ContainerEC extends Container {
public ContainerEC(InventoryPlayer invPlayer, TileEntity te){
	
	//Inventory
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 9; j++) {
					this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
				}
			}
			
			//ActionBar
			for(int i = 0; i < 9; i++) {
				this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
			}
	
}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

	public abstract ItemStack transferStackInSlot(EntityPlayer playerIn, int index);
}
