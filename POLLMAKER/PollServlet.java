import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class PollServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            String userName = request.getParameter("name");
            String ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
            if (ipAddress == null)
                ipAddress = request.getRemoteAddr();
            DBHandler db = new DBHandler();
            db.insertQuery(userName,ipAddress);
            response.sendRedirect("creAnsPoll.html");
            //out.println("Logined Successfully..!");
        }catch(Exception e){
            out.println("Error Occurred..!");
        }
    }
 }