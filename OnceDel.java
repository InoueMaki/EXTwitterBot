package exTwitter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnceDel extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String qry;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		System.out.println("onceDelが呼ばれた");
		HttpSession session = request.getSession(false);
		if(session == null){
			RequestDispatcher dispatch = request.getRequestDispatcher("Control");
			dispatch.forward(request , response);
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("単発削除",1);
		session.setAttribute("onceURL","OnceDelUI.jsp");
		Enumeration<?> names = request.getParameterNames();
		String once_id = new String();
		
		while (names.hasMoreElements()){
		      once_id = (String)names.nextElement();
		}
		qry = "update once set posted = 1 where once_id = " + once_id + ";" ;
		System.out.println(qry);
		updateDB(qry);
		RequestDispatcher rdisp = request.getRequestDispatcher("Control");
		rdisp.forward(request, response);
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
}
