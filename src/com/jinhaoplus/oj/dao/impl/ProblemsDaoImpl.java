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
		Problem problem = (Problem) getSqlMapClientTemplate().queryForObject("getProblemById",id);
		System.out.println(problem);
		return problem;
	}

}