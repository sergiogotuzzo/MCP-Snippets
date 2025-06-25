package client.mods.impl;

import client.mods.Mod;

public class ModParticles extends Mod {
	private boolean alwaysSharpness = true;
	private int multiplierFactor = 2;
	
	public boolean isAlwaysSharpnessToggled() {
		return alwaysSharpness;
	}
	
	public int getMultiplierFactor() {
		return multiplierFactor;
	}
}