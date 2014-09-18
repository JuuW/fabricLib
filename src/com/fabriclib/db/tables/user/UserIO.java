package com.fabriclib.db.tables.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.Message;

public  class UserIO extends DatabaseIO {

    public static Message save(User item) throws Exception {
    	String username  = item.getUsername();
    	if(notExist(username)){
    		List<User> items = new ArrayList<User>();
            items.add(item);
            return saveOrUpdateList(items);
    	}else{
    		return new Message("E","The username " +username+ " is existed." );
    	}
    	
    }
    
    public static boolean notExist(String username){
    	
    	Session sess  = null;
    	sess = getDBSession();
         Query quary = sess.createQuery("from User where username = :username").setString(
                  "username", username);
         return quary.setMaxResults(1).list().isEmpty();
    }
    
}
