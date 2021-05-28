import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class PollCreationServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            String question = request.getParameter("question");
            String option1 = request.getParameter("op1");
            String option2 = request.getParameter("op2");
            String option3 = request.getParameter("op3");
            DBHandler db = new DBHandler();
            db.insertQuery(question,option1,option2,option3);
            response.sendRedirect("create.html");
        }catch(Exception e){
            out.println("Error Occurred..!");

        }
    }
 }