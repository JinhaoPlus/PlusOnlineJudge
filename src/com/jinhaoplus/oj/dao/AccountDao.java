package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.User;

public interface AccountDao {

	public List<User> getList();

	public User getByName(String name);

	public User getById(String id);

	public void insert(User user);

	public void delete(String id);

	public void update(User user);

}
