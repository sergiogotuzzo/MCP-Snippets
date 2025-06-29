# Particles

- Always affect sharpness:

  1. Press CTRL + RSHIFT + T and search for "EntityPlayer.java"
  2. Press CTRL + F, search for "public void attackTargetEntityWithCurrentItem(Entity targetEntity)"
  3. Search for this line: if (flag2)
  4. Write this at the start of the condition (before if (i > 0)):

  ```java
    if (ModInstances.getParticlesMod().isEnabled() && ModInstances.getParticlesMod().isAlwaysSharpnessToggled()) {
        this.onEnchantmentCritical(targetEntity);
    }
  ```

- Choose the multiplier factor:
  1. Press CTRL + RSHIFT + T and search for "EntityParticleEmitter.java"
  2. Press CTRL + F, search for "public void onUpdate()"
  3. Search for this line: for (int i = 0; i < 16; ++i)
  4. Replace "16" with "16 \* (ModInstances.getParticlesMod().isEnabled() ? ModInstances.getParticlesMod().getMultiplierFactor() : 1)"
