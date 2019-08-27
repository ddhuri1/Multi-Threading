package multiThreadedHS.util;

/**
 * @author Devina Dhuri
 */
 
/**MERGE SORT SOURCE:
* https://www.codexpedia.com/java/java-merge-sort-implementation/
*/

import multiThreadedHS.util.FileProcessor;
import multiThreadedHS.util.MyLogger;
import multiThreadedHS.threads.ThreadWorker;
import multiThreadedHS.util.Results;
import java.util.ArrayList;
import multiThreadedHS.util.MyLogger.DebugLevel;

/**
* @author Devina Dhuri
*/

public class MergeSort
{
	private int sizeList;
	int first = 0;
	int last = 0;
	 
    /**
     * Constructor
     */
    public MergeSort(Results res) 
	{
		MyLogger.writeMessage("Sort constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }
	
    /**
	* This function calls merge
	* @param NOne
	* @return None
	*/
	public void mergeSort(Results res)
	{
		ArrayList<Integer> finalL = merge(res.getList());
		sizeList = finalL.size();
		res.setList(finalL);
	}
	
	/**
	* This function divides the array into smallest size
	* and then merges them after sorting
	* @param list ArrayList<Integer> list to be sorted
	* @return ArrayList<Integer> 
	*/
	public ArrayList<Integer> merge(ArrayList<Integer> list)
	{
		int j = 0, k = 0, finalIndex = 0;
		ArrayList<Integer> rest;
		int restIndex;
		ArrayList<Integer> left = new ArrayList<Integer>();
		int mid;
		ArrayList<Integer> right = new ArrayList<Integer>();
		
	    
		int size = list.size();
		if (size <= 1) 
        {
			return list; // Already sorted  
		}
		else 
		{
			mid = size/2;
			
			for (int i=0; i < mid; i++) 
			{
				int datal = list.get(i);
				left.add(datal);
			}
	 
			for (int i=mid; i<list.size(); i++) 
			{
				int datar = list.get(i);
				right.add(datar);
			}
	 
			left  = merge(left);
			right = merge(right);
			
			int leftSize = left.size();
			int rightSize = right.size();
			
			while (k < rightSize && j < leftSize) 
			{
				if ( (left.get(j) > right.get(k))) 
				{
					list.set(finalIndex++, right.get(k++));
				} 
				else 
				{
					list.set(finalIndex++, left.get(j++));
				}
			}
			if (j < leftSize) 
			{
				restIndex = j;
				rest = left;
			} 
			else 
			{
				restIndex = k;
				rest = right;
			}
	 
			for (int i = restIndex; i < rest.size(); i++) 
			{
				list.set(finalIndex++, rest.get(i));
			}
			
		}
		return list;
	}
	
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Total size of shared data is " + sizeList ;
	}
}