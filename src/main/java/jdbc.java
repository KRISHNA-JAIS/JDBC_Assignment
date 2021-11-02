import java.sql.*;
class jdbc {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Assignment", "root", "Jaiswal#123");

            // Creating Database

            String query = "create database Assignments";

            // Using Database

            String sql = "USE Assignments";

            // Creating tables

            String Table1 = "CREATE TABLE PRODUCT(pid int NOT NULL PRIMARY KEY , pname VARCHAR(40) , price int NOT NULL)";
            String Table2 = "CREATE TABLE CART(pid int NOT NULL PRIMARY KEY , qty int NOT NULL)";

            //Insert into the tables

            String product_insert = "Insert into PRODUCT values(1,'REYNOLDS405_KRISHNA',8),(2,'HB Draw Pencil_KRISHNA',10),(3,'Crayons Rainbow_KRISHNA',12),(4,'Notebook_KRISHNA',14),(5,'Draw Sheets_KRISHNA',16),(6,'Brush Air_KRISHNA',5)";
            String cart_insert = "Insert into CART values(1,2),(3,5),(2,3)";

            Statement stmt = con.createStatement();

            // Executing Queries

            stmt.execute(query);
            stmt.execute(sql);
            stmt.execute(Table1);
            stmt.execute(Table2);
            stmt.execute(product_insert);
            stmt.execute(cart_insert);


            System.out.println("Find total amount to be paid");
            ResultSet rs = stmt.executeQuery("SELECT SUM(price * qty) AS \"TOTAL AMOUNT\" FROM PRODUCT INNER JOIN CART USING (pid);");
            while (rs.next())
                System.out.println(rs.getInt(1)); //+"  "+rs.getInt(2)+"  "+rs.getString(3));

            System.out.println("Find the product name which as sold the most");
            ResultSet result2 = stmt.executeQuery("select pname from PRODUCT where pid=(select pid from CART where qty=(select Max(qty) from CART));");
            while (result2.next())
                System.out.println(result2.getString(1)); //+"  "+rs.getInt(2)+"  "+rs.getString(3));

            System.out.println("Find all the product which are not sold");
            ResultSet result3 = stmt.executeQuery("SELECT a.pname FROM PRODUCT a LEFT OUTER JOIN CART b on (a.pid=b.pid) WHERE b.qty IS NULL;");
            while (result3.next())
                System.out.println(result3.getString(1)); //+"  "+rs.getInt(2)+"  "+rs.getString(3));


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
