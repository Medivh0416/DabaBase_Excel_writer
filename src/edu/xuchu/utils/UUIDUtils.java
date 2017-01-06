package edu.xuchu.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getUIUD(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
