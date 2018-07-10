package shopping.Dao;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import shopping.dbc.DBUtils;

//通用DAO select 传递的javaBean 在 servlet 中调用setID 设置查询 条件where 字段

public class BaseDaoImp<V> implements BaseDao<V> {

    /** 操作常量 */
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_SELECT = "select";

    private Class<V> EntityClass; // 获取实体类
    private String sql;          
    private Object argType[];

    
    private QueryRunner queryRunner;
    
    @SuppressWarnings("unchecked")
    public BaseDaoImp() {
        
        /**
         *  传递User就是 com.example.daoimp.BaseDaoImp<com.example.bean.User>
         *  传递Shop就是 com.example.daoimp.BaseDaoImp<com.example.bean.Shop>
         * */
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();   //ParameterizedType 参数化类型，就是泛型，   
        
        /**
         * 这里如果传递的是User.那么就是class com.example.bean.User 
         * 如果传递的是Shop.       那么就是class com.example.bean.Shop
         * */
        
        EntityClass = (Class<V>) type.getActualTypeArguments()[0];  //获得泛型实际类型，就是 User类的Class对象， Class是所有自定义类的类，所以可以用来接收自定义类
        
        this.queryRunner = new QueryRunner(DBUtils.getDataSource());
        
    }

    @Override
    public void add(V v) throws IllegalArgumentException, SQLException, IllegalAccessException {
    	
        // TODO Auto-generated method stub
        sql = this.getSql(SQL_INSERT);   //获取sql.
        // 赋值.
        
            argType = setArgs(v, SQL_INSERT);
            queryRunner.update(sql,argType);
       
    }
//            statement = DBUtils.getPreparedStatement(sql);  //实例化PreparedStatement.
//            //为sql语句赋值.
//            statement = DBUtils.setPreparedStatementParam(statement,
//                    argType);
//            statement.executeUpdate(); //执行语句.
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            DBUtils.release(statement, null);  //释放资源.
//        }
//    }

    @Override
    public void delete(V v) throws IllegalArgumentException, IllegalAccessException, SQLException {
    	sql = this.getSql(SQL_DELETE);
    	
    		argType=this.setArgs(v, SQL_DELETE);
    		queryRunner.update(sql,argType);
    		
    	
    }
//        // TODO Auto-generated method stub
//        sql = this.getSql(SQL_DELETE);
//        try {
//            argType = this.setArgs(t, SQL_DELETE);
//            statement = DBUtils.getPreparedStatement(sql);
//            statement = DBUtils.setPreparedStatementParam(statement,
//                    argType);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            DBUtils.release(statement, null);
//        }
//    }

    @Override
    public void update(V v) throws SQLException, IllegalArgumentException, IllegalAccessException 
    {
    	sql = this.getSql(SQL_UPDATE);
    
    		argType=this.setArgs(v, SQL_UPDATE);
    		queryRunner.update(sql,argType);
    		
    	
    }
//        // TODO Auto-generated method stub
//        sql = this.getSql(SQL_UPDATE);
//        try {
//            argType = setArgs(t, SQL_UPDATE);
//            statement = DBUtils.getPreparedStatement(sql);
//            statement = DBUtils.setPreparedStatementParam(statement,
//                    argType);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            DBUtils.release(statement, null);
//        }
//    }

    @Override
    public V select(V v) throws SQLException, IllegalArgumentException, IllegalAccessException 
    {	V result =null;
    	sql = this.getSql(SQL_SELECT);
    
    		argType=this.setArgs(v, SQL_SELECT);
    		 result  = queryRunner.query(sql, new BeanHandler<V>((Class<V>) v.getClass()),argType);
    		
    	
    	return result;
    }
//        // TODO Auto-generated method stub
//        sql = this.getSql(SQL_SELECT);
//        T obj = null;
//        try {
//            argType = setArgs(t, SQL_SELECT);
//            statement = DBUtils.getPreparedStatement(sql);
//            statement = DBUtils.setPreparedStatementParam(statement,
//                    argType);
//            rs = statement.executeQuery();
//            Field fields[] = EntityClass.getDeclaredFields();
//            while (rs.next()) {
//                obj = EntityClass.newInstance();
//                for (int i = 0; i < fields.length; i++) {
//                    fields[i].setAccessible(true);
//                    fields[i].set(obj, rs.getObject(fields[i].getName()));
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return obj;
//
//    }

    // sql拼接函数 形如 : insert into User(id,username,password,email,grade) values(?,?,?,?,?)
    private String getSql(String operator) {

        StringBuffer sql = new StringBuffer();
        // 通过反射获取实体类中的所有变量
        Field fields[] = EntityClass.getDeclaredFields();

        // 插入操作
        if (operator.equals(SQL_INSERT)) {
            sql.append("insert into " + EntityClass.getSimpleName());// getSimpleName() 得到类名，例如String.class.getSimpleName() -->String
            sql.append("(");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true);    //这句话必须要有,否则会抛出异常.//修改访问权限，getAccessible()设置成true，java类加载器会不进行访问限制检查
                String column = fields[i].getName();
                sql.append(column).append(",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(") values (");
            for (int i = 0; fields != null && i < fields.length; i++) {
                sql.append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);//也可以这么写 sql.delete(sql.length()-1, sql.length()).append(")");
            // 是否需要添加分号
            sql.append(")");
        } else if (operator.equals(SQL_UPDATE)) {
            sql.append("update " + EntityClass.getSimpleName() + " set ");//update tablename set "
            for (int i = 1; fields != null && i < fields.length; i++) {// 将set 写活，where 查询字段 使用fileds[0]
                fields[i].setAccessible(true);
                String column = fields[i].getName();
//                if (column.equals("id")) {						
//                    continue;								
//                }
                sql.append(column).append("=").append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where "+fields[0].getName()+"=?");
        } else if (operator.equals(SQL_DELETE)) {
            sql.append("delete from " + EntityClass.getSimpleName()
                    + " where "+fields[0]+"=?");		//使用fields[0] 作为删除字段
        } else if (operator.equals(SQL_SELECT)) {
            sql.append("select * from " + EntityClass.getSimpleName()
                    + " where +"+fields[0]+" =?");        //使用fields[0] 作为查询字段
        }
        return sql.toString();
    }

    // 获取参数.
    private Object[] setArgs(V entity, String operator)
            throws IllegalArgumentException, IllegalAccessException {

        Field fields[] = EntityClass.getDeclaredFields(); //得到属性
        if (operator.equals(SQL_INSERT)) {					//如果是insert 添加记录

            Object obj[] = new Object[fields.length];		//创建数组
			for (int i = 0; obj != null && i < fields.length; i++) {
				fields[i].setAccessible(true);
                obj[i] = fields[i].get(entity);		 //得到属性对象的值
            }
            return obj;								//得到insert into table 的参数

        } else if (operator.equals(SQL_UPDATE)) {

            Object Tempobj[] = new Object[fields.length];  
            for (int i = 0; Tempobj != null && i < fields.length; i++) {
                fields[i].setAccessible(true);
                Tempobj[i] = fields[i].get(entity);
            }

            Object obj[] = new Object[fields.length];
            System.arraycopy(Tempobj, 1, obj, 0, Tempobj.length - 1);//创建新数组obj[],将tempobj复制给obj，tempobj元素依次向前移动一位，第一位元素是 id 移到obj最后，，update 更新 where 使用id字段，
            obj[obj.length - 1] = Tempobj[0]; 
            return obj;
//            Object index_0 = Tempobj[0];          基础写法使用数组
//            for(int i =0;i<Tempobj.length-1;i++){
//            	Tempobj[i]=Tempobj[i+1];
//            }
//            Tempobj[Tempobj.length-1]=index_0;
//            return Tempobj;
       
            
           

        } else if (operator.equals(SQL_DELETE)) {

            Object obj[] = new Object[1];
            fields[0].setAccessible(true);
            obj[0] = fields[0].get(entity);//使用第一个字段值 作为删除 字段值
            return obj;
        } else if (operator.equals(SQL_SELECT)) {

            Object obj[] = new Object[1];
            fields[0].setAccessible(true);
            obj[0] = fields[0].get(entity);//使用第一个字段值 作为查询字段值
            return obj;
        }
        return null;
    }

}