package edu.Brandeis.cs131.Common.HaoWang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;
import edu.Brandeis.cs131.Common.Abstract.Server;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MasterServer extends Server {

    private final Map<Integer, List<Client>> mapQueues = new HashMap<Integer, List<Client>>();
    private final Map<Integer, Server> mapServers = new HashMap<Integer, Server>();
    
    private final Lock lock=new ReentrantLock();
    private Condition notSuccessful=lock.newCondition();
    private Condition notEmpty=lock.newCondition();
    
    
    public MasterServer(String name, Collection<Server> servers, Log log) {
        super(name, log);
        Iterator<Server> iter = servers.iterator();
        while (iter.hasNext()) {
            this.addServer(iter.next());
        }
    }

    public void addServer(Server server) {
        int location = mapQueues.size();
        this.mapServers.put(location, server);
        this.mapQueues.put(location, new LinkedList<Client>());
    }

    @Override
    public boolean connectInner(Client client) {
    	
    	lock.lock();
    	try{
    		 int keyForClient=getKey(client);
    		 if(mapQueues.get(keyForClient).isEmpty()) 
    		 {
    			 
    			 while(!mapServers.get(keyForClient).connect(client)){
    				 notSuccessful.await();
    			 }
    			 notEmpty.signal();
    			 return true;
    			
    		 }
    		 else{
    			 
    			 mapQueues.get(keyForClient).add(client);
    			 
    			 while(mapServers.get(keyForClient).connect(mapQueues.get(keyForClient).get(0))){
    				 
    				 notSuccessful.await();
    			 }
    			 mapQueues.get(keyForClient).remove(0); 
    			 notEmpty.signal();
    			 return true;   				 
    			
    		 }
   		
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally{
    		lock.unlock();
    	}     
       
        return false;
    }

    @Override
    public void disconnectInner(Client client) {
    	lock.lock();
    	try{
    		
    		int keyForClient=getKey(client);
    		while(mapServers.get(keyForClient).connectInner(client)){
    			
    			notEmpty.await();
    		}
        	mapServers.get(keyForClient).disconnect(client);
        	
        	notSuccessful.signal();
        	
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally{
    		lock.unlock();
    	}
    	

    	
    }

	//returns a number from 0- mapServers.size -1
    // MUST be used when calling get() on mapServers or mapQueues
    private int getKey(Client client) {
        return client.getSpeed() % mapServers.size();
    }
}
