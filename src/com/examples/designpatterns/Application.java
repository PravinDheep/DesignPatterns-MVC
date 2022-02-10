package com.examples.designpatterns;

import com.examples.designpatterns.controller.Controller;
import com.examples.designpatterns.model.Model;
import com.examples.designpatterns.view.View;

public class Application {

	public static void main(String[] args) {
		runApp();
	}
	
	public static void runApp() {
		Model model = new Model();
		
		View view = new View(model);
		
		Controller controller = new Controller(model, view);
		
		view.setLoginListener(controller);
	}

}
