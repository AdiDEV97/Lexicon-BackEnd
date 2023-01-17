package com.Lexicon.Modal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity(name="word_book")
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames= {"words"})})
public class Lexicon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	int id;
	
	@Column(name="words", unique=true)
	String word;
	
	@Column(name="explaination")
	String meaning;
	
	@Column(name="serial_no")
	int serialNo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public Lexicon(int id, String word, String meaning, int serialNo) {
		super();
		this.id = id;
		this.word = word;
		this.meaning = meaning;
		this.serialNo = serialNo;
	}

	public Lexicon() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Lexicon [id=" + id + ", word=" + word + ", meaning=" + meaning + ", serialNo=" + serialNo + "]";
	}
	
	
}
