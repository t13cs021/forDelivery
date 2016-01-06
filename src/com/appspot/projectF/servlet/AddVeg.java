package com.appspot.projectF.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddVeg
 */
public class AddVeg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private static final String html = 
			"<html>\n"
			+ "<body>\n"
			+ " <span style=\"font-size: 200%\">Memo</span>\n"
			+ "  <form action=\"/new\" method=\"post\">\n"
			+ "    <div>\n"
			+ "      <textarea name=\"content\" rows=\"2\" cols=\"40\"></textarea>"
			+ "    </div>\n"
			+ "    <input type=\"submit\" value=\"Submit\" />\n"
			+ "  </form>\n"
			+ "</body>\n"
			+"</html>\n"; 
	
    public AddVeg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Web\u30da\u30fc\u30b8\u3092\u4f5c\u308b
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
       response.getWriter().print(html);
	}

}
