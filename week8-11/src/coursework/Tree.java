package coursework;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class Tree extends JFrame {

	     Tree(Object[][] data) {
	    	 
//	    	 
	    	 String grandparentName = generateTree(data, "null");
	    	 System.out.println(grandparentName);
	    	 
    		 DefaultMutableTreeNode grandparent=new DefaultMutableTreeNode(grandparentName); 
    		 
    		 
    		 boolean childCheckerExist = true;
    		 
    		 String parentName = grandparentName;
    		 DefaultMutableTreeNode parent = grandparent;
    		 
    		 while(childCheckerExist==true) {
    			 String childName = generateTree(data, parentName);
    			 if (childName != "") {
        				 DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(childName); 
        				 parent.add(childNode); 
        				 parentName = childName;
        				 parent = childNode;
    			 }else {
    				 childCheckerExist = false;
    			 }
    		 }
    		 
    		 JTree jt=new JTree(grandparent);
	    	    this.add(jt);  
	    	    this.setSize(200,200);  
	    	    this.setVisible(true);
	    	    
	    	  
	    }
	     
	   public String generateTree(Object[][] data, String name) {
		   String stringData = "";
	      for(Object[] dataItem: data) {
	    	  if(dataItem[2].equals(name)) {
	    		  stringData = dataItem[0].toString();
	    	  }
	      }
  		  return stringData;
  		    
   	   };
	     
}
