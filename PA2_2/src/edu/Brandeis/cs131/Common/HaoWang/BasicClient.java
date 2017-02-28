package edu.Brandeis.cs131.Common.HaoWang;

import edu.Brandeis.cs131.Common.Abstract.Industry;

public class BasicClient extends MyClient {
	String name="";
	Industry industry;
	public BasicClient(String name, Industry industry){
		
		super(name,industry);
		this.name=name;
		this.industry=industry;
		
	}
	public String toString() {
        return String.format("%s BASIC %s", industry, name);
    }
}
