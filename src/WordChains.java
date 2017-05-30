import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WordChains {
	
	public static List<String> getWordChain(String startWord, String endWord) {
		
		Set<String> dictionary = getAllWordsFromDictionary("wordlist.txt");
		
		if(startWord.length()!=endWord.length()) {
			throw new WordsAreNotOfTheSameLengthException();
		}
		
		if(!dictionary.contains(startWord) || !dictionary.contains(endWord) ) {
			throw new WordDoesNotExistInDictionaryException();
		}
		
		if(startWord.equals(endWord)) {
			return Arrays.asList(startWord);
		} else {
			
			return new ArrayList<String>();
		}
	}
	
	private static Set<String> getAllWordsFromDictionary(String filename) {
		return FileArrayProvider.readLines(filename);
	}

}