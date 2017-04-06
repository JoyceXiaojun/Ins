package com.kc.inspiration.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.po.User;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 步骤1：通过session获取登录的账号
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 步骤2：销毁session
		session.removeAttribute("user");
		session.invalidate();
		// 步骤3：从在线列表中移除当前登录账号
		@SuppressWarnings("unchecked")
		List<String> usersOnLineList = (List<String>) this.getServletContext()
				.getAttribute("usersOnLineList");
		usersOnLineList.remove(user.getUsername()); // 移除
		this.getServletContext().setAttribute("usersOnLineList",
				usersOnLineList); // 刷新在线列表
		// 步骤4：页面跳转
		response.sendRedirect("jsp/signin.jsp");
	}

}
