package com.jinhaoplus.oj.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jinhaoplus.oj.domain.SourceCode;

public class Source2FileService {
	private Source2FileService(){
		
	}
	public static void persistentFile(SourceCode code) {
		String source = code.getSource();
		File file = new File("d:/temp.c");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(source);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
