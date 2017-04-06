package com.kc.inspiration.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kc.inspiration.biz.IMessageBiz;
import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.impl.MessageBizImpl;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.po.Message;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class SearchChatServlet
 */
public class SearchChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchChatServlet() {
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
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		IMessageBiz messageBiz = new MessageBizImpl();
		List<Message> lstMsg = new ArrayList<Message>();
		 
		IUserBiz userBiz = new UserBizImpl();
		List<User> lstFollow = userBiz.findAllFollow(user.getUid());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-Control","no-cache");
		
		
		out.println("<response>");
		for(int i=0;i<lstFollow.size();i++){
			lstMsg = messageBiz.findChatMessage(lstFollow.get(i).getUid(), user.getUid());
			System.out.println("wangsize:>"+lstMsg.size());
			StringBuilder result = new StringBuilder("");
			for(int j=0;j<lstMsg.size();j++){
				User sender=userBiz.findById((lstMsg.get(j).getSid()));
				result.append(sender.getUid()+","+sender.getPhoto()+","+lstMsg.get(j).getMsgcontent()+"\n");
				System.out.println("xieguiyang"+sender.getPhoto());
			}
			String chatmsg = result.toString();
			System.out.println("wang"+chatmsg);
			//request.setAttribute("chatList", ChatService.instance().getMsg());
			
			
			out.println("<rid>"+lstFollow.get(i).getUid()+"</rid>");
			out.println("<msg>"+chatmsg+"</msg>");
			
		}
		
		out.println("</response>");	
		out.close();
	}

}
