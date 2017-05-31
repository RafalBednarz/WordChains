package com.sabre.interview.wordchains;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.sabre.interview.wordchains.dictionary.Dictionary;
import com.sabre.interview.wordchains.exception.WordChainNotFoundException;
import com.sabre.interview.wordchains.exception.WordDoesNotExistInDictionaryException;
import com.sabre.interview.wordchains.exception.WordsAreNotOfTheSameLengthException;

public class WordChains {
	
	private Dictionary dictionary;
	public WordChains(Dictionary dictionary) {
		 this.dictionary = dictionary;
	}

	public List<String> getWordChain(String startWord, String endWord) {
		Set<String> wordsInDictionary = dictionary.getAllWordsOfGivenLengthFromDictionary(startWord.length());
		List<String> wordChain = new ArrayList<String>();
		if (startWord.length() != endWord.length()) {
			throw new WordsAreNotOfTheSameLengthException();
		}

		if (!wordsInDictionary.contains(startWord) || !wordsInDictionary.contains(endWord)) {
			throw new WordDoesNotExistInDictionaryException();
		}
		wordChain.add(startWord);
		if (startWord.equals(endWord)) {
			return Arrays.asList(startWord);
		} else {
			return getPathBasedOnBreadthFirstAlgorithm(startWord, endWord, wordsInDictionary);
		}
	}

	private List<String> getPathBasedOnBreadthFirstAlgorithm(String startWord, String endWord, Set<String> wordsInDictionary) {
		Map<String, String> traversedWords = getAllTraversedWords(startWord, endWord, wordsInDictionary);
		List<String> path = new ArrayList<>();
		String word = endWord;
		while (!word.equals(startWord)) {
			path.add(0, word);
			word = traversedWords.get(word);
		}
		path.add(0, startWord);
		return path;
	}

	private Map<String, String> getAllTraversedWords(String startWord, String endWord, Set<String> wordsInDictionary) {
		Queue<String> visitedWords = new LinkedList<>();
		Map<String, String> adjancedWordsMap = new HashMap<>();
		visitedWords.add(startWord);
		String currentWord;

		while(true) {
			if(visitedWords.isEmpty()) {
				throw new WordChainNotFoundException();
			} else {
				currentWord=visitedWords.remove();
				if(currentWord.equals(endWord)) {
					return adjancedWordsMap;
				} else {
					Set<String> adjancedWords = getWordsWhichDifferWith1Letter(currentWord, wordsInDictionary);
					for (String adjancedWord : adjancedWords) {
						if (!adjancedWordsMap.containsKey(adjancedWord)) {
							adjancedWordsMap.put(adjancedWord, currentWord);
							visitedWords.add(adjancedWord);
						}
					}
				}

			}
		}

	}

	private Set<String> getWordsWhichDifferWith1Letter(String baseWord, Set<String> wordsInDictionary) {

		Set<String> wordsWhichDifferOnlyWithOneCharacter = new HashSet<String>();
		for (String wordInDictionary : wordsInDictionary) {
			int numberOfdifferentLetters = 0;
			for (int i = 0; i < wordInDictionary.length(); ++i) {
				if (wordInDictionary.charAt(i) != baseWord.charAt(i))
					numberOfdifferentLetters++;
			}
			if (numberOfdifferentLetters == 1) {
				wordsWhichDifferOnlyWithOneCharacter.add(wordInDictionary);
			}
			
		}
		return wordsWhichDifferOnlyWithOneCharacter;
	}


}