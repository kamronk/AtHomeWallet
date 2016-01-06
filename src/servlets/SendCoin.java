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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.CommWithWallet;
import main.PasswordManager;

/**
 * Servlet implementation class SendCoin
 */
@WebServlet("/SendCoinAction")
public class SendCoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SendCoin.java :: In the send coin servlet");

		boolean validPw = false;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStamp = dateFormat.format(date);

		System.out.println("SendCoin.java :: " + dateStamp);

		String passHash = request.getParameter("pwhash");
		String address = request.getParameter("address");
		String amountString = request.getParameter("amount");
		
		PasswordManager pM = new PasswordManager();
		
		if (passHash != null && !passHash.equals("") && address != null && !address.equals("") && amountString != null && !amountString.equals("") && pM.checkPassword(passHash)){

			try {
				CommWithWallet that = new CommWithWallet();
				
				float amount = Float.parseFloat(amountString);

				JSONParser parser = new JSONParser();

				JSONObject result;
				result = (JSONObject) parser.parse(that.CryptoInvoke("sendtoaddress", address, amount));

				System.out.println("SendCoin.java :: Sent coin response " + result);
				
				if (result.get("error") != null && !result.get("error").equals("")){
					System.out.println("SendCoin.java :: Error - " + ((JSONObject)result.get("error")).get("message").toString());
					PrintWriter writer = response.getWriter();
					writer.println("-1:Error - " + ((JSONObject)result.get("error")).get("message").toString());
					writer.flush();
					writer.close();
				} else {
					String txId = result.get("result").toString();

					System.out.println("SendCoin.java :: Sent coin " + txId);
					PrintWriter writer = response.getWriter();
					writer.println("1:Success (" + txId + ")");
					writer.flush();
					writer.close();
				}
				

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SendCoin.java :: Error");
				PrintWriter writer = response.getWriter();
				writer.println("-1:Error - " + e.getLocalizedMessage());
				writer.flush();
				writer.close();
			} catch (NumberFormatException e){
				e.printStackTrace();
				System.out.println("SendCoin.java :: Error");
				PrintWriter writer = response.getWriter();
				writer.println("-1:Amount was not a number - " + e.getLocalizedMessage());
				writer.flush();
				writer.close();
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("SendCoin.java :: Error");
				PrintWriter writer = response.getWriter();
				writer.println("-1:Error - " + e.getLocalizedMessage());
				writer.flush();
				writer.close();
			}
		} else {
			System.out.println("SendCoin.java :: Password is incorrect " + passHash);
			PrintWriter writer = response.getWriter();
			writer.println("-1:Password has NOT been correctly matched.");
			writer.flush();
			writer.close();
		}
	}

}
