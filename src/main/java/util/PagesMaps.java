package util;

import java.util.Map;
import java.util.Map.Entry;

public class PagesMaps {	
//----------------------- Map <String, String> ---------------------------

	/**
	 * Counts the size of the map
	 * @param map - linked hashmap, type <String, String>
	 * @return - size of map
	 */
	public int getStrStrMapSize(Map<String, String> map) {
		return map.size();
	}
	
	/**
	 * Get a value from a String key and String value pair
	 * @param map - linked hashmap, type <String, String>
	 * @param key - enter key from the key/value pair in String form
	 * @return - value from key/value pair
	 */
	public String getStrStrMapValue(Map<String, String> map, String key) {
		return map.get(key);
	}
	
	/**
	 * Get a key from a String key and String value pair
	 * @param map - linked hashmap, type <String, String>
	 * @param value - enter value from the key/value pair in String form
	 * @return - key from key/value pair
	 */
	public String getStrStrMapKey(Map<String, String> map, String value) {
		String key = "";
		for (Entry<String, String> entry : map.entrySet()) {
			if(entry.getValue().equals(value)) {
				key = entry.getKey();
			}
		}
		return key;
	}
	
//----------------------- Map <String, Integer> ---------------------------

	/**
	 * Counts the size of the map, type <String, Integer>
	 * @param map - linked hashmap, type <String, Integer>
	 * @return - size of map
	 */
	public int getStrIntMapSize(Map<String, Integer> map) {
		return map.size();
	}
	
	/**
	 * Get a value from a String key and Integer value pair
	 * @param map - linked hashmap, type <String, Integer>
	 * @param key - enter key from the key/value pair in String form
	 * @return - value from key/value pair
	 */
	public int getStrIntMapValue(Map<String, Integer> map, String key) {
		return map.get(key);
	}
	
	/**
	 * Get a key from a String key and Integer value pair
	 * @param map - linked hashmap, type <String, Integer>
	 * @param value - enter value from the key/value pair in Integer form
	 * @return - key from key/value pair
	 */
	public String getStrIntMapKey(Map<String, Integer> map, int value) {
		String key = "";
		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() == value) {
				key = entry.getKey();
			}
		}
		return key;
	}
	
	
//----------------- Map <String, String>, Key/Value Pair by Index Number ------------------
	
	/**
	 * Transform and get the key by index number from the map, type <String, String>
	 * @param map - linked hashmap, type <String, String>
	 * @param indexNumber - the position of the key/value pair in the array
	 * @return - key of key/value pair
	 */
	public String getStrStrMapKeyByIndex(Map<String, String> map, int indexNumber) {
		return map.keySet().toArray()[indexNumber].toString();
	}
	
	/**
	 * Transform and get the value by index number from the map, type <String, String>
	 * @param map - linked hashmap, type <String, String>
	 * @param indexNumber - the position of the key/value pair in the array
	 * @return - value of key/value pair
	 */
	public String getStrStrMapValueByIndex(Map<String, String> map, int indexNumber) {
//		Object[] stringArray = stringStringMap.values().toArray();
//		for (Object genString : stringArray) {
//			System.out.println(genString);
//		}
		return map.values().toArray()[indexNumber].toString();
	}
}
