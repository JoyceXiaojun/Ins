package com.kc.inspiration.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.inspiration.biz.ICommentBiz;
import com.kc.inspiration.biz.impl.CommentBizImpl;
import com.kc.inspiration.po.Comment;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		// 步骤1：设置字符编码集合
		request.setCharacterEncoding("UTF-8");
		// 步骤2：接受客户端传入的数据
		int replycid = Integer.parseInt(request.getParameter("replycid").trim());
		int uid = Integer.parseInt(request.getParameter("userid").trim());
		int wid = Integer.parseInt(request.getParameter("workid").trim());
		String content = request.getParameter("content").trim();
		// 步骤3：将其进行对象封装
		Comment comment = new Comment();
		comment.setUid(uid);
		comment.setWid(wid);
		comment.setContent(content);
		comment.setReplycid(replycid);
		System.out.print(comment);
		// 步骤4：调用Biz层业务实现回复动作
		ICommentBiz replyBiz = new CommentBizImpl();
		boolean flag = replyBiz.add(comment);
		// 步骤5：根据动作结果响应客户端
		if(flag){
			response.sendRedirect("jsp/work.jsp?wid="+wid);
		}else{
			System.out.println("回复失败！");
		}
	}

}
