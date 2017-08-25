package com.bigdata2017.guestbook.comtroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.guestbook.dao.GuestbookDao;
import com.bigdata2017.guestbook.vo.GuestbookVo;

@WebServlet("/gb")
public class GuestbookServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");
		if("form".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);//데이터를 같이 넘겨줌
		}else if("insert".equals(actionName)) {
			String Name = request.getParameter("name");
	    	String password = request.getParameter("pass");
	    	String content = request.getParameter("content");
	    	
	    	GuestbookVo vo = new GuestbookVo();
	    	vo.setName(Name);
	    	vo.setContent(content);
	    	vo.setPassword(password);
	    	
	    	new GuestbookDao().insert(vo);
	    	
	    	response.sendRedirect(request.getContextPath()+"/gb");
		}else if("delete".equals(actionName)){
			String password = request.getParameter("password");
	    	String no = request.getParameter("no");
	    	
	    	GuestbookVo vo = new GuestbookVo();
	    	
	    	vo.setNo(Long.parseLong(no));
	    	vo.setPassword(password);
	    	
	    	new GuestbookDao().delete(vo);
	    	
	    	response.sendRedirect(request.getContextPath()+"/gb");
		}else{
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			
			request.setAttribute("guest", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);//데이터를 같이 넘겨줌
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
