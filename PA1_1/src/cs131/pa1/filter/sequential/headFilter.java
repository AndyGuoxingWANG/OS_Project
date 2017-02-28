package cs131.pa1.filter.sequential;
import java.io.File;
//import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class headFilter extends SequentialFilter{

	int lines;
	String fileName;
	boolean lastFilter=false;
	public void setLastFilter(){
		
		
		lastFilter=true;
		
	}
	
	public headFilter(String nameOfFile){
		
		lines=10;
		fileName=nameOfFile;
		
	}
	
	public headFilter(int numOfLines,String nameOfFile){
		
		lines=numOfLines;
		fileName=nameOfFile;
			
	}
	public void test()
	{
		System.out.println(lines+fileName+"testest");
	}
	
	public void process(){
		//System.out.println("test first time");
		
			readFile();
		
		saveFileIntoOutput();
		
		
		
		
		
	}
	public void saveFileIntoOutput(){
		
		try{
			
			
			File file=new File(fileName);		
			Scanner sc=new Scanner(file);		
			int i=0;		
			while(sc.hasNextLine() && i<lines)
			{
				
				//output=new LinkedList<String>();
				output.offer(sc.nextLine());
				i++;
			}
			
			//System.out.println("test: headFilter lines "+i);
			sc.close();
		}
		catch(Exception e){
			
			
			
		}
		
		
		
	}
	
	public void readFile(){
		try{
			
			
			File file=new File(fileName);		
			Scanner sc=new Scanner(file);		
			int i=0;		
			while(sc.hasNextLine() && i<lines)
			{
				if(SequentialCommandBuilder.getLastFilter())
				{
				System.out.println(sc.nextLine());
				}
				i++;
			}
			sc.close();
		}
		catch(Exception e){
			
			
			System.out.print(Message.FILE_NOT_FOUND.with_parameter("head -"+lines+" "+fileName));
			SequentialCommandBuilder.setOutOfProcess();
		}
		
		
		
		
		
		
	}
	
	
	
	
	public String processLine(String line)
	{
		
			
		
		
		
		return "" ;
		
	}
	// for filter head command
		public static SequentialFilter headCommandInFilter(String input){
			
				//sample: head -3 xxx.txt lineAndFile:3 xxx.txt
				if(input.substring(5,6).equals("-")){
					
					String lineAndFile=input.substring(6);										
					int line=0;		
					String file="";
					line=Integer.parseInt(lineAndFile.substring(0,lineAndFile.indexOf(" ")));
					file=lineAndFile.substring(lineAndFile.indexOf(" ")+1);	
					
					//System.out.println("test-headcommand line: "+line+" file: "+file);
					
					//if(checkFile(file)){
						
							SequentialFilter myHead=new headFilter(line,file);
							//SequentialFilter myseq=(SequentialFilter)myHead;
							return myHead;
						
					//}
					/*else{
						System.out.print(Message.FILE_NOT_FOUND.with_parameter(input));
						return null;
					}*/
					
					
				
					
					
				}
				//sample head xxxx.txt
				else{
					//String file="";
					//file=input.substring(5);
					//System.out.println("test-headcommand line: default 10"+"file: "+file);
					SequentialFilter yourHead=new headFilter(input.substring(5));
					
					return yourHead;
				}
					
					
			
		
			
			
			
			
		}//end head method
	
	
	
	
	// for no filter
	public static void headCommand(String input){
		String lineAndFile=input.substring(6);
		try{
			if(input.substring(5,6).equals("-")){
				//String lineAndFile=input.substring(6);										
				int line=0;		
				String file="";
				line=Integer.parseInt(lineAndFile.substring(0,lineAndFile.indexOf(" ")));
				file=lineAndFile.substring(lineAndFile.indexOf(" ")+1);						
				
				headFilter myHead=new headFilter(line,file);
				myHead.nofilterReadFile();
				
				
			}
			else{
				
				headFilter yourHead=new headFilter(input.substring(5));
				yourHead.nofilterReadFile();
				
			}
				
				
		}
		catch(Exception e){
			if(!lineAndFile.contains(" ")){
				
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(input));
			}
			else{
				
				System.out.print(Message.INVALID_PARAMETER.with_parameter(input));
				
			}
			
		}
	
	}
	//nofilter readfile
	public void nofilterReadFile(){
		try{
			
			
			File file=new File(fileName);		
			Scanner sc=new Scanner(file);		
			int i=0;		
			while(sc.hasNextLine() && i<lines)
			{
				
				System.out.println(sc.nextLine());
				
				i++;
			}
			sc.close();
		}
		catch(Exception e){
			
			
			System.out.print(Message.FILE_NOT_FOUND.with_parameter("head "+fileName));
		}
		
		
		
		
		
		
	}
	
	public static boolean checkFile(String input){
		
		
		
		File cWD=new File(System.getProperty("user.dir"));
		File[]listOfFiles=cWD.listFiles();
		for(File f:listOfFiles)
		{
			if(f.isFile() && (f.getName().equals(input)))			
			{
				//System.out.println("here test");
				return true;
			}
		}
		return false;
		
		
		
		
	}

}
