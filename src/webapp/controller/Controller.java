/**
 * Varun Pant
 */

package webapp.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webapp.model.Model;

/**
 * Controller is the base class for this application which provides MVC
 * handling.
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		// instantiates DAO's if database were to be implemented
		Model model = new Model(getServletConfig());
		Action.add(new LoginAction(model));
		Action.add(new ManageTweet(model));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = performTheAction(request, response);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and performs it
	 * 
	 * @param request request input
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request, HttpServletResponse response) {
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case.
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// if the next page is null
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}

		// if action
		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		// if view
		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
			d.forward(request, response);
			return;
		}
		response.sendRedirect(nextPage);
		return;
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}