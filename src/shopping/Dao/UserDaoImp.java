package shopping.Dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import shopping.dbc.DBUtils;
import shopping.vo.User;

public class UserDaoImp extends BaseDaoImp<User> implements IUserDao{
	QueryRunner queryRunner =null;
	public UserDaoImp() {
		this.queryRunner= new QueryRunner(DBUtils.getDataSource());
		
	}
	
	@Override
	public User Login(User vo ) throws Exception {
			String sql = "select * from user where username= ? and password = ? ";
			Object[] params = {vo.getUsername(),vo.getPassword()};
		return queryRunner.query(sql,new BeanHandler<User>(User.class),params);
			
			
		// TODO Auto-generated method stub
	
	}
	@Override
	public User checkUsername(User vo ) throws Exception {
			String sql = "select * from user where username= ? ";
			Object[] params = {vo.getUsername()};
		return queryRunner.query(sql,new BeanHandler<User>(User.class),params);
			
			
		// TODO Auto-generated method stub
	
	}
	@Override
	public User active(User vo) throws Exception {
		String sql = "select * from user where code=? and uid=?";
		Object [] params = {vo.getCode(),vo.getUid()};
		 return queryRunner.query(sql,new BeanHandler<User>(User.class),params);
	}

	@Override
	public void updateState(User vo) throws Exception {
		String sql = "update user set state = 1 where uid = ?";
		queryRunner.update(sql,vo.getUid());
		
	}
	
	
	
}
