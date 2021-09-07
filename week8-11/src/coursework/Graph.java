package coursework;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph {

		int vertices;
	    int adjacency_matrix[][];
	    
	    Graph(int v){
	        vertices=v;
	        
	        adjacency_matrix=new int[vertices][vertices];
	    }
	    
	    
	    void addEdge(int source,int destination) {
	        
	        adjacency_matrix[source][destination]=1;
//	        adjacency_matrix[destination][source]=1;
	        
	    }
	    
	    public void printGraph() {
	        
	        System.out.println("Graph is");
	        for(int i=0;i<vertices;i++) {
	            
	          for(int j=0;j<vertices;j++) {
	              
	              System.out.print(adjacency_matrix[i][j]+" ");
	          }
	          System.out.println("");
	        }
	    }
	    
	    
	    public void printEdges() {
	        
	        for(int i=0;i<vertices;i++) {
	            
	            System.out.print("vertex "+i+ " is connected to ");
	            
	           for(int j=0;j<vertices;j++) {
	              
	               if(adjacency_matrix[i][j]>0) {
	                   System.out.print(j+" ");
	               }
	               
	               
	           }
	           System.out.println("");
	        }
	    }
	    
	    public ArrayList<Integer> getEdgeOf(int index) {
	        ArrayList<Integer> edges = new ArrayList<Integer>();
	        
	        for(int i=0;i<vertices;i++) {
	            if (i==index) {
	                for(int j=0;j<vertices;j++) {
	                   if(adjacency_matrix[i][j]>0) {
	                       edges.add(j);
	                   } 
	               }
	                break;
	                
	            }
	        }
	        return edges;
	    }
		
	}

