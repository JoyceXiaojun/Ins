package com.kc.inspiration.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class UpdateInfoServlet
 */
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String mark = request.getParameter("mark");
		user.setUsername(username);
		user.setEmail(email);
		user.setMark(mark);
		
		IUserBiz userBiz = new UserBizImpl();
		userBiz.modify(user);
		session.setAttribute("user", user);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/information.jsp");
		dispatcher.forward(request, response);
	}

}
