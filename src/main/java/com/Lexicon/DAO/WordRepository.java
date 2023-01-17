package com.Lexicon.DAO;

//import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Lexicon.Modal.Lexicon;

public interface WordRepository extends CrudRepository<Lexicon, Integer> {

	public Lexicon findById(int id);
	
//	@Query("SELECT words FROM word_book words WHERE words = ?1")
//	//@Query("SELECT u FROM word_book u WHERE u.words = ?1")
//	public String findUserByStatus(String word);
	
	@Query("select wb.word from word_book wb where wb.word = :#{#req. word}")
	Optional<Lexicon> findWordData(@Param("req") Lexicon word);
	
	public Lexicon findByWord(String word);
	
}
