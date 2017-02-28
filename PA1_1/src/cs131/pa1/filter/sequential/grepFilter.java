package cs131.pa1.filter.sequential;

//import java.util.LinkedList;

public class grepFilter extends SequentialFilter{
	String word="";
		
	
	public grepFilter(String searchWord){
		
		word=searchWord;
		
		
		
	}
	
	//grep command
	public static SequentialFilter grepCommand(String input){
		//System.out.println("grepcommand test");
		//sample:grep sdfds
		String word=input.substring(5);
		SequentialFilter mygrep=new grepFilter(word);
		return mygrep;
		
		
		
	}
	public void process(){
		//System.out.println("test grep process");
		while (!input.isEmpty()){
			//System.out.println(input.peek());
			String line = input.poll();
			if(line.contains(word)){
			//	System.out.println("test grep process");
				if(SequentialCommandBuilder.getLastFilter())
				{
				System.out.println(line);
				}
				//output=new LinkedList<String>();
				output.add(line);	
				}
			
			
		}	
	}
	
	
	
	
	public String processLine(String line)
	{
		
			return null;
	}

}
