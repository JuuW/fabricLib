package com.fabriclib.db.tables.ts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.Message;

public class FabricIO extends DatabaseIO {

	public static Message save(Fabric item) throws Exception {
		String hangerNo = item.getHangerNo();
		if (notExist(hangerNo)) {
			List<Fabric> items = new ArrayList<Fabric>();
			items.add(item);
			return saveOrUpdateList(items);
		} else {
			return new Message("E", "The hangerNo " + hangerNo + " is existed.");
		}

	}

	public static boolean notExist(String hangerNo) {

		Fabric fabric =  new Fabric();
		fabric.setHangerNo(hangerNo);
		
		return getByExample(fabric,1).isEmpty();
		
	}

	public static List<Fabric> getByExample(Fabric fabric) {
		List<Fabric> items = new ArrayList<Fabric>();
		Session sess = null;
		try {
			sess = getDBSession();
			Criteria criteria = sess.createCriteria(Fabric.class);
			criteria.add(Example.create(fabric));
			items = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}finally{
			sess.clear();
			sess.close();
		}
		return items;
	}
	
	public static List<Fabric> getByCriteria(HashMap<String, String> criteria) {
		List<Fabric> items = new ArrayList<Fabric>();
		return items;
//		Criteria c=s.createCriteria(Admin.class);
		//
//				   c.add(Restrictions.eq("aname",name));//eq是等于，gt是大于，lt是小于,or是或
		//
//				   c.add(Restrictions.eq("apassword", password));
		//
//				   List<Admin> list=c.list();
//				   for(Admin admin:list){
	}
	public static List<Fabric> getByExample(Fabric fabric, int lines) {
		List<Fabric> items = new ArrayList<Fabric>();
		Session sess = null;
		try {
			sess = getDBSession();
			Criteria criteria = sess.createCriteria(Fabric.class);
			criteria.setFetchSize(lines);
			criteria.add(Example.create(fabric));
			items = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}finally{
			sess.clear();
			sess.close();
		}
		return items;
	}
}
