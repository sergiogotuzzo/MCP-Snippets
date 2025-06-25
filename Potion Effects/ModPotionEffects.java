package client.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import client.mods.ModDraggable;
import client.mods.hud.ScreenPosition;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ModPotionEffects extends ModDraggable {	
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));

	@Override
    public int getWidth() {
        return 20 + 2 + font.getStringWidth("Strenght IV");
    }

    @Override
    public int getHeight() {    	
        return dummyPotionEffects.size() * 20;
    }

    @Override
    public void render(ScreenPosition pos) {
        int offsetY = 0;

        for (int i = 0; i < mc.thePlayer.getActivePotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) mc.thePlayer.getActivePotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += 20;
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        int offsetY = 0;

        for (int i = 0; i < dummyPotionEffects.size(); i++) {
            PotionEffect potionEffect = (PotionEffect) dummyPotionEffects.toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += 20;
        }
    }

    private void drawPotionEffect(ScreenPosition pos, int offsetY, PotionEffect potionEffect) {
        if (potionEffect != null) {
        	Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            
            if (potion.hasStatusIcon()) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                
                int iconIndex = potion.getStatusIconIndex();
                  
                drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY + 2, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
            }
            
            font.drawStringWithShadow(getPotionName(potionEffect), pos.getAbsoluteX() + 20 + 2, pos.getAbsoluteY() + offsetY + 2, Color.WHITE.getRGB());
            font.drawStringWithShadow(Potion.getDurationString(potionEffect), pos.getAbsoluteX() + 20 + 2, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + 2, Color.WHITE.getRGB());
        }
    }
    
    private String getPotionName(PotionEffect potionEffect) {
    	String potionName = I18n.format(potionEffect.getEffectName(), new Object[0]);
    	
    	if (potionEffect.getAmplifier() == 1) {
    		potionName = potionName + " " + I18n.format("enchantment.level.2", new Object[0]);
        } else if (potionEffect.getAmplifier() == 2) {
        	potionName = potionName + " " + I18n.format("enchantment.level.3", new Object[0]);
        } else if (potionEffect.getAmplifier() == 3) {
        	potionName = potionName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
    	
    	return potionName;
    }
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
        
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		
		worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();
		worldRenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
		worldRenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		worldRenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		
		tessellator.draw();
	}
}