package coursework;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class Home {
	
	Home(){
				
		JFrame frame = new JFrame();
		
		JLabel title, namelbl, agelbl, assistorlbl, showlbl, tablelabel;
		JTextField nameinp, ageinp;
		JComboBox assistorinp, typecombo;
		JButton addBtn, showBtn, updateBtn, editBtn, deleteBtn, logoutBtn;
		JTable main;
		
		ArrayList<String[]> memberList = new ArrayList<String[]>();
        try {
            File myObj = new File("members.txt");
            Scanner myReader = new Scanner(myObj);              
            
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(",");
                memberList.add(arr);
            }
            
            myReader.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
		ArrayList<String> models = new ArrayList<String>();
		
		for (String[] item: memberList) {
		    models.add(item[0]);
		}
		
		String items[] = new String[models.size()];
		
		for (int i=0; i<models.size(); i++) {
		    items[i] = models.get(i);
		}

		
		title = new JLabel("Family Tree");
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBounds(300, 10, 300, 40);
		
		tablelabel = new JLabel("List of Members");
		tablelabel.setBounds(50, 60, 200, 30);
		String column[] = {"Name", "Age", "Assistor"};
		ArrayList<String []> file_data = new ArrayList<String []>();
        try {
            File obj = new File("members.txt");
            obj.createNewFile();
            Scanner scan = new Scanner(obj);              
            
            while(scan.hasNextLine()) {
                String data = scan.nextLine();
                String arr[] = data.split(",");
                file_data.add(arr);
            }
            
            scan.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Object[][] data = new Object[file_data.size()][column.length];
        
        for (int i=0; i<file_data.size(); i++) {
            data[i][0] = file_data.get(i)[0];
            data[i][1] = file_data.get(i)[1];
            data[i][2] = file_data.get(i)[2];
        }
	
       
		main = new JTable(data, column);
		JScrollPane sp = new JScrollPane(main);
		sp.setBounds(50, 100, 400, 300);
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(50, 450, 100, 30);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(170, 450, 100, 30);
		
		showBtn = new JButton("Show");
		showBtn.setBounds(280, 450, 100, 30);
		showBtn.addActionListener(e -> {
			new Tree(data);
		});
		
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(390, 450, 100, 30);
		logoutBtn.addActionListener(e -> {
			frame.dispose();
			new Login();
		});
		
		namelbl = new JLabel("Name");
		namelbl.setBounds(480, 60, 100, 30);
		nameinp = new JTextField();
		nameinp.setBounds(480,  90, 200, 30);
		
		agelbl = new JLabel("Age");
		agelbl.setBounds(480, 120, 100, 30);
		ageinp = new JTextField();
		ageinp.setBounds(480,  150, 200, 30);
		
		assistorlbl = new JLabel("Assistor");
		assistorlbl.setBounds(480, 180, 100, 30);
		assistorinp = new JComboBox(items);
		assistorinp.setBounds(480,  210, 200, 30);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(480, 250, 200, 30);
		updateBtn.setEnabled(false);
		
		Graph graphObj = new Graph(20);
		File connFileObj = new File("connection.txt");
		
		addBtn = new JButton("Add");
		addBtn.setBounds(480, 290, 200, 30);
		addBtn.addActionListener(e -> {
			String name = nameinp.getText();
			String age = ageinp.getText();
			String assistor = assistorinp.getSelectedItem().toString();
			if(name.length()==0 || age.length() ==0 || assistor.length()==0 ) {
				JOptionPane.showMessageDialog(frame, "All fields are required");
			}
			else {
				boolean result = addMember(name, age, assistor);
				if (result == true) {
					JOptionPane.showMessageDialog(frame, "Member added successfully");
					frame.dispose();
					new Home();
				}
				else {
				JOptionPane.showMessageDialog(frame, "Member not added");
				}
			}
		});
		
		editBtn.addActionListener(e -> {
			
			TableModel model = main.getModel();
			int row = main.getSelectedRow();
			
			if(row >= 0) {
				String name = (String) model.getValueAt(row, 0);
				String age = (String) model.getValueAt(row, 1);
				String assistor = (String) model.getValueAt(row, 2);
				String write = "";
				
				nameinp.setText(name);
				nameinp.setEnabled(false);
				ageinp.setText(age);
				addBtn.setEnabled(false);
				ageinp.setText(age);
				addBtn.setEnabled(false);
				updateBtn.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(frame, "please select row to update");
			}
				
		});		
		
		updateBtn.addActionListener(e -> {
						
			String name = nameinp.getText();
			String age = ageinp.getText();
			String assistor = assistorinp.getSelectedItem().toString();
			String write = "";
			
			for(Object[] item: data) {
                for(int i=0; i<item.length; i++) {
                    if (item[0].equals(name)) {
                        item[1] = age;
                        item[2] = assistor;
                    }
                }
            }
                                
            for (int i=0; i< data.length; i++) {
                int count=0;
                for (int j=0; j<data[i].length-1; j++) {
                    write += data[i][j] + ",";
                    count++;
                }
                write += data[i][count] + "\n";
            }
            
            try {
                                        
                FileWriter myWriter = new FileWriter("members.txt");
                myWriter.write(write);
                myWriter.close();
                JOptionPane.showMessageDialog(sp, "Member updated");
                frame.dispose();
                new Home();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		});
		
		deleteBtn.addActionListener(e -> {
            int row = main.getSelectedRow();
            
            if (row>=0) {
                TableModel model = main.getModel();
                String product_id = (String) model.getValueAt(row, 0);
                String data_to_write = "";
                for (Object[]item:data) {
                    if(!item[0].equals(product_id)) {
                        data_to_write += item[0] + "," + item[1] + "," + item[2] + "\n";
                    }                   
                }
                try {
                    FileWriter myWriter = new FileWriter("members.txt");
                    myWriter.write(data_to_write);
                    myWriter.close();
                    JOptionPane.showMessageDialog(sp, "Member deleted");
                    frame.dispose();
                    new Home();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                
            } else {
                JOptionPane.showMessageDialog(sp, "select row to delete");
            }
            
        });        
    
        
        int [][] matrix = graphObj.adjacency_matrix;
		
		ArrayList<String> all_members = new ArrayList<String>();
        
        for(int i=0; i<memberList.size(); i++) {
            all_members.add(memberList.get(i)[0]);
        }
		
		ArrayList<String[]> relation = new ArrayList<String[]>();
        
        for (int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if (matrix[i][j]>0) {
                    String[] detail = {all_members.get(i), all_members.get(j)};
                    relation.add(detail);
                }
            }
        }
        

		frame.add(title);

		frame.add(namelbl);
		frame.add(nameinp);
		frame.add(editBtn);
		frame.add(deleteBtn);
		frame.add(ageinp);
		frame.add(agelbl);
		frame.add(addBtn);
		frame.add(tablelabel);
		frame.add(sp);
		frame.add(logoutBtn);
		frame.add(updateBtn);
		frame.add(showBtn);
		frame.add(assistorinp);
		frame.add(assistorlbl);
		
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
	public boolean addMember(String name, String age, String assistor) {
			FileWriter fw;
			try {
				fw = new FileWriter("members.txt", true);
				fw.write(name+","+age+","+assistor+"\n");
				
				fw.close();
				return true;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
	}
	
    public int indexOf(String[] arr, String data) {
        int index = -1;
        for(int i=0; i<arr.length; i++) {
            if (arr[i].equals(data)) {
                index = i;
                break;
            }
        }
        return index;
    }
	
}

