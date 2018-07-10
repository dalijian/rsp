package shopping.Dao;

import java.util.List;

import shopping.vo.PageBean;
import shopping.vo.Product;

public interface IProductDao extends IDao<String, Product>{
	boolean delete(String ids) throws Exception;

	List<Product> findHotAll(int categoryName) throws Exception;


	List<Product>  findNewAll() throws Exception;


	List<Product> findByCid(String cid) throws Exception;


	Long getAllCount(String cid) throws Exception;
	
}
