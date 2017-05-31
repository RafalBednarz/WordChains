package com.sabre.interview.wordchains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sabre.interview.wordchains.dictionary.Dictionary;
import com.sabre.interview.wordchains.WordChains;
import com.sabre.interview.wordchains.dictionary.FileBasedDictionary;
import com.sabre.interview.wordchains.exception.WordChainNotFoundException;
import com.sabre.interview.wordchains.exception.WordDoesNotExistInDictionaryException;
import com.sabre.interview.wordchains.exception.WordsAreNotOfTheSameLengthException;


public class ValidateWordChain {

	@Test
	public void testIfReturnsList() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		assertTrue(wordChains.getWordChain("cat", "dog") instanceof List);
	}

	@Test(expected = WordsAreNotOfTheSameLengthException.class) 
	public void testForWordsWithDifferentLength() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		wordChains.getWordChain("cat", "servings");
	}
	
	@Test
	public void testForTheSameWord() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		assertEquals(Arrays.asList("cat"), wordChains.getWordChain("cat", "cat"));
	}
	
	@Test(expected = WordDoesNotExistInDictionaryException.class) 
	public void testForStartWordWhichDoesNotExistInDictionary() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		wordChains.getWordChain("blablabla", "servilely");
	}
	
	@Test(expected = WordDoesNotExistInDictionaryException.class) 
	public void testForEndWordWhichDoesNotExistInDictionary() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		wordChains.getWordChain("servilely", "blablabla");
	}
	
	@Test
	public void testForWordsWhichDiffersWithOneLetterOnly() {
		Dictionary dictionary=new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		assertEquals(Arrays.asList("AB", "AC"), wordChains.getWordChain("AB", "AC"));
	}
	//assertEquals(java.lang.Object expected, java.lang.Object actual) 
	
	@Test
	public void testForChainOfWordsInMockDictionary() {
		
		Dictionary mockDictionary = new Dictionary() {
			@Override
			public Set<String> getAllWordsOfGivenLengthFromDictionary(int lenghtOfWord) {
				Set<String> mockedWords = new HashSet<String>();
				mockedWords.add("cat");
				mockedWords.add("cot");
				mockedWords.add("dot");
				mockedWords.add("dog");
				return mockedWords;
			}
		};
		
		WordChains wordChains = new WordChains(mockDictionary);
		List<String> chainOfWords = wordChains.getWordChain("cat", "dog");
		
		assertEquals(Arrays.asList("cat", "cot", "dot","dog"), chainOfWords);
	}
	
	@Test(expected = WordChainNotFoundException.class)
	public void testForWordChainThatDoesNotExist() {
		
		Dictionary mockDictionary = new Dictionary() {
			@Override
			public Set<String> getAllWordsOfGivenLengthFromDictionary(int lenghtOfWord) {
				Set<String> mockedWords = new HashSet<String>();
				mockedWords.add("cat");
				mockedWords.add("cot");
				mockedWords.add("dog");
				return mockedWords;
			}
		};
		
		WordChains wordChains = new WordChains(mockDictionary);
		List<String> chainOfWords = wordChains.getWordChain("cat", "dog");
		
		assertEquals(Arrays.asList("cat", "cot", "dot","dog"), chainOfWords);
	}

}
