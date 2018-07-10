package shopping.service.factory;

import shopping.service.ICategoryService;
import shopping.service.ILoginService;
import shopping.service.IOrderItemService;
import shopping.service.IOrderService;
import shopping.service.IProductService;
import shopping.service.IUserService;
import shopping.service.imp.CategoryServiceImp;
import shopping.service.imp.LoginServiceImpl;
import shopping.service.imp.OrderItemServiceImp;
import shopping.service.imp.OrderServiceImp;
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
	public static IOrderService getIOrderServiceInstance(){
		return new OrderServiceImp();
		
	}
	public static IOrderItemService getIOrderItemServiceInstance(){
		return new OrderItemServiceImp();
		
	}
	
}
