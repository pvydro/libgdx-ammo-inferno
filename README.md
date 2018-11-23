# Ammo Inferno (2017)
Cross platform casual shooter built in Java with LibGDX. Released on Google Play

Gameplay video by SuperGameDroid: https://www.youtube.com/watch?v=6TB77pe376c

### Download (Android):
[Google Play](https://play.google.com/store/apps/details?id=com.flizzet.main&hl=en_US)

## Description from Play Store
Fight it out in this free, action packed, pixel stuffed, fun filled jump and shoot arcade game.

Fun - 
With easy controls ranging from joystick to tilt, and endless levels ranging from industrial caves to castle caves, test your skills against zombies and a variety of beasts. Quickly hack and hop through hand drawn caves shooting your way through traps and running past monsters.

Fast - 
Quick paced, reflex-testing gameplay made to push any players skills ensures you'll never leave a game unfinished. Kill enemies as you scale up to survive the deep depths and collect all the loot you can.

Unlockables -
From rapid firing guns to auto-zombie-killing-robots, unlock hundreds of upgrade combinations, 20 different characters, and a wide range of weapons. A fully customized loadout will ensure your success in the war you've so inconveniently found yourself in.

Lightweight -
Save your storage. At under 8MB, you'll never find yourself deleting this to save space. Regardless of the hundreds of graphics assets and thousands of lines of code, Ammo Inferno stays lightweight and accessible.

Offline - 
With no connection needed, you can play Ammo Inferno any time you need. Dash through killer foes and dive through deadly obstacles. Test your survival skills and escape the high caves. All this in any location, no wifi needed. It's even been tested at the top of Mount Everest.

Jump in and test your skills against the deathly beasts of the cave.
When you're done and want to tell your story - or managed to break the game, contact us at:
pedro@flizzet.com

## Setup/Requirements
- Android SDK Version: 20
- Android Build Tools Version: 25.0.2
- Gradle version: 1.5.0

### Run configuration
Run configurations must set the working directory to android/assets

## Directory Lister
### Why
Ammo Inferno reads a dirs.txt file, containing all asset file names, for a load on mobile that doesn't require searching through directories - an extremely expensive operation.
### What
Directory lister turns dirs.txt into a array style list, as a work around in the case of file not found exceptions
