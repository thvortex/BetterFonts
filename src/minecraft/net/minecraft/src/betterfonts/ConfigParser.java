/*
 * Minecraft OpenType Font Support Mod
 *
 * Copyright (C) 2012 Wojciech Stryjewski <thvortex@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>..
 */

package net.minecraft.src.betterfonts;

import net.minecraft.client.Minecraft;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * This class encapsulates all of the logic needed for loading and parsing a user configuration file. This file can override the
 * default font name and font point size used for string rendering, but it is not needed for normal operation of the
 * StringCache and GlyphCache classes.
 */
public class ConfigParser
{
    /** Java's logical font names that can always be used inside the font.name property of the configuration file. */
    private static final String LOGICAL_FONTS[] = { "Serif", "SansSerif", "Dialog", "DialogInput", "Monospaced" };

    /** List of all fonts on the system used for checking if font.name in the config file is valid. */
    private Font allFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    /** Properties created after parsing of the config file. */
    private Properties cfgProps = new Properties();

    /**
     * Load and parse the configuration file.
     *
     * @param fileName pathname of the configuration file relative to the .minecraft application directory
     * @return true if the file exists and was parsed successfully as a Java property file
     */
    public boolean loadConfig(String fileName)
    {
        boolean success = false;
    
        try
        {
            FileInputStream cfgFile = new FileInputStream(Minecraft.getMinecraftDir() + fileName);
            cfgProps.load(cfgFile);
            cfgFile.close();
            success = true;
        }
        catch(IOException e)
        {
            System.out.println("BetterFonts " + e.getMessage());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("BetterFonts " + e.getMessage());
        }
        
        return success;
    }
    
    /**
     * Return the optional "font.name" property from user configuration file. Verify the requested font exists on the system
     * (based on a case insensitive string match on the name).
     *
     * @param defaultValue the default value to use if the font.name property is missing or is invalid
     * @return returns the value of the font.name property or defaultValue if font.name is missing/invalid
     */
    public String getFontName(String defaultValue)
    {
        /* Read optional "font.name" property */
        String fontName = cfgProps.getProperty("font.name");
        if(fontName == null)
        {
            return defaultValue;
        }

        /* Java's logical font names are always allowed in the font.name property */
        for(int i = 0; i < LOGICAL_FONTS.length; i++)
        {
            if(LOGICAL_FONTS[i].compareToIgnoreCase(fontName) == 0)
            {
                return LOGICAL_FONTS[i];
            }
        }

        /* Search through all fonts installed on the system for any physical fonts with a matching font name */
        for(int i = 0; i < allFonts.length; i++)
        {
            String name = allFonts[i].getFontName();
            if(name.compareToIgnoreCase(fontName) == 0)
            {
                return name;
            }
        }

        /* Print warning message if the user requested font cannot be found on the system */
        System.out.println("BetterFonts cannot find font.name \"" + fontName + "\"");
        return defaultValue;
    }

    /**
     * Load the optional "font.size" property from user configuration file. Verify the size is greater than zero
     * before returning it.
     *
     * @param defaultValue the default value to use if the font.size property is missing or is invalid
     * @return returns the value of the font.size property or defaultValue if font.size is missing/invalid
     */
    public int getFontSize(int defaultValue)
    {
        /* Read optional "font.name" property and return default if property doesn't exist */
        String value = cfgProps.getProperty("font.size");
        if(value == null)
        {
            return defaultValue;
        }

        /* Parse the string property as an integer which must be greater than zero */
        try
        {
            int i = Integer.parseInt(value);
            if(i <= 0)
            {
                throw new NumberFormatException();
            }
            defaultValue = i;
        }
        catch(NumberFormatException e)
        {
            System.out.println("BetterFonts font.size must be an integer greater than zero");
        }
        
        return defaultValue;
    }
}
