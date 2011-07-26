/*
 * @(#)LicenseInfoTag.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.qm.bb6.perception.servlet.AuthenticatedServlet;
import com.qm.bb6.perception.util.BbUtils;

/**
 * Displays copyright, date and license info (in the future perhaps)
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class LicenseInfoTag extends TagSupport {
	
   
    public int doStartTag() throws JspTagException {
		try {

			Locale locale = (Locale) pageContext.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
			
			StringBuffer sBuf = new StringBuffer();
			sBuf.append( "<table border=\"0\" cellspacing=\"1\" cellpadding=\"1\">");
			sBuf.append( "<tr>" );
				sBuf.append( "<td align=\"left\">" );
					sBuf.append( "<div class=\"receiptDate\">");
					sBuf.append( BbUtils.getSafeSystemString("server-time.label", locale ) + ": " );
					sBuf.append( (new java.util.Date().toString()) );
					sBuf.append( "</div>" );
				sBuf.append( "</td>" );
				sBuf.append( "<td align=\"right\">" );
					sBuf.append( "<div class=\"receiptDate\">");
					sBuf.append( "&nbsp; Copyright &copy;2005 Questionmark Ltd.");
					sBuf.append( "</div>" );
				sBuf.append( "</td>" );
			
			sBuf.append( "</tr>");
			sBuf.append( "</table>");
				
		    pageContext.getOut().write( sBuf.toString() );
		    
		} catch (IOException e) {
		    throw new JspTagException("IOException in LicenseInfoTag: " + e);
		}
		return TagSupport.SKIP_BODY;
    }
    
    public int doEndTag() throws JspTagException {
		return TagSupport.EVAL_PAGE;
    }
}
