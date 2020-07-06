import java.io.*;
import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 

public class Connect extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String country = req.getParameter("country");
		
		Connection con=null;
		Statement smt=null;
		ResultSet rs=null;	
		
        int count = 0;
		String regx = "^[\\p{L} .'-]+$";
		String regx1 = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$";
		String regx2 = 	"^(1[0-2]|0[1-9])/(3[01]"
                       + "|[12][0-9]|0[1-9])/[0-9]{4}$";

        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(name);
        Pattern pattern1 = Pattern.compile(regx1,Pattern.CASE_INSENSITIVE);
	    Matcher matcher1 = pattern1.matcher(email);
        Pattern pattern2 = Pattern.compile(regx2,Pattern.CASE_INSENSITIVE);
	    Matcher matcher2 = pattern2.matcher(dob);
		if(!matcher.find())
		{
            count++;
			out.write("<center>");
            out.write("<h2>");
            out.println("Name is invalid");
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
		}
		if(age < 18 || age >60)
		{
            count++;
			out.write("<center>");
            out.write("<h2>");
            out.println("Age is invalid");
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
		}
		if(!matcher1.find())
		{
            count++;
			out.write("<center>");
            out.write("<h2>");
            out.println("Enter Valid Email");
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
		}
		if(!matcher2.find())
		{
            count++;
			out.write("<center>");
            out.write("<h2>");
            out.println("Enter DOB in correct format");
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
		}
		if(country.equals("default"))
		{
            count++;
			out.write("<center>");
            out.write("<h2>");
            out.println("Select a country");
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
		}
		if(count == 0 )
        {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");  
				con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/Register","root","");  
				smt=con.createStatement();  
				PreparedStatement st = con.prepareStatement("insert into details values(?, ?, ?, ?, ?)");
				st.setString(1,name);
				st.setInt(2,age);
				st.setString(3,email);
				st.setString(4,dob);
				st.setString(5,country);
				st.executeUpdate();
				rs=smt.executeQuery("select * from details");
				out.write("<table border='1.0'><tr><th>Name</th><th>Age</th><th>Mail</th><th>DOB</th><th>Country</th></tr>");
				while(rs.next()) { 
					out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getInt(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
				}
				out.write("</table></br>");
				con.close();  
			}
			catch(Exception e)
			{
				out.println(e);
			}    
            out.write("<h2>");
            out.println("Last value is inserted is"+name);
            out.write("</h2>");
		}
        else
        {
            res.setHeader("Refresh","5;url=./index.html");
        }
	}
}