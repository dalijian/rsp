package shopping.Dao;

import shopping.vo.User;

public interface IUserDao extends BaseDao<User > {
public User Login(User vo ) throws Exception;

public User checkUsername(User vo) throws Exception;
public User active(User vo) throws Exception;

public void updateState(User vo) throws Exception;


}
