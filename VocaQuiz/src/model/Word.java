package model;

public class Word {
	
	private String english;
	private String meaning;
	
	// 생성자
	public Word(String english, String meaning) {
		this.english = english;
		this.meaning = meaning;
	}
	
	// 영어 단어 반환
	public String getEnglish() {
		return english;
	}
	
	// 뜻 반환
	public String getMeaning() {
		return meaning;
	}
}