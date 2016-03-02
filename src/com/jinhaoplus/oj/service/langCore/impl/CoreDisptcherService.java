package com.jinhaoplus.oj.service.langCore.impl;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.service.langCore.CoreDispatcherService;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
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
	public void workFlow(ProblemSolution solution,String path) {
		String code = solution.getCodeSubmit();
		//write Code to temp file
		String fileOrDirName = path+Source2FileService.renameForTempSource(solution)+"."+solution.getSolutionLanguage();
		//Complile source file
		if(langCoreService!=null){
			String sourceFilePath = langCoreService.createTempSourceFile(fileOrDirName);
			Source2FileService.persistentFile(solution, sourceFilePath);
			langCoreService.compileCode(solution.getProblemId(),sourceFilePath);
			langCoreService.runCode(solution.getProblemId(),sourceFilePath);
		}
	}

	
}
