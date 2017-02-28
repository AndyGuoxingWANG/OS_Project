package cs131.pa1.filter.sequential;
//import java.io.File;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	public static String currentWorkingDirectory;
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static boolean notExit=true;
	
	public static void main(String[] args){
		String input="";
		
		Scanner sc = new Scanner(System.in);
		//System.out.print(Message.NEWCOMMAND);
		System.out.print(Message.WELCOME);
		//Using file package
		currentWorkingDirectory=System.getProperty("user.dir");
		do{
			
			System.out.print(Message.NEWCOMMAND);
			//sc = new Scanner(System.in);
			input = sc.nextLine();
			
			// 1st IF : FILTER
			if(input.contains("|")||input.contains(">")){
				
				
				SequentialCommandBuilder.filterSystems(input,currentWorkingDirectory);
				
				
			}
			
			// ls and pwd
			else if(input.startsWith("ls")){
			
				
								
					
					
					lsFilter.getList(currentWorkingDirectory);}
					
				
				
			else if(input.contains("pwd")){		
					System.out.print( currentWorkingDirectory + "\n");
					//String expectation = Message.NEWCOMMAND + "" + System.getProperty("user.dir") + "\n";
					//System.out.print(expectation);
				
				
				}				
				
			//End of ls and pwd
			
			//changeDirectory(cWD);
			
			else if(input.contains("cd")){
				//System.out.println("test1");
				currentWorkingDirectory=cdFilter.changeDirectory(input, currentWorkingDirectory);
				
			}
			
			// head
			else if(input.startsWith("head")){
				//System.out.println("test:!!!!!!!!!!");
				if(input.trim().equals("head")){				
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(input));					
				}			
				else{
					
					headFilter.headCommand(input);
					
				}
				
					
								
				
			}// end head
			else if(input.contains("wc"))
			{
				System.out.print(Message.REQUIRES_INPUT.with_parameter("wc"));	
			}
			else if(input.startsWith("grep"))
			{
				System.out.print(Message.REQUIRES_INPUT.with_parameter(input));	
				
			}
			else if(input.equals("exit"))
			{
				
			}
			else
			{
				// not a command
				
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(input));
				
				
			}
				
			
					
			
			
			
			
			
			
			//test
			//System.out.println("loop test");
		}while(!input.equals("exit")&& notExit);
		
		System.out.print(Message.GOODBYE);
		sc.close();
		
	}// End of Main Method
	/*
	public static void setExit(){
		
		notExit=false;
		
	}
	*/
	
		
		
		
	
	
	

}
