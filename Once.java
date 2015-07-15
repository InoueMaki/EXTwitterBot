package exTwitter;

import java.io.*;
//import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

public class Once extends HttpServlet {
	
	private static final String qry = "select * from once;";
	static ArrayList<OnceBean> onceList = new ArrayList<OnceBean>();
	static int arrCount;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		HttpSession session = request.getSession(false);

		if(session == null){
			RequestDispatcher dispatch = request.getRequestDispatcher("/control");
			dispatch.forward(request , response);
		}
		
		addOnceList();
		
		if(arrCount >= 0)	session.setAttribute("onceList", onceList);
		session.setAttribute("arrCount", new Integer(arrCount));
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/onceUI.jsp");
		dispatch.forward(request , response);

	}
	
	static void addOnceList(){
		try {
			arrCount=0;
			DBManager DBM = new DBManager();
			DBM.getConnection();
			ResultSet rs = DBM.getResultSet(qry);
			OnceBean data = new OnceBean();	
			
			while(rs.next()){
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

