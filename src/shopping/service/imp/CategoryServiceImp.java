package shopping.service.imp;

import java.util.List;

import shopping.Dao.factory.DaoFactory;
import shopping.service.ICategoryService;
import shopping.vo.Category;

public class CategoryServiceImp implements ICategoryService {

	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		return DaoFactory.getICategoryInstance().findAll();
	}

}
