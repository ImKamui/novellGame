package novell;

public class DialogueChoice {
	private String choiceText;
	private int nextSceneId;
	private String requirement;
	
	public DialogueChoice(String choiceText, int nextSceneId)
	{
		this.choiceText = choiceText;
		this.nextSceneId = nextSceneId;
	}
	public String getText() {
        return choiceText;
    }

    public int getNextSceneId() {
        return nextSceneId;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
