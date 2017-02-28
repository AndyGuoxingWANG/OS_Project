package cs131.pa1.filter.concurrent;

public class WcFilter extends ConcurrentFilter {
	private int linecount;
	private int wordcount;
	private int charcount;
	
	public WcFilter() {
		super();
	}
	
	public void process() {
		done=false;
		while (!prev.isDone()){
			String line;
			line = input.poll();
			if(line!=null){
			processLine(line);
				
			//System.out.println("test wc is done");
			}
		}
		while(!input.isEmpty())
		{
			String line;
			
			line = input.poll();
			if(line!=null){
			processLine(line);
			
			//System.out.println("test wc is empty");
			}
		
		}
		
		
		if(input.isEmpty()) {	
			//System.out.println("test wc1 is empty");
				output.add(processLine(null));
			
		}	
		done=true;
	}
	
	public String processLine(String line) {
		//prints current result if ever passed a null
		if(line == null) {
			return linecount + " " + wordcount + " " + charcount;
		}
		
		if(prev.isDone()) {
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return ++linecount + " " + wordcount + " " + charcount;
		} else {
			linecount++;
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return null;
		}
	}
}
