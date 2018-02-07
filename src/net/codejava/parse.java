package net.codejava;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Servlet implementation class parse
 */
@MultipartConfig

@WebServlet(
		urlPatterns = {"/parse", "/parseFile"}
		
)
public class parse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PhoneNumberUtil pUtil = PhoneNumberUtil.getInstance();

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
		String qString = URLDecoder.decode(request.getQueryString(),"UTF-8");
		
		Iterator<PhoneNumberMatch> foundNumbers = pUtil.findNumbers(qString, "CA").iterator();
		ArrayList<PhoneNumber> uniqueNumberList = removeDupeNumbers(foundNumbers);
		sendResponse(uniqueNumberList, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
	    ServletFileUpload uploadedFile = new ServletFileUpload();
	    
	    try {
		    FileItemIterator fileIterator = uploadedFile.getItemIterator(request);
		    FileItemStream fileStream = fileIterator.next();
		    InputStream in = fileStream.openStream();
		    String fString = Streams.asString(in, "UTF-8");

		    Iterator<PhoneNumberMatch> foundNumbers= pUtil.findNumbers(fString, "CA").iterator();
			ArrayList<PhoneNumber> uniqueNumberList = removeDupeNumbers(foundNumbers);
			sendResponse(uniqueNumberList, response);     
	    }catch(FileUploadException ex){
	    	response.getWriter().append("Error: Unable to process the request.");	
	    }
	    
        //String fString = new String(Files.readAllBytes(Paths.get(fileName)));
		

	}
	
	protected ArrayList<PhoneNumber> removeDupeNumbers(Iterator<PhoneNumberMatch> foundNumbers){
		ArrayList<PhoneNumber> uniqueNumberList = new ArrayList<>();
		
		while(foundNumbers.hasNext()) {
			PhoneNumber tempNum = foundNumbers.next().number();
			if(!uniqueNumberList.contains(tempNum)) {
				uniqueNumberList.add(tempNum);
			}
		}
		return uniqueNumberList;
	}
	
	protected void sendResponse(ArrayList<PhoneNumber> uniqueNumberList, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().append("[");
		
		for(int i = 0; i < uniqueNumberList.size(); i++) {
			response.getWriter().append(pUtil.format(uniqueNumberList.get(i), PhoneNumberFormat.NATIONAL))
								.append((i+1 < uniqueNumberList.size())? ", " : "");
		}
		response.getWriter().append("]");
	}
}
