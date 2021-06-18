package jdbc;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class JDBCTest {
	public static void main(String[] args) {
		
		//System.out.println("test");
		
		// 연결 객체 : 연결 정보를 가진다.
		Connection conn = null;
		// sql을 실행할 메소드를 제공한다.
		Statement stmt = null;
		// executeQuery()의 반환타입 -> select의 결과 표(테이블)을 담아 반환하는 객체
		ResultSet rs = null;
		// Statement -> PreparedStatement : 성능개선
		PreparedStatement pstmt = null;
		
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공!");
				
			
			// 2. 연결
			// jdbc : oracle : thin : @localhost : 1521 : xe
			//						  @127.0.01
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("데이터베이스 연결 성공!!!");
			
			
			// 3. sql  처리
			stmt = conn.createStatement();
			
			int dno = 10;
			String otype = "deptno";
			String sqlSelect = 
				"select * from dept where deptno =" + dno + " order by " + otype;
			
			
			rs = stmt.executeQuery(sqlSelect); // 표 형식으로 데이터를 가져옴
			
			// rs.next() -> 다음 행의 존재 유무 확인
			while(rs.next()) {
				int deptno = rs.getInt("deptno");
				System.out.print(deptno + "\t");
				String dname = rs.getString("dname");
				System.out.print(dname + "\t");
				String loc = rs.getString("loc");
				System.out.println(loc + "\t");
				
			}
			
			
			
			///////////////////////////////////////////////
			// PreparedStatement -> Sql을 먼저 등록함 ->
			//	매개변수처럼 ?를 이용해서 나중에 변수를 바인딩처리해줌
			
			System.out.println("=================");
			System.out.println("PreparedStatement 사용");
			
			String sqlSelect2 = 
					"select * from dept where deptno = ? ";
			pstmt = conn.prepareStatement(sqlSelect2);
			// 이후 ? 변수에 데이터 바인딩
			pstmt.setInt(1, 10);
			
			rs = pstmt.executeQuery();	//오버라이딩 된 것을 오버로딩함
			while(rs.next()) {
				int deptno = rs.getInt("deptno");
				System.out.print(deptno + "\t");
				String dname = rs.getString("dname");
				System.out.print(dname + "\t");
				String loc = rs.getString("loc");
				System.out.println(loc + "\t");
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾지못함!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패!!");
			e.printStackTrace();
		}
		finally {
			// 4. close
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
