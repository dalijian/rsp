package shopping.service;

import shopping.vo.User;

public interface IUserService {
	public User login(User vo) throws Exception;
	public  void registry(User vo) throws Exception;
	public User checkUsername(User vo) throws Exception;
	public User active(User vo) throws Exception;
	public void update(User vo) throws Exception;
	public void activeEmail(User vo) throws Exception;
	
	
}
