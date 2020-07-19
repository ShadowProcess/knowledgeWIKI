package cn.itcast.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Resource;
import cn.itcast.service.ResourceService;

public class ShowDownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ResourceService service = new ResourceService();

		try {
			List<Resource> rs = service.findAll();

			request.setAttribute("rs", rs);

			request.getRequestDispatcher("/download.jsp").forward(request,
					response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
