package spammaildetector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SpamView {
	protected Stage stage;
	private SpamModel model;
	
	//new addings
	 private static final String TEXT_PLAIN = "text/plain";
	 private static final String TEXT_HTML = "text/html";
	    
	  private Label fromLabel = new Label();
	  private Label toLabel = new Label();
	  private Label subjectLabel = new Label();
	  private Label contentLabel = new Label();
	  private Label spamScoreLabel = new Label();
     // till here
	  
	public SpamView(Stage primaryStage, SpamModel model) {
		this.stage = primaryStage;
		this.model = model;
		
		//new addings
		Label titleLabel = new Label("Email Parser");
        Label fromTitleLabel = new Label("From:");
        Label toTitleLabel = new Label("To:");
        Label subjectTitleLabel = new Label("Subject:");
        Label contentTitleLabel = new Label("Content:");
        Label spamScoreTitleLabel = new Label("Spam Score:");
        VBox vbox = new VBox(titleLabel, fromTitleLabel, fromLabel, toTitleLabel, toLabel, subjectTitleLabel, subjectLabel, contentTitleLabel, contentLabel, spamScoreTitleLabel, spamScoreLabel);
        Scene scene = new Scene(vbox, 800, 600);
        //till here
		
		/*Menu fileMenu = new Menu("File");
		MenuItem item = new MenuItem("Open multiple File");*/
		
		//fileMenu.getItems().addAll(item);
		FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open email file");
	    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Email files (*.eml)", "*.eml"));
	    //item.setOnAction(new EventHandler<ActionEvent>() {
	         //public void handle(ActionEvent event) {
	            //Opening a dialog box and reading the file
	    File file = fileChooser.showOpenDialog(stage);      
	     // }});
	    // new addings
	    if (file != null) {
            try {
            	MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties()), Files.newInputStream(file.toPath())); 
                Address[] fromAddresses = message.getFrom();
                fromLabel.setText(fromAddresses != null && fromAddresses.length > 0 ? fromAddresses[0].toString() : "");
                Address[] toAddresses = message.getRecipients(Message.RecipientType.TO);
                toLabel.setText(toAddresses != null && toAddresses.length > 0 ? toAddresses[0].toString() : "");
                subjectLabel.setText(message.getSubject());
                String content = getContent(message);
                contentLabel.setText(content);
            } catch (MessagingException | IOException e) {
                contentLabel.setText("Error parsing email: " + e.getMessage());
            }
        }
	    // till here
	    /*MenuBar menuBar = new MenuBar(fileMenu);
	    menuBar.setTranslateX(3);
	    menuBar.setTranslateY(3);*/
	    
	    /*Group root = new Group(menuBar);
	    Scene scene = new Scene(root, 595, 355, Color.BLACK);*/
	    stage.setTitle("File Chooser");
	    stage.setScene(scene);
		
	}
	private String getContent(MimeMessage message) throws MessagingException, IOException {
        Object content = message.getContent();
        if (content instanceof String) {
            return (String) content;
        } else if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType(TEXT_PLAIN)) {
                    return (String) bodyPart.getContent();
                } else if (bodyPart.isMimeType(TEXT_HTML)) {
                    return (String) bodyPart.getContent();
                }
            }
        }
        return "";
    }
	public void start() {
		stage.show();
		
	}

}
