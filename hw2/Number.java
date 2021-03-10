////////////////////////////////////////////////////////////////////////////////
// Main File:        Number.java
// This File:        Number.java
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

public class Number{
           
    public static int getStep(int x, int y) {
        // TO DO
        // Implement the getStep method
        
    	// list to keep all the elements in tree
        List<Integer> tree = new ArrayList<>();
        // list to keep all the corresponding steps
        List<Integer> step = new ArrayList<>();
        
        // check inputs
        if( x < 0 || x > 100 || y < 0 || y > 100) {
        	System.out.println("wrong input");
        	System.exit(0);
        }
        
        // put initial values
        tree.add(x);
        step.add(0);
        for(;;) {
        	// get current value of tree
        	int curr = tree.get(tree.size()-1);
        	tree.remove(tree.size()-1);
        	// get corresponding value of step
        	int currStep = step.get(step.size()-1);
        	step.remove(step.size()-1);
        	
        	// return value of step when condition is met
        	if (curr == y) {
        		return currStep;
        	}	
        	
        	// add 1 to curr and create next node
        	int add = curr++;
        	if( add >= 0 && add < 100) {
        		tree.add(0,add);
        		step.add(0,currStep + 1);
        	}
        	
        	// subtract 1 from curr and create next node
        	int dec = curr--;
        	if( dec >= 0 && dec < 100) {
        		tree.add(0,dec);
        		step.add(0,currStep + 1);
        	}
        	
        	// multiply curr by 3 and create next node
        	int mult = curr * 3;
        	if( mult >= 0 && mult < 100) {
        		tree.add(0,mult);
        		step.add(0,currStep + 1);
        	}
        	
        	// square curr and create next node
        	int square = curr * curr;
        	if( square >= 0 && square < 100) {
        		tree.add(0,square);
        		step.add(0,currStep + 1);
        	}
        } 
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        
        System.out.println(getStep(Integer.parseInt(args[0]), Integer.parseInt(args[1])));

    }
}
