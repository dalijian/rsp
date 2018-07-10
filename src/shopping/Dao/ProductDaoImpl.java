package shopping.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import shopping.dbc.DBUtils;
import shopping.vo.PageBean;
import shopping.vo.Product;

public class ProductDaoImpl  implements IProductDao {
	QueryRunner queryRunner = null;
	Connection connection = null;
	public ProductDaoImpl() {
		this.queryRunner = new QueryRunner(DBUtils.getDataSource());
	}
	@Override
	public Long getAllCount(String cid) throws Exception {
		String sql = "select count(*) from product where cid =?";
		Object[] params = new Object[]{cid};
		return  (Long) queryRunner.query(sql,new ScalarHandler(), params);
	}
	@Override
	public boolean doCreate(Product vo) throws Exception {
		String sql = "insert into product (pid,pname"
				+ ",market_price,shop_price,pdate,is_hot,pdesc,pflag) values(?,?,?,?,?,?,?,?)";
		Object params[] = { vo.getPid(), vo.getPname(), vo.getMarket_price(), vo.getShop_price(), vo.getPdate(),
				vo.getIs_hot(), vo.getPdesc(), vo.getPflag() };
		int result = queryRunner.update(sql, params);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean doUpdate(Product vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception {
		return false;
	}

	@Override
	public Product findById(String id) throws Exception {
		String sql = "select * from  product where pid =?";
		return queryRunner.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	//查询最新商品
	@Override
	public List<Product> findAll() throws Exception {

		String sql = "select * from product order by pdate desc";

		List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
		return list;// TODO Auto-generated method stub
	}
	@Override
	public List<Product> findAllSplit(Integer currentPage, String column, Integer lineSize, String keyWord) throws Exception {

		String sql = "select * from product where "+column+"  like ? limit ?,?";

		Object[] params = { "%" + keyWord + "%", (currentPage - 1) * lineSize, lineSize };

		// String sql ="select * from product where pname like'%联想%' limit 0,5";

		List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), params);
		return list;
	}
	public boolean delete(String ids) throws Exception {
		String sql = "delete from product where pid =?";
		String dropForiegn = "SET FOREIGN_KEY_CHECKS = 0";
		Connection conn = DBUtils.getConnection();
		conn.prepareStatement(dropForiegn).execute();
		QueryRunner queryRuner = new QueryRunner();

		int result = queryRunner.update(sql, ids);
		if (result > 0) {
			return true;
		} else {
			return false;
		}

	}
	@Override
	public List<Product> findHotAll(int is_hot) throws SQLException {
		String sql = "select * from product where is_hot =?";
		 List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),is_hot);
		return list;
		 
	}

	@Override
	public List<Product> findNewAll() throws Exception {
		String sql = "select * from product order by pdate desc ";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findByCid(String cid) throws Exception {
		String sql = "select * from product where cid = ?";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),cid);
	
	}
	

}
