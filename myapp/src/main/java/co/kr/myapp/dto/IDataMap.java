package co.kr.myapp.dto;

import java.io.*;
import java.util.*;

import org.apache.commons.lang.*;

public class IDataMap extends LinkedHashMap implements Serializable{
	
	public static final int DEFAULT_INTEGER_VALUE = 0;
	public static final long DEFAULT_LONG_VALUE = 0L;

	public String getString(String key) {
		
		if(!this.containsKey(key)) {
			return StringUtils.EMPTY;
		}else{
			return StringUtils.defaultIfEmpty(String.valueOf(this.get(key)), StringUtils.EMPTY);
		}
	}
	
	public String getString(String key, String defaultString) {
		
		if(!this.containsKey(key)) {
			return defaultString;
		}else{
			return StringUtils.defaultIfEmpty(String.valueOf(this.get(key)), defaultString);
		}
	}
	
	public int getInt(String key) {

		Object obj = get(key);
		if (obj instanceof Number) {
			return ((Number) obj).intValue();
		} else {
			String val = getString(key);
			if (val == null)
				return DEFAULT_INTEGER_VALUE;
			try {
				return Integer.parseInt(val);
			} catch (Exception e) {
				return DEFAULT_INTEGER_VALUE;
			}
		}
	}
	
	public int getInt(String key, int defaultNum) {

		Object obj = get(key);
		if (obj instanceof Number) {
			
			int retInt = ((Number) obj).intValue();
			if(retInt <= 0) retInt = defaultNum;
			
			return retInt;
		} else {
			String val = getString(key);
			if (val == null)
				return defaultNum;
			try {
				int retInt = Integer.parseInt(val);
				if(retInt <= 0) retInt = defaultNum;
				
				return retInt;
			} catch (Exception e) {
				return defaultNum;
			}
		}
	}
	
	public long getLong(String key) {
		Object obj = get(key);
		if (obj instanceof Number) {
			return ((Number) obj).longValue();
		} else {
			String val = getString(key);
			if (val == null)
				return DEFAULT_LONG_VALUE;
			try {
				return Long.parseLong(val);
			} catch (Exception e) {
				return DEFAULT_LONG_VALUE;
			}
		}
	}
}
