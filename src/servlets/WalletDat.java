package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.CoinInfo;
import main.PasswordManager;

/**
 * Servlet implementation class WalletDat
 */
@WebServlet("/WalletDat")
public class WalletDat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CoinInfo coinInfo = new CoinInfo();
    private String WALLET_FILE_NAME = coinInfo.getWalletFileName();
    private String LINUX_DATA_DIR_NAME = coinInfo.getLinuxDataDirectoryName();
    private String WINDOWS_DATA_DIR_NAME = coinInfo.getWindowsDataDirectoryName();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WalletDat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("WalletDat.java :: In the send coin servlet");

		boolean validPw = false;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStamp = dateFormat.format(date);

		System.out.println("WalletDat.java :: " + dateStamp);

		String passHash = request.getParameter("token");
		
		PasswordManager pM = new PasswordManager();
		
		if (passHash != null && !passHash.equals("") && pM.checkPassword(passHash)){

	        String dir = "";
	        String fileLocation = "";
	        if (System.getProperty("user.home").contains("C:")) {
	            dir = System.getProperty("user.home") + "\\AppData\\Roaming\\" + WINDOWS_DATA_DIR_NAME;
	            fileLocation = dir + "\\" + WALLET_FILE_NAME;
	        } else {
	            dir = "/root/." + LINUX_DATA_DIR_NAME;
	            fileLocation = dir + "/" + WALLET_FILE_NAME;
	        }
	        String filePath = fileLocation;
	        File downloadFile = new File(filePath);
	        FileInputStream inStream = new FileInputStream(downloadFile);
	         
	        // if you want to use a relative path to context root:
	        String relativePath = getServletContext().getRealPath("");
	        System.out.println("relativePath = " + relativePath);
	         
	        // obtains ServletContext
	        ServletContext context = getServletContext();
	         
	        // gets MIME type of the file
	        String mimeType = context.getMimeType(filePath);
	        if (mimeType == null) {        
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	         
	        // modifies response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	         
	        // forces download
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	         
	        // obtains response's output stream
	        OutputStream outStream = response.getOutputStream();
	         
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	         
	        inStream.close();
	        outStream.close();     
	        ///////////////////////
	        
	        
//	        File walletFile = new File(fileLocation);
//	        response.setContentType("application/octet-stream");
//	        response.setHeader("Content-Disposition", "filename=\"hoge.txt\"");
//	        File srcFile = new File("/src_directory_path/hoge.txt");
//	        File.copyFile(srcFile, response.getOutputStream());
	        
		} else {
			System.out.println("Pw is incorrect.");
		}
	}

}
