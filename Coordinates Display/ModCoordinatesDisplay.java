package client.mods.impl;

import java.awt.Color;

import client.mods.ModDraggable;
import client.mods.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class ModCoordinatesDisplay extends ModDraggable {
	@Override
	public int getWidth() {
		int width = 12 + 10 + 6;

		int biomeWidth = font.getStringWidth(getBiomeText());
		int coordsWidth = font.getStringWidth(getLongestCoordinateText());

		if (biomeWidth > coordsWidth) {
			width += biomeWidth;
		} else {
			width += coordsWidth;
		}

		return width;
	}


	@Override
	public int getHeight() {
		return 12 + 10 * 4;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 102).getRGB());

		int i = 11;

		font.drawString("X: " + (int) mc.getRenderViewEntity().posX, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 1 - 6 + 1, Color.WHITE.getRGB());
		font.drawString("Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 2 - 6 + 1, Color.WHITE.getRGB());
		font.drawString("Z: " + (int) mc.getRenderViewEntity().posZ, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 3 - 6 + 1, Color.WHITE.getRGB());
		font.drawString(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - 6 + 1, pos.getAbsoluteY() + i * 1 - 6 + 1, Color.WHITE.getRGB());
		font.drawString(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - 6 + 1, pos.getAbsoluteY() + i * 2 - 6 + 1, Color.WHITE.getRGB());
		font.drawString(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - 6 + 1, pos.getAbsoluteY() + i * 3 - 6 + 1, Color.WHITE.getRGB());
		font.drawString(getBiomeText(), pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 4 - 6 + 1, Color.WHITE.getRGB());
	}
	
	private String getLongestCoordinateText() {
		String longestText = "";
		
		String textX = "X: " + (int) mc.getRenderViewEntity().posX;
		String textY = "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		String textZ = "Z: " + (int) mc.getRenderViewEntity().posZ;
		
		if (font.getStringWidth(textX) > font.getStringWidth(longestText)) {
			longestText = textX;
		}
		
		if (font.getStringWidth(textY) > font.getStringWidth(longestText)) {
			longestText = textY;
		}
		
		if (font.getStringWidth(textZ) > font.getStringWidth(longestText)) {
			longestText = textZ;
		}
		
		return longestText;
	}
	
	private String getBiomeText() {
		BlockPos playerPos = new BlockPos(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ);
		Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(playerPos);
		
		return "Biome: " + chunk.getBiome(playerPos, mc.theWorld.getWorldChunkManager()).biomeName;
	}
	
	private int getDirectionFacing() {
        int yaw = (int) mc.getRenderViewEntity().rotationYaw;
        
        yaw += 360;
        yaw += 22;
        yaw %= 360;
        
        return yaw / 45;
    }
	
	private String getFacingTowardsX() {
        switch (getDirectionFacing()) {
	        case 1:
	            return "-";
	        case 2:
	            return "-";
	        case 3:
	        	return "-";
	        case 5:
	        	return "+";
	        case 6:
	        	return "+";
	        case 7:
	        	return "+";
	        default:
	        	return "";
        }
	}

	private String getFacing() {
        switch (getDirectionFacing()) {
            case 0:
                return "S";
            case 1:
                return "SW";
            case 2:
                return "W";
            case 3:
            	return "NW";
            case 4:
            	return "N";
            case 5:
            	return "NE";
            case 6:
            	return "E";
            case 7:
            	return "SE";
            default:
            	return "";
        }
	}
	
	private String getFacingTowardsZ() {
		switch (getDirectionFacing()) {
	        case 0:
	            return "+";
	        case 1:
	            return "+";
	        case 3:
	        	return "-";
	        case 4:
	        	return "-";
	        case 5:
	        	return "-";
	        case 7:
	        	return "+";
	        default:
	        	return "";
	    }
	}
}