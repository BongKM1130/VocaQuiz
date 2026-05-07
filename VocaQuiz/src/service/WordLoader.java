package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import model.Word;

public class WordLoader {

	public ArrayList<Word> loadWords() {

		ArrayList<Word> words = new ArrayList<>();

		try {
			// 파일 읽기
			BufferedReader br = new BufferedReader(
					new FileReader("data/words.txt"));

			String line;

			// 한 줄씩 읽기
			while ((line = br.readLine()) != null) {

				// | 기준으로 분리
				String[] parts = line.split("\\|");

				// Word 객체 생성
				Word word = new Word(parts[0], parts[1]);

				// 리스트에 추가
				words.add(word);
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return words;
	}
}