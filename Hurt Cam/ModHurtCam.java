package client.mods.impl;

import client.mods.Mod;

public class ModHurtCam extends Mod {
	private boolean hurtShake = true;
	private float hurtShakeIntensity = 14.0F;
	
	public boolean isHurtShakeToggled() {
		return hurtShake;
	}
	
	public float getHurtShakeIntensity() {
		return hurtShakeIntensity;
	}
}
