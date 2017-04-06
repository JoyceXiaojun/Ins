package com.kc.inspiration.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;










import com.kc.inspiration.biz.IAuctionBiz;
import com.kc.inspiration.biz.impl.AuctionBizImpl;
import com.kc.inspiration.po.Auction;
import com.kc.inspiration.po.User;


/**
 * Servlet implementation class AuctionApplicationServelt
 */
public class AuctionApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionApplicationServlet() {
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
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 获取表单内容
		int wid = Integer.parseInt(request.getParameter("workname"));
		Double baseprice = Double.parseDouble(request.getParameter("baseprice"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begintime = null;
		try {
			
			begintime = sdf.parse(request.getParameter("begintime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endtime = null;
		try {
			endtime = sdf.parse(request.getParameter("endtime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mark = request.getParameter("mark");
		Double topprice = baseprice;
		
		//构建Auction对象
		Auction auction = new Auction();
		auction.setUid(user.getUid());
		auction.setWid(wid);
		auction.setBaseprice(baseprice);
		auction.setBegintime(begintime);
		auction.setEndtime(endtime);
		auction.setTopprice(topprice);
		auction.setStatus(1);
		auction.setMark(mark);
		String datetime = sdf.format(new Date());
		Date nowtime = null;
		try {
			nowtime = sdf.parse(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "";
		int msgcode = 0;
		if(nowtime.getTime()<=begintime.getTime()&&begintime.getTime()<endtime.getTime()){
			IAuctionBiz auctionBiz = new AuctionBizImpl();
			boolean flag = auctionBiz.add(auction);
			if(flag){
				// 添加成功
				url="/jsp/auction.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
			else{
				
				// 添加失败
				msgcode = 101;
				request.setAttribute("msgcode", msgcode);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/auctionapplication.jsp");
				dispatcher.forward(request, response);
			}
		}
		else{
			// 表单不符合条件
			msgcode = 102;
			System.out.println("wangrongknag"+msgcode);
			request.setAttribute("msgcode", msgcode);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/auctionapplication.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
	}

}
