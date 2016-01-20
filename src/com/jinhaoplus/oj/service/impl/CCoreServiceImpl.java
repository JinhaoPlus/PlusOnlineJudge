package com.jinhaoplus.oj.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
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
			Process runProcess = Runtime.getRuntime().exec("hello.exe");
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
			String runResult = bufferedReader1.readLine();
			result += "[run INFO] "+runResult+"\n";
			result += "[OJ INFO] AC";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
