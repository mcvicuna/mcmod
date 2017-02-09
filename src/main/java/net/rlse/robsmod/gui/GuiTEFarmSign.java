package net.rlse.robsmod.gui;

import java.io.IOException;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.rlse.robsmod.guicontainer.ContainerTEFarmSign;
import net.rlse.robsmod.guicontainer.ItemMarkerSlot;
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

    /**
     * Copied from vanilla GuiContainer because it is private
     */
    private boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
        return isPointInRegion(slotIn.xPos, slotIn.yPos, 16, 16, mouseX, mouseY);
    }

    /**
     * Copied from vanilla GuiContainer because it is private
     */
    private Slot getSlotAtPosition(int x, int y) {
        for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i);

            if (this.isMouseOverSlot(slot, x, y) && slot.canBeHovered()) {
                return slot;
            }
        }

        return null;
    }
    
	@Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    		throws IOException {
		
        Slot slot = this.getSlotAtPosition(mouseX, mouseY);
        
        if (slot instanceof ItemMarkerSlot) {
        	// Set the slot's item to the held stack, or clear it
        	ItemStack heldStack = this.mc.player.inventory.getItemStack();
        	if (heldStack.isEmpty()) {
        		slot.putStack(ItemStack.EMPTY);
        	} else {
        		ItemStack markerStack = new ItemStack(heldStack.getItem(), 1);
        		slot.putStack(markerStack);
        	}
        } else {
        	// Do the vanilla thing
        	super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
	
	@Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        Slot slot = this.getSlotAtPosition(mouseX, mouseY);
        if (slot instanceof ItemMarkerSlot) {
        	return;
        } else {
        	super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        }
	}

	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        Slot slot = this.getSlotAtPosition(mouseX, mouseY);
        if (slot instanceof ItemMarkerSlot) {
        	return;
        } else {
        	super.mouseReleased(mouseX, mouseY, state);
        }
    }
}
