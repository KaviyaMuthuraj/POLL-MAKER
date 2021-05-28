import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class TotalPollList extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<!DOCTYPE html>\n" +
                           "<html>\n" +
                           "<head>\n" +
                           "    <title>Poll_Maker</title>\n" +
                           "    <style type=\"text/css\">\n" +
                           "        body,html{\n" +
                           "            margin: 0px;\n" +
                           "            box-sizing: border-box;\n" +
                           "            background-image: url('https://www.gtreview.com/wp-content/uploads/2017/07/Abstract-Internet-Data-Technology_News.jpg');\n" +
                           "            background-size: cover;\n" +
                           "        }\n" +
                           "        .main{\n" +
                           "         width:900px;\n" +
                           "         height:800px;\n" +
                           "         line-height:20px;\n" +
                           "         border:0.3px solid rgba(246, 246, 246, 0.2);\n" +
                           "         border-radius:3%;\n" +
                           "         align-items:center;\n" +
                           "         position:absolute;\n" +
                           "         margin:4% 28%;\n" +
                           "         background-color:rgba(246, 246, 246, 0.4);\n" +
                           "       }\n" +
                           "         h1{\n" +
                           "         color:#1D0F53;\n" +
                           "         text-shadow:4px 4px 2px white;\n" +
                           "         font-size:45px;\n" +
                           "         padding:20px;\n" +
                           "         text-align:center;\n" +
                           "       }\n" +
                           "        input[type=button] {\n" +
                           "          width: 18%;\n" +
                           "          font-size:22px;\n" +
                           "          margin-left: 0px;\n" +
                           "          border: 0px;\n" +
                           "          font-weight: bold;\n" +
                           "          padding: 15px;\n" +
                           "          background-color:#1D0F53;\n" +
                           "          color: white;\n" +
                           "          cursor: pointer;\n" +
                           "        }\n" +
                           "\n" +
                           "        input[type=button]:hover {\n" +
                           "          background-color: #f2f8f3;\n" +
                           "          color: #1D0F53;\n" +
                           "        }\n" +
                           "        input[type=submit] {\n" +
                           "          \n" +
                           "          margin:0px;\n" +
                           "          padding:20px;\n" +
                           "          width: 100%;\n" +
                           "          height:100%;\n" +
                           "          ;\n" +
                           "          font-size:22px;\n" +
                           "          background-color: #f2f8f3;\n" +
                           "          color: #1D0F53;\n" +
                           "          cursor: pointer;\n" +
                           "        }\n" +
                           "\n" +
                           "        input[type=submit]:hover {\n" +
                           "          background-color: #1D0F53;\n" +
                           "          color: #f2f8f3;\n" +
                           "        }\n"+
                           "        .btn{\n" +
                           "            margin-top: 50px;\n" +
                           "            text-align: right;\n" +
                           "            margin-right: 90px;\n" +
                           "        }\n" +
                           "\t\ttable,th,td {\n" +
                        "\t\t\tmargin: auto;\n" +
                        
                        "\t\t\tborder-collapse: collapse;\n" +
                        "\t\t\tfont-size: 20px;\n" +
                        "\t\t}\n" +
                        "\t\tth,td {\n" +
                        "\t\t\twidth: 730px;\n" +
                        "\t\t\tpadding: 10px;\n" +
                        "\t\t\theight: 75px;\n" +
                  			"\t\t\tmargin: 30px;\n" +
                        "\t\t\tborder-bottom: 1px solid #B8B7B9;\n" +
                        "\t\t\tbackground-color: rgba(255, 255, 255, 0.7);\n" +
                  			"\t\t\tcolor: rgb(34, 35, 73);\n" +
                        "\t\t}\n" +
                        "\t\tth{ \n" +
                        "\t\t\twidth: 200px;\n" +
                        "\t\t}\n" +
			"\t\ttd{ \n" +
			"\t\t\twidth: 700px;\n" +
			"\t\t\tmargin-left: 40px;\n" +
			"\t\t\ttext-align:center;\n"+
                        "\t\t}\n" +
			"\t\t.table {"+
   			"\t\theight: 500px;"+
    			"\t\toverflow-y: scroll;"+
			"\t\t}\n" +
			"::-webkit-scrollbar {"+
   			"width: 12px;"+
			"}"+

			"::-webkit-scrollbar-track {"+
    			"-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);"+
   			" border-radius: 5px;"+
			"}"+

			"::-webkit-scrollbar-thumb {"+
   			"border-radius: 5px;"+
    			"-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);"+
			"}"+
                        "\t</style>\n" +
                        "\t\n" +
                        "</head>\n" +
                        "<body>\n" +
                            "    <div class=\"main\">\n" +
                            "    <form method=\"POST\" action=\"./answer\">\n" +
                            "        <h1>POLL MAKER</h1><br>\n" +
                            "\t\t\t<div class=\"table\">\n" +
                        "\t\t\t\t <table id=\"output\">\n" +
                        "\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t<th> ---- QUESTIONS ----</th>\n" +
                        "\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t</table>\n" +
                        "\t\t\t</div>\n" +
                      "  <div class=\"btn\">" +
                        "<a href=\"creAnsPoll.html\"><input type=\"button\" name=\"cancel\" value=\"Back\" /></a>\n" +
                   "        </div>\n" +
                   "\n" +
                   "    </form>\n" +
                   "  </div>\n" +
                   "\n" +
                   "</body>\n" +
                   "</html>");
                   try{
            System.out.println("Try block entered");
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Poll_Maker","root","root");
             PreparedStatement stmt = con.prepareStatement("select Question from Questions");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String question = rs.getString(1);
                out.println(
                        "<script type='text/javascript'>\n" +
                                "\t\tfunction _(selector){\n" +
                                "\t\t\treturn document.getElementById(selector);\n" +
                                "\t\t}\n" +
                                "\n" +
                                "\t\tvar outputBox = _('output');\n" +
                                "\n" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td><input type='submit' name='ques' value='"+question+"'/></td></tr>"+'"'+";" +
                                "\t\t\n" +
                                "\t</script>\n" +
                                "\t\n" +
                                "</body>\n" +
                                "</html>"
                );
            }
            con.close();

        }
        catch(Exception e){
            out.println("error");
        }
    }
}
