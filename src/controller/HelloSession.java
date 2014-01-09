package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class HelloSession extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		synchronized (session) {
			String heading;
			Integer accessCount = (Integer) session.getAttribute("accessCount");
			
			if(accessCount ==null){
				accessCount = 0;
				heading = "Welcome newbie";
			}
			else{
				heading = "Welcome back";
				accessCount++;
				
			}
			session.setAttribute("accessCount", accessCount);
			
			PrintWriter out = response.getWriter();
		
			out.println
			("<!DOCTYPE html>" +
			"<HTML>\n" +
			"<HEAD><TITLE> Session tutorial </TITLE></HEAD>\n" +
			"<BODY BGCOLOR=\"#FDF5E6\">\n" +
			"<CENTER>\n" +
			"<H1>" + heading + "</H1>\n" +
			"<H2>Information on Your Session:</H2>\n" +
			"<TABLE BORDER=1>\n" +
			"<TR BGCOLOR=\"#FFAD00\">\n" +
			" <TH>Info Type<TH>Value\n" +
			" <TD>Number of Previous Accesses\n" +
			" <TD>" + accessCount + "\n" +
			"</TABLE>\n" +
			"</CENTER></BODY></HTML>");
		}
	}

}
