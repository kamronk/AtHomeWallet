package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.CommWithWallet;
import main.PasswordManager;

/**
 * Servlet implementation class RstWallDat
 */
@WebServlet("/RstWallDat")
public class RstWallDat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RstWallDat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("RstWallDat.java :: In the send coin servlet");

		boolean validPw = false;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStamp = dateFormat.format(date);

		System.out.println("RstWallDat.java :: " + dateStamp);

		String passHash = request.getParameter("token");
		
		PasswordManager pM = new PasswordManager();
		
		if (passHash != null && !passHash.equals("") && pM.checkPassword(passHash)){
			CommWithWallet comm = new CommWithWallet();
			PrintWriter writer = response.getWriter();
			writer.println(comm.DeleteWallet());
			writer.flush();
			writer.close();
		}
	}

}
