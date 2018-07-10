package shopping.Dao.factory;

import shopping.Dao.CategoryDaoImp;
import shopping.Dao.ICategory;
import shopping.Dao.IProductDao;
import shopping.Dao.IUserDao;
import shopping.Dao.LoginDaoImpl;
import shopping.Dao.ProductDaoImpl;
import shopping.Dao.UserDaoImp;

public class DaoFactory {
	
	public static LoginDaoImpl getILoginDaoInstance() {
		return new LoginDaoImpl();
		
	}
	public static IProductDao getIProductDaoInstancee() {
		return new ProductDaoImpl();
	}
	public static IUserDao getIUserDaoInstance() {
		return new UserDaoImp();
		
	}
	public static ICategory getICategoryInstance() {
		return new CategoryDaoImp();
		
	}
}
