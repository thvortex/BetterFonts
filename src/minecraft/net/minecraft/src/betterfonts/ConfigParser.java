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

    /** List of all fonts on the system + logical font names; used for checking if font.name in the config file is valid. */
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
     * based on a case insensitive substring match between the font face name and the user requested name. Note that Java has
     * some inconsistency in how it reports font face vs font family names compared to what the Fonts control panel under
     * Windows 7 shows. For example, the control panel shows "Franklin Gothic" as a font family with two styles "Medium" and
     * "Medium Italic". Yet Java 6 reports both the font <i>family</y> as "Franklin Gothic Medium". These kinds of variations
     * make it difficult to match font names properly, so the approach here it to first look for an exact font face name
     * match, and failing that a substring match. In other words, if the user specified "Times" it would most likely match with
     * "Times New Roman". Even more interesting, is when a font shows up with family name "Latin Wide" in the control panel
     * but Java returns "Wide Latin" for both the font's famil and face name.
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

        /* Trim whitespace; convert to lowercase so the partial name lookups with indexOf() are case insensitive */
        String searchName = fontName.trim().toLowerCase();

        /* Java's logical font names are always allowed in the font.name property */
        for(int i = 0; i < LOGICAL_FONTS.length; i++)
        {
            if(LOGICAL_FONTS[i].compareToIgnoreCase(searchName) == 0)
            {
                return LOGICAL_FONTS[i];
            }
        }

        /* Some fonts report their plain variety with "Medium" in the name so try exact search on that too */
        String altSearchName = searchName + " Medium" ;

        /* If a font partially matches a user requested name, remember it here in case there are no exact matches */
        String partialMatch = null;

        /* Search through all available fonts installed on the system */
        for(int index = 0; index < allFonts.length; index++)
        {
            /* Always prefer an exact match on the font face name which terminates the search with a result */
            Font font = allFonts[index];
            String name = font.getFontName();
            if(name.compareToIgnoreCase(searchName) == 0 || name.compareToIgnoreCase(altSearchName) == 0)
            {
                return name;
            }

            /*
             * Remember partial name matches so they can be returned if no other exact matche exists. This match is done
             * with both the font family and font face concatenated together to handle the weird case of the "Latin Wide"
             * font. Always prefer to partial match the shortest possible font face name to match "Times New Roman" before
             * "Times New Roman Bold" for instance.
             */
            if((font.getFamily() + " " + name).toLowerCase().indexOf(searchName) != -1)
            {
                if(partialMatch == null || partialMatch.length() > name.length())
                {
                    partialMatch = name;
                }
            }
        }

        /* If not exact match was found, then return the last partial match that was made */
        if(partialMatch != null)
        {
            return partialMatch;
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

    /**
     * Load an optional true/false property from user configuration file. If the property exists and is either the
     * (case-insensitive) string "true" or "false", its value is returned as a boolean. If the property is present,
     * but contains any other string, a warning is printed to the console and the default value is returned.
     * Otherwise
     *
     * @param propertyName the property name to read from the configuration file
     * @param defaultValue the default value to use if the property is missing
     * @return returns the value of the property or defaultValue if the property is missing/invalid
     */
    public boolean getBoolean(String propertyName, boolean defaultValue)
    {
        String value = cfgProps.getProperty(propertyName);
        if(value == null)
        {
            return defaultValue;
        }
        else if(value.compareToIgnoreCase("true") == 0)
        {
            return true;
        }
        else if(value.compareToIgnoreCase("false") == 0)
        {
            return false;
        }
        else
        {
            System.out.println("BetterFonts " + propertyName + " must be either \"true\" or \"false\"");
            return defaultValue;
        }
    }
}
