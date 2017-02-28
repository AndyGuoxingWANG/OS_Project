package edu.Brandeis.cs131.Common.HaoWang;

import java.util.ArrayList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Server;

public class BasicServer extends Server {
	int localBasic=0;
	int localShared=0;
	ArrayList<Client> al=new ArrayList<Client>();
	public BasicServer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized boolean connectInner(Client client) {
		
		if(client instanceof BasicClient)			
		{
			if(localBasic<1 && localShared==0){
				localBasic+=1;
				return true;
			}
			else{
				return false;
			}
			
		}
		else if(client instanceof SharedClient){
			if(localShared==1 && al.get(0).getIndustry()!=client.getIndustry() && localBasic==0){
				localShared+=1;
				al.add(client);
				return true;
				
			}
			else if(localShared==0 && localBasic==0){
				localShared+=1;
				al.add(client);
				return true;
			}
			else{
				
				return false;
			}
			
		}
		
		
		return false;
			
		
	}

	@Override
	public synchronized void disconnectInner(Client client) {
		if(client instanceof BasicClient)			
		{
			localBasic-=1;
		}
	else if(client instanceof SharedClient)
		{
			localShared-=1;
			al.remove(client);
		}
	}
}
