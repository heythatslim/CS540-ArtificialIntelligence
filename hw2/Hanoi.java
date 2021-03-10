////////////////////////////////////////////////////////////////////////////////
// Main File:        Hanoi.java
// This File:        Hanoi.java
// Other Files:      
// Semester:         CS 540 2019 Spring
//
// Author:           Sung Joon Lim
// Email:            lim62@wisc.edu
// CS Login:         sungl
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          TA
//                   
//
// Online sources:   piazza
//                   
//                   
//////////////////////////// 80 columns wide ///////////////////////////////////

import java.util.*;

public class Hanoi {
    
    public static List<String> getSuccessor(String[] hanoi) {
        // TO DOchar to int
        // Implement the getSuccessor method
        List<String> result = new ArrayList<>();
        
        // copy initial hanoi
        String[] init = new String[hanoi.length];
        for(int i=0; i < hanoi.length; i++) {
        	init[i] = hanoi[i];
        }
        
        // list of lists of current hanoi
        List<List<String>> curr = new ArrayList<>();
        for(int i = 0; i < init.length; i++) {
        	// list of each rod 
        	ArrayList<String> currRod = new ArrayList<>();
        	String[] temp = init[i].split("");
        	for (int j =0; j < init[i].length();j++) {
        		currRod.add(temp[j]);
        	}
        	curr.add(currRod);
        }
             
        // iterate through each rod
        for(int i = 0; i < curr.size(); i++) {
        	// skip if rod has nothing to move
        	if(curr.get(i).get(0).equals("0")) {
        		continue;
        	}
        	else{
        		// check rod on right when right is 0
        		if(i != curr.size()-1 
        				&& Integer.parseInt(curr.get(i+1).get(0)) == 0){
        			String currStr = curr.get(i).get(0);
        			curr.get(i).remove(0);
        			curr.get(i+1).add(0, currStr);
        			// if there was nothing("0") in the rod, remove 0
        			if(curr.get(i+1).get(1).contentEquals("0")) {
        				curr.get(i+1).remove(1);
        			}
        			// if nothing is nothing left in the rod, add 0
        			if(curr.get(i).size() == 0) {
        				curr.get(i).add("0");
        			}
        			
        			currStr = "";
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size(); k++) {
        	        		currStr += curr.get(j).get(k);
        	        	}
        	        	currStr += " ";
        	        }
        			result.add(currStr);
        			// clear List
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		curr.get(j).clear();
        	        	}
        	        	curr.clear();
        	        }
        			// initialize List
        			curr = new ArrayList<>();
        	        for(int j = 0; j < init.length; j++) {
        	        	// list of each rod 
        	        	ArrayList<String> currRod = new ArrayList<>();
        	        	String[] temp = init[j].split("");
        	        	for (int k =0; k < init[j].length();k++) {
        	        		currRod.add(temp[k]);
        	        	}
        	        	curr.add(currRod);
        	        }    
        		}
        		// check rod on right when right is not 0
        		else if(i != curr.size()-1 && Integer.parseInt(curr.get(i).get(0))
        				< Integer.parseInt(curr.get(i+1).get(0))
        				&& Integer.parseInt(curr.get(i).get(0)) != 0){
        			String currStr = curr.get(i).get(0);
        			curr.get(i).remove(0);
        			curr.get(i+1).add(0, currStr);
        			// if there was nothing("0") in the rod, remove 0
        			if(curr.get(i+1).get(1).contentEquals("0")) {
        				curr.get(i+1).remove(1);
        			}
        			// if nothing is nothing left in the rod, add 0
        			if(curr.get(i).size() == 0) {
        				curr.get(i).add("0");
        			}
        			
        			currStr = "";
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size(); k++) {
        	        		currStr += curr.get(j).get(k);
        	        	}
        	        	currStr += " ";
        	        }
        			result.add(currStr);
        			// clear List
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		curr.get(j).clear();
        	        	}
        	        	curr.clear();
        	        }
        			// initialize List
        			curr = new ArrayList<>();
        	        for(int j = 0; j < init.length; j++) {
        	        	// list of each rod 
        	        	ArrayList<String> currRod = new ArrayList<>();
        	        	String[] temp = init[j].split("");
        	        	for (int k =0; k < init[j].length();k++) {
        	        		currRod.add(temp[k]);
        	        	}
        	        	curr.add(currRod);
        	        }    
        	        
        		}
        		
        		// check rod on left when left is 0
        		if(i != 0 && Integer.parseInt(curr.get(i-1).get(0)) == 0){
        			String currStr = curr.get(i).get(0);
        			curr.get(i).remove(0);
        			curr.get(i-1).add(0, currStr);
        			// if there was nothing("0") in the rod, remove 0
        			if(curr.get(i-1).get(1).contentEquals("0")) {
        				curr.get(i-1).remove(1);
        			}
        			// if nothing is nothing left in the rod, add 0
        			if(curr.get(i).size() == 0) {
        				curr.get(i).add("0");
        			}
        			
        			currStr = "";
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		currStr += curr.get(j).get(k);
        	        	}
        	        	currStr += " ";
        	        }

        			result.add(currStr);
        			// clear List
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		curr.get(j).clear();
        	        	}
        	        	curr.clear();
        	        }
        			// initialize List
        			curr = new ArrayList<>();
        	        for(int j = 0; j < init.length; j++) {
        	        	// list of each rod 
        	        	ArrayList<String> currRod = new ArrayList<>();
        	        	String[] temp = init[j].split("");
        	        	for (int k =0; k < init[j].length();k++) {
        	        		currRod.add(temp[k]);
        	        	}
        	        	curr.add(currRod);
        	        } 
        		}
        		// check rod on left when left is not 0
        		else if(i != 0 && Integer.parseInt(curr.get(i).get(0))
        				< Integer.parseInt(curr.get(i-1).get(0))
        				&& Integer.parseInt(curr.get(i).get(0)) != 0){
        			String currStr = curr.get(i).get(0);
        			curr.get(i).remove(0);
        			curr.get(i-1).add(0, currStr);
        			// if there was nothing("0") in the rod, remove 0
        			if(curr.get(i-1).get(1).contentEquals("0")) {
        				curr.get(i-1).remove(1);
        			}
        			// if nothing is nothing left in the rod, add 0
        			if(curr.get(i).size() == 0) {
        				curr.get(i).add("0");
        			}
        			
        			currStr = "";
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		currStr += curr.get(j).get(k);
        	        	}
        	        	currStr += " ";
        	        }

        			result.add(currStr);
        			// clear List
        			for(int j=0;j < curr.size();j++) {
        	        	for(int k=0; k < curr.get(j).size();k++) {
        	        		curr.get(j).clear();
        	        	}
        	        	curr.clear();
        	        }
        			// initialize List
        			curr = new ArrayList<>();
        	        for(int j = 0; j < init.length; j++) {
        	        	// list of each rod 
        	        	ArrayList<String> currRod = new ArrayList<>();
        	        	String[] temp = init[j].split("");
        	        	for (int k =0; k < init[j].length();k++) {
        	        		currRod.add(temp[k]);
        	        	}
        	        	curr.add(currRod);
        	        } 
        		}
        	}
        }
        return result;    
    }

    public static void main(String[] args) {
        if (args.length < 3) {
	        return;
	    }
        
        List<String> sucessors = getSuccessor(args);
        for (int i = 0; i < sucessors.size(); i++) {
            System.out.println(sucessors.get(i));
        }    
    }
 
}
