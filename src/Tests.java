import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Tests {

	@Test
	public void testIfReturnsList() {
		assertTrue(WordChains.getWordChain("cat", "dog") instanceof List);
	}
		
	@Test(expected = WordsAreNotOfTheSameLengthException.class) 
	public void testForWordsWithDifferentLength() {
		WordChains.getWordChain("cat", "servings");
	}
	
	@Test
	public void testForTheSameWord() {
		assertEquals(WordChains.getWordChain("cat", "cat"), Arrays.asList("cat"));
	}
	
	@Test(expected = WordDoesNotExistInDictionaryException.class) 
	public void testForStartWordWhichDoesNotExistInDictionary() {
		WordChains.getWordChain("blablabla", "servilely");
	}
	
	@Test(expected = WordDoesNotExistInDictionaryException.class) 
	public void testForEndWordWhichDoesNotExistInDictionary() {
		WordChains.getWordChain("servilely", "blablabla");
	}
	
	@Test
	public void testForWordsWhichDiffersWithOneLetterOnly() {
		assertEquals(WordChains.getWordChain("AB", "AC"), Arrays.asList("AB", "AC"));
	}

}
