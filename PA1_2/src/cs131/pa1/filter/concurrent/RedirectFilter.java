package cs131.pa1.filter.concurrent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class RedirectFilter extends ConcurrentFilter {
	private FileWriter fw;
	
	public RedirectFilter(String line) throws Exception {
		super();
		String[] param = line.split(">");
		if(param.length > 1) {
			if(param[1].trim().equals("")) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
				throw new Exception();
			}
			try {
				fw = new FileWriter(new File(ConcurrentREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + param[1].trim()));
			} catch (IOException e) {
				System.out.printf(Message.FILE_NOT_FOUND.toString(), line);	//shouldn't really happen but just in case
				throw new Exception();
			}
		} else {
			System.out.printf(Message.REQUIRES_INPUT.toString(), line);
			throw new Exception();
		}
	}
	
	public void process() {
		done=false;
		//System.out.println("test redir process");
		while(!prev.isDone()) {
			String thing=input.poll();
			if(thing!=null)
			{
				processLine(thing);
			}
			
			//System.out.println("test redirect isdone");
		}
		while(!input.isEmpty()) {
			String thing=input.poll();
			//System.out.println("test1 "+thing);
			if(thing!=null)
			{
				//System.out.println("test1 "+thing);
				processLine(thing);
				
			}
			
			//System.out.println("test redirect isempty");
		}
		try {
			fw.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		done=true;
	}
	
	public String processLine(String line) {
		try {
			fw.append(line + "\n");
			/*if( input.isEmpty()) {
				fw.flush();
				fw.close();
			}*/
		} catch (IOException e) {
			//System.out.println("test");
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
		}
		return null;
	}
}
