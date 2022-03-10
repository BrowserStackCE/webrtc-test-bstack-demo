import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {

    static JSONParser jsonParser = new JSONParser();

    public static String getLocalFolderPath() throws Exception {

        Object obj = jsonParser.parse(new FileReader("src/config/caps.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String toReturn = (String) jsonObject.get("local_folder_path");
        return toReturn;
    }

    public static String getLocalBinaryPath() throws Exception {

        Object obj = jsonParser.parse(new FileReader("src/config/caps.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String toReturn = (String) jsonObject.get("local_binary_path");
        return toReturn;
    }

    public static String getCustomVideoFile() throws Exception {

        Object obj = jsonParser.parse(new FileReader("src/config/caps.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String toReturn = (String) jsonObject.get("custom_video_file");
        return toReturn;
    }

    public static String getCustomAudioFile() throws Exception {

        Object obj = jsonParser.parse(new FileReader("src/config/caps.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String toReturn = (String) jsonObject.get("custom_audio_file");
        return toReturn;
    }
    
}
