package shopping.dbc;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DBUtilsTest {

	@Test
	void testGetConnection() throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select count(*) from user";
		
	
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet  rs = pstm.executeQuery();
			while(rs.next()) {
				int count = rs.getInt(1);
				System.out.println(count);
			}
	
		
		
	}

	@Test
	void testRelease() {
		fail("Not yet implemented");
	}

}
