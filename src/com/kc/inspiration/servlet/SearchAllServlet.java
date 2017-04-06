package com.kc.inspiration.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

/**
 * Servlet implementation class SearchAllServlet
 */
public class SearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAllServlet() {
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
		request.setCharacterEncoding("UTF-8");
		//Set content type of the response to text/xml
		response.setContentType("text/xml");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 步骤2：接受客户端传入的数据

		String obj = request.getParameter("obj").trim();
		obj = new String(obj.getBytes("iso8859-1"),
				"UTF-8");
		// 步骤3：将其进行对象封装
        //User user = new User();
        //user.setUsername(username);
		IUserBiz userBiz = new UserBizImpl();
		List<User> lstUser = userBiz.findByObject(obj);
		IWorkBiz workBiz = new WorkBizImpl();
		List<Work> lstWork= workBiz.findByObject(obj);
		// 步骤5：根据动作结果响应客户端
		if(lstUser!=null&&lstWork!=null){
         request.setAttribute("lstUser", lstUser);
         request.setAttribute("lstWork", lstWork);
         System.out.println("查找成功！");
         RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/search.jsp");
			dispatcher.forward(request, response);
		}else{
			System.out.println("空");
		}
	}

}
