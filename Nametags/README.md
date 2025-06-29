# Nametags

- Shows your own nametag in third person:

  1. Press CTRL + RSHIFT + T and search for "RenderPlayer.java"
  2. Press CTRL + F and search for "public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks)"
  3. Search for this line: super.doRender(entity, x, d0, z, entityYaw, partialTicks);
  4. Write under that this code:

     ```java
     if (entity instanceof EntityPlayerSP && ModInstances.getNametagsMod().isEnabled()) {
         	if (entity.isSneaking()) {
         		d0 = y - 0.125D;
         	}

             this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, d0, z, 64);
          }
     ```

- Fixes nametag rotation in frontal third person:

  1. Press CTRL + RSHIFT + T and search for "Render.java"
  2. Press CTRL + F and search for "protected void renderLivingLabel(T entityIn, String str, double x, double y, double z, int maxDistance)"
  3. Search for this line: GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
  4. Replace "this.renderManager.playerViewX" with "this.renderManager.playerViewX \* (this.renderManager.options.thirdPersonView == 2 ? -1 : 1)"
