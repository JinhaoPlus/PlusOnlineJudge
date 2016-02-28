package com.jinhaoplus.oj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.Problem;

@Service
public interface ProblemsService {
	public List<Problem> getAllProblems();
	public Problem getById(int id);
}
