import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DictionaryProvider {

    public static Set<String> getAllWordsOfGivenLengthFromDictionary(String filename, int lenghtOfWord) {
        Set<String> lines;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			lines = new HashSet<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.length()==lenghtOfWord) {
					lines.add(line);
					//System.out.println("word was added:" + line);
				}
			    
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