package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardCommand {

	public abstract String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
}
