package spammaildetector;

public class SpamController {
	private SpamModel model;
	private SpamView view;

	public SpamController(SpamModel model, SpamView view) {
		this.model = model;
		this.view = view;
	}
	//Methods to update model class ArrayLists
	public void addSpamKeyword(String keyword) {//Takes String parameter representing new Spam Keyword to add
        model.addSpamKeyword(keyword);//call method on model object, passing keyword as argument, this updates ArrayList with new Keyword
    }
	//Methods to update model class ArrayLists
    public void addHamKeyword(String keyword) {//Takes String parameter representing new Spam Keyword to add
        model.addHamKeyword(keyword);//call method on model object, passing keyword as argument, this updates ArrayList with new Keyword
    }
}
