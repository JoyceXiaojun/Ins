package com.kc.inspiration.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.biz.impl.UserBizImpl;
import com.kc.inspiration.po.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
				"/upload/images/photo");
		System.out.println("[RegisterServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(this.getServletContext()
				.getRealPath("/tempDir"));
		if (!fileUploadTempPath.exists()) {
			// 创建一个全新的
			fileUploadTempPath.mkdir();
		}
		System.out.println("[RegisterServlet] 设置服务器接受客户端上传文件的临时位置是："
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
			System.out.println("[RegisterServlet] 初始化服务器接受客户端上传文件的临时位置完毕！");

			// 步骤4：解析客户端表单待上传的数据
			// 4-1:创建一个ServletFileUpload对象完成对二进制表单数据的解析，并实现服务器上传功能
			ServletFileUpload sfu = new ServletFileUpload(factory);

			try {
				// ★★特别注意★★ 在循环遍历表单数据之前创建实体类对象
				User user = new User();

				// 4-2:将解析到的二进制文件封装到FileItem的对象中
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = sfu.parseRequest(request);
				System.out.println(fileItems.size());
				for (FileItem fileItem : fileItems) {
					//System.out.println(fileItem.toString());
					// 扩展3：判断当前解析的请求数据是否为普通表单数据
					if (fileItem.isFormField()) {
						// 获取客户端表单输入元素的name属性的值
						String name = fileItem.getFieldName().trim();
						// 判断数据为哪个字段数据
						if ("username".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							user.setUsername(value);
							System.out.println("测试-注册用户名：> " + value);
						} else if ("password".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							user.setPassword(value);
							System.out.println("测试-注册密码：> " + value);
						} else if ("email".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							user.setEmail(value);
							System.out.println("测试-用户邮箱：> " + value);
						} else if ("mark".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							user.setMark(value);
							System.out.println("测试-用户签名：> " + value);
						}
					} else {
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						//判断文件是否为空
						if(fileName==null){
							request.setAttribute("msgcode", 4);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("jsp/signup.jsp");
							dispatcher.forward(request, response);
							System.out.println("[RegisterServlet] 没有选择文件");
						}
						System.out.println("[RegisterServlet] 上传文件的原名称："
								+ fileName);
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
							System.out.println("[RegisterServlet] 该类型文件不允许上传！");
							request.setAttribute("msgcode", 5);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("jsp/signup.jsp");
							dispatcher.forward(request, response);
							return;
						}
						System.out.println("[RegisterServlet] 获取上传文件的名称为: "
								+ fileName);
						// ★★特别注意★★ 在这个位置为头像属性赋值
						user.setPhoto(fileName);

						// 4-4:封装上传文件对象并写入到服务器
						File saveFile = new File(fileUploadPath, fileName);
						fileItem.write(saveFile);
						System.out.println("[RegisterServlet] 上传文件成功!");
					}
				}

				// 测试封装对象
				user.setLevel(0);
				user.setHonestylevel(5);
				user.setStatus(1);
				user.setAccstatus(1);
				user.setFanscount(0);
				user.setAttentioncount(0);
				System.out.println(user.toString());
				// 调用Biz层的方法完成注册功能
				IUserBiz userBiz = new UserBizImpl();
				int ret = userBiz.register(user);
				// 根据返回值结果进行页面跳转
				switch (ret) {
				case 1:{
					// 跳转至登录页面
					request.setAttribute("account", user.getAccount());
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/signin.jsp");
					dispatcher.forward(request, response);
					break;
				}
				case 2:{
					request.setAttribute("msgcode", ret);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/signup.jsp");
					dispatcher.forward(request, response);
					System.out.println("用户名已使用！！！！！");
					break;
				}
				case 3:{		
					request.setAttribute("msgcode", ret);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/signup.jsp");
					dispatcher.forward(request, response);
					System.out.println("邮箱已注册 ！！！！！");
					break;
				}
					
				case -1:{
					request.setAttribute("msgcode", ret);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/signup.jsp");
					dispatcher.forward(request, response);
					System.out.println("注册失败！！！！！");
					break;
				}
				default:
					break;
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
