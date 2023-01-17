package com.Lexicon.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lexicon.DAO.WordRepository;
import com.Lexicon.Modal.Lexicon;
import com.Lexicon.Service.WordService;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordRepository wordRepo;
	

	@Override
	public List<Lexicon> getAllWords() {
		List<Lexicon> allWords = (List<Lexicon>) wordRepo.findAll();
		return allWords;
	}
	

	@Override
	public Lexicon getWordById(int id) {

		Lexicon word = wordRepo.findById(id);
		return word;
	}
	
	
	@Override
	public Lexicon getWordByWordName(String word) {
		Lexicon findWord = (Lexicon)wordRepo.findByWord(word);
		return findWord;
	}
	

	@Override
	public Lexicon addNewWord(Lexicon newWord) {
		
		List<Lexicon> allWords = (List<Lexicon>)wordRepo.findAll();
		
		boolean flag = false;
		
		long t1 = System.nanoTime();
		for(int i=0; i<allWords.size(); i++) {
			Lexicon ce = allWords.get(i);
			if(ce.getWord().toLowerCase().equals(newWord.getWord().toLowerCase()) == true) {
				flag = true;
				break;
			}
		}

		long t2 = System.nanoTime();
		System.out.println("flag - " + flag);
		
		if(flag == false) {
			System.out.println("The word - " + "'"+ newWord.getWord() +"'" + " is added to the Dictionary.");
			wordRepo.save(newWord);
		}
		else {
			System.out.println("The word - " + "'"+ newWord.getWord() +"'" + " is already in the the Dictionary.");
		}
		
		System.out.println("t1 - " + t1 + " |||| t2 - " + t2);
		long time = t2-t1;
		
		System.out.println("Time Taken - " + time);
		
		return newWord;
		
	}
	

	@Override
	public void deleteWord(int id) {
		wordRepo.deleteById(id);
		System.out.println("The word with id - " + id + " is deleted from the Dictionary.");
	}
	

	@Override
	public void updateWord(Lexicon newWord, int id) {
		
		List<Lexicon> allWords = (List<Lexicon>) wordRepo.findAll();
		System.out.println("Inside updateWord Impl method.");
		
		allWords = allWords.stream().map((ce) -> {
			System.out.println("Inside map.");
			if(ce.getId() == id) {
				ce.setWord(newWord.getWord());
				ce.setMeaning(newWord.getMeaning());
				ce.setSerialNo(newWord.getSerialNo());
				wordRepo.save(ce);
				System.out.println("=================The word " + newWord.getWord() + " with id - " + id + " updated successfully in the Dictionary.========================");
			}
			else {
				System.out.println("The word " + newWord.getWord() + " with id - " + id + " is not found in the Dictionary. Please try with different one.");
			}
			
			return ce;
			
		}).collect(Collectors.toList());
		
	}
	
	
	
	

}
