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
		
		// ���� ��ü : ���� ������ ������.
		Connection conn = null;
		// sql�� ������ �޼ҵ带 �����Ѵ�.
		Statement stmt = null;
		// executeQuery()�� ��ȯŸ�� -> select�� ��� ǥ(���̺�)�� ��� ��ȯ�ϴ� ��ü
		ResultSet rs = null;
		// Statement -> PreparedStatement : ���ɰ���
		PreparedStatement pstmt = null;
		
		try {
			// 1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����!");
				
			
			// 2. ����
			// jdbc : oracle : thin : @localhost : 1521 : xe
			//						  @127.0.01
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("�����ͺ��̽� ���� ����!!!");
			
			
			// 3. sql  ó��
			stmt = conn.createStatement();
			
			int dno = 10;
			String otype = "deptno";
			String sqlSelect = 
				"select * from dept where deptno =" + dno + " order by " + otype;
			
			
			rs = stmt.executeQuery(sqlSelect); // ǥ �������� �����͸� ������
			
			// rs.next() -> ���� ���� ���� ���� Ȯ��
			while(rs.next()) {
				int deptno = rs.getInt("deptno");
				System.out.print(deptno + "\t");
				String dname = rs.getString("dname");
				System.out.print(dname + "\t");
				String loc = rs.getString("loc");
				System.out.println(loc + "\t");
				
			}
			
			
			
			///////////////////////////////////////////////
			// PreparedStatement -> Sql�� ���� ����� ->
			//	�Ű�����ó�� ?�� �̿��ؼ� ���߿� ������ ���ε�ó������
			
			System.out.println("=================");
			System.out.println("PreparedStatement ���");
			
			String sqlSelect2 = 
					"select * from dept where deptno = ? ";
			pstmt = conn.prepareStatement(sqlSelect2);
			// ���� ? ������ ������ ���ε�
			pstmt.setInt(1, 10);
			
			rs = pstmt.executeQuery();	//�������̵� �� ���� �����ε���
			while(rs.next()) {
				int deptno = rs.getInt("deptno");
				System.out.print(deptno + "\t");
				String dname = rs.getString("dname");
				System.out.print(dname + "\t");
				String loc = rs.getString("loc");
				System.out.println(loc + "\t");
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� Ŭ������ ã������!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����!!");
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
