package spammaildetector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Two ArrayList instance variables: spamkeywords and hamkeywords(no spam)
public class SpamModel {
    protected ArrayList<String> spamKeywords; // change to protected
    protected ArrayList<String> hamKeywords;
    private ArrayList<String> spamdomains;
    private ArrayList<String> goodMails;
   // private static final String[] spamdomains = {"netplus.ch", "spamdomain.com", "otherspamdomain.com"};
    //Constructor to initialize both ArrayLists as empty
    public SpamModel() {
        spamKeywords = new ArrayList<>();
        hamKeywords = new ArrayList<>();
        spamdomains =new ArrayList<>();
        goodMails = new ArrayList<>();
        initializeKeywords();
    }
    //Method to initialize ArrayLists - Mike
    private void initializeKeywords() {
    	//add spam Keywords here
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
        //add ham keywords here
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
    //
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
    	
    		
    		
    		
    		/*for (String st: goodMails)
    		{
    			if (st.equalsIgnoreCase(domain1)) {
    				return 1.0;
    			}
    		}
    	        
    	}
    	return 1.0;*/
    	
    	/*String str;
    	str = emailAddress;
    	String []arr = emailAddress.split("<");
    	arr[1] = arr[1].trim();
    	//arr[1] = arr[1].replace(">","");
    	arr[1] = arr[1].substring(0, arr[1].length()-1);
    	int indexof = arr[1].indexOf('@');
    	String from = arr[1].substring(0, indexof);
    	String domain1 = arr[1].substring(indexof+1);
    	if (from.equalsIgnoreCase(domain1))
    	{
    		return 0.0;
    	}
    	
    	if (goodDomainList.contains(domainString))
    	{
    		return 0.0;
    		
    	
    	}
    	else if ()
    	
    	elseif(domain1.equalsIgnoreCase(gooodMails)){
    		return 0.0;
    	}
    	else {
    		spamdomains.add(domain1);
    		return 1.0;
    	}
 
    	
        for (String domain : spamdomains) {
            if (emailAddress.endsWith("@" + domain)) {
                return 1.0; // high spam score
            }
        }
        return 0.0; // low spam score
    }*/
    }
	/*private void writeSpamDomainsToFile() {
		try {
	        FileWriter writer = new FileWriter("spam_domains.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(writer);
	        for (String domain : spamdomains) {
	            bufferedWriter.write(domain);
	            bufferedWriter.newLine();
	        }
	        bufferedWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}*/
}


