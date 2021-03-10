////////////////////////////////////////////////////////////////////////////////
// Main File:        WaterJug.java
// This File:        WaterJug.java
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
/*
 * Constructs and operations for State which holds data of node
 * 
 */
class State {
    int cap_jug1;
    int cap_jug2;
    int curr_jug1;
    int curr_jug2;
    int goal;
    int depth;
    State parentPt;

    public State(int cap_jug1, int cap_jug2, int curr_jug1, int curr_jug2, int goal, int depth) {
        this.cap_jug1 = cap_jug1;
        this.cap_jug2 = cap_jug2;
        this.curr_jug1 = curr_jug1;
        this.curr_jug2 = curr_jug2;
        this.goal = goal;
        this.depth = depth;
        this.parentPt = null;
    }
/*
 * add possible successors to List and return it
 * 
 * 
 * @return - List of successors
 */
    public List<State> getSuccessors() {

        // TO DO: get all successors and return them in proper order
    	List<State> successors = new ArrayList<>();
    	State parent = new State(this.cap_jug1, this.cap_jug2, this.curr_jug1
    			, this.curr_jug2, this.goal, this.depth);

    	// empty jug1 if it's not 0 and add to List
    	if(parent.curr_jug1 > 0) {
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		node.curr_jug1 = 0;
    		successors.add(node);
    	}
    	
    	// pour from jug1 to jug2 and add to List
    	if(parent.curr_jug1 > 0 && parent.curr_jug2 < parent.cap_jug2){
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		if(node.curr_jug1 + node.curr_jug2 <= node.cap_jug2) {
    			node.curr_jug2 = node.curr_jug1 + node.curr_jug2;
    			node.curr_jug1 = 0;
        		successors.add(node);
    		}
    		else {
    			node.curr_jug1 = node.curr_jug1 + node.curr_jug2 - node.cap_jug2; 
    			node.curr_jug2 = node.cap_jug2;
        		successors.add(node);
    		}
    	}
    	
    	// empty jug2 if it's not 0 and add to List
    	if(parent.curr_jug2 > 0) {
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		node.curr_jug2 = 0;
    		successors.add(node);
    	}
    	
    	// fill jug2 if it's less than full capacity and add to List
    	if(parent.curr_jug2 < parent.cap_jug2) {
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		node.curr_jug2 = node.cap_jug2;
    		successors.add(node);
    	}
    	
    	// pour from jug2 to jug1 and add to List
    	if(parent.curr_jug2 > 0 && parent.curr_jug1 < parent.cap_jug1){
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		if(node.curr_jug1 + node.curr_jug2 <= node.cap_jug1) {
    			node.curr_jug1 = node.curr_jug1 + node.curr_jug2;
    			node.curr_jug2 = 0;
    			successors.add(node);
    		}
    		else {
    			node.curr_jug2 = node.curr_jug1 + node.curr_jug2 - node.cap_jug1;
    			node.curr_jug1 = node.cap_jug1; 
    			successors.add(node);
    		}
    	}
    	
    	// fill jug1 if it's less than full capacity and add to List
    	if(parent.curr_jug1 < parent.cap_jug1) {
    		State node = new State(parent.cap_jug1, parent.cap_jug2, parent.curr_jug1
        			, parent.curr_jug2, parent.goal, parent.depth);
    		node.depth = this.depth + 1;
    		node.parentPt = this;
    		node.curr_jug1 = node.cap_jug1;
    		successors.add(node);
    	}
    	
        return successors;
    }
/*
 * determine if the state is a goal node or not
 * 
 * @return - boolean isGoal
 */
    public boolean isGoalState() {

        // TO DO: determine if the state is a goal node or not and return boolean
    	boolean isGoal = false;
    	if(this.curr_jug1 == this.goal || this.curr_jug2 == this.goal) {
    		isGoal = true;
    	}
        return isGoal;
    }
/*
 * print a State based on given option (flag)
 * 
 * @param - flag and depth
 */
    public void printState(int option, int depth) {

        // TO DO: print a State based on option (flag)
    	if (option == 1) {
    		List<State> successors = new ArrayList<>();
    		successors = getSuccessors();
    		for (int i = 0; i < successors.size(); i++) {
    			State temp = successors.get(i);
    			System.out.println(temp.getOrderedPair());
    		}
    	}
    	else if(option == 2) {
    		List<State> successors = new ArrayList<>();
    		successors = getSuccessors();
    		for (int i = 0; i < successors.size(); i++) {
    			State temp = successors.get(i);
    			System.out.print(temp.getOrderedPair() + " " 
    			+ temp.isGoalState() + "\n");
    		}
    	}
    	else if (option == 3) {
    		UninformedSearch.bfs(this);
    	}
    	else if (option == 4) {
    		UninformedSearch.dfs(this);
    	}
    	else if (option == 5) {
    		UninformedSearch.iddfs(this, depth);
    	}
    	

    }
/*
 * constructs string of current jugs
 * 
 * @return - string of currents jugs
 */
    public String getOrderedPair() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.curr_jug1);
        builder.append(this.curr_jug2);
        return builder.toString().trim();
    }

    // TO DO: feel free to add/remove/modify methods/fields

}
/*
 * holds bfs, dfs, iddfs to choose from depending on inputed flag
 */
class UninformedSearch {
    public static void bfs(State curr_state) {
        // TO DO: run breadth-first search algorithm
    	// list for open and close nodes
    	List <State> open = new ArrayList<>();
    	List <State> close = new ArrayList<>();
    	
    	// put initial state in open list
    	open.add(curr_state);
    	System.out.println(curr_state.getOrderedPair());
    	
    	// iterate until goal
    	for(;;) {
    		// current node, the firstly inputed node in open List
    		State curr = open.get(open.size() - 1);
    		open.remove(open.size()-1);
    		// return when current state contains goal
    		if(curr.isGoalState()) {
    			System.out.println(curr.getOrderedPair() + " Goal");
    			System.out.print("Path ");
    			String path = " ";
    			while (curr.parentPt != null){
    				path = curr.getOrderedPair() + " " + path; 
    				curr = curr.parentPt;
    			}
    			System.out.println(curr.getOrderedPair() +" "
    					+ path.substring(0,path.length()-1));
    			return;
    		}
    		else {
    			System.out.print(curr.getOrderedPair()+ " ");
    			// list of possible successors
    			List<State> successors = new ArrayList<>();
        		successors = curr.getSuccessors();
        		// iterate through list of successors
        		for (int i = 0; i < successors.size(); i++) {
        			State currSuccess = successors.get(i);
        			boolean isOpen = false, isClose = false;
        			// if very first state
        			if (close.size() == 0) {
        				open.add(0,currSuccess);
        			}
        			else {
        				// check if redundant
        				for(int j = 0; j < open.size(); j++) {
        					State currOpen = open.get(j);
        					if(currOpen.getOrderedPair().equals(
        							currSuccess.getOrderedPair())) {
        						isOpen = true;
        					}
        				}
        				// check if not redundant
        				for(int j = 0; j < close.size(); j++) {
        					State currClose = close.get(j);
        					if(currClose.getOrderedPair().equals(
        							currSuccess.getOrderedPair())) {
        						isClose = true;
        					}
        				}
        				// add to open list if not redundant
        				if (isOpen == false && isClose == false) {
        					open.add(0, currSuccess);
        				}
        			}
        		}
        		// add to close list
        		close.add(0, curr);
        		
        		// print as formated
        		String openStr = "[";
        		for(int j = open.size() - 1; j >= 0; j--) {
					openStr += open.get(j).getOrderedPair() + ",";
				}
        		openStr = openStr.substring(0, openStr.length()-1);
        		System.out.print(openStr + "] ");
        		String closeStr = "[";
        		for(int j = close.size() - 1; j >= 0; j--) {
					closeStr += close.get(j).getOrderedPair() + ",";
				}
        		closeStr = closeStr.substring(0, closeStr.length()-1);
        		System.out.println(closeStr + "]");
        		
    		}
    		
    	}	
    }

    public static void dfs(State curr_state) {
        // TO DO: run depth-first search algorithm
    	// list for open and close nodes
    	List <State> open = new ArrayList<>();
    	List <State> close = new ArrayList<>();
    	
    	// put initial state in open list
    	open.add(curr_state);
    	System.out.println(curr_state.getOrderedPair());
    	// iterate until goal
    	for(;;) {
    		// current node, the lastly inputed node in open List
    		State curr = open.get(0);
    		open.remove(0);
    		// return when current state contains goal
    		if(curr.isGoalState()) {
    			System.out.println(curr.getOrderedPair() + " Goal");
    			System.out.print("Path ");
    			String path = " ";
    			while (curr.parentPt != null){
    				path = curr.getOrderedPair() + " " + path; 
    				curr = curr.parentPt;
    			}
    			System.out.println(curr.getOrderedPair() +" "
    					+ path.substring(0,path.length()-1));
    			return;
    		}
    		else {
    			System.out.print(curr.getOrderedPair()+ " ");
    			// list of possible successors
    			List<State> successors = new ArrayList<>();
        		successors = curr.getSuccessors();
        		// iterate through list of successors
        		for (int i = 0; i < successors.size(); i++) {
        			State currSuccess = successors.get(i);
        			boolean isOpen = false, isClose = false;
        			// if very first state
        			if (close.size() == 0) {
        				open.add(0,currSuccess);
        			}
        			else {
        				// check if not redundant
        				for(int j = 0; j < open.size(); j++) {
        					State currOpen = open.get(j);
        					if(currOpen.getOrderedPair().equals(
        							currSuccess.getOrderedPair())) {
        						isOpen = true;
        					}
        				}
        				// check if not redundant
        				for(int j = 0; j < close.size(); j++) {
        					State currClose = close.get(j);
        					if(currClose.getOrderedPair().equals(
        							currSuccess.getOrderedPair())) {
        						isClose = true;
        					}
        				}
        				// add to open list if not redundant
        				if (isOpen == false && isClose == false) {
        					open.add(0, currSuccess);
        				}
        			}
        		}
        		// add to close list
        		close.add(0, curr);
        		// print as formated
        		String openStr = "[";
        		for(int j = open.size() - 1; j >= 0; j--) {
					openStr += open.get(j).getOrderedPair() + ",";
				}
        		openStr = openStr.substring(0, openStr.length()-1);
        		System.out.print(openStr + "] ");
        		String closeStr = "[";
        		for(int j = close.size() - 1; j >= 0; j--) {
					closeStr += close.get(j).getOrderedPair() + ",";
				}
        		closeStr = closeStr.substring(0, closeStr.length()-1);
        		System.out.println(closeStr + "]");
    		}		
    	}
    	
    }

    public static void iddfs(State curr_state, int depth) {
        // TO DO: run IDDFS search algorithm
    	// counter for depth
    	int currDepth = 0;
    	// iterate through tree on each depth
    	for(currDepth = 0; currDepth <= depth; currDepth++) {
    		// list for open and close nodes
    		List <State> open = new ArrayList<>();
        	List <State> close = new ArrayList<>();
        	// initialize state in open list
        	State curr = curr_state;
        	open.add(curr);
        	System.out.println(currDepth +":" + curr.getOrderedPair());
        	
        	// iterate until no more open nodes are available
        	while(open.size() != 0) {
        		// current node, the lastly inputed node in open List
        		curr = open.get(0);
        		open.remove(0);
        		// check if depth of current state is less than current depth
        		if (curr.depth <= currDepth) {
        			// return when current state contains goal
        			if(curr.isGoalState()) {
            			System.out.println(curr.getOrderedPair() + " Goal");
            			System.out.print("Path ");
            			String path = " ";
            			while (curr.parentPt != null){
            				path = curr.getOrderedPair() + " " + path; 
            				curr = curr.parentPt;
            			}
            			System.out.println(curr.getOrderedPair() +" "
            					+ path.substring(0,path.length()-1));
            			return;
            		}
        			else {
        				// add current state to close list
        				close.add(0, curr);
        				// list of possible successors
                		List<State> successors = new ArrayList<>();
                		successors = curr.getSuccessors();
                		// iterate through list of successors
                		for (int i = 0; i < successors.size(); i++) {
                			State currSuccess = successors.get(i);
                			boolean isOpen = false, isClose = false;
                			// if very first state
                			if (close.size() == 0) {
                				if (currSuccess.depth <= currDepth) {
                					open.add(0,currSuccess);
                				}
                			}
                			else {
                				// check if not redundant
                				for(int j = 0; j < open.size(); j++) {
                					State currOpen = open.get(j);
                					if(currOpen.getOrderedPair().equals(
                							currSuccess.getOrderedPair())) {
                						isOpen = true;
                					}
                				}
                				// check if not redundant
                				for(int j = 0; j < close.size(); j++) {
                					State currClose = close.get(j);
                					if(currClose.getOrderedPair().equals(
                							currSuccess.getOrderedPair())) {
                						isClose = true;
                					}
                				}
                				// add to open list if not redundant
                				if (isOpen == false && isClose == false) {
                					if(currSuccess.depth <= currDepth) {
                    					open.add(0, currSuccess);
                					}
                				}
                			}
                		}
        			}
            		
        		}
        		// print as formated
        		System.out.print(currDepth + ":" + curr.getOrderedPair() + " ");
        		String openStr = "";
        		if(open.size()!= 0) {
        			for(int j = open.size() - 1; j >= 0; j--) {
    					openStr += open.get(j).getOrderedPair() + ",";
    				}
        			openStr = openStr.substring(0, openStr.length()-1);
        		}
        		System.out.print("[" + openStr + "] ");
        		String closeStr = "[";
        		for(int j = close.size() - 1; j >= 0; j--) {
					closeStr += close.get(j).getOrderedPair() + ",";
				}
        		closeStr = closeStr.substring(0, closeStr.length()-1);
        		System.out.println(closeStr + "]");
        	}
        	
    	}	
    }

    public static void run(State curr_state, int option, int depth) {
        // TO DO: run either bfs, dfs or iddfs according to option (flag)
    	
    }
}

public class WaterJug {
    public static void main(String args[]) {
        if (args.length != 6) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        int flag = Integer.valueOf(args[0]);
        int cap_jug1 = Integer.valueOf(args[1]);
        int cap_jug2 = Integer.valueOf(args[2]);
        int curr_jug1 = Integer.valueOf(args[3]);
        int curr_jug2 = Integer.valueOf(args[4]);
        int goal = Integer.valueOf(args[5]);

        int option = flag / 100;
        int depth = flag % 100;

        if (option < 1 || option > 5) {
            System.out.println("Invalid flag input");
            return;
        }
        if (cap_jug1 > 9 || cap_jug2 > 9 || curr_jug1 > 9 || curr_jug2 > 9) {
            System.out.println("Invalid input: 2-digit jug volumes");
            return;
        }
        if (cap_jug1 < 0 || cap_jug2 < 0 || curr_jug1 < 0 || curr_jug2 < 0) {
            System.out.println("Invalid input: negative jug volumes");
            return;
        }
        if (cap_jug1 < curr_jug1 || cap_jug2 < curr_jug2) {
            System.out.println("Invalid input: jug volume exceeds its capacity");
            return;
        }
        State init = new State(cap_jug1, cap_jug2, curr_jug1, curr_jug2, goal, 0);
        init.printState(option, depth);
    }
}
