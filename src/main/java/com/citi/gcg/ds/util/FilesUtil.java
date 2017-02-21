/**
 * 
 */
package com.citi.gcg.ds.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
/**
 * @author hd11804
 *
 */
public class FilesUtil {
	
	
	public static String readFile(String fileName) throws Exception{		
		
		String content = new String(readAllBytes(get(fileName)));
		
		return content;
	}
	
	public static void createFile(String fileName,String content){
		
		//delete any old file, if exist
	  	try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	  	
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), charset)) {
		    writer.write(content, 0, content.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}

}
