import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.lang.*; 

public class SelectedPollServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String question = request.getParameter("ques");
        String selectedOption = request.getParameter("option");
        String ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ipAddress == null)
          ipAddress = request.getRemoteAddr();
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
                           "         height:850px;\n" +
                           "         line-height:20px;\n" +
                           "         border:0.3px solid rgba(246, 246, 246, 0.7);\n" +
                           "         border-radius:3%;\n" +
                           "         align-items:center;\n" +
                           "         position:absolute;\n" +
                           "         margin:3% 28%;\n" +
                           "         background-color:rgba(246, 246, 246, 0.4);\n" +
                           "       }\n" +
                           "         h1{\n" +
                           "         color:#1D0F53;\n" +
                           "         text-shadow:4px 4px 2px white;\n" +
                           "         font-size:45px;\n" +
                           "         padding:20px;\n" +
                           "         text-align:center;\n" +
                           "       }\n" +
                           "        input[type=submit] {\n" +
                           "          width: 15%;\n" +
                           "          font-size:20px;\n" +
                           "          margin-left: 75%;\n" +
                           "          border: 0px;\n" +
                           "          font-weight: bold;\n" +
                           "          padding: 15px;\n" +
                           "          background-color:#1D0F53;\n" +
                           "          color: white;\n" +
                           "          cursor: pointer;\n" +
                           "        }\n" +
                           "\n" +
                           "  #myProgress {\n" +
                           "  width: 100%;\n" +
                           "  background-color: grey;\n" +
                           "}" +
                           ".myBar {\n" +
                           "  width: 0%;\n" +
                           "  height: 30px;\n" +
                           "  background-color: #1D0F53;\n" +
                           "  text-align: center; /* To center it horizontally (if you want) */\n" +
                           "  line-height: 30px; /* To center it vertically */\n" +
                           "  color: white;\n" +
                           "}\n" +
                           "        input[type=submit]:hover {\n" +
                           "          background-color: #f2f8f3;\n" +
                           "          color: #1D0F53;\n" +
                           "        }\n" +
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
                        "\t\t\theight: 43px;\n" +
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
			"\t\t\ttext-align:left;\n"+
                        "\t\t}\n" +
			"\t\t.table {"+
   			"\t\theight: 640px;"+
			"\t\t}\n" +
                        "\t</style>\n" +
                        "\t\n" +
                        "</head>\n" +
                        "<body>\n" +
                            "    <div class=\"main\">\n" +
                            "    <form method=\"POST\" action=\"./total\">\n" +
                            "        <h1>POLL MAKER</h1><br>\n" +
                            "\t\t\t<div class=\"table\">\n" +
                        "\t\t\t\t <table id=\"output\">\n" +
                        "\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t<th> ---- QUESTION ----</th>\n" +
                        "\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t<th>"+question+"</th>\n" +
                        "\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t<th> ---- OPTIONS ----</th>\n" +
                        "\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t</table>\n" +
                        "\t\t\t</div>\n" +
                   "\n" +
                   "<input type='submit' value='OK' name='anspoll'/>\n" +
                   "    </form>\n" +
                   
                   "  </div>\n" +
                   "\n" +
                   "</body>\n" +
                   "</html>");
                   try{
             System.out.println("Try block entered");
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Poll_Maker","root","root");
             PreparedStatement stmt = con.prepareStatement("insert into selectedOption(IPAddress,Question,selectedOption) values(?,?,?)");
             stmt.setString(1,ipAddress);
             stmt.setString(2,question);
             stmt.setString(3,selectedOption);
             stmt.executeUpdate();
             PreparedStatement stmt1 = con.prepareStatement("select Option1,Option2,Option3 from Questions where Question like '"+question+"'");
             ResultSet rs = stmt1.executeQuery();
             String option1=null;
             String option2=null;
             String option3=null;
            while(rs.next()){
                option1 = rs.getString(1);
                option2 = rs.getString(2);
                option3 = rs.getString(3);
                out.println(
                        "<script type='text/javascript'>\n" +
                                "\t\tfunction _(selector){\n" +
                                "\t\t\treturn document.getElementById(selector);\n" +
                                "\t\t}\n" +
                                "\n" +
                                "\t\tvar outputBox = _('output');\n" +
                                "\n" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td>"+option1+"</td></tr>"+'"'+";" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td><div id='myProgress'><div class='myBar' id='myBar1'>0%</div></div></td></tr>"+'"'+";" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td>"+option2+"</td></tr>"+'"'+";" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td><div id='myProgress'><div class='myBar' id='myBar2'>0%</div></div></td></tr>"+'"'+";" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td>"+option3+"</td></tr>"+'"'+";" +
                                "\t\toutputBox.innerHTML +="+'"' +"<tr> <td><div id='myProgress'><div class='myBar' id='myBar3'>0%</div></div></td></tr>"+'"'+";" +
                                "\t\t\n" +
                                "\t</script>\n" +
                                "\t\n" +
                                "</body>\n" +
                                "</html>"
                );
            }
            PreparedStatement stmt2 = con.prepareStatement("select selectedOption,count(selectedOption) from selectedOption where Question like '"+question+"' group by selectedOption");
            ResultSet rs1 = stmt2.executeQuery();
            float optionCount1=0;
            float optionCount2=0;
            float optionCount3=0;
            while(rs1.next()){
              String selectedOp=rs1.getString(1);
              float count=rs1.getFloat(2);
              if(selectedOp.equals(option1)){
                optionCount1=count;
              }
              else if(selectedOp.equals(option2)){
                optionCount2=count;
              }
              else{
                optionCount3=count;
              }
            }
            float op1=(optionCount1/(optionCount2+optionCount1+optionCount3))*100;
            float op2=(optionCount2/(optionCount2+optionCount1+optionCount3))*100;
            float op3=(optionCount3/(optionCount2+optionCount1+optionCount3))*100;  
            int opt1=Math.round(op1);
            int opt2=Math.round(op2);
            int opt3=Math.round(op3);
            out.println("<script type='text/javascript'>\n" +
                   "            function _(selector){\n" +
                   "                return document.getElementById(selector);\n" +
                   "            }\n" +
                   "            _('myBar1').style.width="+opt1+"+'%';"+
                   "            _('myBar1').innerHTML="+opt1+"+'%';"+
                   "            _('myBar2').style.width="+opt2+"+'%';"+
                   "            _('myBar2').innerHTML="+opt2+"+'%';"+
                   "            _('myBar3').style.width="+opt3+"+'%';"+
                   "            _('myBar3').innerHTML="+opt3+"+'%';"+
                   "    </script>");
            con.close();

        }
        catch(Exception e){
            out.println("error");
        }
    }
}




