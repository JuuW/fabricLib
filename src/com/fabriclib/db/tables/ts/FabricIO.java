package com.fabriclib.db.tables.ts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.db.util.Properties;
import com.fabriclib.util.Message;

public class FabricIO extends DatabaseIO {

	public static Message save(Fabric item) throws Exception {
		String hangerNo = item.getHangerNo();
		String msgStr;
		if (notExist(hangerNo)) {
			List<Fabric> items = new ArrayList<Fabric>();
			items.add(item);
			
			Message message = saveOrUpdateList(items);
			message.setObj(item.getId());
			return message;
		} else {
			
			return new Message("E", Properties.msg3(hangerNo) );
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
			criteria.add(Restrictions.isNull("deleted"));
			items = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}finally{
			if(sess!=null){
				sess.clear();
				sess.close();
			}
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
			if(sess!=null){
				sess.clear();
				sess.close();
			}
		}
		return items;
	}
	
	public static boolean updateDelete(long id) throws Exception {
		boolean result  = false;
		Session sess = null;
		try {

			sess = getDBSession();
			sess.beginTransaction();
			
			Query query = sess.createQuery("update Fabric f set f.deleted = 'T' where id = " + id);  
			query.executeUpdate();  
			// 分批提交
			sess.flush();
			sess.clear();
			sess.getTransaction().commit();
			result  =  true;
		} catch (Exception e) {
			if (sess != null) {
				sess.getTransaction().rollback();
			}
			throw e;
		} finally {
			if (sess != null) {
				sess.close();
			}

		}
		return  result;
	}
	public static boolean updateColumn(long id,String column,String value) throws Exception {
		boolean result  = false;
		Session sess = null;
		try {

			sess = getDBSession();
			sess.beginTransaction();
			
			Query query = sess.createQuery("update Fabric f set f."+column+" = '"+value+"' where id = " + id);  
			query.executeUpdate();  
			// 分批提交
			sess.flush();
			sess.clear();
			sess.getTransaction().commit();
			result  =  true;
		} catch (Exception e) {
			if (sess != null) {
				sess.getTransaction().rollback();
			}
			throw e;
		} finally {
			if (sess != null) {
				sess.close();
			}

		}
		return  result;
	}
}
