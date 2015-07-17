package exTwitter;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
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

		System.out.println("onceUpdateが呼ばれた");
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatch = request.getRequestDispatcher("Control");

		if(session == null){
			dispatch.forward(request , response);
			return;
		}

		String check[] = request.getParameterValues("chk1");		
		int once_id = selectDB(qry1);
		request.setAttribute("単発",1);
		
		if(once_id == -1){
			session.setAttribute("contribution", -1);
			dispatch.forward(request , response);
			return;
		}else{
			if(updateDB(qry2) == false){
				session.setAttribute("contribution", -1);
				dispatch.forward(request , response);
				return;
			}
		}
		
		qry = createQry(request , check, once_id);
		
		if(updateDB(qry))	session.setAttribute("contribution", 1);
		else	session.setAttribute("contribution", -1);
		dispatch.forward(request, response);
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
	
	private String createQry(HttpServletRequest request,String[] check, int once_id){
		String qry;
		String text = request.getParameter("text");
		if(check!=null){
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String hour = request.getParameter("hour");
			String minute = request.getParameter("minute");
			
			String reserve_time = year + "-" + month + "-" + day +" " + hour + ":" + minute + ":00";
			qry = new String("insert into once values (" + once_id + ", '" + text + "', '" + reserve_time + "', " + 0 + " ); ");
			System.out.println(qry);
		}
		else{
	        Date date = new Date();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    System.out.println(sdf1.format(date));
			qry = new String("insert into once values (" + once_id + ", '" + text + "' , '" + sdf1.format(date) + "' , " + 0 + " ); ");
			System.out.println(qry);
		}
		return qry;
	}
}
