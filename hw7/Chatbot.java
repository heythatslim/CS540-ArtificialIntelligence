////////////////////////////////////////////////////////////////////////////////
// Main File:        Chatbot.java
// This File:        Chatbot.java
// Other Files:      ChatbotDriver.java
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
import java.io.*;

public class Chatbot{
    private static String filename = "./corpus.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    /*
     * range to get index, min, and max
     */
    static class range{
    	int index;
    	double min;
    	double max;
    	public range(int index, double min, double max, int n) {
    		this.index = index;
    		this.min = min;
    		this.max = max;
    	}
    } 
    /*
     * get range to check if the values fit
     */
    static range getRange(int[] countes, double r){
    	int count = 0;
    	double min = 0;
    	double max = 0;
    	double sum = 0;
    	for (int i = 0; i < countes.length; i++){
    		count += countes[i];
    	}
    	for (int i = 0; i < countes.length; i++){
    		min = (double) sum;
    		max =  sum + (countes[i] + 1) / (double) (count + 4700);
    		sum = max;
   
    		if (r == 0){
    			if ((min <= r) && (max >= r)){
    				return new range(i, min, max, count);
    			}
    		}
    		else{
    			if((min < r) && (max >= r)){
    				return new range(i, min, max, count);
    			}
    		}
    	}
    	return null;
    } 
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            //TODO count occurence of w
            // create unigram language model 
            for(int i = 0; i < corpus.size(); i++) {
            	if(corpus.get(i) == w) {
            		count++;
            	}
            }
            double prob = (count + 1) / (double)(corpus.size() + 4700);
            System.out.println(count);
            System.out.println(String.format("%.7f", prob));
            
        }
        // generate random word type based on unigram probability
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //TODO generate
            double r = (double) n1/n2;
            double sum = 0;
            double prob = 0;
            int count = 0;
            for (int i = 0; sum < r; i++) {
            	int curr = 0;
            	for (int j = 0;j < corpus.size(); j++) {
            		if (corpus.get(j) == i) {
            			curr++;
            		}
            	}
            	prob = (curr + 1) / (double) (corpus.size() + 4700);
            	sum += prob;
            	count ++;
            }
            double min = sum - prob;
            double max = sum;
            System.out.println(count - 1);
            System.out.println(String.format("%.7f", min));
            System.out.println(String.format("%.7f", max)); 

        }
        // bigram language model
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            for (int i = 0;i < corpus.size()-1;i++) {
            	if (corpus.get(i) == h) {
            		words_after_h.add(h+1);
            		if (corpus.get(i+1) == w) {
            			count++;
            		}
            	}
            }
            double prob = (count + 1) / (double) (words_after_h.size() + 4700); 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f", prob));
        }
        // bigram language model with geneated random words type
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            //TODO
            double r = n1/(double)n2;
            
            int[] countes = new int[4700];
            for (int i =0; i < corpus.size() - 1; i++) {
            	if (corpus.get(i) == h) {
            		countes[corpus.get(i+1)]++;
            	}
            }
            range range = getRange(countes, r);
            
            System.out.println(range.index);
            System.out.println(String.format("%.7f", range.min));
            System.out.println(String.format("%.7f", range.max));     
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO
            // trigram language model
            for(int i = 0; i < corpus.size()-2; i++) {
            	if(corpus.get(i) == h1 && corpus.get(i+1) == h2) {
            		words_after_h1h2.add(corpus.get(i+2));
            		if (corpus.get(i+2) == w) {
            			count++;
            		}
            	}
            }
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            double prob = (count + 1) / (double) (words_after_h1h2.size() + 4700); 
            System.out.println(String.format("%.7f", prob));
			
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            // trigram language model with probability
            double r = n1/(double)n2;
            int[] countes = new int[4700];
            for (int i = 0; i < corpus.size() - 2 ; i++) {
            	if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
            		countes[corpus.get(i+2)]++;
            	}
            }
            range range = getRange(countes, r);
            
            System.out.println(range.index);
            System.out.println(String.format("%.7f", range.min));
            System.out.println(String.format("%.7f", range.max));
            
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                int[] countes = new int[4700];
                for (int i = 0; i < corpus.size(); i++) {
                	countes[corpus.get(i)]++;
                }
                range range = getRange(countes, r);
                h1 = range.index;
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                int[] countesB = new int[4700];
                for (int i =0; i < corpus.size() - 1; i++) {
                	if (corpus.get(i) == h1) {
                		countesB[corpus.get(i+1)]++;
                	}
                }
                range = getRange(countesB, r);
                h2 = range.index;
                
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                int[] countes = new int[4700];
                for (int i =0; i < corpus.size() - 1; i++) {
                	if (corpus.get(i) == h1) {
                		countes[corpus.get(i+1)]++;
                	}
                }
                range range = getRange(countes, r);
                h2 = range.index;
                
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                int[] countes = new int[4700];
                for (int i = 0; i < corpus.size() - 2 ; i++) {
                	if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
                		countes[corpus.get(i+2)]++;
                	}
                }
                range range = getRange(countes, r);
                if (range == null) {
                	for (int i =0; i < corpus.size() - 1; i++) {
                    	if (corpus.get(i) == h2) {
                    		countes[corpus.get(i+1)]++;
                    	}
                    }
                }
                w = range.index;
                
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
