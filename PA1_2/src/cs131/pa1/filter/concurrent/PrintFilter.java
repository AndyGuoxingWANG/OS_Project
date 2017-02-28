package cs131.pa1.filter.concurrent;

public class PrintFilter extends ConcurrentFilter {
	
	public PrintFilter() {
		super();
	}
	
	
	public void process() {
		done=false;
		while(!prev.isDone() ) {
					
				
			String queueinput=input.poll();
			if(queueinput!=null)
			{	
				processLine(queueinput);
				//System.out.println("test loop in is done");
			}
			
			
		}
		while(!input.isEmpty()){
			
			String queueinput=input.poll();
			if(queueinput!=null)
			{	
				processLine(queueinput);
				//System.out.println("test loop in is empty");
			}
			
		}
		done=true;
		//System.out.println("test hi");
	}
	
	public String processLine(String line) {
		System.out.println(line);
		//System.out.println("test");
		return null;
	}
}
