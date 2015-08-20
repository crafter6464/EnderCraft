package com.crafter6464.endercraft.machines.ender_furnace;

import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

import com.crafter6464.endercraft.machines.abstractmachines.GuiContainerEC;

public class GuiEnderFurnace extends GuiContainerEC {
TileEntityEnderFurnace te;
	public GuiEnderFurnace(String resourcepath, Container container,
			String guiName,TileEntityEnderFurnace te) {
		super(resourcepath,container, guiName,te);
		this.te=te;

		
		// TODO Auto-generated constructor stub
	}
@Override
protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
	super.drawGuiContainerBackgroundLayer(f, i, j);
	
	int k = te.getCookTimeRemainingScaled(24);
	drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 14, k + 1, 17);

	if(this.te.burnTime>0) {
		  // int i1 = this.te.getBurnTimeRemainingScaled(16);
		int k1 = this.te.getBurnTimeRemainingScaled(16)+3;
        drawTexturedModalRect(guiLeft + 56, guiTop + 52-k1, 176, 16-k1, 16, k1-2);
		   }
		   }
@Override
protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	
	
	
}
}
