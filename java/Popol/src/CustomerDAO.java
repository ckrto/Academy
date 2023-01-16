import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerDAO {
    // DB 접속정보
    String driver = "org.h2.Driver";
    // jdbc:h2:~/mydb
    // jdbc:h2:tcp://localhost/~/mydb
    String url = "jdbc:h2:~/mydb";
    String user = "sa";
    String pwd = "";

    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    } // getConnection()
    
    
    public Map<String, Object> getCustomer() {
        Map<String, Object> map = new HashMap<>();
        String[] colNames; // 열이름 => 1차원 배열
        Object[][] data; // 2차원 배열 데이터
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "select * from customer";
            pstmt = con.prepareStatement(sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            // 열의 개수 가져오기
            int colCount = rsmd.getColumnCount();
            System.out.println("열의개수: " + colCount);
            // 행의 개수를 가져오기 위해서 커서를 행마지막으로 옮기기
            rs.last();
            // 행의 개수 가져오기(마지막 행번호가 행의 개수)
            int rowCount = rs.getRow();
            System.out.println("행의개수: " + rowCount);
            
            // 열이름을 저장할 배열객체 생성. 열의크기로 적용.
            colNames = new String[colCount];
            for (int i=0; i<colCount; i++) {
                colNames[i] = rsmd.getColumnName(i+1);
            }
            
            rs.beforeFirst(); // 커서를 원래위치로 설정
            
            // 데이터 => 2차원 배열 저장
            data = new Object[rowCount][colCount];
            for (int r=0; r<rowCount; r++) { // 행
                rs.next();
                for (int c=0; c<colCount; c++) { // 열
                    data[r][c] = rs.getObject(c+1);
                }
            }
            
            map.put("data", data);
            map.put("colNames", colNames);
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			closeJDBC(con, pstmt, rs);
		}
        
        return map;
    } // getCustomer()
    
    
    public int insertCustomer(Map<String, Object> map) {
        int count = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = getConnection();
            String sql = "insert into customer (no,name,email,tel) values (?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, (Integer) map.get("no"));
            pstmt.setString(2, (String) map.get("name"));
            pstmt.setString(3, (String) map.get("email"));
            pstmt.setString(4, (String) map.get("tel"));
            // 실행
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			closeJDBC(con, pstmt, null);
		}
        
        return count;
    } // insertCustomer
    
    public int updateCustomer(Map<String, Object> map) {
    	int count = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
			con = getConnection();
			String sql = "update customer set name = ?, email = ?, tel = ? where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("name"));
			pstmt.setString(2, (String) map.get("email"));
			pstmt.setString(3, (String) map.get("tel"));
			pstmt.setInt(4, (Integer) map.get("no"));
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeJDBC(con, pstmt, null);
		}
    	
    	return count;
    }
    
    public int delete(int no) {
    	int count = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
			con = getConnection();
			String sql = "delete from customer where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeJDBC(con, pstmt, null);
		}
    	
    	return count;
    }
    
    private void closeJDBC(Connection con, PreparedStatement pstmt, ResultSet rs) {
    	
    	if (rs != null) {
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	if (pstmt != null) {
    		try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	if (con != null) {
    		try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    } // closeJDBC()	    
}
