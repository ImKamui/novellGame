package novell;

import java.util.List;
import java.util.ArrayList;

public class Scenes {
	int id;
	String char_name;
	String desc;
	Scenes(int id, String char_name, String desc)
	{
		this.id = id;
		this.char_name = char_name;
		this.desc = desc;
	}
	
	public String getCharName() {return char_name; }
	public String getDesc() {return desc; }
	
}
