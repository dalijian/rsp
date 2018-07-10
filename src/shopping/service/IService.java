package shopping.service;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 
 * @author lijian
 *
 * @param <K>vo类型
 * @param <V>主键字段类型
 */
public interface IService<K,V> {
	/**
	 * K添加数据
	 * @param vo 封装的数据
	 * @return
	 * @throws Exception
	 */
public boolean insert(K vo) ;


/**
 * 修改数据
 * @param vo
 * @return
 * @throws Exception
 */
public boolean update(K vo);
///**
// * 删除数据
// * @param names
// * @return
// * @throws Exception
// */
//public boolean delete(Set<V> names) ;
/**
 * 查询数据 根据主键String name
 * @param name
 * @return
 * @throws Exception
 */
public K get(V name) ;
/**
 * 查询全部数据
 * @return
 * @throws Exception
 */
public List<K> list();

/**
 * 模糊分页查询
 * @param currentPage 当前页面
 * @param lineSize   每页显示的行数
 * @param column    模糊查询字段
 * @param keyWord	模糊查询关键字
 * @return 返回分页记录，用vo封装，和模糊查询得到的用户数量
 * @throws Exception
 */
public Map<V,Object> list(Integer currentPage,String column,Integer lineSize,String keyWord);

/**
 * 判断是否登录成功
 * @param ov 包装好的登录时的username，password ，
 * @return  利用sql 条件查询 username andpassword 判断是否有返回值
 */


}
