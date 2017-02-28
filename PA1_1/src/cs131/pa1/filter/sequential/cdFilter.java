package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.io.File;

public class cdFilter {
	
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static String newPath="";
	
	
	public static String changeDirectory(String input, String cWD)
	{
		String trimmed="";
		trimmed=input.trim();
		input=trimmed;
		if(input.equals("cd"))
		{
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
			return cWD;
		}
		if(!input.substring(2, 3).equals(" ")){
			System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(input));
			return cWD;
			
		}
		
		
		String address=input.substring(3);
		//System.out.println(address+input);
		
		
		if(address.contains("..")){
			
			//System.out.println("..test");
			
			newPath=moveUp(cWD);
			
			//System.out.println("..test "+newPath);
			return newPath;
		}
		else if(address.equals(".")){
			
			//System.out.println(".test");
			
			
			return cWD;
		}
		
		else if(address.substring(0,2).equals("."+FILE_SEPARATOR)){
			
			if(checkPath(address.substring(2),cWD))
			{
				
				newPath=moveDown(address.substring(2),cWD);
				return newPath;
			}
			else{
				
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(input));
				return cWD;
			}
			
			
			
		}
		//order is important after ./
		else if(address.contains(FILE_SEPARATOR)){
			String subAddress=address;
			String nextDirect="";
			String path=cWD;
			while(!subAddress.isEmpty())
			{
				if(subAddress.contains(FILE_SEPARATOR)){
					nextDirect=subAddress.substring(0, subAddress.indexOf(FILE_SEPARATOR));
					subAddress=subAddress.substring(subAddress.indexOf(FILE_SEPARATOR)+1);
					//System.out.println("test "+nextDirect);
					if(checkPath(nextDirect,path))
					{
						
						path=moveDown(nextDirect,path);
						//System.out.println("test "+nextDirect);
						
						//System.out.println("test "+subAddress);
					}
					else{
						//System.out.println("test notfound");
						System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(input));
						return cWD;
					}
							
				}
				// last with sep
				else{
					
					if(checkPath(subAddress,path))
					{
						
						newPath=moveDown(subAddress,path);
						return newPath;
						
						
					}
					else{
					//System.out.println("test cd filter notfound ");
						System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(input));
						return cWD;
					}
					
					
					
				}
				
				
			}
			
			return newPath;
			
		}
		else 
		{
			
			
			
			if(checkPath(address,cWD))
			{
				
				newPath=moveDown(address,cWD);
				return newPath;
				
				
			}
			else{
			
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(input));
				return cWD;
			}
			
		}
			
		
		
		
		
	}
	public static boolean checkPath(String input, String currentWorkDir){
		
		
		
		File cWD=new File(currentWorkDir);
		File[]listOfFiles=cWD.listFiles();
		for(File f:listOfFiles)
		{
			if(f.isDirectory() && (f.getName().equals(input)))			
			{
				//System.out.println("here test");
				return true;
			}
		}
		return false;
		
		
		
		
	}
	public static String moveUp(String cwd)
	{
		String outputPath="";
		int indexOfLastSeparator=0;
		indexOfLastSeparator=cwd.lastIndexOf(FILE_SEPARATOR);
		outputPath=cwd.substring(0, indexOfLastSeparator);
		return outputPath;
	}
	
	public static String moveDown(String input,String cwd){
		String newpath="";
		newpath=cwd.concat(FILE_SEPARATOR).concat(input);
		
		
		return newpath;
	}
	

}
