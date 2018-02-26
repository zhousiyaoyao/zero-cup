package com.lingbei.dao;

import com.lingbei.bean.DiaryBean;
import com.lingbei.bean.PhotoBean;
import com.lingbei.bean.FootmarkBean;
import com.lingbei.bean.DataReturnBean;
import com.lingbei.bean.CommentBean;
import com.lingbei.bean.UserLoginBean;
import com.lingbei.db.DBUtil;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gxy on 2016/12/1.
 */
public class IndividualDao {

    public static Object show(HttpServletRequest request) {

        JSONObject obj = null;
        UserLoginBean bean = new UserLoginBean();
        String uid = request.getParameter("user");


        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();

                String sql="select * from userlogin where USERNAME=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, uid);
                rs=ps.executeQuery();
            if (rs.next()) {
                bean.setRealName(rs.getString("REALNAME"));
                bean.setTrailNum(rs.getInt("trailnum"));
                bean.setDiaryNum(rs.getInt("diarynum"));
                bean.setBirthday(rs.getDate("birthday"));
                bean.setRegtime(rs.getDate("regtime"));
                bean.setDescription(rs.getString("description"));
                bean.setAddress(rs.getString("address"));
                bean.setSmallpic(rs.getString("SMALLPIC"));
                bean.setHeadpic(rs.getString("HEADPIC"));
                bean.setSelfintro(rs.getString("SELFINTRO"));

                bean.setResult(0);
                bean.setMessage("成功");

            }else {
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


    public static Object showDiary(HttpServletRequest request) {
        Connection conn=null;
        PreparedStatement ps=null;

        ResultSet rs=null;

        List<DiaryBean> data=new ArrayList<>();
        DataReturnBean bean=new DataReturnBean();
        String uid = request.getParameter("user");
        try {
            conn= DBUtil.getConnection();
            String sql="select * from diary where USERNAME=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs=ps.executeQuery();
            while(rs.next()){
                DiaryBean Dbean=new DiaryBean();
                Dbean.setTitle(rs.getString("TITLE"));
                Dbean.setContent(rs.getString("CONTENT"));
                Dbean.setCreatetime(rs.getString("CREATETIME"));
                Dbean.setDiaryphoto(rs.getString("diaryphoto"));

                data.add(Dbean);
            }
            rs.close();
            ps.close();
            bean.setData(data);
            bean.setMessage("成功");
            bean.setResult(0);

        } catch (SQLException e) {
            bean.setMessage("失败");
            bean.setResult(2);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        JSONObject obj=new JSONObject(bean);
        return obj;
    }

    public static Object showPhoto(HttpServletRequest request) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        List<PhotoBean> data=new ArrayList<>();
        DataReturnBean bean=new DataReturnBean();
        String uid = request.getParameter("user");
        try {
            conn= DBUtil.getConnection();
            String sql="select * from photo where USERNAME=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs=ps.executeQuery();
            while(rs.next()){
                PhotoBean Dbean=new PhotoBean();
                Dbean.setPath1(rs.getString("Path1"));
                Dbean.setPath2(rs.getString("Path2"));
                Dbean.setPath3(rs.getString("Path3"));
                Dbean.setPath4(rs.getString("Path4"));
                Dbean.setTitle(rs.getString("Title"));
                Dbean.setTitlelist(rs.getString("Titlelist"));
                Dbean.setCreatetime(rs.getString("CREATETIME"));


                data.add(Dbean);
            }
            rs.close();
            ps.close();
            bean.setData(data);
            bean.setMessage("成功");
            bean.setResult(0);

        } catch (SQLException e) {
            bean.setMessage("失败");
            bean.setResult(2);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        JSONObject obj=new JSONObject(bean);
        return obj;
    }


    public static Object showFootmark(HttpServletRequest request) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<FootmarkBean> data=new ArrayList<>();
        DataReturnBean bean=new DataReturnBean();
        String uid = request.getParameter("user");
        try {
            conn= DBUtil.getConnection();
            String sql="select * from footmark where USERNAME=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs=ps.executeQuery();
            while(rs.next()){
                FootmarkBean Dbean=new FootmarkBean();
                Dbean.setFootmark(rs.getString("Footmark"));
                data.add(Dbean);
            }
            rs.close();
            ps.close();
            bean.setData(data);
            bean.setMessage("成功");
            bean.setResult(0);

        } catch (SQLException e) {
            bean.setMessage("失败");
            bean.setResult(2);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        JSONObject obj=new JSONObject(bean);
        return obj;
    }


    public static Object pl(HttpServletRequest request) {
        JSONObject obj = null;
        Connection conn = null;
        CommentBean bean = new CommentBean();
        String content = request.getParameter("comment");
        String createtime = request.getParameter("time");
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into comment(Content,CreateTime) values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setString(2, createtime);
            ps.executeUpdate();
            ps.close();

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            if(conn!=null){
                DBUtil.close(conn);
            }
        }
        obj = new JSONObject(bean);
        return obj;
    }
}


