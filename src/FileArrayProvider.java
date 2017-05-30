import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileArrayProvider {

    public static Set<String> readLines(String filename) {
        Set<String> lines;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			lines = new HashSet<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
			    lines.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
        return lines;
    }
}