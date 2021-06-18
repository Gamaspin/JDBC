package jdbc;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class JDBCTestEMP {
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
				// ��� ��ȣ, ��� �̸�, ����, sal, �μ��̸�, �μ���ġ
			
			
			// ��) Statement
			stmt = conn.createStatement();
			
			// ��) sql �ۼ� (�����ϴ� sql)
			String sql = "select e.empno, e.ename, e.job, e.sal, d.dname, d.loc\r\n"
					+ "from emp e, dept d\r\n"
					+ "where e.deptno = d.deptno\r\n"
					+ "order by e.ename";
			
			// ��) ResultSet ��ü�� ������ �ޱ�
			rs = stmt.executeQuery(sql);
			
			// ��) ���
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"
									+rs.getString(2)+"\t"
									+rs.getString(3)+"\t"
									+rs.getInt(4)+"\t"
									+rs.getString(5)+"\t"
									+rs.getString(6)
									);
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
