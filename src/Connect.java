import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public Statement st; // utk akses database query"
	public ResultSet rs;
	public Connection con;
	public ResultSetMetaData rsm;
	
	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amorepos","root","");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet execQuery (String query) {
		try {
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return rs;
	}
	
	public void insertUser(User us) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
			ps.setString(1, us.getUserid());
			ps.setString(2, us.getFullname());
			ps.setString(3, us.getRole());
			ps.setString(4, us.getEmail());
			ps.setString(5, us.getPassword());
			ps.execute();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	
	public void execUpdate(String query){
		try{
			st = con.createStatement();
			st.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	
	public void updateUser(String userId, String fullName, String role, String email, String password){
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE users" + " SET fullname =  ?"+" ,role = ? "+",email = ? "+",password = ?"+ "WHERE userid = ?");
			ps.setString(1, fullName);
			ps.setString(2, role);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, userId);
			ps.execute();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void deleteUser(String userid){
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE userid = ?");
			ps.setString(1, userid);
			ps.execute();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public String[][] getDataReport(String condition){
    	String[][] data;
    	try {
    		Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(
            		"SELECT transactiondetail.transactionid as 'Transaction ID',"
            		+ " SUM(menu.ingredientprice * transactiondetail.quantity),"
            		+ " SUM(menu.sellprice * transactiondetail.quantity),"
            		+ " SUM(menu.sellprice * transactiondetail.quantity) - SUM(menu.ingredientprice * transactiondetail.quantity)"
            		+ " FROM transactiondetail"
            		+ " INNER JOIN menu ON transactiondetail.menuid = menu.menuid"
            		+ " INNER JOIN transactionheader ON transactiondetail.transactionid = transactionheader.transactionid"
            		+ " WHERE transactionheader.transactiondate LIKE " + condition
            		+ " GROUP BY transactiondetail.transactionid"
            		);
            int i = 0;
            while(rs.next()) {
            	i += 1;
            }
            
            if(i==0) {
            	data = new String[1][4];
            	data[0][0] = null;
        		data[0][1] = null;
        		data[0][2] = null;
        		data[0][3] = null;
            }else {
            	data = new String[i][4];
                stat = con.createStatement();
                rs = stat.executeQuery(
                		"SELECT transactiondetail.transactionid,"
                		+ "SUM(menu.ingredientprice * transactiondetail.quantity),"
                		+ " SUM(menu.sellprice * transactiondetail.quantity),"
                		+ " SUM(menu.sellprice * transactiondetail.quantity) - SUM(menu.ingredientprice * transactiondetail.quantity)"
                		+ " FROM transactiondetail"
                		+ " INNER JOIN menu ON transactiondetail.menuid = menu.menuid"
                		+ " INNER JOIN transactionheader ON transactiondetail.transactionid = transactionheader.transactionid"
                		+ " WHERE transactionheader.transactiondate LIKE " + condition
                		+ " GROUP BY transactiondetail.transactionid"
                		);
                int j = 0;
                while(rs.next()) {
                	data[j][0] = rs.getString(1);
                	data[j][1] = rs.getString(2);
                	data[j][2] = rs.getString(3);
                	data[j][3] = rs.getString(4);
                	j += 1;
                }
            }
    	}catch(Exception e) {
    		System.out.println("SELECT transactiondetail.transactionid as 'Transaction ID',"
            		+ "SUM(menu.ingredientprice * transactiondetail.quantity),"
            		+ "SUM(menu.sellprice * transactiondetail.quantity),"
            		+ "SUM(menu.sellprice * transactiondetail.quantity) - SUM(menu.ingredientprice * transactiondetail.quantity)"
            		+ "FROM transactiondetail"
            		+ "INNER JOIN menu ON transactiondetail.menuid = menu.menuid"
            		+ "INNER JOIN transactionheader ON transactiondetail.transactionid = transactionheader.transactionid"
            		+ "WHERE transactionheader.transactiondate LIKE " + condition
            		+ "GROUP BY transactiondetail.transactionid");
    		data = new String[1][4];
    		data[0][0] = null;
    		data[0][1] = null;
    		data[0][2] = null;
    		data[0][3] = null;
    	}
    	return data;
    }
	
	
}


