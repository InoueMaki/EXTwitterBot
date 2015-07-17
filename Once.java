package exTwitter;

import java.io.*;
//import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

public class Once extends HttpServlet {
	
	private static final String qry = "select * from once;";
	public static ArrayList<OnceBean> onceList;
	static int arrCount;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect("control");
			return;
		}
		else{
			addOnceList();
			response.sendRedirect("OnceUI.jsp");
			return;
		}
	}
	
	static void addOnceList(){
		try {
			onceList = new ArrayList<OnceBean>();
			arrCount = 0;
			DBManager DBM = new DBManager();
			DBM.getConnection();
			ResultSet rs = DBM.getResultSet(qry);
			
			while(rs.next()){
				OnceBean data = new OnceBean();	
				data.setOnceId(rs.getInt("once_id"));
				data.setReserveTime(rs.getTimestamp("reserve_time"));
				data.setPosted(rs.getInt("posted"));
				data.setText(rs.getString("text"));
				onceList.add(data);
				arrCount++;
			}
			DBM.closeConnection();	
	
		}catch(Exception e){
			System.err.println("failure");
		}
	}
	
}
