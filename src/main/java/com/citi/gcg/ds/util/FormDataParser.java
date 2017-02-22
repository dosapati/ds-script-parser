package com.citi.gcg.ds.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FormDataParser {
	
	final static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	public static void main(String[] args) throws Exception{
		
		parseDecodedStringFromFile("./data/if_else_markup.txt");
		
	}

	public static void parseDecodedStringFromFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
		String content = FilesUtil.readFile(fileName);
		
		parseDecodedString(content);
		

	}

	/**
	 * @param content
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	private static void parseDecodedString(String content) throws UnsupportedEncodingException, Exception {
		String decodedString = new String(URLDecoder.decode(content,"UTF-8"));
		
		//System.out.println("decodedString -->"+decodedString);

		
		Map<String,String> paramsMap = splitQuery(content);
		
		Set<Entry<String, String>> paramEntrySet = paramsMap.entrySet();
		
		for (Entry<String, String> entry : paramEntrySet) {
			System.out.println("private String "+entry.getKey()+" = \""+entry.getValue()+"\";");
			if(StringUtils.equalsIgnoreCase(entry.getKey(), "mappedAttrData")){
				List<Map<String,Object>> mappedAttrDataMap = gson.fromJson(entry.getValue(), List.class);
				for (Map<String, Object> map : mappedAttrDataMap) {
					Set<Entry<String, Object>> mappedAttrDataMapEntrySet = map.entrySet();
					
					for (Entry<String, Object> entry1 : mappedAttrDataMapEntrySet) {
						System.out.println(entry1.getKey()+" === "+entry1.getValue());
						
					}
				}
				
			}
		}
	}
	
	public static Map<String, String> splitQuery(String query) throws Exception {
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
        int idx = pair.indexOf("=");
        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
    }
    return query_pairs;
}

}
