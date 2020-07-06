import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;

public class ValServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String country = req.getParameter("country");
                if(name.isEmpty() && age.isEmpty() && email.isEmpty() && dob.isEmpty())
                 {
                           out.write("<center>");
            out.write("<h2>");
            out.println(Must enter the inputs");
            out.write("</h2>");
            out.write("</center>"); 
                 }
        int count = 0;,validate=0;
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
            out.println("Email is in correct format");
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
            out.write("<center>");
            out.write("<h2>");
            out.println("All values are entered correctly");
            out.write("<br>");
            out.write("<br>");
            out.write("<br>");
            out.write("<br>");
            out.write("<br>");
            out.write("<br>");
            out.print("WELCOME "+name);
            out.write("<br>");
            out.write("</h2>");
            out.write("</center>");
        }
        else
        {
            out.write("<center>");
            out.write("<h2>");
            out.println(Must enter the inputs");
            out.write("</h2>");
            out.write("</center>");
        }
	}
}
