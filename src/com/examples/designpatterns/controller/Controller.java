package com.examples.designpatterns.controller;

import java.sql.SQLException;

import com.examples.designpatterns.model.DaoFactory;
import com.examples.designpatterns.model.Model;
import com.examples.designpatterns.model.UserDAO;
import com.examples.designpatterns.model.UserDetails;
import com.examples.designpatterns.view.LoginFormEvent;
import com.examples.designpatterns.view.LoginListener;
import com.examples.designpatterns.view.View;

public class Controller implements LoginListener {
	
	private Model model;
	private View view;
	private UserDAO userDao = DaoFactory.getUserDAO();
	
	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}

	@Override
	public void loginPerformed(LoginFormEvent event) {
		System.out.println("Login event triggered: " + event.getUsername() + " : " + event.getPassword());
		
		userDao.addUser(new UserDetails(event.getUsername(), event.getPassword()));
	}
	
	
}
