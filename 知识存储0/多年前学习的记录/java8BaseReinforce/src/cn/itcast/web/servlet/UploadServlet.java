package cn.itcast.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.domain.Resource;
import cn.itcast.service.ResourceService;
import cn.itcast.utils.FileUploadUtils;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> map = new HashMap<String, String[]>();

		// 1.创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 2.创建ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置上传文件中文名称乱码
		upload.setHeaderEncoding("utf-8");
		// upload.isMultipartContent(request)
		// 3.得到所有的FileItem
		try {
			List<FileItem> items = upload.parseRequest(request);

			// 遍历items，得到所有的上传信息
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 不是上传组件
					map.put(item.getFieldName(),
							new String[] { item.getString("utf-8") }); // 封装非上传组件信息
				} else {
					// 是上传组件
					// 得到上传文件名称
					String filename = item.getName();
					filename = FileUploadUtils.getRealName(filename);
					map.put("realname", new String[] { filename }); // 封装上传文件真实名称
					// 得到随机名称
					String uuidname = FileUploadUtils.getUUIDFileName(filename);

					map.put("uuidname", new String[] { uuidname });// 封装上传文件随机名称
					// 得到随机目录
					String randomDirectory = FileUploadUtils
							.getRandomDirectory(filename);

					String uploadPath = this.getServletContext().getRealPath(
							"/WEB-INF/upload");

					File parentDirectory = new File(uploadPath, randomDirectory);
					if (!parentDirectory.exists()) {
						parentDirectory.mkdirs();
					}

					map.put("savepath", new String[] { uploadPath
							+ randomDirectory });// 封装上传文件保存路径

					IOUtils.copy(item.getInputStream(), new FileOutputStream(
							new File(parentDirectory, uuidname)));

					item.delete();
				}
			}

			// /将数据封装到javaBean
			Resource r = new Resource();

			BeanUtils.populate(r, map);

			// 调用service完成保存数据到db。
			ResourceService service = new ResourceService();

			service.save(r);

			response.sendRedirect(request.getContextPath() + "/index.jsp");

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
