package dept;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeptDao {

	// 1. ��ü ������ �˻� ���
	//  ��ȯ Ÿ�� List<Dept>
	// �Ű����� - Connection ��ü �ʿ� (Statement/PreparedStatement ��ü�� ���� �ʿ���)
	ArrayList<Dept> getDeptList(Connection conn) {
		
		ArrayList<Dept> list = null;
		
		// ������ ���̽��� Dept ���̺� �̿��ؼ� select ����� -> list�� ����
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from dept order by deptno";
			
			// ��� �޾ƿ���
			rs = stmt.executeQuery(sql);
			
			// �����͸� Dept ��ü�� ���� -> list�� ����
			while(rs.next()) {
				list.add(new Dept(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
		
	}
	
}
