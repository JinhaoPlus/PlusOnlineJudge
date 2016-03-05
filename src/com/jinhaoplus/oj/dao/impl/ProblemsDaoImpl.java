package com.jinhaoplus.oj.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

@Repository
public class ProblemsDaoImpl extends SqlMapClientDaoSupport implements ProblemsDao{

	@Resource(name = "dataSource")  
    private DataSource dataSource;  
    @PostConstruct  
        public void initDataSource(){  
         super.setDataSource(dataSource);
    }   

	@Resource(name = "sqlMapClient")  
    private SqlMapClient sqlMapClient;  
    @PostConstruct  
        public void initSqlMapClient(){  
         super.setSqlMapClient(sqlMapClient);  
    }   
	@Override
	public List<Problem> getList() {
		return getSqlMapClientTemplate().queryForList("getAllProblems",null);
	}

	@Override
	public Problem getById(int id) {
		return (Problem) getSqlMapClientTemplate().queryForObject("getProblemById",id);
	}
	
	@Override
	public List<ProblemTest> getTestByProblemId(int problemId) {
		return  (List<ProblemTest>) getSqlMapClientTemplate().queryForList("getTestByProblemId",problemId);
	}
	@Override
	public void insertTestResult(ProblemTestResult testResult) {
		getSqlMapClientTemplate().insert("insertTestResult", testResult);
		
	}
	
	

}
