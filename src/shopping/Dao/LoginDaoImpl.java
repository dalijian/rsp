package shopping.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import shopping.dbc.DBUtils;
import shopping.vo.Login;

public class LoginDaoImpl implements ILoginDao   {
	private QueryRunner queryRunner = null;
	
	/**
	 * 从DBUtils得到连接，不需要实例化Connection
	 * 在数据层关闭DBUtils连接
	 */
	public LoginDaoImpl() {
		this.queryRunner = new QueryRunner(DBUtils.getDataSource());
		
	}
	
//		//在模糊分页查询 先模糊查询，在分页
//	@Override
//	public List<Login> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
//			throws Exception {
//		
//			String sql = "select username,password from login limit where "+column+"like ? limit"+
//					+ +(currentPage-1)*lineSize+","+currentPage*lineSize;
//			this.pstm=this.conn.prepareStatement(sql);
//			this.pstm.setString(1, "%"+keyWord+"%");
//			rs=this.pstm.executeQuery();
//			List<Login> findSplitAll = new ArrayList<Login>();
//			while(rs.next()) {
//				Login vo = new Login();
//				vo.setUsername(rs.getString("username"));
//				vo.setPassword(rs.getString("password"));
//				findSplitAll.add(vo);
//			}
////			DBUtils.release(conn, pstm, rs);
//			return findSplitAll;
//	}
//				
//			
//			
//
//	@Override
//	public Integer getAllCount(String column, String keyWord) throws Exception {
//		String sql = "select count( username) from login where "+column +"like ?";
//		this.pstm = this.conn.prepareStatement(sql);
//		this.pstm.setString(1, "%"+keyWord+"%");
//		ResultSet rs = this.pstm.executeQuery();
//		int result=0;
//		while(rs.next()){
//			result = rs.getInt(1);
//		}
//		// TODO Auto-generated method stub
////		DBUtils.release(conn, pstm, rs);
//		return result;
//	}
	

	@Override
	public boolean doCreate(Login vo) throws Exception {
		 String sql = "insert into login (id,username,password,"
		 		+ "name,email,sex,birthday,telephone) values("
		 		+ "?,?,?,?,?,?,?,?)";
		 
		 Object[] params = { vo.getId(),vo.getUsername(), vo.getPassword(), vo.getEmail(), vo.getName(), vo.getSex(),
					vo.getBirthday(),vo.getTelephone() };
			int result = queryRunner.update(sql, params);
		if(result!=0) {
				return true;
		}else {
			return false;
		}
		
		 // TODO Auto-generated method stub
	
	}

	@Override
	public boolean doUpdate(Login vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Login findById(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(Login vo) throws Exception {
			String sql = "select * from login where username= ? and password = ? ";
			Object[] params = {vo.getUsername(),vo.getPassword()};
		Login result = 	queryRunner.query(sql,new BeanHandler<Login>(Login.class),params);
			if(result!= null) {
				return true;
			}else {
				return false;
			}
			
		// TODO Auto-generated method stub
	
	}

	@Override
	public List<Login> findAllSplit(Integer currentPage, String column, Integer lineSize, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Login v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Login v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Login v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Login select(Login v) {
		// TODO Auto-generated method stub
		return null;
	}
	

	


}
