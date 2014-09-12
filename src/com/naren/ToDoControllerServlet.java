package com.naren;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ToDoControllerServlet
 */
public class ToDoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of doGet of CS");
		request.setAttribute("task", null);
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside of doPost of CS");
		//doGet(request, response);
		process(request, response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd = null;
		String uri = request.getRequestURI();
		Model m = new Model();
		if(uri.contains("/add_task"))
		{
			System.out.println("add_task.do is found");
			ToDoBean td = (ToDoBean) request.getAttribute("task");
			System.out.println("task is "+ td.getTask()+ " Date is "+td.getDate());
			String insert_result = m.save_task(td);
			request.setAttribute("insert_tasks", insert_result);
			response.sendRedirect("dispTask.do");
			/*rd = request.getRequestDispatcher("dispTask.do");
			//request.setAttribute("task", null);
			rd.forward(request, response);*/
		}
		if(uri.contains("/dispTask"))
		{
			String disp_result = m.dispTask();
			request.setAttribute("disp_tasks", disp_result);
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}
