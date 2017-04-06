package com.kc.inspiration.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class DeleteFollowServlet
 */
public class DeleteFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFollowServlet() {
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
		int msgcode;
		// 步骤5：根据动作结果响应客户端
		if(attBiz.delete(att)){
			//修改User的fanscount属性
			user.setAttentioncount(user.getAttentioncount());;
			IUserBiz userBiz=new UserBizImpl();
			userBiz.modify(user);
			User following=userBiz.findById(uid);
			following.setFanscount(following.getFanscount()-1);
			userBiz.modify(following);
			msgcode=100;
			System.out.println("取消关注成功！");
		}else{
			msgcode=101;
			System.out.println("取消关注失败！");
		}
		request.setAttribute("msgcode", msgcode);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/focus.jsp");
		dispatcher.forward(request, response);
	}

}
