package com.appspot.projectF.servlet;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelClimate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
	    PersistenceManager pm = null;
	    try {
	        pm = PMF.get().getPersistenceManager();
	        Query query = pm.newQuery(Climate.class);
	        List<Climate> climates = (List<Climate>) query.execute();
	        pm.deletePersistentAll(climates);
	
	    } finally {
	        if (pm != null && !pm.isClosed())
	            pm.close();
	    }
	    resp.sendRedirect("/addclimate");
	}
}
