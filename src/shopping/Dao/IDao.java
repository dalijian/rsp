package shopping.Dao;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author lijian
 *
 * @param <K>查询数据表的主键类型
 * @param <V>查询的具体vo
 */
public interface IDao<K,V> {
	
/**
 * 添加数据
 * @param vo添加的vo 封装的数据
 * @return true 添加成功，false添加失败
 * @throws Exception
 */
	public boolean doCreate(V vo) throws Exception;
	
	/**
	 * 修改表单数据
	 * @param vo 将数据包装在 vo中
	 * @return 修改成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdate(V vo) throws Exception;
	/**
	 * 删除数据 
	 * @param ids 批量删除根据主键ids 集合删除
	 * @return 删除个数与set.size()相同返回true
	 * @throws Exception
	 */
	public boolean doRemoveBatch(Set<K> ids ) throws Exception;
	/**
	 * 查询数据
	 * @param 根据主键K查询数据，数据以vo封装
	 * @return
	 * @throws Exception
	 */
	public V findById(K k)throws Exception;
	
	public List<V> findAll() throws Exception;
	/**
	 * 分页实现模糊查询，查询结果以集合的形式返回
	 * 
	 * currentPage 当前所在的页
	 * lineSize 每页显示数据行数
	 * column 要进行模糊查询到数据列
	 * keyword 模糊查询的关键字
	 * 
	 * 如果没有数据 集合的长度 0 size()==0,不是 null
	 * 
	 * 
	 * 
	 */
	public List<V> findAllSplit(Integer currentPage,String column,Integer lineSize,String keyWord) throws Exception;
	/**
	 * 统计所有用户的数量
	 * 进行模糊查询信息量的统计
	 * column 模糊查询数据列
	 * keyword 模糊查询的关键字
	 * 
	 * 
	 * 
	 */
	
}
