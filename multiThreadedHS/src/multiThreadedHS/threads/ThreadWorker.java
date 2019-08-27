package multiThreadedHS.threads;

import multiThreadedHS.util.Results;
import multiThreadedHS.util.FileProcessor;
import multiThreadedHS.util.MyLogger;
import multiThreadedHS.util.MyLogger.DebugLevel;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Devina Dhuri
 */

/**SOURCE:
* Quick Sort for threads:
* https://codereview.stackexchange.com/questions/181391/sorting-an-arraylist-of-vehicles-using-quick-sort
*/

public class ThreadWorker implements Runnable 
{
	int number = 0;
    FileProcessor fp;
	private String name;
	String infile = "";
	String line = "";
    // Thread thread[];
	Thread thread;
    Results results;
	int data;
	private int numberThreads = 0;
	ArrayList<Integer> threadArray = new ArrayList<Integer>();
	
	/**
	* Constructor
	*/
	public ThreadWorker( Results results, String infile,  int numThreads, String name) 
	{
        MyLogger.writeMessage("ThreadWorker Constructor ", MyLogger.DebugLevel.CONSTRUCTOR);
        this.infile = infile;
		this.name=name;
        this.results = results;
		this.numberThreads = numThreads;
    }
	
	/**
     * Function starts when thread.start() is invoked.
	 * Thread begins its life here.
     * @param none
     */
	@Override
    public void run() 
	{
		try
		{
			FileProcessor inputFileProcessor = new FileProcessor(infile);
			File file=new File(infile);
			boolean exists = file.exists();
			if(exists == false)
			{
				throw new FileNotFoundException("Error infile Not Found");
			}
			if(file.length() == 0)
			{
				throw new Exception("Error Empty Input infile");
			}
			line = inputFileProcessor.readLine();
			while(line != null) 
			{
				data = Integer.parseInt(line);
				if(data < 10000 || data > 99999)
				{
					throw new Exception("input value must be integers between 10000 and 99999 inclusive");
				}
				else
				{
					threadArray.add(data);
				}
				
				line = inputFileProcessor.readLine();
			}
			
			threadArray = sortArray(threadArray);
			
			results.insert(threadArray, name);
			// results.print();
			
		}
		catch(NumberFormatException e) 
		{
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch(FileNotFoundException e) 
		{ 
			System.err.println("Exit from Driver : Exiting Program");
			e.printStackTrace();
			System.exit(1);
		}
		catch(Exception e) 
		{ 
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
    }
	
	/**
     * Function to sort the list belonging to each thread
     * @param ArrayList<Integer> list to be sorted
	 * return ArrayList<Integer>
     */
	public ArrayList<Integer> sortArray(ArrayList<Integer> list)
	{
		// sort(list, 0, list.size());
		ArrayList<Integer> array;
		int size = list.size();
		if (size <= 1) 
        {
			return list; // Already sorted  
		}
		
		ArrayList<Integer> left = new ArrayList<Integer>();
		int pivot = list.get(list.size()-1); 
		ArrayList<Integer> right = new ArrayList<Integer>();
		
		for (int i = 0; i < size-1; i++)
		{
			int datai = list.get(i);
			if (datai < pivot)
			{
				left.add(datai);    
			}
			else
			{
				right.add(datai);   
			}
		}

		left = sortArray(left);
		right = sortArray(right);

		left.add(pivot);
		for(int j=0; j< right.size();j++)
		{
			left.add(right.get(j));
		}
		
		array = left;

		return array;
	}

	/**
     * Getter method for private data members
     * @param None
	 * @return String
     */
	 public String getName()
	 {
		 return name;
	 }
	 
	 /**
     * Setter method for private data members
     * @param name String name of thread
	 * @return None
     */
	 public void setName(String name)
	 {
		 this.name = name;
	 }
	 
	 /**
     * Getter method for private data members
     * @param None
	 * @return int
     */
	 public int getNumberT()
	 {
		 return numberThreads;
	 }
	 
	 /**
     * Setter method for private data members
     * @param int number of thread
	 * @return None
     */
	 public void setNumberT(int numberThreads)
	 {
		 this.numberThreads = numberThreads;
	 }
	 
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Number of threads created are:" + getNumberT() + "--------------------" + "\n" ;
	}
	
}