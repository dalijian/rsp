package shopping.Dao;

import java.util.List;

import shopping.vo.Category;

public interface ICategory extends BaseDao<Category> {

	List<Category> findAll() throws Exception;

}
