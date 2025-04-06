package novell;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DialogueScene {
	int id;
	String text;
	String speaker;
	int next;
	List<DialogueChoice> choices;
}
