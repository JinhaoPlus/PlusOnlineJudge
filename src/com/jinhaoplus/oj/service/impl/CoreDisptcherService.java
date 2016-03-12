package com.jinhaoplus.oj.service.impl;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.CoreDispatcherService;
import com.jinhaoplus.oj.service.ProblemsService;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.service.langCore.impl.CCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.CppCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.HaskellCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.JavaCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.PyCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.RubyCoreServiceImpl;
import com.jinhaoplus.oj.util.PropertiesUtil;
import com.jinhaoplus.oj.util.Source2FileService;

@Service
public class CoreDisptcherService implements CoreDispatcherService{

	private final String COMPILE_SUCCESS_CODE = PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE");
	private final String COMPILE_SUCCESS = PropertiesUtil.getProperty("COMPILE_SUCCESS");
	private final String COMPILE_ERROR_CODE = PropertiesUtil.getProperty("COMPILE_ERROR_CODE");
	private final String COMPILE_ERROR = PropertiesUtil.getProperty("COMPILE_ERROR");

	private final String RUN_SUCCESS_CODE = PropertiesUtil.getProperty("RUN_SUCCESS_CODE");
	private final String RUN_ERROR_CODE = PropertiesUtil.getProperty("RUN_ERROR_CODE");
	
	
	private LangCoreService langCoreService = null;
	
	public void setLangCoreService(LangCoreService langCoreService) {
		this.langCoreService = langCoreService;
	}
	
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}

	@Autowired
	private CCoreServiceImpl cCoreService;
	
	public void setcCoreService(CCoreServiceImpl cCoreService) {
		this.cCoreService = cCoreService;
	}
	
	@Autowired
	private PyCoreServiceImpl pyCoreService;
	
	public void setPyCoreService(PyCoreServiceImpl pyCoreService) {
		this.pyCoreService = pyCoreService;
	}
	
	@Autowired
	private JavaCoreServiceImpl javaCoreService;
	public void setJavaCoreService(JavaCoreServiceImpl javaCoreService) {
		this.javaCoreService = javaCoreService;
	}

	@Autowired
	private CppCoreServiceImpl cppCoreService;
	public void setCppCoreService(CppCoreServiceImpl cppCoreService) {
		this.cppCoreService = cppCoreService;
	}

	@Autowired
	private RubyCoreServiceImpl rubyCoreService;
	public void setRubyCoreService(RubyCoreServiceImpl rubyCoreService) {
		this.rubyCoreService = rubyCoreService;
	}

	@Autowired
	private HaskellCoreServiceImpl haskellCoreService;
	public void setHaskellCoreService(HaskellCoreServiceImpl haskellCoreService) {
		this.haskellCoreService = haskellCoreService;
	}


	@Override
	public void dispatchSolution(ProblemSolution solution) {
		String lang = solution.getSolutionLanguage();
		if("java".equals(lang)){
			langCoreService = javaCoreService;
		}
		else if ("c".equals(lang)) {
			langCoreService = cCoreService;
		}
		else if ("cpp".equals(lang)) {
			langCoreService = cppCoreService;
		}
		else if ("rb".equals(lang)) {
			langCoreService = rubyCoreService;
		}
		else if ("py".equals(lang)) {
			langCoreService = pyCoreService;
		}
		else if ("haskell".equals(lang)) {
			langCoreService = haskellCoreService;
		}
	}


	@Override
	public List<ProblemTestResult> workFlow(ProblemSolution solution,String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		//write Code to temp file
		String fileOrDirName = path+Source2FileService.renameForTempSource(solution)+"."+solution.getSolutionLanguage();
		//workflow source file
		if(langCoreService!=null){
			String sourceFilePath = langCoreService.createTempSourceFile(fileOrDirName);
			
			Source2FileService.persistentFile(solution, sourceFilePath);
			CommonMessage message = langCoreService.compileCode(solution.getProblemId(),sourceFilePath);
			System.out.println("[+]compileinfo+"+message);
			if(COMPILE_SUCCESS_CODE.equals(message.getCode())){
				List<ProblemTestResult> results = langCoreService.runCode(solution.getProblemId(),solution.getSolutionId() , sourceFilePath);
				String finalOJResult = "AC";
				for (ProblemTestResult problemTestResult : results) {
					if(!"AC".equals(problemTestResult.getOjResult())){
						finalOJResult = "WA";
						break;
					}
				}
				solution.setFinalOJResult(finalOJResult);
				problemsService.updateSolution(solution);
				return results;
			}else if(COMPILE_ERROR_CODE.equals(message.getCode())){
				solution.setFinalOJResult("CE");
				problemsService.updateSolution(solution);
				
				List<ProblemTestResult> results = new ArrayList<ProblemTestResult>();
				ProblemTestResult problemTestResult = new ProblemTestResult();
				problemTestResult.setMessage(message);
				results.add(problemTestResult);
				return results;
			}
		}
		return null;
	}

	
}
