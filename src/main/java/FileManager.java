import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileManager {


    public static String[] fileToArray(String fileName){
        ArrayList<String> lines = new ArrayList();
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(FileManager.class.getClassLoader().getResourceAsStream(fileName)))) {
            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return lines.toArray(new String[0]);
    }




}
