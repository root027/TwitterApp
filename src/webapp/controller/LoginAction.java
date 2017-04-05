/**
 * Varun Pant
 */

package webapp.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import webapp.formbean.LoginForm;
import webapp.model.Model;

/**
 * LoginAction class is used when twitter handle is input in the app.
 */
public class LoginAction extends Action {

	public LoginAction(Model model) {
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		if (request.getParameter("Homepage") != null) {
			return "login.jsp";
		}
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		LoginForm form = new LoginForm(request);
		request.setAttribute("form", form);
		// If no params were passed, return with no errors so that the form will
		// be presented (we assume for the first time).
		if (!form.isPresent()) {
			return "login.jsp";
		}

		// Any validation errors?
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) {
			return "login.jsp";
		}

		String ip1 = form.getTwitterid();
		request.setAttribute("twitterid", ip1);
		request.getSession().setAttribute("twitterid", ip1);
		return "managetweet.do";
	}
}