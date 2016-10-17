/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4; // cannot be in default package
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
      //  System.out.println("GLHF");
        runProgram();
        /* Write your code above */
        System.out.flush();

    }
    public static void runProgram(){
    	boolean quit=false;
    	String input;
    	String[] inputArgs;
    	String firstArg;
    	while(!quit){
    		System.out.print("Critter>");
    		input=kb.nextLine();
    		inputArgs=input.trim().split("\\s+");
    		firstArg=inputArgs[0];
    		if(firstArg.equals("quit")){
    			quit=parseQuit(inputArgs,input);
    		}
    		else if(firstArg.equals("show")){
    			parseShow(inputArgs, input);
    		}
    		else if(firstArg.equals("step")){
    			parseStep(inputArgs,input);
    		}
    		else if(firstArg.equals("seed")){
    			parseSeed(inputArgs,input);
    		}
    		else if(firstArg.equals("make")){
    			parseMake(inputArgs,input);
    		}else if(firstArg.equals("stats")){
    			parseStats(inputArgs,input);
    		}
    		else{//To do
    			System.out.println("Invalid input");
    		}
    		
    		
    		
    	}
    	
    	
    }
    public static void parseShow(String[] inputArgs,String input){
    	if(inputArgs.length==1 && inputArgs[0].equals("show")){
    	Critter.displayWorld();
    	}
    	else{
    		System.out.println("error processing: "+input.trim());
    	}
    }
    public static boolean parseQuit(String[] inputArgs,String input){
    	if(inputArgs.length==1 && inputArgs[0].equals("quit")){
    		return true;
    	}
    	else{
    		System.out.println("error processing: "+input.trim());
    		return false;
    	}
    }
    public static void parseStep(String[] inputArgs,String input){
    	int step=0;
    	if(inputArgs.length==1 && inputArgs[0].equals("step")){
    		Critter.worldTimeStep();
    	}
    	else if(inputArgs.length==2 && inputArgs[0].equals("step")){
    		try{
    			step=Integer.parseInt(inputArgs[1]);
    		}catch(NumberFormatException ex){
    			System.out.println("error processing: "+input.trim());
    			return;
    		}
    		for(int i=0;i<step;i++){
    			Critter.worldTimeStep();
    		}
    	}
    	else{
    		System.out.println("error processing: "+input.trim());
    		
    	}
    }
    public static void parseSeed(String[] inputArgs,String input){
    	int seed=0;
    	 if(inputArgs.length==2 && inputArgs[0].equals("seed")){
    		try{
    			seed=Integer.parseInt(inputArgs[1]);
    		}catch(NumberFormatException ex){
    			System.out.println("error processing: "+input.trim());
    			return;
    		}
    		Critter.setSeed(seed);
    	}
    	else{
    		System.out.println("error processing: "+input.trim());
    		
    	}
    }
    public static void parseMake(String[] inputArgs,String input){
    	String name;
    	int numCreated=0;
    	 if(inputArgs.length==2  && inputArgs[0].equals("make")){
  
    			name=inputArgs[1];
    		try{
    		Critter.makeCritter(name);
    		}catch(InvalidCritterException ex){
    			//To do
    		}
  
    	} 
    	 else if(inputArgs.length==3  && inputArgs[0].equals("make")){
 			name=inputArgs[1];
 			try{
 				numCreated=Integer.parseInt(inputArgs[2]);
 			}catch(NumberFormatException ex){
 				System.out.println("error processing: "+input.trim());
 				return;
 			}
 			try{
 				for(int i=0;i<numCreated;i++){
 					Critter.makeCritter(name);
 				}
 			}catch(InvalidCritterException ex){
 				//To finish
 			System.out.println("No critter found");
 			return;
 			}
 	} 
    	else{
    		System.out.println("error processing: "+input.trim());
    		
    	}
    }
    public static void parseStats(String[] inputArgs,String input){
    	int seed=0;
    	 if(inputArgs.length==2 && inputArgs[0].equals("stats")){
    		 List<Critter> res;
    		 try{
    		 res=Critter.getInstances(inputArgs[1]);
    		 runStatsMethod(inputArgs[1],res);
    		 }catch(InvalidCritterException ex){
    			 //To finish
    			 System.out.println("Critter not found");
    			 return;
    		 }
    		 
    	}
    	else{
    		System.out.println("error processing: "+input.trim());
    		
    	}
    }
    //See http://stackoverflow.com/questions/9042740/call-static-method-given-a-class-object-in-java
    public static void runStatsMethod(String critter_class_name,List<Critter> res) throws InvalidCritterException{
    	try{
    	Class<?> inClass=Class.forName(myPackage+"."+critter_class_name);
    	Method inMethod=inClass.getMethod("runStats",List.class);
    			inMethod.invoke(res);
    	}catch(Exception ex){//Still need to do
    		ex.printStackTrace(System.out);
    		throw new InvalidCritterException(critter_class_name);
    		
    	}
    	
    }
   
    
    
}
