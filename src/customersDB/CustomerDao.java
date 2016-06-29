package customersDB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerDao {
	public Set<Integer> ids;

	public List<Customer> getRecordLastName(String lastName) {
		ids = new HashSet<Integer>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement sql = null;
		List<Customer> list = new ArrayList<Customer>();
		try {
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			sql = con.prepareStatement("SELECT a.customerid, a.title," 
					+ " a.firstname,"
					+ " a.lastname," 
					+ " e.company," 
					+ " a.streetaddress," 
					+ " b.city,"
					+ " c.state," 
					+ " a.zipcode," 
					+ " a.emailaddress," 
					+ " d.position" 
					+ " FROM customers a"
					+ " JOIN companies e" 
					+ " ON a.companyID = e.companyid" 
					+ " JOIN cities b"
					+ " ON a.cityID = b.cityid" 
					+ " JOIN states c" 
					+ " ON a.stateid = c.stateid"
					+ " JOIN positions d" 
					+ " ON a.positionid  = d.positionid" 
					+ " WHERE a.lastname   = ?");
			sql.setString(1, lastName);
			rs = sql.executeQuery();
			
			while (rs.next()) {
				ids.add(rs.getInt(1));
				System.out.println(rs.getInt(1));
				list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)
						,rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
						,rs.getString(10),rs.getString(5),rs.getString(11)));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				sql.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int updateAddress(int id, String address) {
		Connection con = null;
		int rs = 0;
		PreparedStatement sql = null;
		try {
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			sql = con.prepareStatement("UPDATE CUSTOMERS SET STREETADDRESS = ?" 
					+ " WHERE CUSTOMERID   = ?");
			sql.setString(1, address);
			sql.setInt(2, id);
			rs = sql.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sql.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

}
