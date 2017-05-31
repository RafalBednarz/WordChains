package com.sabre.interview.wordchains;

import com.sabre.interview.wordchains.dictionary.Dictionary;
import com.sabre.interview.wordchains.dictionary.FileBasedDictionary;

public class Main {

	public static void main(String[] args) {
		
		Dictionary dictionary = new FileBasedDictionary();
		WordChains wordChains = new WordChains(dictionary);
		System.out.println(wordChains.getWordChain(args[0], args[1]));
		
	}

}