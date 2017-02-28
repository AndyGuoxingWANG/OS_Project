package cs131.pa1.filter.sequential;
//import java.io.PrintWriter;
import java.io.*;
public class redirectionFilter extends SequentialFilter{

	String filename="";
	
	public redirectionFilter(String fileName){
		
		filename=fileName;
		
	}
	
	public static redirectionFilter redirCommand(String input){
		int indexOfFile=(input.indexOf(" ")+1);
		String nameOfFile=input.substring(indexOfFile);
		
		redirectionFilter myredir=new redirectionFilter(nameOfFile);
		
		
		return myredir;
	}
	
	
	
	@Override
	public void process(){
		//PrintWriter wr;
		try{
			/*
			wr=new PrintWriter(file,"UTF-8");
			while (!input.isEmpty()){
				String line = input.poll();
				wr.print(line+"\n");	
			
			}*/
			File myfile=new File(filename);
			
			myfile.createNewFile();
			
			FileWriter writer=new FileWriter(myfile);
			
			while (!input.isEmpty()){
				String line = input.poll();
				writer.write(line+"\n");	
				
			}
			writer.close();
			
			//wr.close();
		}
		catch(Exception e)
		{
			
		}	
		
		//wr.close();		
		
	}
		
	public String processLine(String line)
	{
		
			
			
		return null ;
		
	}
}
