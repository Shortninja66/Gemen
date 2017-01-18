package us.theaura.gemen.server;

import us.theaura.gemen.TheAura;
import us.theaura.gemen.util.JavaUtils;

/**
 * Enum that stores all config options.
 * 
 * @since 24 September 2016 8:36 PM
 * @author Shortninja
 */

public enum Option
{
	PRIMARY("primary-color"), SECONDARY("secondary-color"), TERTIARY("tertiary-color"),
	MONGO_USER("mongodb.user"), MONGO_PASSWORD("mongodb.password"), SQL_SERVER("mysql.server"),
	SQL_DATABASE("mysql.database"), SQL_USER("mysql.user"), SQL_PASSWORD("mysql.password"),
	LOG_KEYS("log-keys");
	
	private String node;
	private String value = null;
	private int intValue = -1;
	
	Option(String node)
	{
		this.node = node;
	}
	
	public String getValue()
	{
		if(value == null)
		{
			value = TheAura.instance().getConfig().getString(node);
		}
		
		return value;
	}
	
	public int getValueAsInt()
	{
		if(intValue < 0 && JavaUtils.isInteger(getValue()))
		{
			intValue = Integer.parseInt(getValue());
		}
		
		return intValue;
	}
}