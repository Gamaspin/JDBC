package dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DeptMain {
	public static void main(String[] args) {

		Connection conn = null;
		
		
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 연결
			// jdbc : oracle : thin : @localhost : 1521 : xe
			//						  @127.0.01
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
		
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("데이터베이스 연결 성공!!!");

			DeptDao dao = new DeptDao();

			List<Dept> list = dao.getDeptList(conn);
			
			for(Dept dept : list) {
				System.out.println(dept);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("드라이버 로드 성공!");




	}
}
