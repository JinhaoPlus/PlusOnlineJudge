package com.jinhaoplus.oj.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.service.CCoreService;

@Service
public class CCoreServiceImpl implements CCoreService {

	@Override
	public String compileCode(Long codeId) {
		String result="";
		try {
			Process compileProcess = Runtime.getRuntime().exec("gcc -o hello d:\\temp.c");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
			String compileResult = bufferedReader.readLine();
			result += "[compile INFO] "+compileResult+"\n";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String runCode(Long codeId) {
		String result="";
		try {
			Process runProcess = Runtime.getRuntime().exec("hello.exe");
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
			String runResult = bufferedReader1.readLine();
			result += "[run INFO] "+runResult+"\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String OJResult() {
		String result="";
		try {
			result += "[run INFO] "+"AC"+"\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
