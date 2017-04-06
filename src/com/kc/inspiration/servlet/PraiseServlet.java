package com.kc.inspiration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IPraiseBiz;
import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.PraiseBizImpl;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.Praise;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

/**
 * Servlet implementation class PraiseServlet
 */
public class PraiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PraiseServlet() {
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
		//
		System.out.println("debug");
		request.setCharacterEncoding("UTF-8");
		//Set content type of the response to text/xml
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		
		// 步骤2：接受客户端传入的数据
		HttpSession session = request.getSession(); // 获取session对象
		User user = (User) session.getAttribute("user");
		int wid = Integer.parseInt(request.getParameter("wid").trim());
		
		// 步骤3：将其进行对象封装
		Praise praise = new Praise();
		praise.setUid(user.getUid());
		praise.setWid(wid);	
		
		IWorkBiz workBiz = new WorkBizImpl();
		Work work = workBiz.findById(wid);
		
		// 步骤4：调用Biz层业务实现动作
		IPraiseBiz praiseBiz = new PraiseBizImpl();
		boolean flag = praiseBiz.add(praise);
		// 步骤5：根据动作结果响应客户端
		if(flag){
			//点赞成功
			//更新Work对象的点赞次数,加1
			work.setPraisecount(work.getPraisecount()+1);
			workBiz.modify(work);
			String responseText= "点赞次数："+work.getPraisecount();;
			//Write the response back to the browser 
			PrintWriter out =response.getWriter();
			//out.println(responseText);
			out.println("<response>");
			out.println("<flag>true</flag>");
			out.println("<responsetext>"+responseText+"</responsetext>");
			out.println("</response>");
			//close the writer
			out.close();
			System.out.println("点赞成功！");
		}else{
			//点赞失败
			String responseText= "点赞次数："+work.getPraisecount();;
			//Write the response back to the browser 
			PrintWriter out =response.getWriter();
			out.println("<response>");
			out.println("<flag>false</flag>");
			out.println("<responsetext>"+responseText+"</responsetext>");
			out.println("</response>");
			out.close();
			System.out.println("点赞失败！");
		}	

	}

}
