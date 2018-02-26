package com.lingbei.interceptor;

import com.lingbei.bean.ReturnBean;
import com.lingbei.dao.*;
import com.lingbei.util.LogUtil;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxInterceptor implements Filter
{
	private FilterConfig config = null;
	public static long i=0;
	public void destroy()
	{
		System.out.println("MyCharsetFilter准备销毁...");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain chain) throws IOException, ServletException
	{
		// 强制类型转换
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 获取web.xml设置的编码集，设置到Request、Response中
		request.setCharacterEncoding(config.getInitParameter("charset"));
		response.setContentType(config.getInitParameter("contentType"));
		String url=request.getRequestURI();
		LogUtil.log(url);
		String[] str=url.split("/");
		if(str.length==0){
			return;
		}
		String path=str[str.length-1];
		Object user1 = request.getSession().getAttribute("user");
		if(path.indexOf(".")!=-1) {
			if(user1==null&&!path.equals("login.html")&&path.endsWith(".html")){
				response.addHeader("location", "login.html");
				response.setStatus(302);
			}
			else {
				response.setHeader("Cache-Control", "max-age=0");
				chain.doFilter(request, response);
			}
			return;
		}
		Object obj=null;

		if(path.equals("login")){
		obj=LoginDao.manageLogin(request,response);
		}
		else if(path.equals("register")){
		obj=LoginDao.register(request);
		}

		if(obj!=null){
			response.getWriter().write(obj.toString());
			return;
		}
		if(user1==null){
			obj=setReturn();
		}else{

				if (path.equals("individual")) {
					obj = IndividualDao.show(request);
				} else if (path.equals("diary")) {
					obj = IndividualDao.showDiary(request);
				}else if (path.equals("photo")) {
					obj = IndividualDao.showPhoto(request);
				}else if (path.equals("footmark")) {
					obj = IndividualDao.showFootmark(request);
				}else if (path.equals("pinglun")) {
					obj = IndividualDao.pl(request);
				}

		}
		if(obj==null){
			obj=setReturn();
		}
		LogUtil.log(obj.toString());
		response.getWriter().write(obj.toString());
	}
	private Object setOkReturn() {
		ReturnBean bean=new ReturnBean();
		bean.setResult(0);
		bean.setMessage("");
		JSONObject obj=new JSONObject(bean);
		return obj;
	}

	private Object setReturn() {
		ReturnBean bean=new ReturnBean();
		bean.setResult(1);
		bean.setMessage("失败");
		JSONObject obj=new JSONObject(bean);
		return obj;
	}
	public void init(FilterConfig arg0) throws ServletException
	{
		this.config = arg0;
		System.out.println("MyCharsetFilter初始化...");
	}
}