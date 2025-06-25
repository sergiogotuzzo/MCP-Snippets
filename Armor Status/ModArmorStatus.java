package client.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

import client.mods.ModDraggable;
import client.mods.hud.ScreenPosition;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModArmorStatus extends ModDraggable {
	@Override
	public int getWidth() {
		return 16 + 2 + font.getStringWidth("1561");
	}

	@Override
	public int getHeight() {
		return 16 * 5;
	}

	@Override
	public void render(ScreenPosition pos) {
		int i = 0;
		
		for (ItemStack itemStack : getPlayerInventory()) {
			drawItemStack(pos, i, itemStack);
			
			i++;
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		Collection<ItemStack> dummyPlayerInventory = new ArrayList<ItemStack>();

		dummyPlayerInventory.add(new ItemStack(Items.diamond_sword));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_boots));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_leggings));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_chestplate));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_helmet));
		
		int i = 0;
		
		for (ItemStack itemStack : dummyPlayerInventory) {
			drawItemStack(pos, i, itemStack);
			
			i++;
		}
	}

	private void drawItemStack(ScreenPosition pos, int i, ItemStack itemStack) {
		if (itemStack != null) {
			GL11.glPushMatrix();

			RenderHelper.enableGUIStandardItemLighting();

			int offsetY = (-16 * i) + getHeight() - 16;
			
			mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY);
			
			if (itemStack.isStackable()) {
				mc.getRenderItem().renderItemOverlays(font, itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY);
			}
			
			if (itemStack.getItem().isDamageable()) {
				mc.getRenderItem().renderItemOverlays(font, itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY);
				
				font.drawStringWithShadow(String.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage()), pos.getAbsoluteX() + 16 + 2, pos.getAbsoluteY() + offsetY + 5, Color.WHITE.getRGB());
			}
			
			GL11.glPopMatrix();
		}
	}
	
	private Collection<ItemStack> getPlayerInventory() {
		Collection<ItemStack> playerInventory = new ArrayList<ItemStack>();
		
		if (mc.thePlayer.inventory.getCurrentItem() != null) {
			playerInventory.add(mc.thePlayer.inventory.getCurrentItem());
		}
		
		for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
			if (itemStack != null) {
				playerInventory.add(itemStack);
			}
		}
		
		return playerInventory;
	}
}