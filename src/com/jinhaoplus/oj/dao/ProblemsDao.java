package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.Problem;

public interface ProblemsDao {
	public List<Problem> getList();

	public Problem getById(int id);

}
