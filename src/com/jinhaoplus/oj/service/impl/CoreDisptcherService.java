package com.jinhaoplus.oj.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.CoreDispatcherService;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.service.langCore.impl.CCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.CppCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.HaskellCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.JavaCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.PyCoreServiceImpl;
import com.jinhaoplus.oj.service.langCore.impl.RubyCoreServiceImpl;
import com.jinhaoplus.oj.util.Source2FileService;

@Service
public class CoreDisptcherService implements CoreDispatcherService{

	private LangCoreService langCoreService = null;
	
	public void setLangCoreService(LangCoreService langCoreService) {
		this.langCoreService = langCoreService;
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
	public void dispatchCore(ProblemSolution solution) {
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
		//write Code to temp file
		String fileOrDirName = path+Source2FileService.renameForTempSource(solution)+"."+solution.getSolutionLanguage();
		//workflow source file
		if(langCoreService!=null){
			String sourceFilePath = langCoreService.createTempSourceFile(fileOrDirName);
			
			Source2FileService.persistentFile(solution, sourceFilePath);
			langCoreService.compileCode(solution.getProblemId(),sourceFilePath);
			
			List<ProblemTestResult> results = langCoreService.runCode(solution.getProblemId(),solution.getSolutionId() , sourceFilePath);
			
			return results;
		}
		return null;
	}

	
}
