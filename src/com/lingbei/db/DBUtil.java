package com.lingbei.db;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
	static String ConLinkDBurl = "";
	static String ConLinkDBuser = "";
	static String ConLinkDBpword = "";
	static String ConLinkDBfname="";

	static {
		Properties p=new Properties() ;
		try {
			p.load(new FileInputStream(new File("C:\\setting\\setting.properties")));
			ConLinkDBurl=p.getProperty("url");
			ConLinkDBuser=p.getProperty("user");
			ConLinkDBpword=p.getProperty("password");
			ConLinkDBfname=p.getProperty("forname");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lingbei?useUnicode=true" +
					"&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8","root","amtf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn){
		if(conn==null){
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	//最后关闭数据库连接
    public static void closeConn(ResultSet rs,PreparedStatement prep,Connection conn){
        try {
            if (rs!=null) {//如果返回的结果集对象不能为空,就关闭连接
                rs.close();
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        try {
            if (prep!=null) {
            	prep.close();//关闭预编译对象
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        try {
            
            if (conn!=null) {
                conn.close();//关闭结果集对象
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
	
	public static String existFlag(String sql, Object ...arg) throws SQLException{
		Connection conn=(Connection) DBUtil.getConnection();
		String id="";
		 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		 for(int i=0;i<arg.length;i++){
			 pstmt.setString(i+1, arg[i].toString());
		 }
		 ResultSet rs=pstmt.executeQuery();
		 if(rs.next()){
			 id=rs.getString("id");
		 }
		 return id;
	}
	public static void main(String[] args){
		System.out.println(getConnection());
	}
}
