package novell;

import java.util.List;
import java.util.ArrayList;

public class DialogueScene {
	private int id;
	private String text;
	private String speaker;
	private int nextSceneId;
	private List<DialogueChoice> choices;
	private String nextSceneImage;
	
	public DialogueScene()
	{
		this.choices = new ArrayList<>();
	}
	
	public DialogueScene(int id, String text)
	{
		this();
		this.id = id;
		this.text = text;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public int getNextSceneId() {
        return nextSceneId;
    }

    public void setNextSceneId(int nextSceneId) {
        this.nextSceneId = nextSceneId;
    }

    public List<DialogueChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<DialogueChoice> choices) {
        this.choices = choices;
    }
    
    public void addChoice(String text, int nextSceneId) {
        if (choices == null) {
            choices = new ArrayList<>();
        }
        choices.add(new DialogueChoice(text, nextSceneId));
    }
    
    public boolean hacChoice()
    {
    	return choices != null && !choices.isEmpty();
    }
    
    public String getSceneImage() 
    {
    	return nextSceneImage;
    }
    
    public void setSceneImage(String pathToImage)
    {
    	this.nextSceneImage = pathToImage;
    }
    
}
