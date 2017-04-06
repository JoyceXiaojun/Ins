package com.kc.inspiration.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IAuctionBiz;
import com.kc.inspiration.biz.INworkBiz;
import com.kc.inspiration.biz.IResourceBiz;
import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.AuctionBizImpl;
import com.kc.inspiration.biz.impl.NworkBizImpl;
import com.kc.inspiration.biz.impl.ResourceBizImpl;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.Auction;
import com.kc.inspiration.po.Resource;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;
import com.kc.inspiration.vo.Nwork;

/**
 * Servlet implementation class AuctionRecoderServlet
 */
public class AuctionRecoderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionRecoderServlet() {
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
		int wid = Integer.parseInt(request.getParameter("wid"));
		System.out.println("wangrongkang"+wid);
		// 从Session中取出User
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		int uid = user.getUid();
		IAuctionBiz auctionBiz = new AuctionBizImpl();
		Auction auction = auctionBiz.findByUidWid(uid, wid);
		request.setAttribute("auction", auction);
		
		IWorkBiz workBiz = new WorkBizImpl();
		Work work = workBiz.findById(auction.getWid());
		INworkBiz nworkBiz = new NworkBizImpl();
		Nwork nwork = nworkBiz.findById(auction.getWid());
		request.setAttribute("nwork", nwork);
		request.setAttribute("work", work);
		IResourceBiz resourceBiz = new ResourceBizImpl();
		Resource resource = resourceBiz.findById(work.getRid());
		request.setAttribute("resource", resource);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/auctionrecoder.jsp");
		dispatcher.forward(request, response);
		
	}

}
