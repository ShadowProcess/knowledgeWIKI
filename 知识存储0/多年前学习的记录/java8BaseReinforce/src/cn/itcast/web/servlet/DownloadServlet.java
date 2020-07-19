package cn.itcast.web.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Encoder;

import cn.itcast.domain.Resource;
import cn.itcast.service.ResourceService;
import cn.itcast.utils.FileUploadUtils;

public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.得到id
		String id = request.getParameter("id");

		// 2.调用service,得到Resource对象.
		ResourceService service = new ResourceService();
		try {
			Resource r = service.findById(id);

			File file = new File(r.getSavepath(), r.getUuidname());

			if (file.exists()) {
				// 资源存在
				String filename = r.getRealname();
				// 下载注意事项1--设置下载文件的mimeType
				String mimeType = this.getServletContext()
						.getMimeType(filename);
				response.setContentType(mimeType);

				String agent = request.getHeader("user-agent");
				if (agent.contains("MSIE")) {
					// IE浏览器
					filename = URLEncoder.encode(filename, "utf-8");

				} else if (agent.contains("Firefox")) {
					// 火狐浏览器
					BASE64Encoder base64Encoder = new BASE64Encoder();
					filename = "=?utf-8?B?"
							+ base64Encoder.encode(filename.getBytes("utf-8"))
							+ "?=";
				} else {
					// 其它浏览器
					filename = URLEncoder.encode(filename, "utf-8");
				}

				// 下载注意事项2--永远是下载
				response.setHeader("content-disposition",
						"attachment;filename=" + filename);

				byte[] b = FileUtils.readFileToByteArray(file); // 将指定文件读取到byte[]数组中.

				response.getOutputStream().write(b);

			} else {
				throw new RuntimeException("资源不存在");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
