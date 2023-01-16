package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PurDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JSONObject list(SqlVO vo) {
		JSONObject object=new JSONObject();
		try {
			String sql="call list('purchase',?,?,?,?,?,?)";
			CallableStatement cs=Database.CON.prepareCall(sql);
			cs.setString(1, vo.getKey());
			cs.setString(2, vo.getWord());
			cs.setString(3, vo.getOrder());
			cs.setString(4, vo.getDesc());
			cs.setInt(5, vo.getPage());
			cs.setInt(6, vo.getPer());
			cs.execute();
			
			ResultSet rs=cs.getResultSet();
			JSONArray jArray=new JSONArray();
			while(rs.next()) {
				JSONObject obj=new JSONObject();
				obj.put("order_id", rs.getString("order_id"));
				obj.put("name", rs.getString("name"));
				obj.put("email", rs.getString("email"));
				obj.put("tel", rs.getString("tel"));
				obj.put("address", rs.getString("address"));
				obj.put("pdate", sdf.format(rs.getTimestamp("pdate")));
				obj.put("payType", rs.getString("payType"));				
				obj.put("status", rs.getString("status"));				
				jArray.add(obj);
			}
			object.put("array", jArray);
			
			cs.getMoreResults();
			
			rs=cs.getResultSet();
			int total=0;
			if(rs.next()) total=rs.getInt("total");
			object.put("total", total);
			
			int last=total%vo.getPer()==0 ? total/vo.getPer():
				total/vo.getPer()+1;
			object.put("last", last);
		}catch(Exception e) {
			System.out.println("��ü���:" + e.toString());
		}
		return object;
	}
}