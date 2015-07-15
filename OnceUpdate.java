package exTwitter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnceUpdate extends HttpServlet {
	
	private String qry;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession(false);

		if(session == null){
			RequestDispatcher dispatch = request.getRequestDispatcher("/control");
			dispatch.forward(request , response);
			}

		String text = (String) session.getAttribute("text");
		Calendar cal_time = (Calendar) session.getAttribute("reserve_time");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String reserve_time = sdf.format(cal_time.getTime());
		qry = new String("insert into once values (" + text + ", " + reserve_time + ", " + 0 + " ); ");
		
		if(insertDB(qry)){
			Once.addOnceList();	//addOnceListÇÃçXêV
			session.setAttribute("onceList", Once.onceList);
			session.setAttribute("arrCount", Once.arrCount);
			session.setAttribute("contribution", 1);
		}
		else{
			session.setAttribute("contribution", -1);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/onceUI.jsp");
		dispatch.forward(request , response);
	}

	boolean insertDB(String qry){
		DBManager DBM = new DBManager();
		DBM.getConnection();
		int inCount = DBM.exeUpdate(qry);
		DBM.closeConnection();
		if(inCount == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
}
