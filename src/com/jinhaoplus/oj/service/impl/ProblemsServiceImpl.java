package com.jinhaoplus.oj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.service.ProblemsService;

@Service
public class ProblemsServiceImpl implements ProblemsService{

	@Autowired
	private ProblemsDao problemsDao;
	
	

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}



	@Override
	public List<Problem> getAllProblems() {
		return problemsDao.getList();
	}



	@Override
	public Problem getById(int id) {
		return problemsDao.getById(id);
	}

}
