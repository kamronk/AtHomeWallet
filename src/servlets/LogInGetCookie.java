package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.PasswordManager;

/**
 * Servlet implementation class LogInGetCookie
 */
public class LogInGetCookie extends HttpServlet {

	   
	private static final long serialVersionUID = 1L;
	   static Logger log = Logger.getLogger(LogInGetCookie.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInGetCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("LogInGetCookie.java :: In the log in servlet to check the password");

		boolean validPw = false;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStamp = dateFormat.format(date);

		System.out.println("LogInGetCookie.java :: " + dateStamp);

		String passHash = request.getParameter("pwhash");
		String passHashConfirm = request.getParameter("pwhashconfirm");
		String currentPassHash = request.getParameter("currentpwhash");

		PasswordManager pM = new PasswordManager();

		if (pM.isThereApassword()){
			if (passHash != null && !passHash.equals("")){
				if (currentPassHash != null && !currentPassHash.equals("")){
					if (pM.checkPassword(currentPassHash)){
						if (passHash.equals(passHashConfirm)){
							pM.savePw(passHash);
							System.out.println("LogInGetCookie.java :: Resetting password " + passHash);
							PrintWriter writer = response.getWriter();
							writer.println("0:Password has been successfully reset.");
							writer.flush();
							writer.close();
						} else {
							System.out.println("LogInGetCookie.java :: Failed at Resetting password " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
							PrintWriter writer = response.getWriter();
							writer.println("-1:Fail, Password has NOT been successfully reset. (new and confirm pass no match)");
							writer.flush();
							writer.close();
						}
					} else {
						System.out.println("LogInGetCookie.java :: Failed at Resetting password (curr pass no match) " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
						PrintWriter writer = response.getWriter();
						writer.println("-1:Fail, Password has NOT been successfully reset. Current password incorrect");
						writer.flush();
						writer.close();
					}
				} else {
					if (pM.checkPassword(passHash)){
						System.out.println("LogInGetCookie.java :: Password is correct " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
						PrintWriter writer = response.getWriter();
						writer.println("0:Password has been correctly matched.");
						writer.flush();
						writer.close();
					} else {
						System.out.println("LogInGetCookie.java :: Password is incorrect " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
						PrintWriter writer = response.getWriter();
						writer.println("-1:Password has NOT been correctly matched.");
						writer.flush();
						writer.close();
					}
				}
				
			} else {
				System.out.println("LogInGetCookie.java :: Inbound password is blank/null. " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
				PrintWriter writer = response.getWriter();
				writer.println("-1:Inbound password is blank/null.");
				writer.flush();
				writer.close();
			}
		} else {
			//no password file
			if (passHash != null && !passHash.equals("") && passHashConfirm != null && !passHashConfirm.equals("")
					&& passHash.equals(passHashConfirm)){
				pM.savePw(passHash);
				System.out.println("LogInGetCookie.java :: Saved new password hash " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
				PrintWriter writer = response.getWriter();
				writer.println("1:Successfully wrote new pw to the file.");
				writer.flush();
				writer.close();
			} else {
				System.out.println("LogInGetCookie.java :: Failed to save new password, didn't match or was empty. " + passHash + ":" + passHashConfirm + ":" + currentPassHash);
				PrintWriter writer = response.getWriter();
				writer.println("-1:Failed to write new pw to the file.");
				writer.flush();
				writer.close();
			}
		}
	}

}
