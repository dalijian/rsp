package shopping.dbc;




import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBUtils {
	
	private static final ComboPooledDataSource DATA_SOURCE =new ComboPooledDataSource();
	/**
	 * 从c3p0连接池中得到连接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DATA_SOURCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 得到c3p0连接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return DATA_SOURCE;
	}

	
	
}

//    private static ComboPooledDataSource datasource = new ComboPooledDataSource();
//    private static Connection connection=null;
//    private static PreparedStatement  pstm=null;
//    private static ResultSet rs= null;
//
//    static {
//
//            try {
//                connection = datasource.getConnection();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//    }
//    public static Connection getConnection(){
//        return connection;
//    }



//  public static void release(Connection conn,PreparedStatement pstm,ResultSet rs){
//
//        if(rs != null){
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            rs = null;
//        }
//        if(pstm != null){
//            try {
//                pstm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//           pstm = null;
//        }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            conn = null;
//        }
//    }
//}
