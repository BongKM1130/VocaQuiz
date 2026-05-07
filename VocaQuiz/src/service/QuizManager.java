package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Word;

public class QuizManager {

	private ArrayList<Word> words;
	private ArrayList<Word> usedWords;

	private Word currentWord;

	private ArrayList<String> options;

	private Random random;

	// 생성자
	public QuizManager(ArrayList<Word> words) {

		this.words = words;

		this.usedWords = new ArrayList<>();

		random = new Random();
	}

	// 문제 생성
	public void makeQuestion() {

		// 모든 문제 다 사용했으면 초기화
		if (usedWords.size() == words.size()) {

			usedWords.clear();
		}

		options = new ArrayList<>();

		// 중복 없는 문제 선택
		do {

			currentWord =
					words.get(
						random.nextInt(words.size())
					);

		} while (usedWords.contains(currentWord));

		// 사용한 문제 저장
		usedWords.add(currentWord);

		// 정답 추가
		options.add(currentWord.getMeaning());

		// 오답 4개 추가
		while (options.size() < 5) {

			Word randomWord =
					words.get(
						random.nextInt(words.size())
					);

			String wrongAnswer =
					randomWord.getMeaning();

			// 중복 방지
			if (!wrongAnswer.equals(currentWord.getMeaning())
					&& !options.contains(wrongAnswer)) {

				options.add(wrongAnswer);
			}
		}

		// 보기 섞기
		Collections.shuffle(options);
	}

	// 현재 문제 반환
	public Word getCurrentWord() {

		return currentWord;
	}

	// 보기 반환
	public ArrayList<String> getOptions() {

		return options;
	}

	// 정답 체크
	public boolean checkAnswer(String answer) {

		return answer.equals(
				currentWord.getMeaning()
		);
	}
}