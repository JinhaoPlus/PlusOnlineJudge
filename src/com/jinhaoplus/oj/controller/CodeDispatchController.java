package com.jinhaoplus.oj.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinhaoplus.oj.domain.SourceCode;
import com.jinhaoplus.oj.service.CCoreService;
import com.jinhaoplus.oj.util.Source2FileService;

@Controller

public class CodeDispatchController{
	@Autowired
	private CCoreService cCoreService;
	public void setcCoreService(CCoreService cCoreService) {
		this.cCoreService = cCoreService;
	}

	@RequestMapping(value="/code")
	@ResponseBody
	public String getUserByName(HttpServletRequest request,SourceCode code){
		code.setSourceType("C");
		code.setUploadDate(new Date());
		Long id = new Long((long)1);
		code.setSourceId(id);
		Source2FileService.persistentFile(code);
		String result = cCoreService.compileCode(id);
		result += cCoreService.runCode(id);
		result += cCoreService.OJResult();
		return result;
	}
}
