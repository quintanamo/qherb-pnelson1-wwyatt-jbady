package edu.ycp.cs320.entrelink.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.entrelink.controller.PostController;
import edu.ycp.cs320.entrelink.model.DateModifier;
import edu.ycp.cs320.entrelink.model.Post;

public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		session.getAttribute("loggedInName");
		req.setAttribute("errorMessage", "");
		
		System.out.println("New Post Servlet: doGet");
		
		req.getRequestDispatcher("/_view/new_post.jsp").forward(req, resp);
	}	
	protected void doOpenProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
	}
	protected void doOpenProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	protected void doOpenSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);
	}
	protected void doOpenHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("\nNewPostServlet: doPost");
		HttpSession session=req.getSession();
		session.getAttribute("loggedInName");
		
		// here are some good heckin' variables
		String	errorMessage	= null;
		String	successMessage	= null;
		String 	postTitle 		= null;
		String 	postDescription	= null;
		String 	tags			= null;
		String	postType		= null;
		int		logId			= 1;
		int		type			= 0;
		
		// here is the date!
		DateModifier dmod = new DateModifier();
		String date = dmod.getCurrentDate();
		
		// Decode form parameters and dispatch to controller
		postTitle 		= req.getParameter("postTitle");
		postDescription = req.getParameter("postDescription");
		tags 			= req.getParameter("tags");
		postType		= req.getParameter("postType");
		logId			= Integer.parseInt(req.getParameter("loggedInId"));
		
		// set fields
		req.setAttribute("postTitle", postTitle);
		req.setAttribute("postDescription", postDescription);
		req.setAttribute("tags", tags);
		
		// Set the post type value accordingly...
		if(session.getAttribute("loggedInType").equals("Business")) type = 2;
		else if(postType.equals("Student Skill")) type = 1;
		
		if(postTitle 		== null || postTitle.equals("") 		||
		   postDescription 	== null || postDescription.equals("") 	||
		   tags 			== null || tags.equals("")) {
			
			errorMessage = "Please fill in all of the required fields";
			session.setAttribute("errorMessage", "ERROR: Please fill in all required fields!");
			req.getRequestDispatcher("/_view/new_post.jsp").forward(req, resp);
		} else {
			session.setAttribute("errorMessage", "");
			controller = new PostController();
			req.setAttribute("errorMessage", errorMessage);
			if(controller.createNewPost(logId, date, postTitle, postDescription, type, tags) != null) {
				System.out.println("Successfully inserted post.");
				// Forward to view to render the result HTML document		
				Post post = new Post();
				post.postPosts(req);
				req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
			}
			else {
				System.out.println("Post was not successfully inserted. Check your code, you butt.");
			}

		}
		


		
		
	}
}
