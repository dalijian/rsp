package shopping.Dao;

import shopping.vo.Login;

public interface ILoginDao extends IDao<String, Login> {
	public boolean login(Login vo ) throws Exception;

}
