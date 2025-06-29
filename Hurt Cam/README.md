# Hurt Cam

- Disable hurt shake:
  1. Press CTRL + RSHIFT + T and search for "EntityRenderer.java"
  2. Press CTRL + F, search for "this.hurtCameraEffect(partialTicks);", go to that line and replace it with:
     ```java
     if (ModInstances.getHurtCamMod().isEnabled() && ModInstances.getHurtCamMod().isHurtShakeToggled() || !ModInstances.getHurtCamMod().isEnabled()) {
     	this.hurtCameraEffect(partialTicks);
     }
     ```
- Choose the hurt shake intensity:
  1. Press CTRL + RSHIFT + T and search for "EntityRenderer.java"
  2. Press CTRL + F, search for "private void hurtCameraEffect(float partialTicks)"
  3. Search for this line: GlStateManager.rotate(-f \* 14.0F, 0.0F, 0.0F, 1.0F);
  4. Replace "14.0F" with "(ModInstances.getHurtCamMod().isEnabled() ? ModInstances.getHurtCamMod().isHurtShakeToggled() ? ModInstances.getHurtCamMod().getHurtShakeIntensity() : 0.0F : 14.0F)"
