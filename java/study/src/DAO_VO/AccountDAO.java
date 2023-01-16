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
	String sql;
	PreparedStatement ps;
	ResultSet rs;
	
	// 입금 완료 후 잔액 업데이트
	public void update(String accountNo, int tradeBalance) {
		try {
			sql = "update tbl_account set balance = balance + ? where accountNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tradeBalance);
			ps.setString(2, accountNo);
			ps.execute();
			
		} catch(Exception e) {
			System.out.println("update를 확인해주세요. : " + e.toString());
		}
	}
	
	public void withdrawupdate(String accountNo, int tradeBalance) {
		try {
			sql = "update tbl_account set balance = balance - ? where accountNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tradeBalance);
			ps.setString(2, accountNo);
			ps.execute();
			
		} catch(Exception e) {
			System.out.println("update를 확인해주세요. : " + e.toString());
		}
	}
	
	public void withdraw(TradeVO tradeVO) {
		try {
			sql = "insert into tbl_trade(accountNo, tradeTypeNo, tradeBalance) values(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, tradeVO.getAccountNo());
			ps.setString(2, tradeVO.getTradeTypeNo());
			ps.setInt(3, tradeVO.getTradeBalance());
			ps.execute();
		} catch(Exception e) {
			System.out.println("출금 : " + e.toString());
		}
	}
	
	// 입금
	public void deposit(TradeVO tradeVO) {
		try {
			sql = "insert into tbl_trade(accountNo, tradeTypeNo, tradeBalance) values(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, tradeVO.getAccountNo());
			ps.setString(2, tradeVO.getTradeTypeNo());
			ps.setInt(3, tradeVO.getTradeBalance());
			ps.execute();
		} catch(Exception e) {
			System.out.println("insert를 확인해주세요. : " + e.toString());
		}
	}
	
	// 거래내역 확인
	public static String[][] getTrade(String accountNo) {
		Connection con = Database.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		DecimalFormat dfWon = new DecimalFormat("#,###원");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AccountVO accountVO;
		ArrayList<String[]> list;
		String[][] array;
		String sql;
		String accountDate;
		
		try {
			sql = "select * from view_trade where accountNo = ? order by tradeDate desc";	// ** 뷰 다시 생성해야함 **
			ps = con.prepareStatement(sql);
			ps.setString(1, accountNo);
			rs = ps.executeQuery();
			list = new ArrayList<String[]>();
			while(rs.next()) {
				list.add(new String[] {
					rs.getString("accountNo"),
					rs.getString("tradeType"),
					dfWon.format(rs.getInt("tradeBalance")),
					sdf.format(rs.getDate("tradeDate")),
//					dfWon.format(rs.getInt("balance"))
				});
			}
			array = new String[list.size()][4];
			return list.toArray(array);
		} catch(Exception e) {
			System.out.println("getTrade를 확인해주세요. : " + e.toString());
			return null;
		}
	}
	
	// 계좌번호 확인
	public AccountVO read(String accountNo) {
		AccountVO accountVO = new AccountVO();
		try {
			sql = "select * from view_tradeInfo where accountNo = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, accountNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				accountVO = new AccountVO();
				accountVO.setAccountNo(rs.getString("accountNo"));
				accountVO.setAccountName(rs.getString("accountName"));
				accountVO.setBalance(rs.getInt("balance"));
				accountVO.setAccountTypeNo(rs.getString("accountTypeNo"));	// ? 적금이면 출금 불가 ?
				accountVO.setAccountDate(rs.getString("accountDate"));
				accountVO.setPassword(rs.getString("password"));
			}
		}
		catch(Exception e) {
			System.out.println("read를 확인해주세요." + e.toString());
		}
		return accountVO;
	}

	public static String[][] getlist() {
		Connection con = Database.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		DecimalFormat dfWon = new DecimalFormat("#,###원");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AccountVO accountVO;
		ArrayList<String[]> list;
		String[][] array;
		String sql;
		String accountDate;
		
		try {
			sql = "select * from tbl_account order by accountNo";
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
	
	public void insert(TradeVO tradeVO) {
		try {
			sql = "insert into tbl_trade(accountNo, tradeTypeNo, tradeBalance) values(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, tradeVO.getAccountNo());
			ps.setString(2, tradeVO.getTradeTypeNo());
			ps.setInt(3, tradeVO.getTradeBalance());
			ps.execute();
		} catch(Exception e) {
			System.out.println("insert를 확인해주세요. : " + e.toString());
		}
	}
	
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
}
