package com.kc.inspiration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.ICollectionBiz;
import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.CollectionBizImpl;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.Collection;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

/**
 * Servlet implementation class CollectServlet
 */
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CollectServlet() {
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
		System.out.println("[CollectServlet]: >收藏作品！");
		// 步骤1：设置字符编码集合
		request.setCharacterEncoding("UTF-8");
		//Set content type of the response to text/xml
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		// 步骤2：接受客户端传入的数据
		HttpSession session = request.getSession(); // 获取session对象
		User user = (User) session.getAttribute("user");
		int wid = Integer.parseInt(request.getParameter("wid").trim());
		// 步骤3：将其进行对象封装
		Collection collect = new Collection();
		collect.setUid(user.getUid());
		collect.setWid(wid);
		IWorkBiz workBiz = new WorkBizImpl();
		Work work = workBiz.findById(wid);
		
		// 步骤4：调用Biz层业务实现回复动作
		ICollectionBiz collectBiz = new CollectionBizImpl();
		boolean flag = collectBiz.add(collect);
		// 步骤5：根据动作结果响应客户端
		if (flag) {
			//收藏成功
			//更新Work对象的收藏次数
			work.setCollectcount(work.getCollectcount() + 1);
			workBiz.modify(work);
			String responseText= "收藏次数："+work.getCollectcount();
			PrintWriter out =response.getWriter();
			out.println("<response>");
			out.println("<flag>true</flag>");
			out.println("<responsetext>"+responseText+"</responsetext>");
			out.println("</response>");
			out.close();
			System.out.println("收藏成功！");
		} else {
			String responseText= "收藏次数："+work.getCollectcount();
			PrintWriter out =response.getWriter();
			out.println("<response>");
			out.println("<flag>false</flag>");
			out.println("<responsetext>"+responseText+"</responsetext>");
			out.println("</response>");
			out.close();
			System.out.println("收藏失败！");
		}
		
	}

}
