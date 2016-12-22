package us.theaura.gemen.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Various useful methods from multiple sources.
 * 
 * @since 20 February 2016 at 5:47 AM
 * @author Shortninja, libhalt, ...
 */

public class JavaUtils
{
	/**
	 * Uses #valueOf() to check if an enum is valid.
	 * 
	 * @param enumClass Enum class to check from.
	 * @param enumName Enum value to check for.
	 * @return Whether or not the enum is valid for the given class.
	 */
    public static boolean isValidEnum(Class enumClass, String enumName)
    {
        if(enumName == null)
        {
            return false;
        }
        
        try
        {
            Enum.valueOf(enumClass, enumName);
            return true;
        }catch(IllegalArgumentException ex)
        {
            return false;
        }
    }
    
    /**
     * Tries to parse an integer with #parseInt() and Catches NumberFormatException.
     * 
     * @param string The string to parse.
     * @return Whether or not the string can be parsed as an integer.
     */
	public static boolean isInteger(String string)
	{
		boolean isInteger = true;
		
		try
		{
			Integer.parseInt(string);
		}catch(NumberFormatException exception)
		{
			isInteger = false;
		}
		
		return isInteger;
	}
	
	/**
	 * Inserts a commas between each word in the given string with StringBuilder.
	 * 
	 * @param string The string to insert commas in.
	 * @return New string with commas.
	 */
	public static String insertCommas(String string)
	{
		StringBuilder builder = new StringBuilder();
		String[] words = string.split(" ");
		
		for(int i = 0; i < words.length; i++)
		{
			String word = words[i];
			String suffix = ",";
			
			if((i + 1) == words.length)
			{
				suffix = "";
			}
			
			builder.append(word + suffix);
		}
		
		return builder.toString();
	}
	
	/**
	 * Splits a string with commas in it into a string List.
	 * 
	 * @param commas The string to split.
	 * @return The split string List.
	 */
	public static List<String> stringToList(String commas)
	{
		List<String> list = new ArrayList<String>();
		ListIterator<String> iterator = Arrays.asList(commas.split("\\s*,\\s*")).listIterator();
		
		if(iterator != null)
		{
			while(iterator.hasNext())
			{
				list.add(iterator.next());
			}
		}else list = Arrays.asList(commas);
		
		return list;
	}
	
	/**
	 * Inserts a commas between each word in the given string with StringBuilder.
	 * 
	 * @param string The string to insert commas in.
	 * @return New string with commas.
	 */
	public static String listToCommas(List<String> list)
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < list.size(); i++)
		{
			String string = list.get(i);
			
			if((i + 1) == list.size())
			{
				builder.append(string);
			}else builder.append(string + ", ");
		}
		
		return builder.toString().trim();
	}
	
	/**
	 * Uses SimpleDateFormat#format() to format the date at the given time.
	 * 
	 * @param time The time in milliseconds.
	 * @return The date in the format of "MM-dd-YY".
	 */
	public static String getDate(long time)
	{
        return new SimpleDateFormat("MM-dd-YY").format(new Date(time));
	}
	
	/**
	 * Creates a timestamp using the Timestamp class.
	 * 
	 * @param time The time in milliseconds.
	 * @return The time in military format with seconds.
	 */
	public static String getTime(long time)
	{
		String stamp = new Timestamp(time).toString();
		
		return stamp.substring(0, stamp.length() - 4);
	}
	
	/**
	 * @param map The map to get entries from.
	 * @param value The value to search for.
	 * @return The assumed key from the given value. 
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value)
	{
		for(Entry<T, E> entry : map.entrySet())
		{
			if(Objects.equals(value, entry.getValue()))
			{
				return entry.getKey();
			}
		}
		
		return null;
	}
	
	/**
	 * Attempts to replace the given string with the replacement string. Taken from
	 * org.apache.commons.lang3.StringUtils and slightly modified.
	 * 
     * @param text Text to search and replace in, may be null.
     * @param searchString The String to search for, may be null.
     * @param replacement The String to replace it with, may be null.
     * @param max Maximum number of values to replace, or -1 if no maximum.
     * @return The text with any replacements processed.
	 */
	public static String replace(final String text, final String searchString, final String replacement)
	{
		int max = -1;
		
		if(isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
		{
			return text;
		}
		
		int start = 0;
		int end = text.indexOf(searchString, start);
		final int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= max < 0 ? 16 : max > 64 ? 64 : max;
		final StringBuilder buf = new StringBuilder(text.length() + increase);
		
		if(end == - 1)
		{
			return text;
		}
		
		while(end != - 1)
		{
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			
			if(--max == 0)
			{
				break;
			}
			
			end = text.indexOf(searchString, start);
		}
		
		return buf.append(text.substring(start)).toString();
	}
    
	/**
	 * Checks if the given char sequence is empty. Taken from 
	 * org.apache.commons.lang3.StringUtils.
	 * 
     * @param chars The CharSequence to check, may be null.
     * @return If the CharSequence is empty or null.
	 */
    public static boolean isEmpty(CharSequence chars)
    {
    	return chars == null || chars.length() == 0;
    }
    
	/**
	 * Gets the displayed message of a stack trace. Taken from
	 * org.apache.commons.lang.exception.ExceptionUtils
	 * 
     * @param throwable The instance in which the stack trace will be examined.
     * @return The exception that would be printed to console.
	 */
    public static String getStackTrace(final Throwable throwable)
    {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter, true);
        
        throwable.printStackTrace(printWriter);
        return stringWriter.getBuffer().toString();
    }
    
    /**
     * Returns a Calendar instance with the current system time in milliseconds.
     * 
     * @return
     */
    public static Calendar getCurrentCalendar()
    {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(System.currentTimeMillis());
		return calendar;
    }
}