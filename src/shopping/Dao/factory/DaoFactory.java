package shopping.Dao.factory;

import shopping.Dao.CategoryDaoImp;
import shopping.Dao.ICategory;
import shopping.Dao.IOrder;
import shopping.Dao.IOrderItem;
import shopping.Dao.IProductDao;
import shopping.Dao.IUserDao;
import shopping.Dao.LoginDaoImpl;
import shopping.Dao.OrderDaoImp;
import shopping.Dao.OrderItemDaoImp;
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
	public static IOrder getIOrderInstance(){
		return new OrderDaoImp();
	}
	public static IOrderItem getIOrderItemInstance(){
		return new OrderItemDaoImp();
		
	}
}
