package com.jinhaoplus.oj.service.langCore.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.common.ResultReadCallable;
import com.jinhaoplus.oj.common.TestWriteCallable;
import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class CCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors .newCachedThreadPool();
	
	@Override
	public CommonMessage compileCode(int problemId,String path) {
//		CommonMessage message;
//		String fileName = path.substring(0,path.lastIndexOf("."));
//		ProcessBuilder processBuilder;
//        boolean compiled = true;
//        processBuilder = new ProcessBuilder("gcc","-std=c++0x","-w","-o", fileName, path);
//        processBuilder.redirectErrorStream(true);
//
//        try {
//            Process compileProcess = processBuilder.start();
//            //Regard the shell as IO object , so we can use inputStream to read some lines from this special IO
//            InputStream is = compileProcess.getInputStream();
//            BufferedReader b = new BufferedReader(new InputStreamReader(is));
//            StringBuffer compileInfo=new StringBuffer("");
//            String compileInfoLine;
//            //if correctly compiled , there should not be output in the shell , so the inputStream should be null
//            while ((compileInfoLine = b.readLine()) != null) {
//                compiled = false;
//                compileInfo.append(compileInfoLine);
//            }
//            compileProcess.waitFor();
//
//            if(compiled){
//            	message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
//            								PropertiesUtil.getProperty("COMPILE_SUCCESS"),
//            								"");
//            	is.close();
//            }
//            else{
//            	message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_ERROR_CODE"), 
//											PropertiesUtil.getProperty("COMPILE_ERROR"),
//											compileInfo.toString());
//                is.close();
//               
//            }
//            return message;
//        } catch (Exception e) {
//            System.out.println("in compile\n " + e);
//        }
//		return null;
		CommonMessage message = null;
		ProcessBuilder processBuilder;

		String fileName = path.substring(0,path.lastIndexOf("."));
		processBuilder = new ProcessBuilder("gcc","-o",fileName,path);
		processBuilder.redirectErrorStream(true);

		try {
			Process compileProcess = processBuilder.start();
			final InputStream inputStream = compileProcess.getInputStream();
			final InputStream errorStream = compileProcess.getErrorStream();

			ResultReadCallable compileResultThread = new ResultReadCallable(inputStream);
			ResultReadCallable compileErrorThread = new ResultReadCallable(errorStream);
			Future<String> compileErrorInfo = executor.submit(compileErrorThread);
			Future<String> compileResultInfo = executor.submit(compileResultThread);
			System.out.println("-------------------------------------------");
			System.out.println(compileResultInfo.get());
			System.out.println(compileErrorInfo.get());
			System.out.println("-------------------------------------------");
			compileProcess.waitFor();
			compileProcess.destroy();
			
			if(compileProcess.exitValue()==0){
				System.out.println("compile successfully");
			}else{
				System.out.println("compile error");
				System.out.println(compileErrorInfo.get());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String runCode(int problemId, String path) {
		List<ProblemTest> problemTests = problemsDao
				.getTestByProblemId(problemId);
		for (ProblemTest problemTest : problemTests) {
			CommonMessage message = null;
			ProcessBuilder processBuilder;
			String javaDir = path.substring(0,path.lastIndexOf("/"));
			processBuilder = new ProcessBuilder("cd",javaDir,"&&","java","Test");
			System.out.println("run Command:"+"cd "+javaDir+" && "+"java Test");
			processBuilder.redirectErrorStream(true);

			try {
				Process runProcess = processBuilder.start();
				final InputStream inputStream = runProcess.getInputStream();
				final InputStream errorStream = runProcess.getErrorStream();
				final OutputStream outputStream = runProcess.getOutputStream();

				ResultReadCallable runResultThread = new ResultReadCallable(inputStream);
				ResultReadCallable runErrorThread = new ResultReadCallable(errorStream);
				TestWriteCallable runTestWriteThread = new TestWriteCallable(outputStream, problemTest.getProblemTestInput());
				Future<String> runErrorInfo = executor.submit(runErrorThread);
				Future<String> runResultInfo = executor.submit(runResultThread);
				executor.submit(runTestWriteThread);
				System.out.println("-------------------------------------------");
				System.out.println(runResultInfo.get());
				System.out.println(runErrorInfo.get());
				System.out.println("-------------------------------------------");
				runProcess.waitFor();
				runProcess.destroy();
				
				if(runProcess.exitValue()==0){
					System.out.println("run successfully");
				}else{
					System.out.println("run error");
					System.out.println(runErrorInfo.get());
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return null;
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

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		return fileOrDirName;
	}

	
}
