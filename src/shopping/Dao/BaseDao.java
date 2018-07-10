package shopping.Dao;

import shopping.vo.User;

/**
 * 
 * @auVhor lijian
 *
 * @param <V>javaBean类型
 * 
 */
public interface BaseDao<V> {
	/**
	 * 
	 * @param v 向数据库添加一条记录  封装javaBean
	 * @return 
	 * @throws Exception
	 */
    void  add(V v) throws Exception;
    /**
     * 
     * @param v 向数据库删除一条记录 封装javaBean
     * @throws Exception
     */
    void delete(V v) throws Exception;
    /**
     * 
     * @param v 向数据库修改一条记录 封装javaBean
     * @throws Exception
     */
    void update(V v) throws Exception;
    /**
     * 默认将javaBean声明第一个属性作为查询字段
     * @param v 向数据库查询一条记录  封装javaBean
     
     * @return 查询结果的第一条记录,封装成javaBean返回
     * @throws Exception
     */
    V select(V v) throws Exception;
}
