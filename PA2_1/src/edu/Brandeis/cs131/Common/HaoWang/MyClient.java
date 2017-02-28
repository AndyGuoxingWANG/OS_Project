package edu.Brandeis.cs131.Common.HaoWang;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Industry;

public abstract class MyClient extends Client {
	public MyClient(String name, Industry industry){
		
		super(name,industry,3,3);
		
	}

    public MyClient() {
        super("NOT IMPLEMENTED", Industry.TECHNOLOGY, 0, 0);
    }
   
}
