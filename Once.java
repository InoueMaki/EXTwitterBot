package exTwitter;

import java.io.*;
//import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class Once extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String qry = "select * from once where posted = 0;";
	public static ArrayList<OnceBean> onceList;
	static int arrCount;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("onceが呼ばれた");
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect("Control");
			return;
		}
		else{
			addOnceList();
			String URL = (String) request.getAttribute("onceURL");
			if(URL == null){
				response.sendRedirect("OnceUI.jsp");
				return;
			}
			else if(URL.equals("OnceUI.jsp")){
				System.out.println(URL);
				response.sendRedirect(URL);
				return;
			}
			else{
				System.out.println(URL);
				response.sendRedirect(URL);
				return;
			}
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
