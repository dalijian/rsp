package shopping.service.imp;

import java.sql.SQLException;

import shopping.Dao.factory.DaoFactory;
import shopping.service.IUserService;
import shopping.vo.User;

public class UserServiceImp  implements IUserService{

	@Override
	public User login(User vo) throws Exception {
		// TODO Auto-generated method stub
		return DaoFactory.getIUserDaoInstance().Login(vo);
		}

	@Override
	public void  registry(User vo) throws Exception {
		// TODO Auto-generated method stub
		DaoFactory.getIUserDaoInstance().add(vo);
	}
	@Override
	public User  checkUsername(User vo) throws Exception {
		// TODO Auto-generated method stub
		return DaoFactory.getIUserDaoInstance().checkUsername(vo);
	}

	@Override
	public User active(User vo) throws Exception {
		// TODO Auto-generated method stub
		return DaoFactory.getIUserDaoInstance().active(vo);
	}

	@Override
	public void update(User vo) throws Exception {
		DaoFactory.getIUserDaoInstance().update(vo);
		
	}

	@Override
	public void activeEmail(User vo) throws Exception {
		DaoFactory.getIUserDaoInstance().updateState(vo);
		
	}
	

}
