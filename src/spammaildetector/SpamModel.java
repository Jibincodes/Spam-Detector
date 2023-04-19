package spammaildetector;
import java.util.ArrayList;
// Two ArrayList instance variables: spamkeywords and hamkeywords(no spam)
public class SpamModel {
    private ArrayList<String> spamKeywords;
    private ArrayList<String> hamKeywords;
    //Constructor to initialize both ArrayLists as empty
    public SpamModel() {
        spamKeywords = new ArrayList<>();
        hamKeywords = new ArrayList<>();
        initializeKeywords();
    }
    //Method to initialize ArrayLists
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
    //Method to add keywords to spamKeywords
    public void addSpamKeyword(String keyword) {
        spamKeywords.add(keyword);
    }
    //Method to add keywords to hamKeywords 
    public void addHamKeyword(String keyword) {
        hamKeywords.add(keyword);
    }
    // Getter method to return Spam ArrayList
    public ArrayList<String> getSpamKeywords() {
        return spamKeywords;
    }
    //Getter method to return Ham ArrayList
    public ArrayList<String> getHamKeywords() {
        return hamKeywords;
    }
}
