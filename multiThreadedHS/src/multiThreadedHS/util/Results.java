package multiThreadedHS.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import multiThreadedHS.util.MyLogger;
import multiThreadedHS.util.MyLogger.DebugLevel;

/**
* @author Devina Dhuri
*/

public class Results implements FileDisplayInterface, StdoutDisplayInterface 
{
	private String outFile;
	private PrintWriter writer;
	private BufferedWriter bw;
    private String strResult;
	private ArrayList<Integer> myList = new ArrayList<Integer>();
	
	/** 
	* Constructor
	*/
	public Results(String outFile) 
	{
		MyLogger.writeMessage("Results constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		this.outFile = outFile;
		try 
		{
			bw = new BufferedWriter(new FileWriter(outFile));
		}
		catch(IOException e) 
		{
			System.err.println("Exception: creating file object");
			System.err.println("Exiting");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
     * This function write the result to a file
     * @param s String to be written
	 * @param outFile File where result is to be written
	 * @return None
     */
	 public void writeToFile(String s) 
	 {
		try 
		{
			bw.write(s);
			bw.write("\n");
		}
		catch(IOException e) 
		{
			System.err.println("Exception: writing to output.txt");
			System.err.println("Exiting");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
	 }
	 
	 /**
     * This function closes a file
     * @param None
	 * @return None
     */
	 public void closeFile()
	 {
		try 
		{
			MyLogger.writeMessage("Results written to file", MyLogger.DebugLevel.RESULTS);
			bw.close();
		}
		catch(IOException e) 
		{
			System.err.println("Exception: Closing the file");
			System.err.println("Exiting");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	 }
	 
	
	/**
     * This function stores the data read by each thread into a shared data structure
     * @param data ArrayList to be stored
	 * @return None
     */
	public synchronized void insert(ArrayList<Integer> data, String number) 
	{
		
		for(int i = 0; i< data.size(); i++)
		{
			myList.add(data.get(i));
		}
		MyLogger.writeMessage( number + " Accessed the shared data structure.", MyLogger.DebugLevel.INSERT);
	}
	
	/**
     * This function prints the data stored by each thread into a shared data structure
     * @param None
	 * @return None
     */
	public void print()
	{
		System.out.println(myList.toString());
	}
	
	
	/**
     * Getter method for private data members
     * @param None
	 * @return ArrayList<Integer>
     */
	 public ArrayList<Integer> getList()
	 {
		 return myList;
	 }
	 
	 /**
     * Setter method for private data members
     * @param ArrayList<Integer> list
	 * @return None
     */
	 public void setList(ArrayList<Integer> list)
	 {
		 this.myList = list;
	 }
	 
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Output FileName:" + outFile + "--------------------" + "\n" ;
	}
}
