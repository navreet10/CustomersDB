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
			sql = con.prepareStatement("SELECT a.customerid, a.title," + " a.firstname," + " a.lastname,"
					+ " e.company," + " a.streetaddress," + " b.city," + " c.state," + " a.zipcode,"
					+ " a.emailaddress," + " d.position" + " FROM customers a" + " JOIN companies e"
					+ " ON a.companyID = e.companyid" + " JOIN cities b" + " ON a.cityID = b.cityid" + " JOIN states c"
					+ " ON a.stateid = c.stateid" + " JOIN positions d" + " ON a.positionid  = d.positionid"
					+ " WHERE a.lastname   = ?");
			sql.setString(1, lastName);
			rs = sql.executeQuery();

			while (rs.next()) {
				ids.add(rs.getInt(1));
				System.out.println(rs.getInt(1));
				list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(5),
						rs.getString(11)));

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
			sql = con.prepareStatement("UPDATE CUSTOMERS SET STREETADDRESS = ?" + " WHERE CUSTOMERID   = ?");
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

	public int addCustomer(Customer c) {
		Connection con = null;
		int rs = 0;
		PreparedStatement sql = null;
		try {
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");

			// Insert into companies and get company ID
			sql = con.prepareStatement("Insert into Companies (company) values (?)");
			insert(con, sql, c.getCompany());

			sql = con.prepareStatement("select companyid from Companies where company = ?");
			int companyId = select(sql, c.getCompany());

			// Insert into cities and get company ID
			sql = con.prepareStatement("Insert into CITIES (city) values (?)");
			insert(con, sql, c.getCity());

			sql = con.prepareStatement("select cityid from Cities where city = ?");
			int cityId = select(sql, c.getCity());

			// Insert into states and get company ID
			sql = con.prepareStatement("Insert into States (state) values (?)");
			insert(con, sql, c.getState());

			sql = con.prepareStatement("select stateid from States where state = ?");
			int stateId = select(sql, c.getState());

			// Insert into positions and get company ID
			sql = con.prepareStatement("Insert into Positions (position) values (?)");
			insert(con, sql, c.getPosition());

			sql = con.prepareStatement("select positionid from Positions where position = ?");
			int positionId = select(sql, c.getPosition());
			

			sql = con.prepareStatement("Insert into CUSTOMERS values(?,?,?,?,?,?,?,?,?,?,"
					+ "(select max(CUSTOMERID)+1 from CUSTOMERS))");
			sql.setString(1, c.getTitle());
			sql.setString(2, c.getFirstName());
			sql.setString(3, c.getLastName());
			sql.setString(4, c.getStreetAddress());
			sql.setString(5, c.getZip());
			sql.setString(6, c.getEmail());
			sql.setInt(7, companyId);
			sql.setInt(8, cityId);
			sql.setInt(9, stateId);
			sql.setInt(10, positionId);

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

	private int select(PreparedStatement sql, String param) throws SQLException {
		sql.setString(1, param);
		ResultSet rs = sql.executeQuery();
		while (rs.next()) {
			int res = rs.getInt(1);
			sql.close();
			return res;
		}
		sql.close();
		return -1;		
	}

	private void insert(Connection con, PreparedStatement sql, String param) throws SQLException {
		sql.setString(1, param);
		sql.executeUpdate();
		sql.close();
		con.commit();

	}

}
