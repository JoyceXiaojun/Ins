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
import com.kc.inspiration.biz.impl.MessageBizImpl;
import com.kc.inspiration.po.Message;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class ChatServlet
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
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
		//IMessageBiz messageBiz = new MessageBizImpl();
		//List<Message> lstMsg = new ArrayList<Message>();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String msg = request.getParameter("chatMsg");
		msg = new String(msg.getBytes("iso8859-1"),
				"UTF-8");
		int rid = Integer.parseInt(request.getParameter("rid"));
		System.out.println("msg:>" + msg);
		System.out.println("rid:>"+rid);
		//添加消息进数据库
				int sid = user.getUid();
				int msgstatus = 1;
				Message message = new Message();
				message.setSid(sid);
				message.setRid(rid);
				message.setMsgcontent(msg);
				message.setMsgstatus(msgstatus);
				IMessageBiz messageBiz = new MessageBizImpl();
				boolean flag = messageBiz.add(message);
				System.out.println(flag);
	
		
		List<Message> lstMsg = new ArrayList<Message>();
		lstMsg = messageBiz.findChatMessage(rid, user.getUid());
		System.out.println("size:>"+lstMsg.size());
		StringBuilder result = new StringBuilder("");
		for(int i=0;i<lstMsg.size();i++){
			result.append(lstMsg.get(i).getMsgcontent()+"\n");
		}
		String chatmsg = result.toString();
		System.out.println(chatmsg);
		//request.setAttribute("chatList", ChatService.instance().getMsg());
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-Control","no-cache");
		
		
		out.println("<response>");
		out.println("<rid>"+rid+"</rid>");
		out.println("<msg>"+chatmsg+"</msg>");
		out.println("</response>");	
		out.close();
//	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/message.jsp");
//	    dispatcher.forward(request, response);
		
		
	}

}
