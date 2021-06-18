package jdbc;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class JDBCTestDML_update {
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
			
			
			// ����ڿ��� ������ �޾� �����͸� ����
			// 10 dev SEOUL ==> " " 
						
			System.out.println("�μ� �������� ������ �����մϴ�.");
			System.out.println("10 dev SEOUL �������� �����͸� �Է����ּ���");
			String input = sc.nextLine();
		
			// String[]
			String[] inputs = input.split(" ");
			
//			for(String str : inputs) {
//				System.out.println(str);
//			}
			
			
			// 3. sql  ó��
							
			String sql = "update dept01 set dname = ?, loc = ? where deptno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputs[1]);
			pstmt.setString(2, inputs[2]);
			pstmt.setInt(3, Integer.parseInt(inputs[0]));
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("�����Ǿ����ϴ�.");
			} else {
				System.out.println("ã���ô� �μ��� �������� �ʽ��ϴ�.");
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
