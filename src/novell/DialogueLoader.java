package novell;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DialogueLoader {
    public static Map<Integer, DialogueScene> loadDialogues(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            
            Type listType = new TypeToken<List<DialogueScene>>(){}.getType();
            
            
            List<DialogueScene> scenes = gson.fromJson(reader, listType);
            
            
            Map<Integer, DialogueScene> sceneMap = new HashMap<>();
            for (DialogueScene scene : scenes) {
                sceneMap.put(scene.id, scene);
            }
            return sceneMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
