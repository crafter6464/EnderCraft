package com.crafter6464.endercraft.machines.abstractmachines;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.crafter6464.endercraft.machines.ender_furnace.TileEntityEnderFurnace;
import com.crafter6464.endercraft.reference.Reference;



public abstract class GuiContainerEC extends GuiContainer {
	protected ResourceLocation guiTexture;
	protected String guiName;
	/**
	 * 
	 * @param resourcepath all you need is the png files name without the '.png'
	 * @param container container we are drawing for
	 */
	public GuiContainerEC(String resourcepath,Container container,String guiName,TileEntityEnderFurnace enderFurnace){
		super(container);
		this.guiTexture = new ResourceLocation(Reference.MOD_ID + ":" + "/textures/gui/"+resourcepath+".png");
		this.guiName=guiName;
	}
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = guiName;	
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
	}
	@Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
		
		
		
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
