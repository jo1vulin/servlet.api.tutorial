package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/listOfData")
public class ListOfUserData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Items Purchased";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<H1>" + title + "</H1>");
		out.println("<form acton=\"listOfData\" method=\"post\">");
		out.println("<input id=\"name\" name=\"name\">");
		out.println("<input type=\"submit\">");
		out.println("</form>");
		out.println("</BODY></HTML>");
	}
	

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		synchronized (session) {
			@SuppressWarnings("unchecked")
			List<String> previousItems = (List<String>) session
					.getAttribute("previousItems");

			if (previousItems == null) {
				previousItems = new ArrayList<String>();
			}
			String newItem = request.getParameter("name");
			if ((newItem != null) && (!newItem.trim().equals(""))) {
				previousItems.add(newItem);
			}

			session.setAttribute("previousItems", previousItems);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String title = "Items Purchased";
			String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
					+ "Transitional//EN\">\n";
			out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title
					+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
					+ "<H1>" + title + "</H1>");
			if (previousItems.size() == 0) {
				out.println("<I>No items</I>");
			} else {
				out.println("<UL>");
				for (String item : previousItems) {
					out.println(" <LI>" + item);
				}
				out.println("</UL>");
			}
			out.println("</BODY></HTML>");
		}

	}

}
