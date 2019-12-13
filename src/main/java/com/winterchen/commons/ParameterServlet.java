package com.winterchen.commons;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;


/**
 * Servlet implementation class for Servlet: System
 *
 */
 public class ParameterServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig config) throws ServletException {
		DictParameter.getInstance();
		super.init(config);
	}	  	    
}
