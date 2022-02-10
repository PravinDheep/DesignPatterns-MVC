package com.examples.designpatterns.view;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.examples.designpatterns.model.Database;
import com.examples.designpatterns.model.Model;

public class View extends JFrame implements ActionListener {
	
	private Model model;
	
	//private JButton helloButton;
	
	//private JButton goodByeButton;
	
	private JLabel username;
	
	private JLabel password;
	
	private JTextField userNameTextField;
	
	private JPasswordField passwordTextField;
	
	private JButton submitButton;
	
	private LoginListener loginListener;
	
	
	public View(Model model) throws HeadlessException {
		super("MVC Demo");
		this.model = model;
		JPanel phonePanel = new JPanel();
		setLayout(null);
		
		username = new JLabel("UserName :");
		userNameTextField = new JTextField(15);
		password = new JLabel("Password :");
		passwordTextField = new JPasswordField(15);
		submitButton = new JButton("Submit");
		
		username.setBounds(20,150,100,30);
		userNameTextField.setBounds(150,150,150,30);
		password.setBounds(20,200,100,30);
		passwordTextField.setBounds(150,200,150,30);
        submitButton.setBounds(150,300,100,30);
	       
		/*
		 * phonePanel.add(username); phonePanel.add(userNameTextField);
		 * phonePanel.add(password); phonePanel.add(passwordTextField);
		 * phonePanel.add(submitButton);
		 */
		
		add(username);
		add(password);
		add(userNameTextField);
		add(passwordTextField);
		add(submitButton);
		setBounds(10,10,370,600);
		//phonePanel.setBounds(10,10,250,600);
		
		add(phonePanel);
		/*
		 * password.setBounds(30,50,25,2); passwordTextField.setBounds(30,50,25,2);
		 * 
		 * submitButton.setBounds(50, 60, 25, 2);
		 */
		
		
		/*
		 * username.setBounds(10, 40, 25, 2); password.setBounds(10, 40, 25, 2);
		 * userNameTextField.setBounds(10, 40, 25, 2); passwordTextField.setBounds(10,
		 * 40, 25, 2);
		 */
		
		/*
		 * setLayout(new GridBagLayout());
		 * 
		 * GridBagConstraints gc = new GridBagConstraints(); gc.anchor =
		 * GridBagConstraints.LAST_LINE_END; gc.gridx = 1; gc.gridy = 1; gc.weightx = 1;
		 * gc.weighty = 1; gc.insets = new Insets(0,0,0,5); gc.fill =
		 * GridBagConstraints.NONE; add(new Label("UserName :"), gc);
		 * 
		 * gc.anchor = GridBagConstraints.LAST_LINE_START; gc.gridx = 2; gc.gridy = 1;
		 * gc.weightx = 1; gc.weighty = 1; gc.insets = new Insets(0,0,0,0); gc.fill =
		 * GridBagConstraints.NONE; add(userNameTextField, gc);
		 * 
		 * gc.anchor = GridBagConstraints.LINE_END; gc.gridx = 1; gc.gridy = 2;
		 * gc.weightx = 1; gc.weighty = 1; gc.insets = new Insets(0,0,0,5); gc.fill =
		 * GridBagConstraints.NONE; add(new Label("Password "), gc);
		 * 
		 * gc.anchor = GridBagConstraints.LINE_START; gc.gridx = 2; gc.gridy = 2;
		 * gc.weightx = 1; gc.weighty = 1; gc.insets = new Insets(0,0,0,0); gc.fill =
		 * GridBagConstraints.NONE; add(passwordTextField, gc);
		 * 
		 * gc.anchor = GridBagConstraints.FIRST_LINE_START; gc.gridx = 2; gc.gridy = 3;
		 * gc.weightx = 1; gc.weighty = 1; gc.fill = GridBagConstraints.NONE;
		 * 
		 * 
		 * add(submitButton, gc);
		 */
		
		submitButton.addActionListener(this);
		
		/*
		 * helloButton.addActionListener(this); goodByeButton.addActionListener(this);
		 * goodByeButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * System.out.print("Sorry to see you go. "); } });
		 */
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					Database.getInstance().connect();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(View.this, "Unable to connect to Database",
							"ERROR", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Database.getInstance().disconnect();
			}

		});
		
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * JButton src = (JButton) e.getSource();
		 * 
		 * //String result = (src == helloButton) ? "Hello.. button" :
		 * "Good Bye button"; if(src == helloButton) { System.out.println("Hello.. "); }
		 * else { System.out.println("Good Bye.. "); }
		 */
		String name = userNameTextField.getText();
		String password = passwordTextField.getText();
		System.out.println("Logged in user: " + name);
		
		fireLoginEvent(new LoginFormEvent(name, password));
		
		
	}
	
	public void setLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
	}
	
	public void fireLoginEvent(LoginFormEvent event) {
		if(loginListener != null) {
			loginListener.loginPerformed(event);
		}
	}
}