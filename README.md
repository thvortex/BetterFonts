> ## Minecraft OpenType Font Support Mod ##
>
> #### Copyright (C) 2012 Wojciech Stryjewski <`thvortex`@`gmail.com`> ####
>
> This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
> 
> This program is distributed in the hope that it will be useful, but **WITHOUT ANY WARRANTY**; without even the implied warranty of MERCHANTABILITY or **FITNESS FOR A PARTICULAR PURPOSE**.
> See the GNU Lesser General Public License for more details.
>
> You should have received a copy of the GNU Lesser General Public License along with this program.
> If not, see <<http://www.gnu.org/licenses/>>.

----------

>> ### [1.2.5] BetterFonts - OpenType Font Support ###
>> #### ThVortex ####
>> 05 April 2012 - 05:07 PM
>> www.minecraftforum.net/topic/1142084-


The BetterFonts mod adds OpenType font support for Minecraft. This mod will use the fonts installed on your system for drawing text instead of the builtin bitmap fonts that come with Minecraft. Languages such as Arabic and Hindi look much better with this mod since both require complex layout that the bitmap fonts simply can't provide. All in-game text will change to use the new fonts including GUIs, the F3 debug screen, chat, and even signs. This mod should have little or no impact on performance.

**Server Support:**
The current Minecraft server has support for full Unicode text inside chat. However, both Bukkit and Spout servers will remove any non Latin-1 characters from chat messages. I've opened a [BUKKIT-1522](https://bukkit.atlassian.net/browse/BUKKIT-1522) bug report to address this.

#### **Screenshots:** ####
> ![English Title Screen](http://lh5.googleusercontent.com/-BSVTO520XtM/T30lcUsatWI/AAAAAAAAAzg/ruUVL9SL2_M/s853/title.png)
> English Title Screen

> ![Arabic Options](http://lh6.googleusercontent.com/-o1UQZEMqGoE/T33uk5tV4SI/AAAAAAAAA0c/sgPPCUxefzM/s851/options.png)
> Arabic Options

> ![Language Selection](http://lh3.googleusercontent.com/-tKPfGw-A-bE/T33uk3ouJ0I/AAAAAAAAA0Y/xs25h0QEgdc/s851/lang.png)
> Language Selection

> ![F3 Debug Screen](http://lh3.googleusercontent.com/-DeTb7J-ipGc/T30leXP8mxI/AAAAAAAAAz4/7MmCAuEUrCI/s852/debug.png)
> F3 Debug Screen

#### **Downloads:** ####
[BetterFonts 1.2.5-3](http://dev.bukkit.org/media/files/587/899/BetterFonts_1.2.5-3.zip) - 2012/04/15

**Change Log:**
* Improved font.name lookup including matching partial font names.
* Added font.antialias and font.dropshadow options to config file.

**Older Versions:**
* [BetterFonts 1.2.5-2](http://dev.bukkit.org/media/files/586/844/BetterFonts_1.2.5-2.zip) - 2012/04/10
> Added support for a configuration file to change default font.
* [BetterFonts 1.2.5](http://dev.bukkit.org/media/files/585/767/BetterFonts_1.2.5.zip) - 2012/04/06
> Initial release. No config file support.

**Installation:**
Installation is the same as for any other mod. Extract the contents of the downloaded .zip file into your minecraft.jar, and make sure to **delete the META-INF folder** inside minecraft.jar. This mod is compatible with OptiFine, but make sure to install it **after** OptiFine.

**Source Code:**
The source code is available in a [GitHub repository](https://github.com/thvortex/BetterFonts) and is licensed under the LGPL.

#### **Configuration:** ####
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

#### **Known Issues:** ####
These are issues that I plan to eventually fix in a future version of the mod:
1. Add a GUI screen for selecting the font in-game instead of having to edit a config file.
2. The CJK unified Han characters are always displayed in the same font, but ideally different fonts would be used for Traditional Chinese, Simplified Chinese, Japanese, and Korean based on the in-game language selection.
3. Bidirectional text editing in the chat message line is broken. You can still type it (with the right keyboard layout) or copy and paste it, but creating text selections in it or moving the cursor through it will look weird.
4. The text is a little distorted at the smallest GUI size, because it is still drawn at the same font point size, and rescaled as an image. The letters should instead be redrawn using a smaller point size.
5. The §k "random text" [style code](http://wiki.vg/Chat#Styles) is not supported by this mod, but I've only seen it used for the splash text on the title screen.
6. When incoming chat text is displayed, it doesn't get wrapped at word boundries the way it normally would without this mod installed.

--------
