package main;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

public class CryptoTransaction {

	private String type;
	private float amount;
	private String txId;
	private String dateTime;
	private int confirmations;
	private double fee;
	private float total;
	
	public CryptoTransaction(String type, float amount, String txId, String dateTime, int confirmations, double fee) {
		this.type = type;
		this.amount = amount;
		this.txId = txId;
		this.dateTime = dateTime;
		this.confirmations = confirmations;
		this.fee = fee;
		this.total = (float) (amount + fee);
	}
	public String getType() {
		return type;
	}
	public String getAmount() {
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(8); 
		return df.format(amount);
	}
	public String getTxId() {
		return txId;
	}
	public String getDateTime() {
		Date expiry = new Date(Long.parseLong(dateTime) * 1000);
		return expiry.toString();
	}
	public int getConfirmations() {
		return confirmations;
	}
	public String getFee() {
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(8); 
		return df.format(fee);
	}
	public String getTotal() {
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(8); 
		return df.format(total);
	}

}
