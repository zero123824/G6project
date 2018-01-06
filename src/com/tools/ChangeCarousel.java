package com.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/img/changecarousel.do")
public class ChangeCarousel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,List<String>> picMap = new HashMap<String,List<String>>(); 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("change".equals(action)){
			String pageFrom = req.getParameter("pageFrom");
			try{
				Collection<Part> parts = req.getParts();
				for(Part part : parts){
					File localdir = new File("D://image");
					File[] files = localdir.listFiles();			        
					for(File file :files){
						if(file.getName().equals(part.getName()) && part.getSize() !=0 ){
							//寫入完整圖片
							part.write(file.getAbsolutePath());
							//裁圖片
							List<String> params = picMap.get(part.getName());
					        String format = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")+1,file.getAbsolutePath().length());  
					        BufferedImage image = ImageIO.read(file);
					        Integer x = new Float(params.get(0)).intValue();
					        Integer y = new Float(params.get(1)).intValue();
					        Integer w = new Float(params.get(2)).intValue();
					        Integer h = new Float(params.get(3)).intValue();
					        image = image.getSubimage(x,y,w,h);  
					        ImageIO.write(image, format, new File(file.getAbsolutePath()));  
						}
					}
				}
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				res.setHeader("Pragma", "no-cache");
				res.setDateHeader("Expires", 0);
				res.sendRedirect(pageFrom);
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if("setSize".equals(action)){
			String which = (req.getParameter("which"));
			String param = (req.getParameter("param"));
			if(which!= null && param != null) {
				String[] params = param.split(",");	
				picMap.put(which, Arrays.asList(params));
			}
		}
		
	}
}
//Set<String> imgpath = getServletContext().getResourcePaths("/img");
//if(imgpath.contains("/img/"+part.getName()) && part.getSize() != 0){
//	String path = ("/img/"+part.getName());					
//	getServletContext().getResourceAsStream(path);
//	String realPath = getServletContext().getRealPath("/img/"+part.getName());
//	File f = new File(realPath);
//	part.write(f.toString());					
//}
//part.getName();
