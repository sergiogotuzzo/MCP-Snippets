package client.mods.impl;

import java.awt.Color;

import client.mods.ModDraggable;
import client.mods.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.ResourcePackRepository;

public class ModPackDisplay extends ModDraggable {
	@Override
	public int getWidth() {
		if (getSelectedPack() != null) {
			String longestName = getSelectedPack().getResourcePackName();
			String longestDescription = getSelectedPack().getTexturePackDescription();
			
			String longestText = longestName;
			
			if (font.getStringWidth(longestDescription) > font.getStringWidth(longestText)) {
				longestText = longestDescription;
			}
			
			return 8 + 28 + font.getStringWidth(longestText);
		}
		
		return 0;
	}

	@Override
	public int getHeight() {
		return getSelectedPack() != null ? 28 : 0;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (getSelectedPack() != null) {
			Gui.drawRect(pos.getAbsoluteX() + 28, pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + 28, new Color(0, 0, 0, 102).getRGB());
			
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			getSelectedPack().bindTexturePackIcon(mc.getTextureManager());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY(), 0.0F, 0.0F, 28, 28, 28, 28);
			
			font.drawString(getSelectedPack().getResourcePackName(), pos.getAbsoluteX() + 4 + 28, pos.getAbsoluteY() + 4, Color.WHITE.getRGB());
			font.drawString(getSelectedPack().getTexturePackDescription(), pos.getAbsoluteX() + 4 + 28, pos.getAbsoluteY() + 28 - font.FONT_HEIGHT - 4, Color.GRAY.getRGB());		
		}
	}
	
	private ResourcePackRepository.Entry getSelectedPack() {
		return mc.getResourcePackRepository().getRepositoryEntries().isEmpty() ? null : mc.getResourcePackRepository().getRepositoryEntries().get(mc.getResourcePackRepository().getRepositoryEntries().size() - 1);
	}
}