package shopping.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shopping.Dao.factory.DaoFactory;
import shopping.service.ILoginService;
import shopping.service.factory.ServiceFactory;
import shopping.vo.Login;

public class LoginServiceImpl  implements ILoginService{
	
	@Override
	public boolean insert(Login vo) {
		// TODO Auto-generated method stub
		if(vo==null) {
			return false;
		}
	
			try {
				return DaoFactory.getILoginDaoInstance().doCreate(vo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	
	}
	@Override
	public boolean update(Login vo) {
		// TODO Auto-generated method stub
		if(vo==null) {
			return false;
		}
		try {
			return DaoFactory.getILoginDaoInstance().doUpdate(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

//	@Override
//	public boolean delete(Set<String> names)  {
//		// TODO Auto-generated method stub
//		if(names==null) {
//			return false;
//		}
//		try {
//			return DaoFactory.getILoginDaoInstance().doRemoveBatch(names);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//		
//	}

	@Override
	public Login get(String name)  {
		// TODO Auto-generated method stub
		if(name==null) {
			return null;
		}
		try {
			return DaoFactory.getILoginDaoInstance().findById(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Login> list() {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getILoginDaoInstance().findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> list(Integer currentPage,  String column,Integer lineSize, String keyWord)
			 {
		// TODO Auto-generated method stub
		Map  map = new HashMap();
		List<Login> list = null;
		try {
			list = DaoFactory.getILoginDaoInstance().findAllSplit(currentPage,  column,lineSize,keyWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		try {
			count = DaoFactory.getILoginDaoInstance().getAllCount(column, keyWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("list_vo", list);
		map.put("count", count);
		return map;
		
		
		
		
		
	}
	@Override
	public boolean login(Login ov) {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getILoginDaoInstance().login(ov);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return false;
	}
	

}
