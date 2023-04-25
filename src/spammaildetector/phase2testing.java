package spammaildetector;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class phase2testing {
	String s;
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)														//found on stackoverflow
	      .matches();
	}
	public void Arrays() {
	
	
	ArrayList<String> goodMails = new ArrayList<String>();
	goodMails.add("gmail.com");
	goodMails.add("yahoo.com");
	goodMails.add("hotmail.com");
	goodMails.add("bluewin.com");
	goodMails.add("aol.com");
	goodMails.add("hotmail.co.uk");
	goodMails.add("hotmail.fr");
	goodMails.add("outlook.com");
	goodMails.add("ymail.com");
	goodMails.add("icloud.com");
	
	
	
	ArrayList<String> badMails = new ArrayList<String>();
	badMails.add("netplus.com");
	badMails.add("xbxx.com");
	badMails.add("xyz.com");
	badMails.add("asfhfgahfga");
	badMails.add("bjjkanffkjsa");
	badMails.add("fasvaeffasd");
	badMails.add("gaednankasna");
	
	}
	
	public String CompareMails() {
		
		
		
		return s;
		
	}
}
