package multiThreadedHS.driver;

import multiThreadedHS.util.Results;
import multiThreadedHS.util.MergeSort;
import multiThreadedHS.util.FileProcessor;
import multiThreadedHS.util.MyLogger;
import multiThreadedHS.util.MyLogger.DebugLevel;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import multiThreadedHS.threads.ThreadWorker;
import java.util.ArrayList;

/**
* @author Devina Dhuri
*/

public class Driver 
{
	public static void main(String[] args) 
	{
		String inFile1 = "";
		String inFile2 = "";
		String name = "";
		String OutFile = "";
		String line;
		int noOfFiles = 0;
		int debugValue = -1;
		Results res ;
		int NUM_THREADS = 0;
		String[] infiles = new String[3];
		ArrayList<Integer> ress = new ArrayList<Integer>();

		try 
		{
			//validation check for arguments.
			if (args.length < 4 || args.length > 6)
			{
				throw new IllegalArgumentException("Error Illegal Number of Arguments");
			}	
			try
			{
				NUM_THREADS = Integer.parseInt(args[0]);
				if(!(NUM_THREADS >= 1 && NUM_THREADS <= 3))
				{
					throw new Exception("Number of threads must be between 1 and 3 inclusive");
				}
			}
			catch(NumberFormatException e) 
			{
				System.err.println("Exit from Driver : Exiting Program");
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
			} 
			
			if(args.length != (NUM_THREADS + 3))
			{
				throw new IllegalArgumentException("Error Illegal Number of Arguments: Input files should match N");
			}
			
			for(String arg : args)
			{
				if(arg.contains(".txt"))
				{
					noOfFiles ++;
				}
			}			
			if (NUM_THREADS != noOfFiles -1)
			{
				throw new Exception("Incorrect number/type of input files");
			}
			
			for(int i = 0; i<NUM_THREADS; i++)
			{
				infiles[i] = args[i+1];
			}
			
			if(args[NUM_THREADS + 1] != null ) 
			{
				OutFile = args[NUM_THREADS + 1];
			}
			else 
			{
				throw new IllegalArgumentException("Invalid OutFile file");
			}
			if(args[NUM_THREADS + 2] != null ) 
			{
				try 
				{
					debugValue = Integer.parseInt(args[NUM_THREADS + 2]);
					if(!(debugValue >= 0 && debugValue <= 5)) 
					{
						throw new NumberFormatException("Debug value must be integers between 0 and 5 inclusive");
					}
					MyLogger.setDebugValue(debugValue);
				}
				catch(NumberFormatException e) 
				{
					System.err.println("Exit from Driver : Exiting Program");
					e.printStackTrace();
					System.exit(1);
				}
			}
			else 
			{
				throw new IllegalArgumentException("Please enter debug value");
			}
			
			 res = new Results(OutFile);
			 for ( int i=0; i<NUM_THREADS; i++) 
			 {
				ThreadWorker thread = new ThreadWorker(res, infiles[i], NUM_THREADS, "Thread" + i);
				Thread threads = new Thread(thread);
				MyLogger.writeMessage("New Thread - Thread " + i + " Created.", MyLogger.DebugLevel.CREATE);
				threads.start();
				try
				{
					threads.join();
					MyLogger.writeMessage("Thread " + i + " Completed.", MyLogger.DebugLevel.COMPLETE);
				}
				catch(InterruptedException ie)
				{
					System.err.println("Exit from Driver : Exiting Program");
					ie.printStackTrace();
					System.exit(1);
				}
			}
			
			MergeSort hs = new MergeSort(res);
			hs.mergeSort(res);
			ress = res.getList();
			for(int i =0; i< ress.size(); i++)
			{
				String s = ress.get(i).toString();
				res.writeToFile(s);
			}
			res.closeFile();
			
		}
		catch(NumberFormatException e) 
		{
			System.err.println("Exit from Driver : Exiting Program");
			e.printStackTrace();
			System.exit(1);
		}
		
		catch(IllegalArgumentException e) 
		{ 
			
			System.err.println("Exit from Driver : Exiting Program");
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
			e.printStackTrace();
			System.exit(1);
		}
	}
}