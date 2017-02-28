package cs131.pa1.filter.sequential;

public class pwdFilter extends SequentialFilter{
	String cwd="";
	public  pwdFilter(String currentWorkDir){
		
		
		cwd=currentWorkDir;
		
		
	}
	
	
	public static void pwdCommand(String input){
		
		
		
		
	}
	@Override
	public void process(){
		System.out.println(cwd);
		output.add(cwd);
		
	}
	
	
	protected String processLine(String line){
		
		
		
		return "";
		
	}
	
	
	
	
	
	
	

}
