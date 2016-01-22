package com.appspot.projectF.servlet;

import java.io.IOException;
import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.projectF.datastore.*;
import com.appspot.projectF.util.*;

public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str = req.getParameter("pref");
		String veg = req.getParameter("yasai");
		
		
		PersistenceManager pm = null;
		//DB開けなかったら，開けてもデータストアを閉じる
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Crops.class);

			List<Crops> allcrops = (List<Crops>) query.execute();
			List<Crops> crops = new ArrayList<Crops>();
			for (Crops sr : allcrops) {
				if (veg.equals(sr.getName())) {
					crops.add(sr);
				}
			}
			Sort.sortCropsForMonth(crops);
			req.setAttribute("crops", crops);
		} finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
		
		req.setAttribute("prefName", str);
		req.setAttribute("vegName", veg);
		
		req.getRequestDispatcher("/detail.jsp").forward(req, resp);
	}
}
