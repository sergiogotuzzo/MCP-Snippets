# Tab Overlay

- Choose if players' head must be rendered:
  1. Press CTRL + RSHIFT + T and search for "GuiPlayerTabOverlay.java"
  2. Press CTRL + F, search for "public void renderPlayerlist(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn)"
  3. Search for this line: boolean flag = this.mc.isIntegratedServerRunning() || this.mc.getNetHandler().getNetworkManager().getIsencrypted();
  4. Replace it with "boolean flag = !ModInstances.getTabOverlayMod().isEnabled() && (this.mc.isIntegratedServerRunning() || this.mc.getNetHandler().getNetworkManager().getIsencrypted()) || ModInstances.getTabOverlayMod().isEnabled() && ModInstances.getTabOverlayMod().isPlayersHeadToggled();"
- Choose if header must be rendered:

  1. Press CTRL + RSHIFT + T and search for "GuiPlayerTabOverlay.java"
  2. Press CTRL + F, search for "public void renderPlayerlist(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn)"
  3. Search for this line: if (this.header != null)
  4. Replace all the condition with this:

  ```java
      if (this.header != null)
          {
              if (!ModInstances.getTabOverlayMod().isEnabled() || ModInstances.getTabOverlayMod().isEnabled() && ModInstances.getTabOverlayMod().isHeaderToggled()) {
                  list1 = this.mc.fontRendererObj.listFormattedStringToWidth(this.header.getFormattedText(), width - 50);

                  for (String s : list1)
                  {
                      l1 = Math.max(l1, this.mc.fontRendererObj.getStringWidth(s));
                  }
              }
          }
  ```

- Choose if footer must be rendered:

  1. Press CTRL + RSHIFT + T and search for "GuiPlayerTabOverlay.java"
  2. Press CTRL + F, search for "public void renderPlayerlist(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn)"
  3. Search for this line: if (this.footer != null)
  4. Replace all the condition with this:

  ```java
    if (this.footer != null)
        {
        	if (!ModInstances.getTabOverlayMod().isEnabled() || ModInstances.getTabOverlayMod().isEnabled() && ModInstances.getTabOverlayMod().isFooterToggled()) {
        		list2 = this.mc.fontRendererObj.listFormattedStringToWidth(this.footer.getFormattedText(), width - 50);

                for (String s2 : list2)
                {
                    l1 = Math.max(l1, this.mc.fontRendererObj.getStringWidth(s2));
                }
            }
        }
  ```
