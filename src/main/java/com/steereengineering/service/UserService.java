package com.steereengineering.service;

import com.steereengineering.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
