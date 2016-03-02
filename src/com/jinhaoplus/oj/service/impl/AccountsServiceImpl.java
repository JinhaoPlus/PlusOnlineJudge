package com.jinhaoplus.oj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.AccountsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.AccountsService;
import com.jinhaoplus.oj.util.SecurityUtils;


@Service
public class AccountsServiceImpl implements AccountsService{

	@Autowired
	private AccountsDao accountsDao;
	
	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Override
	public CommonMessage signUp(User user) {
		user.setPassword(SecurityUtils.AESEncrypt(user.getPassword()));
		User probUser = accountsDao.getByName(user.getUsername());
		if(probUser == null){
			accountsDao.insert(user);
			return new CommonMessage("200", "signup successfully","");
		}
		else
			return new CommonMessage("500", "username invalid","");
	}

	@Override
	public CommonMessage login(User user) {
		user.setPassword(SecurityUtils.AESEncrypt(user.getPassword()));
		User probUser = accountsDao.getByName(user.getUsername());
		if(probUser != null && probUser.getPassword().equals(user.getPassword())){
			return new CommonMessage("200", "login successfully","");
		}
		else {
			return new CommonMessage("500", "invalid name or password","");
		}
	}

}
