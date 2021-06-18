package jdbc;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class JDBCTestDML_delete {
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
		
		Scanner sc = new Scanner(System.in);
		
		
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
			
			
			// Ʈ����� ���� -> �ؿ� conn.commit(); �Ǳ������� Ŀ�Ե��� ����
			conn.setAutoCommit(false);
			
			
			System.out.println("�μ� ������ ������ �����մϴ�.");
			System.out.println("�μ� ��ȣ�� �Է����ּ���");
			String deptno = sc.nextLine();
		
			
			
			
			// 3. sql  ó��
				// ����ڿ��� ������ �޾� �μ� �����͸� �Է��ϴ� ���α׷��� ������
			String sql = "delete from dept01 where deptno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(deptno));
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println(result + "�� ���� �����Ǿ����ϴ�.");
			} else {
				System.out.println("���ǿ� �´� �����Ͱ� �������� �ʽ��ϴ�.");
			}
			
			
		//Ʈ����� �Ϸ�(����)
		conn.commit();
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� Ŭ������ ã������!!");
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println("�����ͺ��̽� ���� ����!!");
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
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
