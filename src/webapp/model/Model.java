/**
 * Varun Pant
 */
package webapp.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * If a database were to be included in the future, this is where the DAO's
 * would be initalised.
 */
public class Model {
	public Model(ServletConfig config) throws ServletException {
		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL = config.getInitParameter("jdbcURL");
	}
}