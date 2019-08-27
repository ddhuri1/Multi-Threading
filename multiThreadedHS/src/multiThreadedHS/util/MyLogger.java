package multiThreadedHS.util;

/**
* @author Devina Dhuri
*/

public class MyLogger
{

    /**
	 * DEBUG_VALUE=5 [Print to stdout everytime a thread accesses the shared data structure]
	 * DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
     * DEBUG_VALUE=3 [Print to stdout everytime a thread is created]
     * DEBUG_VALUE=2 [Print to stdout everytime a thread finished running ]
     * DEBUG_VALUE=1 [Print to stdout everytime Results is written to File]
     * DEBUG_VALUE=0 [No output is printed from the application to stdout. It writes to the output file though" ]
     */

    public static enum DebugLevel 
	{
		INSERT,
		CONSTRUCTOR,
		CREATE,
		COMPLETE,
		RESULTS,
		RELEASE
                                   
	};

	private static DebugLevel debugLevel;

	/**
	 * @param levelIn debug value specified on command line.
	 * @return None
	 */
    public static void setDebugValue (int levelIn) 
	{
    	switch (levelIn) 
		{
			case 5: debugLevel = DebugLevel.INSERT; 
				break;
    		case 4: debugLevel = DebugLevel.CONSTRUCTOR; 
				break;
    		case 3: debugLevel = DebugLevel.CREATE; 
				break;
    		case 2: debugLevel = DebugLevel.COMPLETE; 
				break;
    		case 1: debugLevel = DebugLevel.RESULTS; 
				break;
    		case 0: debugLevel = DebugLevel.RELEASE; 
				break;
    	}
    }

	/**
	 * @param levelIn debug value specified on command line.
	 * @return None
	 */
    public static void setDebugValue (DebugLevel levelIn) 
	{
    	debugLevel = levelIn;
    }

     /**
	 * @param String message to be displayed for given debug value
	 * @param levelIn debug value specified on command line.
	 * @return None
	 */
    public static void writeMessage (String  message, DebugLevel levelIn ) 
	{
		if (levelIn == debugLevel)
			System.out.println(message);
    }

    /**
	* @param None 
	* @return String
	*/
    public String toString() 
	{
    	return "Debug Level is " + debugLevel;
    }
}