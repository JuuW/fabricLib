package com.fabriclib.db.tables.ts.test;

import org.junit.Test;

import junit.framework.Assert;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.util.DatabaseIO;

public  class FabricIOTest extends DatabaseIO {

	@Test
    public void testSave() throws Exception {
    	Fabric fabric =  new Fabric();
    	fabric.setHangerNo("23");
    	Assert.assertEquals("S", FabricIO.save(fabric).getMsgType());
    }
}
