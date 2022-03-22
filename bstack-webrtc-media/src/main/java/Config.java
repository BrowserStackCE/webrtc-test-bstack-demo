import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {

    static JSONParser jsonParser = new JSONParser();
    static JSONObject jsonObject = null;

    public static JSONObject getJsonObject() throws Exception{

        if(jsonObject != null){
            return jsonObject;
        }

        Object obj = jsonParser.parse(new FileReader("src/config/caps.json"));
        jsonObject = (JSONObject) obj;

        return jsonObject;
    }

    public static String getLocalFolderPath() throws Exception {

        JSONObject jsonObject = getJsonObject();
        String toReturn = (String) jsonObject.get("local_folder_path");
        return toReturn;
    }

    public static String getLocalBinaryPath() throws Exception {

        JSONObject jsonObject = getJsonObject();
        String toReturn = (String) jsonObject.get("local_binary_path");
        return toReturn;
    }

    public static String getCustomVideoFile() throws Exception {

        JSONObject jsonObject = getJsonObject();
        String toReturn = (String) jsonObject.get("custom_video_file");
        return toReturn;
    }

    public static String getCustomAudioFile() throws Exception {

        JSONObject jsonObject = getJsonObject();
        String toReturn = (String) jsonObject.get("custom_audio_file");
        return toReturn;
    }
    
}
