package cs131.pa1.filter.sequential;

public class wcFilter extends SequentialFilter{

		int lines=0;
		int character=0;
		int word=0;
	
		public void process(){
			//System.out.println("test wc process");
			if(input.isEmpty()){
				
				output.offer("0 0 0");
				
			}
			else{
				
				while (!input.isEmpty()){
					String line = input.poll();
				
						counter(line);
					
					
					
					
				}
				
				
			}
			if(SequentialCommandBuilder.getLastFilter())
			{
			//System.out.println(Integer.toString(lines));
				System.out.println(""+lines+" "+word+" "+character);
			}
			output.add(""+lines+" "+word+" "+character);
		}
	
		
		
		
		public void counter(String line)
		{
			int countChar=0;
			int countWord=0;
			
			lines++;
			countChar=line.length();
			character=countChar+character;
			
			
			if(line.isEmpty())
			{
				countWord=0;
			}
			else{
				countWord=line.split("\\s+").length;
				
			}
			word=word+countWord;
			
			
		}
		protected String processLine(String line){
			return "";
		}
}
