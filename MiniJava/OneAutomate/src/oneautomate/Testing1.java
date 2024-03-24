package oneautomate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Testing1 {
    String data;
    public Testing1(int value)
    {
    try
    {
         Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/oneautomate","root","admin");
         String q = "select status from approvestatus where admin_no = '" +value+ "'"; 
         PreparedStatement pst=con.prepareStatement(q);
         ResultSet rs1 = pst.executeQuery();
         while (rs1.next()) {
                // Retrieve the data from the result set and store it in a Java string
                data = rs1.getString("status");

                // Do something with the retrieved data
                System.out.println("Retrieved data: " + data);
            }
    }
    catch(SQLException e)
    {
        System.out.print(e);
    }
    }
    public String returnRes()
    {
        return data;
    }
}
