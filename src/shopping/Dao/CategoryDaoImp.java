package shopping.Dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import shopping.dbc.DBUtils;
import shopping.vo.Category;

public class CategoryDaoImp  extends BaseDaoImp<Category> implements ICategory{
	QueryRunner  queryRunner ;
	
	public CategoryDaoImp() {
		super();
		this.queryRunner = new QueryRunner(DBUtils.getDataSource());
	}

	@Override
	public List<Category> findAll() throws SQLException {
		String  sql = "select * from category";
		
		return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
		
	}

}
