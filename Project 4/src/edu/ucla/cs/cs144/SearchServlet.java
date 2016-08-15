package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {

	public SearchServlet() {}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{



		String q = request.getParameter("q");
		request.setAttribute("q", q);

		int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
		request.setAttribute("numResultsToSkip", numResultsToSkip);

		int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));
		request.setAttribute("numResultsToReturn", numResultsToReturn);

		SearchResult[] sr = AuctionSearchClient.basicSearch(q, numResultsToSkip,numResultsToReturn);


		for(int i=0; i<sr.length; i++){
			request.setAttribute("itemId"+i, sr[i].getItemId());
			request.setAttribute("itemName"+i, sr[i].getName());
		}

		request.setAttribute("srLength", sr.length);

		request.getRequestDispatcher("/basicSearchResult.jsp").forward(request, response);


	}
}