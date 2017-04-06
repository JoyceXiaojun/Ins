package com.kc.inspiration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IAttentionBiz;
import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.impl.AttentionBizImpl;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.po.Attention;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class AddFollowServlet
 */
public class AddFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFollowServlet() {
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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		request.setCharacterEncoding("UTF-8");
		//Set content type of the response to text/xml
		response.setContentType("text/xml");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 步骤2：接受客户端传入的数据

		int  uid= Integer.parseInt(request.getParameter("uid").trim());
		
		// 步骤3：将其进行对象封装
		Attention att=new Attention();
        att.setBuid(uid);
        att.setUid(user.getUid());
        
		IAttentionBiz attBiz = new AttentionBizImpl();
		// 步骤5：根据动作结果响应客户端
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
		response.setHeader("Cache-Control","no-cache");
		if(attBiz.add(att)){
			//修改User的fanscount属性
			user.setAttentioncount(user.getAttentioncount());;
			IUserBiz userBiz=new UserBizImpl();
			userBiz.modify(user);
			User following=userBiz.findById(uid);
			following.setFanscount(following.getFanscount()+1);
			userBiz.modify(following);
			
			out.println("<response>");
			out.println("<passed>关注成功！</passed>");
			out.println("</response>");		
			out.close();
			
			System.out.println("添加关注成功！");
		}else{
			out.println("<response>");
			out.println("<passed>对不起！您已经关注该好友！</passed>");
			out.println("</response>");		
			out.close();
			System.out.println("添加关注失败！");
}
	}

}
