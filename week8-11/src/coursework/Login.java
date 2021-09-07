package coursework;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class Login {
	
	Login(){
		
		JTextField namefield, passwordfield;
		JLabel titlelabel, namelabel, passwordlabel, orlabel;
		JButton loginBtn, registerBtn;
		
		JFrame frame = new JFrame();
		
		titlelabel = new JLabel("Login Here");
		titlelabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		titlelabel.setBounds(200, 150, 200, 40);
		
		namelabel = new JLabel("Username");
		namelabel.setBounds(200, 200, 200, 30);

		namefield = new JTextField();
		namefield.setBounds(320, 200, 250, 30);
		
		// password
		passwordlabel = new JLabel("Password");
		passwordlabel.setBounds(200, 250, 200, 30);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(320, 250, 250, 30);
		
		// login button
		loginBtn = new JButton("Log In");
		loginBtn.setBounds(320, 300, 250, 30);
		loginBtn.addActionListener((e) -> {
			
			String name = namefield.getText();
			String password = passwordfield.getText();
			String user_data = name+","+password;
			
			if(name.length()==0 && password.length()==0) {
				JOptionPane.showMessageDialog(frame, "Input fields can't be empty");
			}
			else {
				File fileObj = new File("users.txt");
				boolean isLogged = false;
				try {
					
					Scanner scObj = new Scanner(fileObj);
					while (scObj.hasNextLine()) {
						String data = scObj.nextLine();
//						System.out.println(data);
						if (data.equals(user_data)) {
							frame.dispose();
							isLogged= true;
							new Home();
						} 
					}
					if (!isLogged){
						JOptionPane.showMessageDialog(frame, "Can't login with provided cretindials");
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		orlabel = new JLabel("OR");
		orlabel.setBounds(430,  320, 100, 50);
		
		registerBtn = new JButton("Sign Up");
		registerBtn.setBounds(320, 360, 250, 30);
		registerBtn.addActionListener((e) -> {
			
			String name = namefield.getText();
			String password = passwordfield.getText();
			
			if(name.length() !=0 && password.length() != 0) {
				FileWriter fw;
				try {
					fw = new FileWriter("users.txt", true);
					fw.write(name+","+password+"\n");
					fw.close();
					JOptionPane.showMessageDialog(frame, "User added successfully");				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "All fields are required");
			}
		});

		
		frame.add(titlelabel);
		frame.add(namelabel);
		frame.add(namefield);
		frame.add(passwordlabel);
		frame.add(passwordfield);
		frame.add(loginBtn);
		frame.add(orlabel);	
		frame.add(registerBtn);
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
}
