package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordManager {
	
	public static void main(String[] args){
	}
	
	public boolean checkPassword(String inputPwHash){
		File pwFile = new File(System.getProperty("user.home") + "/temp/.stakerPw");
		if (pwFile.exists()){
			try {
				BufferedReader in = new BufferedReader(new FileReader(pwFile));
				String storedHash = "";
				while ((storedHash = in.readLine()) != null){
					System.out.println("comparing pw's...in:" + inputPwHash + "...stored:" + storedHash);
					if (storedHash.equals(inputPwHash)){
						in.close();
						return true;
					}
				}
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isThereApassword(){
		File pwFile = new File(System.getProperty("user.home") + "/temp/.stakerPw");
		if (pwFile.exists()){
			return true;
		} else {
			return false;
		}
	}
	
	public void savePw(String inputPwHash){
		try {
			File file = new File(System.getProperty("user.home") + "/temp/.stakerPw");
			file.getParentFile().mkdirs();
			BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/temp/.stakerPw"));
			out.write(inputPwHash);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
