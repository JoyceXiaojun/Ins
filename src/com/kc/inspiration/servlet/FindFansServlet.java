package com.kc.inspiration.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class FindFansServlet
 */
public class FindFansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFansServlet() {
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
		User user = (User)session.getAttribute("user");
		IUserBiz userBiz = new UserBizImpl();
		List<User> lstFollower = userBiz.findAllFollower(user.getUid());
		if(lstFollower.size()>=4){
			lstFollower =lstFollower.subList(0, 4);
		}
		List<User> lstFollow = userBiz.findAllFollow(user.getUid());
		if(lstFollow.size()>=4){
			lstFollow =lstFollow.subList(0, 4);
		}
		request.setAttribute("lstFollower", lstFollower);
		request.setAttribute("lstFollow", lstFollow);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/information.jsp");
		dispatcher.forward(request, response);
	}

}
