import java.sql.*;
import java.util.*;
public class DBHandler{
        private Connection con=null;
        private PreparedStatement st = null;
        ResultSet rs = null;
        public void insertQuery(String userName,String ipAddress){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Poll_Maker","root","root");
                 st=con.prepareStatement("insert into Users (Username,IPAddress) values(?,?)");
                 st.setString(1,userName);
                 st.setString(2,ipAddress);
                 st.executeUpdate();
            }catch(Exception e){
                System.out.println("Error in insertQuery..!");
            }   
        }
        public void insertQuery(String question,String option1,String option2,String option3){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Poll_Maker","root","root");
                 st=con.prepareStatement("insert into Questions (Question,Option1,Option2,Option3) values(?,?,?,?)");
                 st.setString(1,question);
                 st.setString(2,option1);
                 st.setString(3,option2);
                 st.setString(4,option3);
                 st.executeUpdate();
            }catch(Exception e){
                System.out.println("Error in insertQuery..!");
            }   
        }
        public void selectQuery(){
            try{
               
            }catch(Exception e){
                System.out.println("Error in insertQuery..!");
            }   
        }
}