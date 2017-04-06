package com.kc.inspiration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class SearchUserServlet
 */
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//Set content type of the response to text/xml
		response.setContentType("text/xml");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 步骤2：接受客户端传入的数据

		String username = request.getParameter("str").trim();
		username = new String(username.getBytes("iso8859-1"),
				"UTF-8");
		// 步骤3：将其进行对象封装
        User user = new User();
        user.setUsername(username);
		IUserBiz userBiz = new UserBizImpl();
		User u = userBiz.findByUsername(username);
		// 步骤5：根据动作结果响应客户端
		if(u!=null){
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
			response.setHeader("Cache-Control","no-cache");
			out.println("<response>");
			out.println("<uid>"+u.getUid()+"</uid>");
			out.println("<photo>"+u.getPhoto()+"</photo>");
			out.println("<username>"+u.getUsername()+"</username>");
			out.println("<mark>"+u.getMark()+"</mark>");
			out.println("<uid>"+u.getUid()+"</uid>");
			out.println("</response>");		
			out.close();
			System.out.println("查找成功！");
		}else{
			System.out.println("空");
		}
	}

}
