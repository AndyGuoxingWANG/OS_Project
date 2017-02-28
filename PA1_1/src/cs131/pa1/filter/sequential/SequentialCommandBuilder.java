package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.util.List;
import java.util.regex.Pattern;
import java.util.*;

public class SequentialCommandBuilder{

	
		public static String cwd="";
		//public List<SequentialFilter> filterList=new LinkedList<SequentialFilter>();
		
		public static boolean notRecognized=true;
		public static boolean lastFilter=false;
		public static boolean notOutOfProcess=true;
		
	
	public static void setNotRecognized(){
		
		
		notRecognized=false;
		
	}
	
	
	
	
	
	
	public static void filterSystems(String command, String currentWorkingDir){
		cwd=currentWorkingDir;
		//1. create filter objects for each command
		List<SequentialFilter> filterList=new LinkedList<SequentialFilter>();	
		filterList=createFiltersFromCommand(command);
		//System.out.println("test"+notRecognized);
		//if not recognized skip it
		if(notRecognized){
			//2. link filterList
			//System.out.println("test: link filters");
			filterList=linkFilters(filterList);
			// check final filter and initial an output for it
			int lastFilter=(filterList.size()-1);
			filterList.get(lastFilter).output=new LinkedList<String>();
		
			//3. run the filters
			//System.out.println("test: run filters");
			runFilters(filterList);	
			
		}
		notRecognized=true;
		lastFilter=false;
	}
	
	public static   List<SequentialFilter> createFiltersFromCommand(String command){
		List<SequentialFilter> sf=new LinkedList<SequentialFilter>();
		//System.out.println("Filter is here test");
		// only one > sample: head  txt  > txt
		if((!command.contains("|"))&&(command.indexOf(">")!=0)&& (command.indexOf(">")!=(command.length()-1))){
			//System.out.println("test: >");
			String front="";
			String end="";
			front=(command.substring(0,command.indexOf(">")-1)).trim();
			end=(command.substring((command.indexOf(">")+1))).trim();
			sf.add(constructFilterFromSubCommand(front));
			sf.add(redirectionFilter.redirCommand(end));
			return sf;
		}
		
		
		
		String[]segments=command.split(Pattern.quote("|"));
		for(int i=0; i<segments.length;i++)
		{
			String trimed;
			trimed=segments[i].trim();
			segments[i]=trimed;
				
		}
		for(int i=0; i<segments.length;i++)
		{
			if(segments[i].startsWith("cd") && i==1){
				System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(segments[i]));
				setNotRecognized();
				return sf;
			}
			if(segments[i].startsWith("cd") && i<(segments.length-1)){
				
				System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(segments[i]));
				setNotRecognized();
				return sf;
				
			}
			if(segments[i].indexOf(">")==segments[i].length()-1){
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
				setNotRecognized();
				return sf;
				
			}
			if(segments[i].indexOf(">")==0 ){
				System.out.print(Message.REQUIRES_INPUT.with_parameter(segments[i]));
				setNotRecognized();
				return sf;
				
			}
			
			if(segments[i].equals("ls")&&i>0){
				System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("ls"));
				setNotRecognized();
				return sf;
				
			}
			if(segments[i].startsWith("grep")&&i==0){
				System.out.print(Message.REQUIRES_INPUT.with_parameter(segments[i]));
				setNotRecognized();
				return sf;
				
			}
			if(segments[i].startsWith("pwd")&&i>0){
				System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("pwd"));
				setNotRecognized();
				return sf;
				
			}
			//sampe head hello-world.txt > new-hello-world.txt|wc
			if(segments.length>1&&segments[i].contains(">")&&i<(segments.length-1)){
				String parameter=segments[i].substring(segments[i].indexOf(">"));
				System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(parameter));
				setNotRecognized();
				return sf;
				
				
				
			}
		}
	
		
		
		for(int i=0;i<segments.length;i++){
			//
			//check the comment undefined command
			//
			if(i==0)
			{
				//check valid piping order
				if(segments[i].startsWith("head")||segments[i].startsWith("pwd")||segments[i].startsWith("ls")||segments[i].startsWith("cd")){
					if(constructFilterFromSubCommand(segments[i])==null){
						//the comment was not recognized
						System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(segments[i]));
						
						setNotRecognized();
						
					}
					else{
					sf.add(constructFilterFromSubCommand(segments[i]));
					}
				}
				
				else{
				
				String categoryOfCommand=segments[i].substring(0, segments[i].indexOf(" "));
				System.out.println(Message.REQUIRES_INPUT.with_parameter(categoryOfCommand));
				
				
				}
				// end of check valid piping order
				
			}
			// check whether " > xxx.txt" in the last argument
			else if(i==(segments.length-1))
			{	
				if(segments[i].contains(" > ")){
					int indexOfFirstSubstring=(segments[i].indexOf(">")-1);//-1 means the empty space before ">"
					int indexOfSecondSubstring=segments[i].indexOf(">");
					String first=segments[i].substring(0, indexOfFirstSubstring);
					String second=segments[i].substring(indexOfSecondSubstring);
					
					sf.add(constructFilterFromSubCommand(first));
					sf.add(constructFilterFromSubCommand(second));
					
				}
				else					
				{
					if(segments[i].equals("grep")){
						System.out.print(Message.REQUIRES_PARAMETER.with_parameter("grep"));
						setNotRecognized();
						
					}
					else
					{
						if(segments[i].contains("head")){
							
							
							System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(segments[i]));
							setNotRecognized();
						}			
						
						else{
							
							if(constructFilterFromSubCommand(segments[i])==null){
								//the comment was not recognized
								System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(segments[i]));
								//System.out.println("test setnotrecognized 1st");
								setNotRecognized();
								
							}
							else{
								
								sf.add(constructFilterFromSubCommand(segments[i]));
							}
							
						}
						
					}
					
					
					
				}
						
				
			}
			else{
				//the comment was not recognized
				//System.out.println("test: begin from 2nd segments");
				if(constructFilterFromSubCommand(segments[i])==null){
					
					System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(segments[i]));
					//System.out.println("test setnotrecognized 2nd");
					setNotRecognized();
				}
				else{
					
					sf.add(constructFilterFromSubCommand(segments[i]));
				}
				
				
				
			}
			
			//System.out.println("test:i");
			
			
		}
	
		return sf;
		
	}
	

	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		
		if(subCommand.startsWith("pwd")){
			if(subCommand.equals("pwd")){
				//System.out.println("test:construct pwd filter");
				SequentialFilter mypwd=new pwdFilter(cwd);
				return mypwd;
			}
			else{
				
				System.out.println("invalid pwd");
				
			}
			
		}
		
		else if(subCommand.startsWith("ls")){
			//System.out.println("test:construct ls filter");
			if(subCommand.equals("ls")){
				//System.out.println("test:construct ls filter");
				SequentialFilter myls=new lsFilter(cwd);
				return myls;
			}
			else{
				
				System.out.println("invalid ls");
				
			}
			
			
			
			
		}
		else if(subCommand.startsWith("cd")){
			
			System.out.println("nothing in cd");
			
			
			
			
		}
		else if(subCommand.startsWith("head ")){
			//System.out.println("test:construct head filter");
			return headFilter.headCommandInFilter(subCommand);
			
	
		}
		else if(subCommand.startsWith("grep ")){
			
			//System.out.println("test:construct grep filter");
			return grepFilter.grepCommand(subCommand);
			
			
			
		}
		else if(subCommand.startsWith("wc")){
			
			//System.out.println("test:construct wc filter");
			if(subCommand.equals("wc")){
				
				SequentialFilter mywc=new wcFilter();
				return mywc;
			}
			else{
				System.out.println("invalid wc");
			}
			
			
		}
		else if(subCommand.startsWith(">")){
			
			if(subCommand.equals(">"))
			{
				//System.out.println("test:construct > filter");
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter(subCommand));
				
			}else
			{
				
				return redirectionFilter.redirCommand(subCommand);
			}
			
			
			
			
		}
		else{
			
			return null;
		}
		
		return null;
	}

	// link filterlist
	public static List<SequentialFilter> linkFilters(List<SequentialFilter> filters){
		//System.out.println("test: filter linklist size: "+filters.size());
	
		//System.out.println("test"+filters.get(0) instanceof SequentialFilter );
		//System.out.println("test"+filters.get(1) instanceof SequentialFilter );
		for(int i=0;i<filters.size()-1;i++)
		{
			
			filters.get(i).setNextFilter(filters.get(i+1));
			
			
		}
			
		return filters;
		
	}
	// run the filters
	public static void runFilters(List<SequentialFilter> filters){
		
		//System.out.println("test runfilter");
		for(int i=0;i<filters.size();i++)
		{
			if(notOutOfProcess){
				
				if(i==(filters.size()-1)){
					//	System.out.println("test work");
						setLastFilter();
					}
					
						
						filters.get(i).process();
						
					
					
					//System.out.println("test: "+(i+1)+" time run process");
				
				
				
				
			}
			
			
			
		}
		
		notOutOfProcess=true;
		
		
	}
	
	// run the filters
	
	public static boolean getLastFilter(){
		
		return lastFilter; 
		
	}
	public static void setLastFilter(){
		
		lastFilter=true; 
		
	}
	public static void setOutOfProcess(){
		
		notOutOfProcess=false;
		
		
	}
	//end link filterlist
	
	
	

	
	
	
	/*
	
	private static SequentialFilter determineFinalFilter(String command){
		
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	*/
}

