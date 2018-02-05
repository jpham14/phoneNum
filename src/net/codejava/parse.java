package net.codejava;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;

/**
 * Servlet implementation class parse
 */
@WebServlet("/parse")
public class parse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public parse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PhoneNumberUtil pUtil = PhoneNumberUtil.getInstance();
		String qString = URLDecoder.decode(request.getQueryString(),"UTF-8");
		
		Iterator<PhoneNumberMatch> foundNum = pUtil.findNumbers(qString, "CA").iterator();
		
		response.getWriter().append("[");
		while(foundNum.hasNext()) {
			response.getWriter().append(pUtil.format(foundNum.next().number(), PhoneNumberFormat.NATIONAL))
								.append((foundNum.hasNext())? ", " : "");
		}
		response.getWriter().append("]");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
