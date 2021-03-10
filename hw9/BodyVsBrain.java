////////////////////////////////////////////////////////////////////////////////
// Main File:        BodyVsBrain.java
// This File:        BodyVsBrain.java
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// data to organize bodyweights and brainweights
class Data{
	double body;
	double brain;
	
	public Data(double body, double brain) {
		this.body = body;
		this.brain = brain;
	}
	
	public double getBody() {
		return body;
	}
	
	public double getBrain() {
		return brain;
	}
}

public class BodyVsBrain{	
	// arraylist to read data from file and store in arraylist
    private static ArrayList<Data> readData(){
    	String COMMA = ",";
        BufferedReader br = null;
        ArrayList<Data> data = new ArrayList<Data>();
        try {
        	br = new BufferedReader(new FileReader("./data.csv"));
        	String line = "";
        	br.readLine();
        	while((line = br.readLine()) != null) {
        		String[] weights = line.split(COMMA); 
        		if(weights.length > 0) {
        			Data curr = new Data(Double.parseDouble(weights[0])
        					, Double.parseDouble(weights[1]));
        			data.add(curr);
        		}
        	}
        }
        catch (IOException ee) {
			System.out.println("BufferedReader error");
		}
        return data;
    }
    
    static public void main(String[] args){
    	
        ArrayList<Data> data = readData();
		int flag = Integer.valueOf(args[0]);
        
		// find mean and standard deviation
        if(flag == 100){
        	int numData = data.size();
        	double bodyAvg = 0;
        	double bodyStdv = 0;
        	double brainAvg = 0;
        	double brainStdv = 0;
        	
        	for (int i = 0; i < numData; i++) {
        		bodyAvg += data.get(i).getBody();
        		brainAvg += data.get(i).getBrain();
        	}
        	bodyAvg = bodyAvg / numData;
        	brainAvg = brainAvg / numData;
        	
        	for (int i = 0; i < data.size(); i++) {
        		bodyStdv += Math.pow(data.get(i).getBody() - bodyAvg, 2);
        		brainStdv += Math.pow(data.get(i).getBrain() - brainAvg, 2);
        	}
        	bodyStdv = Math.sqrt(bodyStdv / (numData -1));
        	brainStdv = Math.sqrt(brainStdv / (numData - 1));
        	
        	System.out.println(numData);
        	System.out.println(String.format("%.4f",bodyAvg) + " "
        			+ String.format("%.4f",bodyStdv));
        	System.out.println(String.format("%.4f",brainAvg) + " "
        			+ String.format("%.4f",brainStdv));
        	
        }
        // perform linear regression
        else if(flag == 200){
            double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);
            
            int numData = data.size();
            double MSE = 0;
            for (int i = 0; i < data.size(); i++) {
        		double fx = beta0 + (beta1 * data.get(i).getBody());
        		MSE += Math.pow(fx - data.get(i).getBrain(), 2);
        	}
            MSE = MSE / numData;
            System.out.println(String.format("%.4f",MSE));

        }
        // perform gradient descent on MSE
        else if(flag == 300){
        	double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);
            
            int numData = data.size();
            double gradientB0 = 0;
            double gradientB1 = 0;
            for (int i = 0; i < data.size(); i++) {
        		double fx = beta0 + (beta1 * data.get(i).getBody())
        				- data.get(i).getBrain();
        		gradientB0 += fx;
        		gradientB1 += fx * data.get(i).getBody();
        		
        	}
            gradientB0 = gradientB0 * 2 / numData;
            gradientB1 = gradientB1 * 2 / numData;
            
            System.out.println(String.format("%.4f",gradientB0));
            System.out.println(String.format("%.4f",gradientB1));

        }
        // iterating gradient descent
        else if(flag == 400){
        	double eta = Double.valueOf(args[1]);
            double time = Double.valueOf(args[2]); 
            
            int numData = data.size();
            double beta0 = 0;
            double beta1 = 0;
            
            double gradientB0 = 0;
            double gradientB1 = 0;
            
            double MSE = 0;
            
            for(int t = 1; t <= time; t++) {
            	 for (int i = 0; i < data.size(); i++) {
             		double fx = beta0 + (beta1 * data.get(i).getBody())
             				- data.get(i).getBrain();
             		gradientB0 += fx;
             		gradientB1 += fx * data.get(i).getBody();
             	}
                 gradientB0 = gradientB0 * 2 / numData;
                 gradientB1 = gradientB1 * 2 / numData;

                 beta0 = beta0 - (eta * gradientB0);
                 beta1 = beta1 - (eta * gradientB1);
                 
                 for (int i = 0; i < data.size(); i++) {
             		double fx = beta0 + (beta1 * data.get(i).getBody());
             		MSE += Math.pow(fx - data.get(i).getBrain(), 2);
             	}
                 MSE = MSE / numData;
                 
                 System.out.println(t + " " 
                 		+ String.format("%.4f",beta0) + " "
                 		+ String.format("%.4f",beta1) + " "
                 		+ String.format("%.4f",MSE));
                 MSE = 0;
                 gradientB0 = 0;
                 gradientB1 = 0;  
            }       
        }
        // compute closed-form solution
        else if(flag == 500){
        	
        	int numData = data.size();
        	double bodyAvg = 0;
        	double brainAvg = 0;
        	for (int i = 0; i < numData; i++) {
        		bodyAvg += data.get(i).getBody();
        		brainAvg += data.get(i).getBrain();
        	}
        	bodyAvg = bodyAvg / numData;
        	brainAvg = brainAvg / numData;
        	
        	double beta0 = 0;
            double beta1 = 0;
            double top = 0;
            double bot = 0;
            
            for(int i = 0; i< numData; i++) {
            	top += (data.get(i).getBody() - bodyAvg)
            			* (data.get(i).getBrain() - brainAvg);
            	bot += Math.pow((data.get(i).getBody() - bodyAvg), 2);
            }
            beta1 = top / bot;
            beta0 = brainAvg - (beta1 * bodyAvg);
            
            double MSE = 0;
            for (int i = 0; i < data.size(); i++) {
        		double fx = beta0 + (beta1 * data.get(i).getBody());
        		MSE += Math.pow(fx - data.get(i).getBrain(), 2);
        	}
            MSE = MSE / numData;
            
            System.out.println(String.format("%.4f",beta0) + " "
             		+ String.format("%.4f",beta1) + " "
             		+ String.format("%.4f",MSE));
        }
        // predict brain weight using body weight
        else if(flag == 600){
        	double givenBody = Double.valueOf(args[1]);
        	
        	int numData = data.size();
        	double bodyAvg = 0;
        	double brainAvg = 0;
        	for (int i = 0; i < numData; i++) {
        		bodyAvg += data.get(i).getBody();
        		brainAvg += data.get(i).getBrain();
        	}
        	bodyAvg = bodyAvg / numData;
        	brainAvg = brainAvg / numData;
        	
        	double beta0 = 0;
            double beta1 = 0;
            double top = 0;
            double bot = 0;
            
            for(int i = 0; i< numData; i++) {
            	top += (data.get(i).getBody() - bodyAvg)
            			* (data.get(i).getBrain() - brainAvg);
            	bot += Math.pow((data.get(i).getBody() - bodyAvg), 2);
            }
            beta1 = top / bot;
            beta0 = brainAvg - (beta1 * bodyAvg);
            
            double prediction = beta0 + beta1 * givenBody;
            System.out.println(String.format("%.4f",prediction));
            
        }
        // use normalized bodyweight for closed form
        else if(flag == 700){
        	double eta = Double.valueOf(args[1]);
            double time = Double.valueOf(args[2]); 
            
            int numData = data.size();
            
            double bodyAvg = 0;
        	double bodyStdv = 0;
        	
        	for (int i = 0; i < numData; i++) {
        		bodyAvg += data.get(i).getBody();
        	}
        	bodyAvg = bodyAvg / numData;
        	
        	for (int i = 0; i < data.size(); i++) {
        		bodyStdv += Math.pow(data.get(i).getBody() - bodyAvg, 2);
        	}
        	bodyStdv = Math.sqrt(bodyStdv / (numData -1));
        	
            double beta0 = 0;
            double beta1 = 0;
            
            double gradientB0 = 0;
            double gradientB1 = 0;
            
            double MSE = 0;
            
            for(int t = 1; t <= time; t++) {
            	 for (int i = 0; i < data.size(); i++) {
             		double fx = beta0 + (beta1 
             				* ((data.get(i).getBody()- bodyAvg) / bodyStdv))
             				- data.get(i).getBrain();
             		gradientB0 += fx;
             		gradientB1 += fx 
             				* ((data.get(i).getBody()- bodyAvg) / bodyStdv);
             	}
                 gradientB0 = gradientB0 * 2 / numData;
                 gradientB1 = gradientB1 * 2 / numData;

                 beta0 = beta0 - (eta * gradientB0);
                 beta1 = beta1 - (eta * gradientB1);
                 
                 for (int i = 0; i < data.size(); i++) {
             		double fx = beta0 + (beta1 
             				*((data.get(i).getBody()- bodyAvg) / bodyStdv));
             		MSE += Math.pow(fx - data.get(i).getBrain(), 2);
             	}
                 MSE = MSE / numData;
                 
                 System.out.println(t + " " 
                 		+ String.format("%.4f",beta0) + " "
                 		+ String.format("%.4f",beta1) + " "
                 		+ String.format("%.4f",MSE));
                 MSE = 0;
                 gradientB0 = 0;
                 gradientB1 = 0;  
            }
            
        }
        // SGD
        else if(flag == 800){
        	double eta = Double.valueOf(args[1]);
            double time = Double.valueOf(args[2]); 
            
            int numData = data.size();
            
            double bodyAvg = 0;
        	double bodyStdv = 0;
        	
        	for (int i = 0; i < numData; i++) {
        		bodyAvg += data.get(i).getBody();
        	}
        	bodyAvg = bodyAvg / numData;
        	
        	for (int i = 0; i < data.size(); i++) {
        		bodyStdv += Math.pow(data.get(i).getBody() - bodyAvg, 2);
        	}
        	bodyStdv = Math.sqrt(bodyStdv / (numData -1));
        	
            double beta0 = 0;
            double beta1 = 0;
            
            double gradientB0 = 0;
            double gradientB1 = 0;
            Random rand = new Random();
            double MSE = 0;
            
            for(int t = 1; t <= time; t++) {
            	 int randItem = rand.nextInt(numData);
            	 int curr = randItem;
            	 
                 gradientB0 = 2 * (beta0 + (beta1* ( (data.get(curr).getBody()- bodyAvg) / bodyStdv))
          				- data.get(curr).getBrain());
                 gradientB1 = 2 * (beta0 + (beta1* ( (data.get(curr).getBody()- bodyAvg) / bodyStdv))
           				- data.get(curr).getBrain()) 
                		 * ((data.get(curr).getBody()- bodyAvg) / bodyStdv);

                 beta0 = beta0 - (eta * gradientB0);
                 beta1 = beta1 - (eta * gradientB1);
                 
                 for (int i = 0; i < data.size(); i++) {
             		double fx = beta0 + (beta1 
             				*((data.get(i).getBody()- bodyAvg) / bodyStdv));
             		MSE += Math.pow(fx - data.get(i).getBrain(), 2);
             	}
                 MSE = MSE / numData;
                 
                 System.out.println(t + " " 
                 		+ String.format("%.4f",beta0) + " "
                 		+ String.format("%.4f",beta1) + " "
                 		+ String.format("%.4f",MSE));
                 MSE = 0;
                 gradientB0 = 0;
                 gradientB1 = 0;  
            }
        }

        return;
    }
}
