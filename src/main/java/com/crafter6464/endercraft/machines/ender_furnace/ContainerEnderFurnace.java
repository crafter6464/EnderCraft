package com.crafter6464.endercraft.machines.ender_furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.crafter6464.endercraft.machines.abstractmachines.ContainerEC;
import com.crafter6464.endercraft.utility.Log;

public class ContainerEnderFurnace extends ContainerEC {
TileEntityEnderFurnace te;
private int lastCookTime;
private int lastBurnTime;
private int lastCurrentItemBurnTime;
	public ContainerEnderFurnace(InventoryPlayer invPlayer, TileEntity te) {
		super(invPlayer, new TileEntityEnderFurnace());
//0-35 is used
		this.te=(TileEntityEnderFurnace) te;
		this.addSlotToContainer(new Slot((IInventory) te, 0, 56, 17));
		this.addSlotToContainer(new SlotFurnaceFuel((IInventory) te, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnaceOutput(invPlayer.player,(IInventory) te, 2, 116, 35));
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		Slot slot = (Slot) this.inventorySlots.get(index);
		Log.info("Slot:" + index);
		ItemStack itemstack=null;
		if(slot!=null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index < 36 && FurnaceRecipes.instance().getSmeltingResult(itemstack1)!=null){
					if(!mergeItemStack(itemstack1,36,37,false)){
						return null;
					}
				}
			else if(index<36 && 	this.te.isItemFuel(itemstack1)){
					if(!mergeItemStack(itemstack1,37,38,false)){
						return null;
					}
				}
				
			else if(index >35){
				if(!mergeItemStack(itemstack1, 0, 33, false)){
					return null;
				}
				
			}
			else if(index > 26 && index<36){
			
				if(!this.mergeItemStack(itemstack1, 0, 25, false)){
					return null;
				}
			}
			else if(index <27){
				if(!mergeItemStack(itemstack1, 27, 35, false)){
					return null;
				}
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(playerIn, itemstack1);
		}
		
		return null;
	}
	
	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.te.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.te.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.te.currentItemBurnTime);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.lastCookTime != this.te.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.te.cookTime);
			}
			
			if(this.lastBurnTime != this.te.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.te.burnTime);
			}
			
			if(this.lastCurrentItemBurnTime != this.te.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.te.currentItemBurnTime);
			}
		}
		this.lastCookTime=this.te.cookTime;
		this.lastBurnTime = this.te.burnTime;
		this.lastCurrentItemBurnTime = this.te.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0) {
            this.te.cookTime = par2;
        }

        if (par1 == 1) {
            this.te.burnTime = par2;
        }

        if (par1 == 2) {
            this.te.currentItemBurnTime = par2;
        }
    }
}
