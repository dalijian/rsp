package shopping.service;
/**
 *login 业务层接口
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.Set;

import shopping.vo.Login;

public interface ILoginService  extends IService<Login, String>{

public boolean login(Login ov) ;

}

