# (Fixed) Freelook

- Fixes mouse sentivity (ModFreelook.java on line 62)
- Fixes camera pitch (ModFreelook.java on line 68)
- Fixes nametags rotation in third person:
  1. Press CTRL + RSHIFT + T and search for "RenderManager.java"
  2. Press CTRL + F, search for "livingPlayerIn.prevRotationYaw" and replace it with "ModInstances.getFreelookMod().getCameraYaw()", same for "livingPlayerIn.rotationYaw"
  3. Press CTRL + F, search for "livingPlayerIn.prevRotationPitch" and replace it with "ModInstances.getFreelookMod().getCameraPitch()", same for "livingPlayerIn.rotationPitch"
