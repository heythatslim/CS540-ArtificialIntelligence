////////////////////////////////////////////////////////////////////////////////
// Main File:        NeuralNet.java
// This File:        NeuralNet.java
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

// organize extracted data from image
class Image{
	double x1;
	double x2;
	double x3;
	double x4;
	int label;
	
	public Image(double variance, double skewness, double curtosis, double entropy, int imgClass) {
		this.x1 = variance;
		this.x2 = skewness;
		this.x3 = curtosis;
		this.x4 = entropy;
		this.label = imgClass;
	}
	
}

public class NeuralNet {
	
	// arraylist to read data from train.csv and store
    private static ArrayList<Image> readTrain(){
    	String COMMA = ",";
        BufferedReader br = null;
        ArrayList<Image> images = new ArrayList<Image>();
        try {
        	br = new BufferedReader(new FileReader("./train.csv"));
        	String line = "";
        	while((line = br.readLine()) != null) {
        		String[] info = line.split(COMMA); 
        		if(info.length > 0) {
        			Image curr = new Image(Double.parseDouble(info[0])
        					, Double.parseDouble(info[1])
        					, Double.parseDouble(info[2])
        					, Double.parseDouble(info[3])
        					, Integer.parseInt(info[4]));
        			images.add(curr);
        		}
        	}
        }
        catch (IOException ee) {
			System.out.println("BufferedReader error");
		}
        return images;
    }
    
    // arraylist to read data from eval.csv and store
    private static ArrayList<Image> readEval(){
    	String COMMA = ",";
        BufferedReader br = null;
        ArrayList<Image> images = new ArrayList<Image>();
        try {
        	br = new BufferedReader(new FileReader("./eval.csv"));
        	String line = "";
        	while((line = br.readLine()) != null) {
        		String[] info = line.split(COMMA); 
        		if(info.length > 0) {
        			Image curr = new Image(Double.parseDouble(info[0])
        					, Double.parseDouble(info[1])
        					, Double.parseDouble(info[2])
        					, Double.parseDouble(info[3])
        					, Integer.parseInt(info[4]));
        			images.add(curr);
        		}
        	}
        }
        catch (IOException ee) {
			System.out.println("BufferedReader error");
		}
        return images;
    }
    
    // arraylist to read data from test.csv and store
    private static ArrayList<Image> readTest(){
    	String COMMA = ",";
        BufferedReader br = null;
        ArrayList<Image> images = new ArrayList<Image>();
        try {
        	br = new BufferedReader(new FileReader("./test.csv"));
        	String line = "";
        	while((line = br.readLine()) != null) {
        		String[] info = line.split(COMMA); 
        		if(info.length > 0) {
        			Image curr = new Image(Double.parseDouble(info[0])
        					, Double.parseDouble(info[1])
        					, Double.parseDouble(info[2])
        					, Double.parseDouble(info[3])
        					, Integer.parseInt(info[4]));
        			images.add(curr);
        		}
        	}
        }
        catch (IOException ee) {
			System.out.println("BufferedReader error");
		}
        return images;
    }
    
    static public void main(String[] args){
    	ArrayList<Image> train = readTrain();
    	ArrayList<Image> eval = readEval();
    	ArrayList<Image> test = readTest();
    	
    	int flag = Integer.valueOf(args[0]);
    	
    	// forward propagation
    	if (flag == 100) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);

    		double x1 = Double.valueOf(args[14]);
    		double x2 = Double.valueOf(args[15]);
    		double x3 = Double.valueOf(args[16]);
    		double x4 = Double.valueOf(args[17]);
    		
    		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
    		a21 = 1 / (1 + Math.exp(-a21));
    		
    		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
    		a22 = 1 / (1 + Math.exp(-a22));
    		
    		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
    		a31 = 1 / (1 + Math.exp(-a31));
    		
    		System.out.println(String.format("%.5f", a21) + " "
    				+ String.format("%.5f", a22));
    		System.out.println(String.format("%.5f", a31));
    		
    	}
    	
    	// Back propagation
    	if (flag == 200) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);

    		double x1 = Double.valueOf(args[14]);
    		double x2 = Double.valueOf(args[15]);
    		double x3 = Double.valueOf(args[16]);
    		double x4 = Double.valueOf(args[17]);
    		double y = Double.valueOf(args[18]);
    		
    		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
    		a21 = 1 / (1 + Math.exp(-a21));
    		
    		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
    		a22 = 1 / (1 + Math.exp(-a22));
    		
    		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
    		a31 = 1 / (1 + Math.exp(-a31));
    		
    		double delta31 = (a31 - y) * a31 * (1 - a31);
    		
    		System.out.println(String.format("%.5f", delta31));
    	}
    	
    	//partial derivative of error repsect to hidden layers
    	if (flag == 300) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);

    		double x1 = Double.valueOf(args[14]);
    		double x2 = Double.valueOf(args[15]);
    		double x3 = Double.valueOf(args[16]);
    		double x4 = Double.valueOf(args[17]);
    		double y = Double.valueOf(args[18]);
    		
    		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
    		a21 = 1 / (1 + Math.exp(-a21));
    		
    		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
    		a22 = 1 / (1 + Math.exp(-a22));
    		
    		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
    		a31 = 1 / (1 + Math.exp(-a31));
    		double delta31 = (a31 - y) * a31 * (1 - a31);
    		
    		double delta21 = delta31 * w311 * a21 * (1 - a21);
    		double delta22 = delta31 * w312 * a22 * (1 - a22);
    		
    		System.out.println(String.format("%.5f", delta21) + " "
    				+ String.format("%.5f", delta22));
    	}
    	
    	// compute the gradient of error with repect to edge weights
    	if (flag == 400) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);

    		double x1 = Double.valueOf(args[14]);
    		double x2 = Double.valueOf(args[15]);
    		double x3 = Double.valueOf(args[16]);
    		double x4 = Double.valueOf(args[17]);
    		double y = Double.valueOf(args[18]);
    		
    		double a20 = 1;
    		
    		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
    		a21 = 1 / (1 + Math.exp(-a21));
    		
    		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
    		a22 = 1 / (1 + Math.exp(-a22));
    		
    		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
    		a31 = 1 / (1 + Math.exp(-a31));
    		double delta31 = (a31 - y) * a31 * (1 - a31);
    		
    		double delta21 = delta31 * w311 * a21 * (1 - a21);
    		double delta22 = delta31 * w312 * a22 * (1 - a22);
    		
    		double w310Deriv = delta31 * a20;
    		double w311Deriv = delta31 * a21;
    		double w312Deriv = delta31 * a22;
    		
    		double w210Deriv = delta21 * 1;
    		double w211Deriv = delta21 * x1;
    		double w212Deriv = delta21 * x2;
    		double w213Deriv = delta21 * x3;
    		double w214Deriv = delta21 * x4;
    		
    		double w220Deriv = delta22 * 1;
    		double w221Deriv = delta22 * x1;
    		double w222Deriv = delta22 * x2;
    		double w223Deriv = delta22 * x3;
    		double w224Deriv = delta22 * x4;
    		
    		System.out.println(String.format("%.5f", w310Deriv) + " "
    				+ String.format("%.5f", w311Deriv) + " "
    				+ String.format("%.5f", w312Deriv));
    		System.out.println(String.format("%.5f", w210Deriv) + " "
    				+ String.format("%.5f", w211Deriv) + " "
    				+ String.format("%.5f", w212Deriv) + " "
    				+ String.format("%.5f", w213Deriv) + " "
    				+ String.format("%.5f", w214Deriv));
    		System.out.println(String.format("%.5f", w220Deriv) + " "
    				+ String.format("%.5f", w221Deriv) + " "
    				+ String.format("%.5f", w222Deriv) + " "
    				+ String.format("%.5f", w223Deriv) + " "
    				+ String.format("%.5f", w224Deriv));
    	}
    	
    	// Stochastic Gradient Descent
    	if (flag == 500) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);
    		double rate = Double.valueOf(args[14]);
    		
    		for (int i = 0; i < train.size(); i++) {
    			
        		
        		double x1 = train.get(i).x1;
        		double x2 = train.get(i).x2;
        		double x3 = train.get(i).x3;
        		double x4 = train.get(i).x4;
        		double y = train.get(i).label;
        		
        		double a20 = 1;
        		
        		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
        		a21 = 1 / (1 + Math.exp(-a21));
        		
        		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
        		a22 = 1 / (1 + Math.exp(-a22));
        		
        		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
        		a31 = 1 / (1 + Math.exp(-a31));
        		double delta31 = (a31 - y) * a31 * (1 - a31);
        		
        		double delta21 = delta31 * w311 * a21 * (1 - a21);
        		double delta22 = delta31 * w312 * a22 * (1 - a22);
        		
        		double w310Deriv = delta31 * a20;
        		double w311Deriv = delta31 * a21;
        		double w312Deriv = delta31 * a22;
        		
        		double w210Deriv = delta21 * 1;
        		double w211Deriv = delta21 * x1;
        		double w212Deriv = delta21 * x2;
        		double w213Deriv = delta21 * x3;
        		double w214Deriv = delta21 * x4;
        		
        		double w220Deriv = delta22 * 1;
        		double w221Deriv = delta22 * x1;
        		double w222Deriv = delta22 * x2;
        		double w223Deriv = delta22 * x3;
        		double w224Deriv = delta22 * x4;
        		
        		w210 = w210 - rate * w210Deriv;
        		w211 = w211 - rate * w211Deriv;
        		w212 = w212 - rate * w212Deriv;
        		w213 = w213 - rate * w213Deriv;
        		w214 = w214 - rate * w214Deriv;
        		
        		w220 = w220 - rate * w220Deriv;
        		w221 = w221 - rate * w221Deriv;
        		w222 = w222 - rate * w222Deriv;
        		w223 = w223 - rate * w223Deriv;
        		w224 = w224 - rate * w224Deriv;
        		
        		w310 = w310 - rate * w310Deriv;
        		w311 = w311 - rate * w311Deriv;
        		w312 = w312 - rate * w312Deriv;
        		
        		double Eeval = 0;
        		
        		for (int j = 0; j < eval.size(); j++) {
        			double evalX1 = eval.get(j).x1;
            		double evalX2 = eval.get(j).x2;
            		double evalX3 = eval.get(j).x3;
            		double evalX4 = eval.get(j).x4;
            		double evalY = eval.get(j).label;
            		
            		double newA21 = 1 * w210 + evalX1 * w211 + evalX2 * w212 
            				+ evalX3 * w213 + evalX4 * w214;
            		newA21 = 1 / (1 + Math.exp(-newA21));
            		
            		double newA22 = 1 * w220 + evalX1 * w221 + evalX2 * w222 
            				+ evalX3 * w223 + evalX4 * w224;
            		newA22 = 1 / (1 + Math.exp(-newA22));
            		
            		double newA31 = 1 * w310 + newA21 * w311 + newA22 * w312;
            		newA31 = 1 / (1 + Math.exp(-newA31));
            		
        			Eeval += 0.5 * Math.pow((newA31 - evalY), 2);
        		}
        		
        		System.out.println(String.format("%.5f", w210) + " "
        				+ String.format("%.5f", w211) + " "
        				+ String.format("%.5f", w212) + " "
        				+ String.format("%.5f", w213) + " "
        				+ String.format("%.5f", w214) + " "
        				
        				+ String.format("%.5f", w220) + " "
        				+ String.format("%.5f", w221) + " "
        				+ String.format("%.5f", w222) + " "
        				+ String.format("%.5f", w223) + " "
        				+ String.format("%.5f", w224) + " "
        				
        				+ String.format("%.5f", w310) + " "
        				+ String.format("%.5f", w311) + " "
        				+ String.format("%.5f", w312));
        		System.out.println(String.format("%.5f", Eeval));
    		}	
    	}
    	
    	// make prediction
    	if (flag == 600) {
    		double w210 = Double.valueOf(args[1]);
    		double w211 = Double.valueOf(args[2]);
    		double w212 = Double.valueOf(args[3]);
    		double w213 = Double.valueOf(args[4]);
    		double w214 = Double.valueOf(args[5]);
    		
    		double w220 = Double.valueOf(args[6]);
    		double w221 = Double.valueOf(args[7]);
    		double w222 = Double.valueOf(args[8]);
    		double w223 = Double.valueOf(args[9]);
    		double w224 = Double.valueOf(args[10]);
    		
    		double w310 = Double.valueOf(args[11]);
    		double w311 = Double.valueOf(args[12]);
    		double w312 = Double.valueOf(args[13]);
    		double rate = Double.valueOf(args[14]);
    		
    		for (int i = 0; i < train.size(); i++) {
    			
        		
        		double x1 = train.get(i).x1;
        		double x2 = train.get(i).x2;
        		double x3 = train.get(i).x3;
        		double x4 = train.get(i).x4;
        		double y = train.get(i).label;
        		
        		double a20 = 1;
        		
        		double a21 = 1 * w210 + x1 * w211 + x2 * w212 + x3 * w213 + x4 * w214;
        		a21 = 1 / (1 + Math.exp(-a21));
        		
        		double a22 = 1 * w220 + x1 * w221 + x2 * w222 + x3 * w223 + x4 * w224;
        		a22 = 1 / (1 + Math.exp(-a22));
        		
        		double a31 = 1 * w310 + a21 * w311 + a22 * w312;
        		a31 = 1 / (1 + Math.exp(-a31));
        		double delta31 = (a31 - y) * a31 * (1 - a31);
        		
        		double delta21 = delta31 * w311 * a21 * (1 - a21);
        		double delta22 = delta31 * w312 * a22 * (1 - a22);
        		
        		double w310Deriv = delta31 * a20;
        		double w311Deriv = delta31 * a21;
        		double w312Deriv = delta31 * a22;
        		
        		double w210Deriv = delta21 * 1;
        		double w211Deriv = delta21 * x1;
        		double w212Deriv = delta21 * x2;
        		double w213Deriv = delta21 * x3;
        		double w214Deriv = delta21 * x4;
        		
        		double w220Deriv = delta22 * 1;
        		double w221Deriv = delta22 * x1;
        		double w222Deriv = delta22 * x2;
        		double w223Deriv = delta22 * x3;
        		double w224Deriv = delta22 * x4;
        		
        		w210 = w210 - rate * w210Deriv;
        		w211 = w211 - rate * w211Deriv;
        		w212 = w212 - rate * w212Deriv;
        		w213 = w213 - rate * w213Deriv;
        		w214 = w214 - rate * w214Deriv;
        		
        		w220 = w220 - rate * w220Deriv;
        		w221 = w221 - rate * w221Deriv;
        		w222 = w222 - rate * w222Deriv;
        		w223 = w223 - rate * w223Deriv;
        		w224 = w224 - rate * w224Deriv;
        		
        		w310 = w310 - rate * w310Deriv;
        		w311 = w311 - rate * w311Deriv;
        		w312 = w312 - rate * w312Deriv;
        		
    		}
    		
    		int count = 0;
    		for (int i = 0; i < test.size(); i++) {
    			
    			double testX1 = test.get(i).x1;
        		double testX2 = test.get(i).x2;
        		double testX3 = test.get(i).x3;
        		double testX4 = test.get(i).x4;
        		int testY = test.get(i).label;
        		
        		double newA21 = 1 * w210 + testX1 * w211 + testX2 * w212 
        				+ testX3 * w213 + testX4 * w214;
        		newA21 = 1 / (1 + Math.exp(-newA21));
        		
        		double newA22 = 1 * w220 + testX1 * w221 + testX2 * w222 
        				+ testX3 * w223 + testX4 * w224;
        		newA22 = 1 / (1 + Math.exp(-newA22));
        		
        		double newA31 = 1 * w310 + newA21 * w311 + newA22 * w312;
        		newA31 = 1 / (1 + Math.exp(-newA31));
        		
        		int prediction = 0;
        		if(newA31 > 0.5) {
        			prediction = 1;
        		}
        		
        		if (prediction == testY) {
        			count++;
        		}
        		
        		System.out.println(testY + " " + prediction + " "
        				+ String.format("%.5f", newA31));
    		}
    		
    		double accuracy = count / (double) test.size();
    		System.out.println(String.format("%.2f", accuracy));
    	}
    	
    }
}

