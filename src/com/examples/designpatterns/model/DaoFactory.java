package com.examples.designpatterns.model;

public class DaoFactory {

	public static UserDAO getUserDAO() {
		return new UserDAO();
	}
}
