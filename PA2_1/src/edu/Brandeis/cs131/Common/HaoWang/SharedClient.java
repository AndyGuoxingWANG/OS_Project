package edu.Brandeis.cs131.Common.HaoWang;

import edu.Brandeis.cs131.Common.Abstract.Industry;

public class SharedClient extends MyClient {
	String name="";
	Industry industry;
			
	public SharedClient(String name, Industry industry){
		super(name,industry);
		this.name=name;
		this.industry=industry;
	}
	public String toString() {
        return String.format("%s SHARED %s", industry, name);
    }
	
}
