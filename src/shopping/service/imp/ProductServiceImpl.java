package shopping.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopping.Dao.factory.DaoFactory;
import shopping.service.IProductService;
import shopping.vo.PageBean;
import shopping.vo.Product;

public class ProductServiceImpl implements IProductService {
	
	public Product findById(String id) throws Exception{
		return DaoFactory.getIProductDaoInstancee().findById(id);
	}
	@Override
	public boolean insert(Product vo) {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getIProductDaoInstancee().doCreate(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Product vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getIProductDaoInstancee().findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> list(Integer currentPage, String column, Integer lineSize, String keyWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("select",
					DaoFactory.getIProductDaoInstancee().findAllSplit(currentPage, column, lineSize, keyWord));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	@Override
	public boolean delete(String ids) {
		try {
			return DaoFactory.getIProductDaoInstancee().delete(ids);// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PageBean findHotAll(int  categoryName) throws Exception {
		// TODO Auto-generated method stub
		List<Product> list = DaoFactory.getIProductDaoInstancee().findHotAll(categoryName);
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean findNewAll() throws Exception {
		// TODO Auto-generated method stub
		List<Product> list =  DaoFactory.getIProductDaoInstancee().findNewAll();
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean findPageAll(String cid,String currentPage) throws Exception {
		int amount = DaoFactory.getIProductDaoInstancee().getAllCount(cid).intValue();
		
		Integer currentpage = Integer.parseInt(currentPage);
		String column = "cid";
		Integer lineSize= 10;
		String keyWord = cid;
		Integer totalPage = (int) Math.ceil(amount*1.0/lineSize);
		
		List<Product> list = DaoFactory.getIProductDaoInstancee().findAllSplit(currentpage, column, lineSize, keyWord);
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrPage(currentpage);
		pageBean.setList(list);
		pageBean.setPageSize(lineSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(amount);
		
		return  pageBean;
	}
	@Override
	public Product findByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		return DaoFactory.getIProductDaoInstancee().findById(pid);
	}

}
