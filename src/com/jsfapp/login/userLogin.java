package com.jsfapp.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

@ManagedBean(name="userLogin")
@SessionScoped
public class userLogin {
	private String username;
	private String password;
    private String dbusername;
    private String dbpassword;
	public String getusername(){
		return username;
	}
	public String getpassword(){
		return password;
	}
	public String getdbusername() {
		return dbusername;
	}
	public String getdbpassword() {
		return dbpassword;
	}
	public void setusername(String username){
		this.username=username;
	}
	public void setpassword(String password){
		this.password=password;
	}
	public void setdbuserName(String dbusername) {
		this.dbusername = dbusername;
	}
	public void setdbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	Connection conn = null;
	public void validate(String usernaem){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Webapp", "root", "");
			PreparedStatement ps = conn.prepareStatement("Select * from login where name like ('" + usernaem +"')");
			ResultSet rs = ps.executeQuery();
			rs.next();
			dbusername = rs.getString(1).toString();
            dbpassword = rs.getString(2).toString();
            } catch (SQLException ex){
			ex.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		 } finally {
		    try {
		    	conn.close(); 
		    } catch (Exception e) {
		     /*Stuff*/
		    }
		}
	}
	public String login(){
		validate(username);
		if(username.equals(dbusername)){
            if(password.equals(dbpassword)){
                return "success";
            }
            else{
            	return "failure";
            }
		}else{
            return "failure";
             }
	}
}
