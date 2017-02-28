package cs131.pa1.filter.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class ConcurrentREPL {

	static String currentWorkingDirectory;
	
	static boolean addList=false;
	
	
		
	
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		List<String> listOfCommand=new ArrayList<String>();
		List<List<Thread>> listOfThreadList=new ArrayList<>();
		List<String> listOfAlive=new ArrayList<String>();
		
		List<List<ConcurrentFilter>>listOfFilterList=new ArrayList<>();
		while(true) {
			
			boolean isAlive=false;
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;
			} 
			else if(command.equals("repl_jobs")){											
				for(List<ConcurrentFilter> ls:listOfFilterList){
							
					isAlive=ls.get(ls.size()-1).isDone();
							
					if(isAlive==true)
					{
						listOfAlive.add("1");
						//System.out.println("test alive");
					}else
					{
						
						listOfAlive.add("0");
						//System.out.println("test noalive");
					}				
				}										
				int i=0;
				int j=1;
				Collections.reverse(listOfCommand);
				Collections.reverse(listOfAlive);
				for(String st:listOfCommand){
					if(listOfAlive.get(i)=="1")
					{
						System.out.println("\t"+j+". "+st);
						j++;
					}
									
					i++;
				}
				
				listOfCommand.clear();
				listOfThreadList.clear();
				listOfAlive.clear();
				
			}			
			else if(!command.trim().equals("")) {
				
				
				
				//building the filters list from the command
				List<ConcurrentFilter> filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				
				
				List<Thread> threadList=new ArrayList<Thread>();
				
				Thread t;
				int i=0;
				
				if(filterlist!=null)
				{
					while( i<filterlist.size())
					{
						t=new Thread(filterlist.get(i));
						
					
						
						t.start();	
						
						
						
						threadList.add(t);
						
						
						
						i++;
					}
					for(Thread th:threadList){
						try {
							th.join();
						} catch (InterruptedException e) {

						}
					}
					if(addList){
						
						
						listOfCommand.add(command);
						
						listOfFilterList.add(filterlist);
					}
					addList=false;
									
				}
				

				
				//checking to see if construction was successful. If not, prompt user for another command
				/*if(filterlist != null) {
					//run each filter process manually.
					Iterator<ConcurrentFilter> iter = filterlist.iterator();
					while(iter.hasNext()){
						iter.next().process();
					}
				}*/
			}
			//System.out.println("test while loop in repl");
		}
		s.close();
		System.out.print(Message.GOODBYE);
	
		
	
	
	
	}

	public static void setAddList(){
			
			addList=true;
			
		
	}
}
