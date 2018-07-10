package shopping.service;

import shopping.vo.PageBean;
import shopping.vo.Product;

public interface IProductService extends IService<Product, String> {
	boolean delete(String ids);




	PageBean findHotAll(int categoryName) throws Exception;



	PageBean findNewAll()throws Exception;



	PageBean findPageAll(String cid,String currentPage) throws Exception;




	Product findByPid(String pid) throws Exception;
	

}
