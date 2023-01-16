package DAO_VO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bank.Database;

public class AccountDAO {
	DecimalFormat df = new DecimalFormat("00000");
	Connection con = Database.getConnection();
	public void insert(AccountVO vo) {
		try {
	         String sql="insert into tbl_account values (?,?,?,?,?)";
	         PreparedStatement ps = con.prepareStatement(sql);
	         ps.setString(1,vo.getAccountNo());
	         ps.setString(2,vo.getAccountName());
	         ps.setInt(3,vo.getBalance() );
	         ps.setString(4, vo.getAccountTypeNo());
	         ps.setString(5, vo.getAccountDate());
	         ps.execute();
	      }catch(Exception e) {
	         System.out.println("등록"+e.toString());
	      }
	   }
	//maxNo
	   public String getMaxNo() {
	      String maxNo="";
	      
	      try {
		         String sql="select max(accountno) mano from tbl_account";
		         PreparedStatement ps=con.prepareStatement(sql);
		         ResultSet rs=ps.executeQuery();
		         if(rs.next()) {
		            String newStr=rs.getString("mano"); //newStr : 620-00003;
		            String[] arr=newStr.split("-"); //arr[0]:620; arr[1]:3;
		            String lastNo=df.format(Integer.valueOf(arr[1])+1); //lastNo:3;
		            maxNo = arr[0] + "-" + lastNo;
		            
		         }
		      }catch(Exception e) {
		    	  System.out.println(e.toString());
		      }
	      return maxNo;
	   }
	   //계좌목록
	   public String[][] getlist() {
			PreparedStatement ps;
			ResultSet rs;
			DecimalFormat dfWon = new DecimalFormat("#,###원");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ArrayList<String[]> list;
			String[][] array;
			String sql;
			
			try {
				sql = "select * from tbl_account order by accountno";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				list = new ArrayList<String[]>();
				while(rs.next()) {
					list.add(new String[] {
						rs.getString("accountNo"),
						rs.getString("accountName"),
						dfWon.format(rs.getInt("balance")),
						rs.getString("accountTypeNo"),
						sdf.format(rs.getDate("accountDate"))				
					});
				}
				array = new String[list.size()][5];
				return list.toArray(array);
			} catch(Exception e) {
				System.out.println("ArrayList를 확인해주세요. : " + e.toString());
				return null;
			}
		}
}
