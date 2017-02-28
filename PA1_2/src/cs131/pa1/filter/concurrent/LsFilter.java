package cs131.pa1.filter.concurrent;
import java.io.File;

public class LsFilter extends ConcurrentFilter{
	int counter;
	File folder;
	File[] flist;
	
	public LsFilter() {
		super();
		counter = 0;
		folder = new File(ConcurrentREPL.currentWorkingDirectory);
		flist = folder.listFiles();
	}
	
	@Override
	public void process() {
		done=false;
		while(counter < flist.length) {
			output.add(processLine(""));
		}
		done=true;
	}
	
	@Override
	public String processLine(String line) {
		return flist[counter++].getName();
	}
}
