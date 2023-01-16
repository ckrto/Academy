package DAO_VO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bank.Database;

public class TradeDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//특정 계좌의 거래내역(입출금 메세지 나오게)
	public ArrayList<TradeVO> list(String accountNo){
		ArrayList<TradeVO> array= new ArrayList<TradeVO>();
		try {
			String sql="select * from view_trade where accountNo=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, accountNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				TradeVO vo= new TradeVO();
				vo.setAccountNo(rs.getString("accountNo"));
				vo.setTradeBalance(rs.getInt("tradeBalance"));
				vo.setTradeDate(sdf.format(rs.getDate("tradeDate")));
				vo.setTradeType(rs.getString("tradeType"));
				array.add(vo);
			}
			
		}
		catch(Exception e) {
			System.out.println();
		}
		
		return array;
	}
}

