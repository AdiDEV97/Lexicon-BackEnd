package com.Lexicon.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Lexicon.Modal.Lexicon;
import com.Lexicon.Service.WordService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/word-book/app")
public class WordController {
	
	
	@Autowired
	private WordService wordServ;

	
	@GetMapping("/dictionary")
	public ResponseEntity<List<Lexicon>> getFullDict() {
		
		List<Lexicon> fullDict = wordServ.getAllWords();
		
		if(fullDict.isEmpty()) {
			System.out.println("Database is Empty!! Please add atleast one record.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			//System.out.println("Dictionary Words - " + fullDict);
			return ResponseEntity.of(Optional.of(fullDict));
		}
	}
	
	
	@GetMapping("/dictionary/{id}")
	public ResponseEntity<Lexicon> dictWordById(@PathVariable("id") int id) {
		Lexicon wordById = wordServ.getWordById(id);
		if(wordById == null) {
			System.out.println("The word with id - " + id + " is not found. Please check.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			System.out.println("The word - " + "'"+wordById.getWord()+"'" + " is available in the Dictionary.");
			return ResponseEntity.of(Optional.of(wordById));
		}
	}
	
	
	@GetMapping("/dictionary-word/{wordName}")
	public ResponseEntity<Lexicon> wordByName(@PathVariable("wordName") String wordName) {
		
		Lexicon wordByWordName = wordServ.getWordByWordName(wordName);
		
		if(wordByWordName == null) {
			System.out.println("The word - " + wordName + " is not found. Please check.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			System.out.println("The word - " + wordName + " is available in the Dictionary.");
			return ResponseEntity.of(Optional.of(wordByWordName));
		}
	}
	
	
	@PostMapping("/new-word")
	public ResponseEntity<Lexicon> dictNewWord(@RequestBody Lexicon newWord) {
		try {
			Lexicon newWordData = wordServ.addNewWord(newWord);
			return ResponseEntity.of(Optional.of(newWordData));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Getting wrong data!! Please try again.");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
	
	@DeleteMapping("/delete-word/{id}")
	public ResponseEntity<Void> deleteDictWord(@PathVariable("id") int id) {
		try {
			wordServ.deleteWord(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error!! The word with id - " + id + " is not in the Dictionary.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@PutMapping("/update-word/{id}")
	public ResponseEntity<Lexicon> updateDictWord(@RequestBody Lexicon newWord, @PathVariable("id") int id) {
		try {
			wordServ.updateWord(newWord, id);
			return ResponseEntity.of(Optional.of(newWord));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
