package com.kc.inspiration.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kc.inspiration.biz.IResourceBiz;
import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.biz.impl.ResourceBizImpl;
import com.kc.inspiration.biz.impl.WorkBizImpl;
import com.kc.inspiration.po.Resource;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

/**
 * Servlet implementation class PublishServlet
 */
public class PublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublishServlet() {
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
		// 步骤1：设置上传文件的参数
		// 1-1：设置服务器接受上传文件的位置（服务器的文件夹）
		String fileUploadPath = this.getServletContext().getRealPath(
				"/upload/images/works");
		System.out.println("[PublishServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(this.getServletContext()
				.getRealPath("/tempDir"));
		if (!fileUploadTempPath.exists()) {
			// 创建一个全新的
			fileUploadTempPath.mkdir();
		}
		System.out.println("[PublishServlet] 设置服务器接受客户端上传文件的临时位置是："
				+ fileUploadTempPath.getPath());

		// 步骤2：判断表单是否符合上传要求
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 步骤3：设置文件上传缓冲区对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 3-1:缓冲区对象与磁盘物理位置的绑定
			factory.setRepository(fileUploadTempPath);
			// 3-2：设置缓冲区对象的大小（4*1024 字节）
			factory.setSizeThreshold(4 * 1024);
			System.out.println("[PublishServlet] 初始化服务器接受客户端上传文件的临时位置完毕！");

			// 步骤4：解析客户端表单待上传的数据
			// 4-1:创建一个ServletFileUpload对象完成对二进制表单数据的解析，并实现服务器上传功能
			ServletFileUpload sfu = new ServletFileUpload(factory);
			try {
				// 创建实体类对象
				Work work = new Work();
				Resource res = new Resource();
				// 4-2:将解析到的二进制文件封装到FileItem的对象中
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = sfu.parseRequest(request);
				//测试
				//System.out.println(fileItems.size());
				for (FileItem fileItem : fileItems) {
					// System.out.println(fileItem.toString());
					// 扩展3：判断当前解析的请求数据是否为普通表单数据
					if (fileItem.isFormField()) {
						// 获取客户端表单输入元素的name属性的值
						String name = fileItem.getFieldName().trim();
						// 判断数据为哪个字段数据
						if ("workname".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							work.setWorkname(value);
							System.out.println("作品名称：> " + value);
						} else if ("category".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							work.setCatagory(value);
							System.out.println("作品类别：> " + value);
						} else if ("description".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							work.setWorkdescription(value);
							System.out.println("作品描述：> " + value);
						}

					} else {
						System.out.println("文件：>");
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						// 扩展1：唯一命名
						String fileExtName = fileName.substring(fileName
								.lastIndexOf("."));
						fileName = generateUnqieName() + fileExtName;
						// 扩展2：限制上传文件类型
						String[] allowedTypes = new String[] { ".jpg", ".jpeg",
								".png", ".bmp" };
						Arrays.sort(allowedTypes);
						int searchIndex = Arrays.binarySearch(allowedTypes,
								fileExtName);
						if (searchIndex < 0) {
							int msgcode = 100;
							request.setAttribute("msgcode", msgcode);
							RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/publish.jsp");
							dispatcher.forward(request, response);
							System.out.println("[PublishServlet] 该类型文件不允许上传！");
							return;
						}
						System.out.println("[PublishServlet] 获取上传文件的名称为: "
								+ fileName);
						// 4-4:封装上传文件对象并写入到服务器
						File saveFile = new File(fileUploadPath, fileName);
						fileItem.write(saveFile);
						System.out.println("[PublishServlet] 上传文件成功！");				
						res.setPath(fileName);
						res.setType(fileExtName);
					}
				}
				// 调用Biz层将Resource对象存入数据库
				IResourceBiz resBiz = new ResourceBizImpl();	
				if (resBiz.add(res)) {
					// 测试封装对象
					//获取资源的ID
				    work.setRid(resBiz.findByPath(res.getPath()).getRid());
				    HttpSession session = request.getSession(); // 获取session对象
				    User user =(User)session.getAttribute("user");
					work.setUid(user.getUid());
					work.setPublishtime(new Date());
					// 调用Biz层的方法完成发布功能
					IWorkBiz workBiz = new WorkBizImpl();
					// 验证该用户是否已经发布过同一名称的作品
					if(workBiz.findByObject(user.getUid(), work.getWorkname())!=null){
						// 该用户已经存在同名作品
						int msgcode=101;
						request.setAttribute("msgcode", msgcode);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("jsp/publish.jsp");
						dispatcher.forward(request, response);
						System.out.println("发布失败！！！！！");
					}
					// 根据返回值结果进行页面跳转
					if (workBiz.add(work)) {
						// 作品添加成功，跳转至作品页面
						int wid=workBiz.findByObject(user.getUid(), work.getWorkname()).getWid();
					    //request.setAttribute("wid",wid );
					    
//						RequestDispatcher dispatcher = request
//								.getRequestDispatcher("jsp/work.jsp?wid="+wid);
//						dispatcher.forward(request, response);
						response.setContentType("text/html;charset=UTF-8");
						response.sendRedirect("jsp/work.jsp?wid="+wid);
					} else {
						//
						int msgcode=102;
						request.setAttribute("msgcode", msgcode);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("jsp/publish.jsp");
						dispatcher.forward(request, response);
						System.out.println("作品添加失败！！！！！");
					}
				} else {
					//图片上传错误
					int msgcode=103;
					request.setAttribute("msgcode", msgcode);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/publish.jsp");
					dispatcher.forward(request, response);
					System.out.println("图片上传错误！！！！！");
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("客户端表单不符合上传要求！");
		}
	}

	// 自定义方法完成上传文件名称的自动生成
	private synchronized String generateUnqieName() {
		return String.valueOf(System.nanoTime());
	}

}
