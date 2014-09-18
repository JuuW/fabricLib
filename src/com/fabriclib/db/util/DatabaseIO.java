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

//    @SuppressWarnings("unchecked")
//    protected static List<WorkFlowMappingItem> getAllWorkflowMapItems() {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            List<WorkFlowMappingItem> mappingItems = sess.createQuery("from WorkFlowMappingItem").list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

//    @SuppressWarnings("unchecked")
//    protected static List<Object[]> getAllMainDataMappingItems() {
//        Session sess = null;
//        try {
//            sess = getDBSession();
//            List<Object[]> mappingItems = sess.createSQLQuery(
//                    "select SAP_MTYPE_,SAP_MID_,FMIS_FTYPE_,FMIS_FID_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA").list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

//    @SuppressWarnings("unchecked")
//    protected static List<Object[]> getAllMainDataMappingItemsWithAllInformation() {
//        Session sess = null;
//        try {
//            sess = getDBSession();
//            List<Object[]> mappingItems = sess
//                    .createSQLQuery(
//                            "select SAP_MTYPE_,SAP_MTYPENAME_,SAP_MID_,SAP_MNAME_,FMIS_FTYPE_,FMIS_FTYPE_NAME_,FMIS_FID_,FMIS_FNAME_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA")
//                    .list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

//    @SuppressWarnings("unchecked")
//    protected static List<CodeIDMappingItem> getAllCodeidMapItems() {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            List<CodeIDMappingItem> mappingItems = sess.createQuery("from CodeIDMappingItem").list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

//    @SuppressWarnings("unchecked")
//    protected static List<ProjectMappingItem> getAllProjectMappingItem() {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            List<ProjectMappingItem> mappingItems = sess.createQuery("from ProjectMappingItem").list();
//            return mappingItems;
//        } finally {
//            sess.clear();
//            sess.close();
//        }
//    }

    /**
     * 如果无法从缓存中找到记录，则从数据库中找 SAP_TO_FMIS key = sapMtype-sapMID FMIS_TO_SAP key =
     * fmisDxlxid-fmisDxid
     * 
     * @param key
     * @return
     */
//    @SuppressWarnings("unchecked")
//    protected static List<MainDataMappingItem> findMainDataItemsNoInCache(String key, String type) {
//        // if (key.trim().endsWith(Mapping.KEYWORD_CONNECTOR) ||
//        // key.trim().startsWith(Mapping.KEYWORD_CONNECTOR)) {
//        // throw new RuntimeException("传入的主数据key内容不足，" + key);
//        // }
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            Query quary = null;
//            if (type.equals(Mapping.TYPE_SAP_TO_FMIS)) {
//                // quary = sess
//                // .createSQLQuery(
//                // "select SAP_MTYPE_,SAP_MID_,FMIS_FTYPE_,FMIS_FID_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA where SAP_MTYPE_ = :sapMtype and SAP_MID_ = :sapMID")
//                // .setString("sapMtype",
//                // key.split(Mapping.KEYWORD_CONNECTOR)[0]).setString("sapMID",
//                // key.split(Mapping.KEYWORD_CONNECTOR)[1]);
//            	System.out.println("key============"+key);
//                quary = sess.createQuery("from MainDataMappingItem where sapMType = :sapMtype and sapMID = :sapMID")
//                        .setString("sapMtype", key.split(Mapping.KEYWORD_CONNECTOR)[0])
//                        .setString("sapMID", key.split(Mapping.KEYWORD_CONNECTOR)[1]);
//            } else {
//                // quary = sess
//                // .createSQLQuery(// 查找无需对象类型
//                // "select SAP_MTYPE_,SAP_MID_,FMIS_FTYPE_,FMIS_FID_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA where  FMIS_FID_ = :fmisDxid")
//                // .setString("fmisDxid",
//                // key.split(Mapping.KEYWORD_CONNECTOR)[1]);
//                quary = sess.createQuery(// 查找无需对象类型
//                        "from MainDataMappingItem where fmisFid=:fmisfid").setString("fmisfid",
//                        key.split(Mapping.KEYWORD_CONNECTOR)[1]);
//            }
//            List<MainDataMappingItem> mappingItems = quary.list();
//            return mappingItems;
//        } catch (Throwable e) {
//            throw new RuntimeException("凭证传递主数据-增加主数据映射到map错误：" + e.getMessage(), e);
//        } finally {
//            sess.close();
//        }
//    }

    /**
     * 如果无法从缓存中找到记录，则从数据库中找 SAP_TO_FMIS key = sapMtype-sapMID FMIS_TO_SAP key =
     * fmisDxlxid-fmisDxid
     * 
     * @param key
     * @return
     */
//    @SuppressWarnings("unchecked")
//    protected static List<AccountMappingItem> findAccountNoInCache(String key, String type) {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            Query quary = null;
//            if (type.equals(Mapping.TYPE_SAP_TO_FMIS)) {
//                quary = sess.createQuery("from AccountMappingItem where sapAccountID=:sapAccountID").setString(
//                        "sapAccountID", key);
//            } else {
//                quary = sess.createQuery(// 查找无需对象类型
//                        "from AccountMappingItem where kmid=:kmid").setLong("kmid", Long.valueOf(key));
//            }
//            List<AccountMappingItem> mappingItems = (List<AccountMappingItem>) quary.list();
//            return mappingItems;
//        } catch (Throwable e) {
//            throw new RuntimeException("凭证传递科目-增加帐户映射到map错误：" + e.getMessage(), e);
//        } finally {
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

//    public static void main(String[] args) {
//        getDBSession();
//    }



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

    /**
     * 协同平台科目映射
     * 
     * @param sapAccountID
     * @return
     */
//    protected static AccountMappingItem getAccountMappingItem(String sapAccountID) {
//        Session sess = null;
//        try {
//            sess = MappingDataDBHepler.getDBSession();
//            Query query = sess.createQuery("from AccountMappingItem where sapAccountID = :sapAccountID order by id")
//                    .setString("sapAccountID", sapAccountID);
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
//    /**
//     * 协同平台主数据映射
//     * 
//     * @param sapMainDataType
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    protected static List<MainDataMappingItem> findFmisMainDataId(String sapMainDataType) {
//        Session sess = null;
//        try {
//            // sess = MappingDataDBHepler.getDBSession();
//            // Query query = sess
//            // .createSQLQuery(
//            // "select SAP_MTYPE_,SAP_MID_,FMIS_FTYPE_,FMIS_FID_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA where SAP_MTYPE_ = :sapMtype")
//            // .setString("sapMtype", sapMainDataType);
//            // List<Object[]> mappingItems = query.list();
//            // return mappingItems;
//            sess = MappingDataDBHepler.getDBSession();
//            Query query = sess.createQuery("from MainDataMappingItem where sapMType = :sapMtype").setString("sapMtype",
//                    sapMainDataType);
//            List<MainDataMappingItem> mappingItems = query.list();
//            return mappingItems;
//        } catch (Throwable e) {
//            throw new RuntimeException("协同平台映射主数据失败：" + e.getMessage(), e);
//        } finally {
//            sess.close();
//        }
//    }

    /**
     * 协同平台主数据映射
     * 
     * @param sapMainDataType
     * @param sapMainDataId
     * @return
     */
//    @SuppressWarnings("unchecked");
//    protected static List<MainDataMappingItem> findFmisMainDataId(String sapMainDataType, String sapMainDataId) {
//        Session sess = null;
//        try {
//            // sess = MappingDataDBHepler.getDBSession();
//            // Query quary = null;
//            // quary = sess
//            // .createSQLQuery(
//            // "select SAP_MTYPE_,SAP_MID_,FMIS_FTYPE_,FMIS_FID_,XTDW_ from NBI_COMMON_MAPPING_MAINDATA where SAP_MTYPE_ = :sapMtype and SAP_MID_ = :sapMID")
//            // .setString("sapMtype", sapMainDataType).setString("sapMID",
//            // sapMainDataId);
//            // List<Object[]> mappingItems = quary.list();
//            // return mappingItems;
//            sess = MappingDataDBHepler.getDBSession();
//            Query quary = null;
//            quary = sess.createQuery("from MainDataMappingItem where sapMType = :sapMtype and sapMID = :sapMID")
//                    .setString("sapMtype", sapMainDataType).setString("sapMID", sapMainDataId);
//            List<MainDataMappingItem> mappingItems = quary.list();
//            return mappingItems;
//        } catch (Throwable e) {
//            throw new RuntimeException("协同平台映射主数据失败：" + e.getMessage(), e);
//        } finally {
//            sess.close();
//        }
//    }
}
