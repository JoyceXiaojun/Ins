package com.kc.inspiration.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

/**
 * Servlet implementation class PreAuctionApplication
 */
public class PreAuctionApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreAuctionApplication() {
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
		IWorkBiz workBiz = new WorkBizImpl();
		List<Work> lstWork = workBiz.findAll(user.getUid());
		request.setAttribute("lstWork", lstWork);
		System.out.println(lstWork.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/auctionapplication.jsp");
		dispatcher.forward(request, response);
	}

}
