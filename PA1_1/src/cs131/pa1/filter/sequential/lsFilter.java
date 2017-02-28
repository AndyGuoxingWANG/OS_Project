package cs131.pa1.filter.sequential;

import java.io.File;

public class lsFilter extends SequentialFilter{
	
	//private static boolean lastFilter=false;
	String cwd="";
	
	
	public lsFilter(String currentWorkDir){
		
		cwd=currentWorkDir;
		
		
	}
	
		
	public static void getList(String currentWorkingDirectory){
		File cWD=new File(currentWorkingDirectory);
		File[]listOfFiles=cWD.listFiles();
		for(File f:listOfFiles)
		{
			System.out.println(f.getName());						
		}
				
	}
	public void process(){
		
		File cWD=new File(cwd);
		File[]listOfFiles=cWD.listFiles();
		//System.out.println(lastFilter);
		if(SequentialCommandBuilder.getLastFilter())
		{
		//	System.out.println("test list everything");
			if(SequentialCommandBuilder.getLastFilter())
			{
				for(File f:listOfFiles)
				{
					System.out.println(f.getName());
					
				}
			}
			//close any time after use
			
		}
		for(File f:listOfFiles)
		{
			output.offer(f.getName());						
		}
		
		
	}
	
	
	protected  String processLine(String line){
		
		
		
		return "";
	}
	
	/*public static void setLf(){
		lastFilter=true;
	}
	*/
	

}
