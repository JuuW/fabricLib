package com.fabriclib.db.tables.ts;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.Message;

public  class FabricIO extends DatabaseIO {

    public static Message save(Fabric item) throws Exception {
    	String hangerNo  = item.getHangerNo();
    	if(notExist(hangerNo)){
    		List<Fabric> items = new ArrayList<Fabric>();
            items.add(item);
            return saveOrUpdateList(items);
    	}else{
    		return new Message("E","The hangerNo " +hangerNo+ " is existed." );
    	}
    	
    }
    
    public static boolean notExist(String hangerNo){
    	
    	Session sess  = null;
    	sess = getDBSession();
         Query quary = sess.createQuery("from Fabric where hangerNo = :hangerNo").setString(
                  "hangerNo", hangerNo);
         return quary.setMaxResults(1).list().isEmpty();
    }
    
}
