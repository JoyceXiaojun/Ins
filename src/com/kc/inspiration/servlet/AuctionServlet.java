package com.kc.inspiration.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IAuctionBiz;
import com.kc.inspiration.biz.INworkBiz;

import com.kc.inspiration.biz.impl.AuctionBizImpl;
import com.kc.inspiration.biz.impl.NworkBizImpl;

import com.kc.inspiration.po.Auction;

import com.kc.inspiration.po.User;
import com.kc.inspiration.vo.Nwork;

/**
 * Servlet implementation class AuctionServlet
 */
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionServlet() {
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
		IAuctionBiz auctionBiz = new AuctionBizImpl();
		INworkBiz nworkBiz = new NworkBizImpl();
		List<Auction> lstAuction = auctionBiz.findAllByUid(user.getUid());
		//正在拍卖作品的集合
		List<Nwork> lstWork = new ArrayList<Nwork>();
		//已经拍卖的作品集合
		List<Nwork> lstWorked = new ArrayList<Nwork>();
		for(int i=0;i<lstAuction.size();i++){
			if(lstAuction.get(i).getStatus() == 1){
				Nwork work = new Nwork();
				work = nworkBiz.findById(lstAuction.get(i).getWid());
				lstWork.add(work);
			}
			else{
				Nwork work = new Nwork();
				work = nworkBiz.findById(lstAuction.get(i).getWid());
				lstWorked.add(work);
			}
		}
		List<Nwork> lstWork0 = new ArrayList<Nwork>();
		List<Nwork> lstWork1 = new ArrayList<Nwork>();
		if(lstWork.size()>0){
			if(lstWork.size() > 3){
				for(int i=0;i<3;i++){
					lstWork0.add(lstWork.get(i));
				}
			}
			else{
				for(int i=0;i<lstWork.size();i++){
					lstWork0.add(lstWork.get(i));
				}
			}
		}
		else{lstWork0 = null;}
		if(lstWorked.size()>0){
			if(lstWorked.size() > 3){
				for(int i=0;i<3;i++){
					lstWork1.add(lstWork.get(i));
				}
			}
			else{
				for(int i=0;i<lstWorked.size();i++){
					lstWork1.add(lstWorked.get(i));
				}
			}
		}else{
			lstWork1=null;
		}
		System.out.println("wang"+lstWork0.size());
		request.setAttribute("lstWork0", lstWork0);
		request.setAttribute("lstWork1", lstWork1);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/jsp/auction.jsp");
		dispatcher.forward(request, response);
		
	}

}
