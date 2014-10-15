package com.fabriclib.db.tables.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.Message;

public class UserIO extends DatabaseIO {

	public static Message save(User item) throws Exception {
		String username = item.getUsername();
		if (notExist(username)) {
			List<User> items = new ArrayList<User>();
			items.add(item);
			return saveOrUpdateList(items);
		} else {
			return new Message("E", "The username " + username + " is existed.");
		}

	}

	public static boolean notExist(String username) {

		Session sess = null;
		sess = getDBSession();
		Query quary = sess.createQuery("from User where username = :username")
				.setString("username", username);
		if (sess!=null) {
			sess.clear();
			sess.close();
		}
		return quary.setMaxResults(1).list().isEmpty();
	}

	public static User login(String username, String password) {

		User user = null;
		Session sess = null;
		try {
			sess = getDBSession();
			Criteria cr = sess.createCriteria(User.class);
			cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password", password));
			cr.setFetchSize(1);
			List<User> list = cr.list();
			if (list != null && list.size() > 0) {
				user = list.get(0);
			}

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			if(sess!=null){
				sess.clear();
				sess.close();
			}
		}

		return user;
	}

}
