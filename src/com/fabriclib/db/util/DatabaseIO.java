package com.fabriclib.db.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.fabriclib.util.Message;

/**
 * 映射数据访问服务类
 * 
 * @author 
 * @since JDK_1.5.0
 */
public class DatabaseIO {

//    private static Properties dbprop;
    
    private static Configuration cfg;

    private static SessionFactory sessionFactory;

    /**
     * 获取全部帐户映射数据
     * 
     * @return
     */
//    @SuppressWarnings("unchecked")
//    protected static List<AccountMappingItem> getAllAccountMappingItem() {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            List<AccountMappingItem> mappingItems = sess.createQuery("from AccountMappingItem order by id").list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

    /**
     * 如果无法从缓存中找到记录，则从数据库中找
     * 
     * @param key
     * @return
     */
//    protected static AccountMappingItem findAccMapItemsNoInCache(String key, String type) {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            Query quary = null;
//            if (type.equals(Mapping.TYPE_FMIS_TO_SAP)) {
//                quary = sess
//                        .createQuery("from AccountMappingItem where sapAccountID = :sapAccountID and sapMID = :sapMID");
//            } else {
//                quary = sess.createQuery("from AccountMappingItem where sapAccountID = :sapAccountID").setString(
//                        "sapAccountID", key);
//            }
//            AccountMappingItem mappinogItem = (AccountMappingItem) quary.setMaxResults(1).uniqueResult();
//            return mappingItem;
//        } catch (Throwable e) {
//            throw new RuntimeException("凭证传递科目-增加帐户映射到map错误：" + e.getMessage(), e);
//        } finally {
//            sess.close();
//        }
//    }

    public static Message save(Object item) throws Exception {
    	
        List<Object> items = new ArrayList<Object>();
        items.add(item);
        return saveOrUpdateList(items);

    }
    
    /**
     * 保存对象列表
     * 
     * @param items
     * @return
     * @throws Exception
     */
    protected static boolean saveList(List<?> items) throws Exception {
        Session sess = null;
        try {
            sess = getDBSession();
            sess.beginTransaction();
            int i = 1;
            for (Object item : items) {
                sess.save(item);
                // 分批提交
                if (i % 20 == 0) {
                    sess.flush();
                    sess.clear();
                }
                i++;
            }
            sess.getTransaction().commit();
            return true;
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
    }

    /**
     * 保存对象列表
     * 
     * @param items
     * @return
     * @throws Exception
     */
    public static boolean delete(Object item) throws Exception {
        Session sess = null;
        try {
            sess = getDBSession();
            sess.beginTransaction();
            sess.refresh(item);
            sess.delete(item);
            sess.getTransaction().commit();
            return true;
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
    }

    public static Message saveOrUpdateList(List<?> items) throws Exception {
        Session sess = null;
        try {
            sess = getDBSession();
            sess.beginTransaction();
            int i = 1;
            for (Object item : items) {
                sess.saveOrUpdate(item);
                // 分批提交
                if (i % 20 == 0) {
                    sess.flush();
                    sess.clear();
                }
                i++;
            }
            sess.getTransaction().commit();
            return new Message("S","save to database successfuly");
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
    }

    /**
     * 获取数据库连接
     * 
     * @return
     */
    protected static Session getDBSession() {
        return getSessionFactory().openSession();
    }

    protected static synchronized Configuration getCfg() {
        if (cfg == null) {
            // get cfg is the first step for all action
            // no cfg no cache
            // so when first get cfg ,then init cluster
//            CommomAccountMappingPluginClusterManager.init();
            cfg = new AnnotationConfiguration().configure();
                      
//            cfg.setProperties(getDbprop());
            updateSchema(cfg);
        }
        return cfg;
    }

    private static void updateSchema(Configuration cfg) {
        SchemaUpdate se = new SchemaUpdate(cfg);
        se.execute(true, true);
    }

    protected static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = getCfg().buildSessionFactory();
        }
        return sessionFactory;
    }


//    public static final String CFG_FILE = "common.accoutmapping.hibernate.cfg.xml";

//    public static Properties getDbprop() {
//        if (dbprop == null) {
////            DbPersistenceServiceFactory dbf = new DbPersistenceServiceFactory();
////            dbprop = dbf.getConfiguration().getProperties();
//        }
//        return dbprop;
//    }

    public static void setDbprop(Properties dbprop) {
//        DatabaseIO.dbprop = dbprop;
    }

//    /**
//     * 协同平台科目映射
//     * 
//     * @param sapAccountID
//     * @return
//     */
//    protected static AccountMappingItem getAccountMappingItem(String sapAccountID,int sxYear) {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            Query query = sess.createQuery("from AccountMappingItem where sapAccountID = :sapAccountID and sxYear=:sxYear order by id")
//                    .setString("sapAccountID", sapAccountID)
//                    .setInteger("sxYear", sxYear);
//            AccountMappingItem mappingItems = (AccountMappingItem) query.setMaxResults(1).uniqueResult();
//            return mappingItems;
//        } catch (Throwable e) {
//            throw new RuntimeException("协同平台映射科目失败：" + e.getMessage(), e);
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }    
//
}
