package com.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/img/changecarousel.do")
public class ChangeCarousel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("change".equals(action)){
			Collection<Part> parts = req.getParts();
			for(Part part : parts){
				Set<String> imgpath = getServletContext().getResourcePaths("/img");
				if(imgpath.contains("/img/"+part.getName())){
					String path = ("/img/"+part.getName());
					getServletContext().getResourceAsStream(path);
					String realPath = getServletContext().getRealPath("/img/"+part.getName());
					File f = new File(realPath);
					part.write(f.toString());					
				}
				part.getName();
			}
		}
	}

}
