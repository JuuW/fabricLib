package com.fabriclib.db.tables.user.test;

import junit.framework.Assert;

import org.junit.Test;

import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.tables.user.UserIO;
import com.fabriclib.db.util.DatabaseIO;

public  class UserIOTest extends DatabaseIO {

	@Test
    public void testSave() throws Exception {
		User user =  new User();
    	user.setUsername("233");
    	Assert.assertEquals("S", UserIO.save(user).getMsgType());
    	
    	
    }
    

    
}
