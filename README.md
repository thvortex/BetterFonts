## Minecraft TrueType/OpenType Font Support Mod ##

The BetterFonts mod adds TrueType/OpenType font support for Minecraft. This mod will use the fonts installed on your system for drawing text instead of the builtin bitmap fonts that come with Minecraft. Languages such as Arabic and Hindi look much better with this mod since both require complex layout that the bitmap fonts simply can't provide. All in-game text will change to use the new fonts including GUIs, the F3 debug screen, chat, and even signs. This mod should have little or no impact on performance.

**Unicode Text in Chat:**
Starting with 1.3.2, both the regular Minecraft server and CraftBukkit allow using full Unicode text in chat. Unfortunately, the Minecraft client comes with an older version of the LWJGL library which doesn't handle keyboard layouts properly. You will have to [manually update LWJGL](http://www.minecraftwiki.net/wiki/LWJGL) to the latest version, if you need Unicode support in chat.

### Forum & Downloads: ###
For more information, discussions, and downloadable versions of the mod, see the [BetterFonts Thread](http://www.minecraftforum.net/topic/1142084-125-betterfonts-opentype-font-support/) on the Minecraft Forums.

### Screenshots: ###
Click on any of the screenshots below to see a full-sized version of the image.

<table>
<tr>
<td>
<p align="center">
<img src="http://lh5.googleusercontent.com/-BSVTO520XtM/T30lcUsatWI/AAAAAAAAAzg/ruUVL9SL2_M/s853/title.png" alt="English Title Screen" width="400px" height="225px">
English Title Screen
</p>
</td>

<td>
<p align="center">
<img src="http://lh6.googleusercontent.com/-o1UQZEMqGoE/T33uk5tV4SI/AAAAAAAAA0c/sgPPCUxefzM/s851/options.png" alt="Arabic Options" width="400px" height="225px">
Options Screen in Arabic
</p>
</td>
</tr>

<tr>
<td>
<p align="center">
<img src="http://lh3.googleusercontent.com/-tKPfGw-A-bE/T33uk3ouJ0I/AAAAAAAAA0Y/xs25h0QEgdc/s851/lang.png" alt="Language Selection" width="400px" height="225px">
Language Selection Screen
</p>
</td>

<td>
<p align="center">
<img src="http://lh3.googleusercontent.com/-DeTb7J-ipGc/T30leXP8mxI/AAAAAAAAAz4/7MmCAuEUrCI/s852/debug.png" alt="F3 Debug Screen" width="400px" height="225px">
F3 Debug Screen
</p>
</td>
</tr>
</table>

### Installation: ###
Installation is the same as for any other mod. Extract the contents of the downloaded .zip file into your minecraft.jar, and make sure to delete the META-INF folder inside minecraft.jar. This mod is compatible with OptiFine and MCPatcher, but make sure to install it after either mod.

### Configuration: ###
You can have a config file to change the default font and text size this mod uses. If your game is installed in a `.minecraft` directory, then the config file will be `.minecraft/config/BetterFonts.cfg` (the same place ModLoader keeps its config files). Please note that the `config` directory does **not go inside minecraft.jar** but directly into the game folder. You can either [download](http://dev.bukkit.org/media/files/587/923/BetterFonts_Config.zip) the config file or look at the example below:
```ini
# This is the BetterFonts configuration file. Any lines that start with # are
# comments and get ignored. If the same property name appears multiple times
# in the file, only the last value will be used.

# The font.name property changes the default font used in Minecraft. This can
# be the name of a real font like Times New Roman, or one of Java's logical
# fonts like Serif, SansSerif, or Monospaced. If font.name is missing, the
# default font used will be SansSerif.
font.name=SansSerif
#font.name=Monospaced
#font.name=Times New Roman

# You can use the font.size property to affect how large or small the text
# will appear. This must be an integer greater than zero, and if omitted
# the default size used is 18.
font.size=18

# This property enables anti-aliasing which blurs the edges of the font to
# make it seem less jagged, but this can also make the font seem less sharp.
# The property should be set to either the word "true" or "false, and you'll
# have to experiment with both options to determine which one looks better.
# If font.antialias is missing, the default is to disable anti-aliasing.
font.antialias=false
#font.antialias=true

# By default, Minecraft draws drop shadow for any text in the GUIs and the
# chat. Setting this to "false" will disable this shadow which can make some
# fonts more readable.
font.dropshadow=true
#font.dropshadow=false
```

### License: ###
This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but **WITHOUT ANY WARRANTY**; without even the implied warranty of MERCHANTABILITY or **FITNESS FOR A PARTICULAR PURPOSE**. See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along with this program. If not, see <<http://www.gnu.org/licenses/>>.
