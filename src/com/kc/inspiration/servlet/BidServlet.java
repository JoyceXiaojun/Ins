package com.kc.inspiration.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IAuctionBiz;
import com.kc.inspiration.biz.IBidBiz;
import com.kc.inspiration.biz.impl.AuctionBizImpl;
import com.kc.inspiration.biz.impl.BidBizImpl;
import com.kc.inspiration.po.Auction;
import com.kc.inspiration.po.Bid;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class BidServlet
 */
public class BidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidServlet() {
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
		//获取网页参数
		User user = (User) session.getAttribute("user");
		double price=Double.parseDouble(request.getParameter("price"));
		int auctionid=Integer.parseInt(request.getParameter("aid"));
		//获取构造Bid对象
		Bid bid = new Bid();
		bid.setAuctionid(auctionid);
		bid.setPrice(price);
		bid.setUid(user.getUid());
		
		//创建Biz
		IBidBiz bidBiz = new BidBizImpl();
		IAuctionBiz auctionBiz= new AuctionBizImpl();
		
		//获取Auction对象
		Auction auction =auctionBiz.findById(auctionid);
		
		//
		//判断所出价格是否高于现在的最高价
		if(price<auction.getTopprice()){
			//用户所出价格低于当前最高价
			//修改Auction成功
	    	int msgcode=100;
			request.setAttribute("msgcode", msgcode);
	    	RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/bid.jsp?aid="+auctionid);
			dispatcher.forward(request, response);
		}
		else{
			if(bidBiz.add(bid)){
				//数据库添加成功
				System.out.println("竞价成功");
				//修改对应Auction的topprice
				
				
				auction.setTopprice(price);
			    if(auctionBiz.modify(auction)){
			    	//修改Auction成功
			    	int msgcode=101;
					request.setAttribute("msgcode", msgcode);
			    	RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/bid.jsp?aid="+auctionid);
					dispatcher.forward(request, response);
			    }else{
			    	//修改Auction表失败
			    	int msgcode=102;
					request.setAttribute("msgcode", msgcode);
					request.setAttribute("msgcode", msgcode);
			    	RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/bid.jsp?aid="+auctionid);
					dispatcher.forward(request, response);
			    }
			}
			else{
				//Bid表添加失败
				int msgcode=103;
				request.setAttribute("msgcode", msgcode);
		    	RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsp/bid.jsp?aid="+auctionid);
				dispatcher.forward(request, response);
				
			}
		}
		
		
	}

}
