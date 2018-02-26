package com.lingbei.dao;

import com.lingbei.bean.LoginReturnBean;
import com.lingbei.bean.UserLoginBean;
import com.lingbei.db.DBUtil;
import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginDao{
	public static Object manageLogin(HttpServletRequest request, HttpServletResponse response) {

		JSONObject obj = null;
		UserLoginBean user = new UserLoginBean();
		LoginReturnBean bean = new LoginReturnBean();
		String uid = request.getParameter("username");
		String pwd = request.getParameter("password").toUpperCase();
		String rmb = request.getParameter("rememberMe");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from userlogin where USERNAME=? and PASSWORD=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, pwd);
			rs = ps.executeQuery();

			if (rs.next()) {

				Cookie nameCookie = new Cookie("username", uid);      //将当前帐号保存到cookie中
				nameCookie.setPath(request.getContextPath() + "/");
				nameCookie.setMaxAge(7 * 24 * 60 * 60);
				Cookie remCookie = new Cookie("rem", "1");;
				if ("yes".equals(rmb)) {           //勾选记住我，则cookie记录保存7天
					remCookie.setMaxAge(7 * 24 * 60 * 60);
				} else {                            //没有勾选，则时间设置为0
					remCookie.setMaxAge(0);
				}
				response.addCookie(nameCookie);
				response.addCookie(remCookie);
				bean.setResult(0);
				bean.setMessage("成功");
				user.setName(uid);
				request.getSession().setAttribute("user", user);
				bean.setUser(user);
			} else {
				bean.setResult(2);
				bean.setMessage("失败");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			bean.setResult(2);
			bean.setMessage("失败");
			e.printStackTrace();
		}
		obj = new JSONObject(bean);
		return obj;
	}


	public static Object register(HttpServletRequest request) {

		JSONObject obj = null;
		UserLoginBean user = new UserLoginBean();
		LoginReturnBean bean = new LoginReturnBean();
		String uid = request.getParameter("username1");
		String realname = request.getParameter("realname");
		String pwd = request.getParameter("password1");

		PreparedStatement ps = null;
		PreparedStatement re = null;
		ResultSet rs = null;
		try {
			Connection conn = DBUtil.getConnection();
			String numsql = "SELECT COUNT(*)  FROM userlogin WHERE USERNAME=? or REALNAME=?";
			re = conn.prepareStatement(numsql);
			re.setString(1, uid);
			re.setString(2, realname);
			rs = re.executeQuery();
			rs.next();
			int A=rs.getInt(1);  //执行sql语句查询后获取用户表中的一列，用来判断邮箱或者用户是否已经存在
			if( A> 0){           //若存在即A>0,则返回“失败”
				bean.setResult(2);
				bean.setMessage("失败");
			}
			else {
				String sql = "insert into userlogin (USERNAME,REALNAME,PASSWORD) VALUES (?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, uid);
				ps.setString(2, realname);
				ps.setString(3, pwd);
				ps.executeUpdate();
				ps.close();
				bean.setResult(0);
				bean.setMessage("成功");
			}
			re.close();
			rs.close();
			conn.close();
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		obj = new JSONObject(bean);
			return obj;
}

}
