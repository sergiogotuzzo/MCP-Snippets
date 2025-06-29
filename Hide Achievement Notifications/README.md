# Hide Achievement Notifications

- Hide the achievement notifications:

  1. Press CTRL + RSHIFT + T and search for "NetHandlerPlayClient.java"
  2. Press CTRL + F, search for "this.gameController.guiAchievement.displayAchievement(achievement);" and delete that line
