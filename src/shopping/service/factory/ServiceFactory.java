package shopping.service.factory;

import shopping.service.ICategoryService;
import shopping.service.ILoginService;
import shopping.service.IProductService;
import shopping.service.IUserService;
import shopping.service.imp.CategoryServiceImp;
import shopping.service.imp.LoginServiceImpl;
import shopping.service.imp.ProductServiceImpl;
import shopping.service.imp.UserServiceImp;

public class ServiceFactory {
	public static ILoginService getLoginServiceInstance() {
		return new LoginServiceImpl();
		
	}
	public static IProductService getProductSeriveInstance() {
		return new ProductServiceImpl() ;
	}
	public static IUserService getUserServiceInstance() {
		return new UserServiceImp() ;
	}
	public static ICategoryService getICategoryServiceInstance() {
		return new  CategoryServiceImp() ;
	}
}
