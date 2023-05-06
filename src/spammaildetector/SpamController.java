package spammaildetector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class SpamController {
	private SpamModel model;
	private SpamView view;
	private static final int SPAM1 = 1;
	// Mike
	private static final List<String> spamKeywords = new ArrayList<String>() {{
   	 // Add more Spam Keywords in English here
   	add("$$$");
   	add("Bulk email");
   	add("Buy direct");
   	add("Cancel at any time");
   	add("Check or money order");
   	add("Congratulations");
   	add("Confidentiality");
   	add("Cures");
   	add("Dear friend");
   	add("Direct email");
   	add("Direct marketing");
   	add("Hidden charges");
   	add("Human growth hormone");
   	add("Internet marketing");
   	add("Lose weight");
   	add("Mass email");
   	add("Meet singles");
   	add("Multi-level marketing");
   	add("No catch");
   	add("No cost");
   	add("No credit check");
   	add("No fees");
   	add("No gimmick");
   	add("No hidden costs");
   	add("No hidden fees");
   	add("No interest");
   	add("No investment");
   	add("No obligation");
   	add("No purchase necessary");
   	add("No questions asked");
   	add("No strings attached");
   	add("Not junk");
   	add("Notspam");
   	add("Obligation");
   	add("Passwords");
   	add("Requires initial investment");
   	add("Social security number");
   	add("This isn't a scam");
   	add("This isn't junk");
   	add("This isn't spam");
   	add("Undisclosed");
   	add("Unsecured credit");
   	add("Unsecured debt");
   	add("Unsolicited");
   	add("Valium");
   	add("Viagra");
   	add("Vicodin");
   	add("We hate spam");
   	add("Weight loss");
   	add("Xanax");
       add("Act now");
       add("Apply now");
       add("Become a member");
       add("Call now");
       add("Click below");
       add("Click here");
       add("Get it now");
       add("Do it today");
       add("Don't delete");
       add("Exclusive deal");
       add("Get started now");
       add("Important information regarding");
       add("Information you requested");
       add("Instant");
       add("Limited time");
       add("New customers only");
       add("Order now");
       add("Please read");
       add("See for yourself");
       add("Sign up free");
       add("Take action");
       add("This won't last");
       add("Urgent");
       add("What are you waiting for?");
       add("#1");
       add("100% more");
       add("100% free");
       add("100% satisfied");
       add("Additional income");
       add("Be your own boss");
       add("Best price");
       add("Big bucks");
       add("Billion");
       add("Cash bonus");
       add("Cents on the dollar");
       add("Consolidate debt");
       add("Double your cash");
       add("Double your income");
       add("Earn extra cash");
       add("Earn money");
       add("Eliminate bad credit");
       add("Extra cash");
       add("Extra income");
       add("Expect to earn");
       add("Fast cash");
       add("Financial freedom");
       add("Free access");
       add("Free consultation");
       add("Free gift");
       add("Free hosting");
       add("Free info");
       add("Free investment");
       add("Free membership");
       add("Free money");
       add("Free preview");
       add("Free quote");
       add("Free trial");
       add("Full refund");
       add("Get out of debt");
       add("Get paid");
       add("Giveaway");
       add("Guaranteed");
       add("Increase sales");
       add("Increase traffic");
       add("Incredible deal");
       add("Lower rates");
       add("Lowest price");
       add("Make money");
       add("Million dollars");
       add("Miracle");
       add("Money back");
       add("Once in a lifetime");
       add("One time");
       add("Pennies a day");
       add("Potential earnings");
       add("Prize");
       add("Promise");
       add("Pure profit");
       add("Risk-free");
       add("Satisfaction guaranteed");
       add("Save big money");
       add("Save up to");
       add("Special promotion");
       // Add Spam keywords in German here 
       add("€€€");
       add("100% gratis");
       add("100% kostenlos");
       add("Sichere Anlage");
       add("Bargeld");
       add("Bargeld-günstig");
       add("Begünstigter");
       add("Einkommen verdoppeln");
       add("Einkommen von Zuhause");
       add("Extra Bargeld verdienen");
       add("Fondsmanagement");
       add("Geld verdienen leicht gemacht");
       add("Günstiger Kredit");
       add("Günstige Refinanzierung");
       add("Hypothek");
       add("Ihr Zahlungsverzug");
       add("Kostenlos");
       add("Kontosicherheit");
       add("Paypal");
       add("Rechnung");
       add("Rendite");
       add("Senken Sie Ihre Hypothek");
       add("Schulden beseitigen");
       add("Verdienen Sie “x” pro Woche");
       add("Versteckte Kosten");
       add("Viel Geld sparen");
       add("Visa/Mastercard");
       add("Völlig kostenlos");
       add("Von Zuhause arbeiten");

   }};
   // Mike
   private static final List<String> hamKeywords = new ArrayList<String>() {{
       add("meeting");
       add("agenda");
       add("presentation");
       add("schedule");
       add("appointment");
       add("Termin");
       add("deadline");
       add("feedback");
       add("customer support");
       add("Rechnung");
       add("Mahnung");
       add("Invoice");
       add("Bestätigung");
       add("Confirmation");
       add("Bewerbung");
       add("Application");
       add("Job");
       add("Password");
       // Add more ham keywords here
   }};

	public SpamController(SpamModel model, SpamView view) {
		this.model = model;
		this.view = view;
		
	}
	//=========================================================================================================
	//Methods to update model class ArrayLists - not implemented
	public void addSpamKeyword(String keyword) {
        model.addSpamKeyword(keyword);
    }
	//Methods to update model class ArrayLists - not implemented
    public void addHamKeyword(String keyword) {
        model.addHamKeyword(keyword);
    }
    //============================================================================================================
    // new changes to check the word in the content
    // Mike & Sascha
    public boolean isSpam(Message message) throws MessagingException, IOException {
        Double spamScore = 0.0;
        // Check body for spam keywords
        if (message instanceof MimeMessage) {
            MimeMessage mimeMessage = (MimeMessage) message;
            String contentType = mimeMessage.getContentType();
            if (contentType.toLowerCase().contains("multipart/alternative")) {
                Object content = mimeMessage.getContent();
                if (content instanceof Multipart) {
                    Multipart multipart = (Multipart) content;
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        String bodyContentType = bodyPart.getContentType();
                        if (bodyContentType.toLowerCase().contains("text/plain")) {
                            String bodyContent = (String) bodyPart.getContent();
                            String str = bodyContent;
                            // just for testing purpose
                            //System.out.print(str);
                            if (containsKeyword(str, spamKeywords)) {
                                spamScore =  1.0;
                                view.spamScoreofContent.setText(String.format("%.2f", spamScore));
                            } else if (containsKeyword(str, hamKeywords)) {
                                spamScore =  0.0;
                                view.spamScoreofContent.setText(String.format("%.2f", spamScore));
                            }
                        }
                    }
                }
            }
        }
        // This method is really restricted  and if its not in both List, we set for now score =1
        // We would like to develop this program more in the future
        return spamScore >= SPAM1;
    }
    // Jibin
    private boolean containsKeyword(String text, Iterable<String> keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    // Jibin
    public void processEmailFile(File file) {
        if (file != null) {
            try {
                MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties()), Files.newInputStream(file.toPath()));
                boolean isSpam = isSpam(message);
            } catch (MessagingException | IOException e) {
                view.contentLabel.setText("Error parsing email: " + e.getMessage());
            }
        }
    }
}
    


