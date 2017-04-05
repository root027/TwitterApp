/**
 * Varun Pant 
 */
package webapp.formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginForm is a formbean which is used for the fields of the form
 */
public class LoginForm {
	private String twitterid;
	private String button;
	private String buttonr;

	public LoginForm(HttpServletRequest request) {
		twitterid = request.getParameter("twitterid");
		button = request.getParameter("Loginbutton");
		buttonr = request.getParameter("Homepage");
	}

	public String getTwitterid() {
		return twitterid;
	}

	public String getButton() {
		return button;
	}

	public String getButtonr() {
		return buttonr;
	}

	public boolean isPresent() {
		if (button == null && buttonr == null)
			return false;
		return true;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (buttonr != null) {
			return errors;
		}
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(twitterid);

		if (matcher.find()) {
			errors.add("Spaces not allowed in twitter id");
			return errors;
		}

		if (twitterid == null || twitterid.trim().length() == 0) {
			errors.add("Twitterid is required");
			return errors;
		}

		if (twitterid.charAt(0) != '@') {
			errors.add("Twitter ID should start with @");
			return errors;
		}

		String twitteridi = twitterid.substring(1, twitterid.length());
		if (twitteridi.trim().length() < 1) {
			errors.add("Twitter id cannot be blank");
			return errors;
		}

		if (!twitteridi.matches("[a-zA-Z0-9_]*")) {
			errors.add("Only use letters, numbers and '_'");
			return errors;
		}

		if (twitterid.length() > 15) {
			errors.add("Twitter ID canot be greater than 15 characters");
			return errors;
		}

		if (button == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		// avoids scripting attacks - cross site scripting
		if (twitterid.matches(".*[<>\"].*"))
			errors.add("TwitterID may not contain angle brackets or quotes");

		return errors;
	}
}