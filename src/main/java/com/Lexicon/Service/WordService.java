package com.Lexicon.Service;

import java.util.List;

import com.Lexicon.Modal.Lexicon;

public interface WordService {
	
	public List<Lexicon> getAllWords();
	
	public Lexicon getWordById(int id);
	
	public Lexicon getWordByWordName(String word);
	
	public Lexicon addNewWord(Lexicon newWord);
	
	public void deleteWord(int id);
	
	public void updateWord(Lexicon newWord, int id);
}
