import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
    public static void main(String[] args) throws Exception {
        
        Gson gson = new Gson();

        try (Reader reader = new FileReader("team.json")) {

            Type playerListType = new TypeToken<List<Player>>(){}.getType(); //Generic Type <-> List of player objects
            List<Player> team = gson.fromJson(reader, playerListType); // JSON to JAVA

            for (Player p : team) {
                System.out.println(p + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
