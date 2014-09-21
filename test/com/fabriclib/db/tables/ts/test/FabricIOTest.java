package com.fabriclib.db.tables.ts.test;

import org.junit.Test;

import junit.framework.Assert;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.Jackson;

public  class FabricIOTest extends DatabaseIO {

	@Test
    public void testSave() throws Exception {
    	Fabric fabric =  new Fabric();
    	fabric.setHangerNo("23");
    	Assert.assertEquals("S", FabricIO.save(fabric).getMsgType());
    }
	@Test
	public void testGetByExample() throws Exception{
		Fabric fabric =  new Fabric();
//    	fabric.setHangerNo("23");
    	String s = Jackson.getJson(FabricIO.getByExample(fabric));
    	System.out.println(s);
	}
	
	 
}
