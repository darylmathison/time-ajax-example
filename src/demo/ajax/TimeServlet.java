package demo.ajax;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TimeServlet
 */
public class TimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TimeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String timeid = request.getParameter("timeid");
		System.out.println("timeid is " + timeid);

		if (timeid != null && timeid.length() > 0 ) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			
			Calendar cal = null;
			String dateformat = "HH:mm";
			SimpleDateFormat format = null;
			
			if ("local".equals(timeid)) {
				System.out.println("in local");
				cal = Calendar.getInstance();
				format = new SimpleDateFormat(dateformat);
				response.getWriter().write("<time>" + 
						format.format(cal.getTime())+ "</time>");
				
			} else if("GMT".equals(timeid)) {
				
				cal = Calendar.getInstance();
				format = new SimpleDateFormat(dateformat);
				format.setTimeZone(TimeZone.getTimeZone("GMT"));
				
				response.getWriter().write("<time>" + 
						format.format(cal.getTime()) + "</time>");
				
			} else if("newyork".equals(timeid)) {
				
				TimeZone tz = TimeZone.getTimeZone("America/New_York");
				
				cal = Calendar.getInstance();
				format = new SimpleDateFormat(dateformat);
				format.setTimeZone(tz);
				response.getWriter().write("<time>" + 
						format.format(cal.getTime())+ "</time>");
			} else {
				response.getWriter().write("cannot find timeid");
			}
		} else {
			response.getWriter().write("there is no timeid");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
