package spammaildetector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamModel {
    protected ArrayList<String> spamKeywords; // this arrayList has not been used
    protected ArrayList<String> hamKeywords; //this arrayList has not been used
    private ArrayList<String> spamdomains;
    private ArrayList<String> goodMails;
    //Constructor to initialize both ArrayLists as empty
    public SpamModel() {
        spamKeywords = new ArrayList<>(); //not implemented 
        hamKeywords = new ArrayList<>(); // not implemented
        spamdomains =new ArrayList<>();
        goodMails = new ArrayList<>();
        initializeKeywords();
    }
    //Method to initialize ArrayLists - Mike
    private void initializeKeywords() {
    	//add spam Keywords here - not used
    	spamKeywords.add("make money fast");
        spamKeywords.add("get rich quick");
        spamKeywords.add("money-back guarantee");
        spamKeywords.add("free trial");
        spamKeywords.add("online pharmacy");
        spamKeywords.add("multi-level marketing");
        spamKeywords.add("work from home");
        spamKeywords.add("lottery winner");
        spamKeywords.add("viagra");
        spamKeywords.add("increase your income");
        //================================================
        // - Sascha
        spamdomains.add("netplus");
        spamdomains.add("spam123");
        spamdomains.add("spamdomain");
        spamdomains.add("spamdomain1");
        //===============================================
        // - Sascha
        goodMails.add("gmail");
    	goodMails.add("yahoo");
    	goodMails.add("hotmail");
    	goodMails.add("bluewin");
    	goodMails.add("aol");
    	goodMails.add("duck");
    	goodMails.add("outlook");
    	goodMails.add("ymail");
    	goodMails.add("icloud");
    	goodMails.add("fhnw");
        //=============================================
        //add ham keywords here - not used
        hamKeywords.add("meeting");
        hamKeywords.add("agenda");
        hamKeywords.add("schedule");
        hamKeywords.add("appointment");
        hamKeywords.add("deadline");
        hamKeywords.add("teamwork");
        hamKeywords.add("task");
        hamKeywords.add("project");
        hamKeywords.add("customer support");
        hamKeywords.add("feedback");
        
    }
    //====================================================================================
    // These are implementations that we wish to set up in the future for the headers
    //Method to add keywords to spamKeywords-Mike
    public void addSpamKeyword(String keyword) {
        spamKeywords.add(keyword);
    }
    //Method to add keywords to hamKeywords -Mike
    public void addHamKeyword(String keyword) {
        hamKeywords.add(keyword);
    }
    // Getter method to return Spam ArrayList -Mike
    public ArrayList<String> getSpamKeywords() {
        return spamKeywords;
    }
    //Getter method to return Ham ArrayList -Mike
    public ArrayList<String> getHamKeywords() {
        return hamKeywords;
    }  
    //=====================================================================================
    // getSpamScore by Jibin
    public double getSpamScore(String emailAddress) {
    	String str;
    	str = emailAddress;
    	Pattern pattern = Pattern.compile("(?<=@)[^.]+(?=\\.)");
        Matcher matcher = pattern.matcher(str);
    	if(matcher.find()) {
    		String domain1 = matcher.group();
    	 if (goodMails.contains(domain1))
    	 {
    		 return 0.0; // not spam
    	 }
    	 if (spamdomains.contains(domain1))
    	 {
    		 return 1.0; // high spam score
    	 }
    	 else
    	 {
    		 spamdomains.add(domain1);
    		 try {
                 FileWriter writer = new FileWriter("spam_domains.txt", true);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer);
                 bufferedWriter.write(domain1);
                 bufferedWriter.newLine();
                 bufferedWriter.close();
                 File file = new File("spam_domains.txt");
             	System.out.println("File path: " + file.getAbsolutePath());

             } catch (IOException e) {
                 e.printStackTrace();
             }
    		 return 1.0; // high spam score
    		 
    		 
    	 }
    	 }
    	
    	return 0.0;
    }
}


