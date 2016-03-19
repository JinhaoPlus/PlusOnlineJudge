package com.jinhaoplus.oj.util;

import java.io.File;
import java.io.FileWriter;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;

public class Source2FileService {
	private Source2FileService(){
		
	}
	public static String renameForTempSource(ProblemSolution solution){
		StringBuffer rename = new StringBuffer();
		long sysMill = System.currentTimeMillis();
		rename.append("prblm");
		rename.append(solution.getProblemId());
		rename.append(solution.getSolutionCoderId());
		rename.append(new String(new Long(sysMill).toString()));
		return rename.toString();
	}
	public static void persistentFile(ProblemSolution solution,String path) {
		try {
			String source = solution.getCodeSubmit();
			File file = new File(path);
			file.createNewFile();
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(source);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ProblemSolution sourceForACE(ProblemSolution solution) {
		String source = solution.getCodeSubmit();
		String modifySource = source.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\"", "\\\\\"");
		solution.setCodeSubmit(modifySource);
		return solution;
	}
	public static ProblemTest beautyTestIO(ProblemTest problemTest){
		String visableTestInput = problemTest.getProblemTestInput().replaceAll("\\\\n", "↵<br/>");
		String visableTestOutput = problemTest.getProblemTestOutput().replaceAll("\\\\n", "↵<br/>");
		problemTest.setProblemTestInput(visableTestInput);
		problemTest.setProblemTestOutput(visableTestOutput);
		return problemTest;
	}
}
