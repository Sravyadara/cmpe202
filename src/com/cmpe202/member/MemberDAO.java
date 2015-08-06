package com.cmpe202.member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.member.Member;

public class MemberDAO {
	private Connection connection;
    private Statement statement;
 
    public MemberDAO() { }
 
    public Member getMember(String MemberId) throws SQLException {
        String query = "SELECT * FROM customer where customer_emailid=\"" + MemberId + "\"";
        ResultSet rs = null;
        Member member = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
            	member = new Member();
            	member.setMemberid(rs.getString("customer_emailid"));
            	member.setPassword(rs.getString("password"));
            	member.setRole(rs.getString("role"));
            	member.setMemberName(rs.getString("name"));
            	member.setAddress(rs.getString("address"));
            	member.setContact(rs.getString("contactno"));
            	member.setPaymentDetails(rs.getString("paymentcard"));
            	//member.setLicenseDetails(rs.getString("employee_emailid"));
            	
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return member;
    }

}
