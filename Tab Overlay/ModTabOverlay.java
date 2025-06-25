package client.mods.impl;

import client.mods.Mod;

public class ModTabOverlay extends Mod {
	private boolean playersHead = true;
	private boolean header = true;
	private boolean footer = true;
	
	public boolean isPlayersHeadToggled() {
		return playersHead;
	}
	
	public boolean isHeaderToggled() {
		return header;
	}
	
	public boolean isFooterToggled() {
		return footer;
	}
}
