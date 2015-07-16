package exTwitter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnceUpdate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String qry;
	private final String qry1 = "select once_id from numbering;" ;	//採番TBLから値とってくる
	private final String qry2 = "update numbering set once_id = once_id + 1" ;	//採番TBLの更新
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession(false);
		if(session == null){
			RequestDispatcher dispatch = request.getRequestDispatcher("/control");
			dispatch.forward(request , response);
			return;
			}
		
		response.setContentType("text/html;charset=Shift_JIS");

		String text = request.getParameter("text");
		String check[] = request.getParameterValues("chk1");
		int once_id = selectDB(qry1);
		
		if(once_id == -1){
			System.out.println("select error2");
			session.setAttribute("contribution", -1);
			response.sendRedirect("OnceUI.jsp");
			return;
		}else{
			if(updateDB(qry2) == false){
				System.out.println("numbering add error");
				session.setAttribute("contribution", -1);
				response.sendRedirect("OnceUI.jsp");
				return;
			}
		}
		
		if(check!=null){
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String hour = request.getParameter("hour");
			String minute = request.getParameter("minute");
			
			String reserve_time = year + "-" + month + "-" + day +" " + hour + ":" + minute + ":00";
			qry = new String("insert into once values (" +once_id + ", '" + text + "', '" + reserve_time + "', " + 0 + " ); ");
			System.out.println(qry);
		}
		else{
	        Date date = new Date();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
		    System.out.println(sdf1.format(date));
			qry = new String("insert into once values (" + once_id + ", '" + text + "' , '" + sdf1.format(date) + "' , " + 0 + " ); ");
			System.out.println(qry);
		}
		
		if(updateDB(qry)){
			Once.addOnceList();
			session.setAttribute("onceList", Once.onceList);
			session.setAttribute("arrCount", Once.arrCount);
			session.setAttribute("contribution", 1);
			System.out.println("ok");
		}
		else{
			session.setAttribute("contribution", -1);
			System.out.println("ng");
		}
		response.sendRedirect("OnceUI.jsp");
	}

	private boolean updateDB(String qry){
		DBManager DBM = new DBManager();
		DBM.getConnection();
		int inCount = DBM.exeUpdate(qry);
		if(inCount == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	private int selectDB(String qry){
		
		DBManager DBM = new DBManager();
		DBM.getConnection();
		ResultSet rs = DBM.getResultSet(qry);
		int once_id = -1;	//失敗したときに-1を返す
		
		try{
			while(rs.next()){
					once_id = rs.getInt("once_id");
			}
			System.out.println("id:"+once_id);
		}
		catch(Exception e){
			System.err.println("select_error");
		}
		return once_id;
	}
}
