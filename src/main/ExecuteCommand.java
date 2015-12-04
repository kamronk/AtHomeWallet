package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommand {

	private boolean debugMode = false;

	public static void main(String[] args) {
		ExecuteCommand exec = new ExecuteCommand("cp /root/.bitcoin/bitcoin.conf /root/");
		// ExecuteCommand exec2 = new
		// ExecuteCommand("chmod 666 /var/mqsi/rp/GXSSal/20120605_123436_049023_SLSRPT-025009-06052012_PROD.txt");
	}

	public ExecuteCommand(String command) {
		try {

			if (debugMode) {
				System.out.println(command);
			} else {
				System.out.println("EXECUTING!::" + command);
				Process child = Runtime.getRuntime().exec(command);
				child.waitFor();
				BufferedReader in = new BufferedReader(new InputStreamReader(child.getErrorStream()));
				String line = "";
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				in.close();
				BufferedReader in2 = new BufferedReader(new InputStreamReader(child.getInputStream()));
				String line2 = "";
				while ((line2 = in2.readLine()) != null) {
					System.out.println(line2);
				}
				in.close();
				child.destroy();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getLocalizedMessage());
		} catch (InterruptedException ie) {
			System.out.println("Error: " + ie.getLocalizedMessage());
		} catch (Exception ie) {
			System.out.println("Error: " + ie.getLocalizedMessage());
		}
	}

}
