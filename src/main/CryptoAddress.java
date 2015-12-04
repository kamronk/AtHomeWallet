package main;

import java.util.Date;

public class CryptoAddress {

	private String address;
	private float received;
	
	public CryptoAddress(String address, float received) {
		this.address = address;
		this.received = received;
	}

	public String getAddress() {
		return address;
	}

	public float getReceived() {
		return received;
	}
	
	

}
