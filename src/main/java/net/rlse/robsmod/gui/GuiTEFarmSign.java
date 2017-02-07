package net.rlse.robsmod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.rlse.robsmod.guicontainer.ContainerTEFarmSign;
import net.rlse.robsmod.tileentity.TEFarmSign;

public class GuiTEFarmSign extends GuiContainer {

	private IInventory playerInv;
	private TEFarmSign te;
	private ResourceLocation backgroundTextureResource;
	
	public GuiTEFarmSign(IInventory playerInv, TEFarmSign te) {
		super(new ContainerTEFarmSign(playerInv, te));
		
		this.playerInv = playerInv;
		this.te = te;
		xSize = 178;
		ySize = 166;
		
		backgroundTextureResource = new ResourceLocation("robsmod:textures/gui/container/farm_sign.png");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(backgroundTextureResource);
		drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, xSize, ySize);
	}

}
