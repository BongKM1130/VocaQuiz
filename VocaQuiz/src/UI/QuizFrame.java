package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Word;
import service.QuizManager;
import service.WordLoader;

public class QuizFrame extends JFrame {

	private JPanel contentPane;

	private JLabel lblQuestion;
	private JLabel lblResult;
	private JLabel lblScore;

	private JButton btnChoice1;
	private JButton btnChoice2;
	private JButton btnChoice3;
	private JButton btnChoice4;
	private JButton btnChoice5;

	private JButton btnNext;

	private QuizManager quizManager;

	private int score = 0;
	private int total = 0;

	public static void main(String[] args) {

		new QuizFrame().setVisible(true);
	}

	public QuizFrame() {

		// 단어 불러오기
		WordLoader loader = new WordLoader();

		ArrayList<Word> words = loader.loadWords();

		quizManager = new QuizManager(words);

		// JFrame 설정
		setTitle("VOCA QUIZ");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 600, 700);

		setLocationRelativeTo(null);

		setResizable(false);

		// contentPane
		contentPane = new JPanel();

		contentPane.setBorder(
				new EmptyBorder(20, 20, 20, 20)
		);

		contentPane.setBackground(
				new Color(245, 245, 245)
		);

		contentPane.setLayout(
				new BorderLayout(20, 20)
		);

		setContentPane(contentPane);

		// 상단 패널
		JPanel topPanel = new JPanel();

		topPanel.setLayout(
				new GridLayout(3, 1, 10, 10)
		);

		topPanel.setBackground(
				new Color(245, 245, 245)
		);

		contentPane.add(topPanel, BorderLayout.NORTH);

		// 제목
		JLabel lblTitle =
				new JLabel("VOCA QUIZ");

		lblTitle.setHorizontalAlignment(
				SwingConstants.CENTER
		);

		lblTitle.setFont(
				new Font("맑은 고딕", Font.BOLD, 32)
		);

		topPanel.add(lblTitle);

		// 부제목
		JLabel lblSubTitle =
				new JLabel("영단어 학습 프로그램");

		lblSubTitle.setHorizontalAlignment(
				SwingConstants.CENTER
		);

		lblSubTitle.setFont(
				new Font("맑은 고딕", Font.PLAIN, 15)
		);

		topPanel.add(lblSubTitle);

		// 문제 라벨
		lblQuestion = new JLabel("문제");

		lblQuestion.setHorizontalAlignment(
				SwingConstants.CENTER
		);

		lblQuestion.setFont(
				new Font("맑은 고딕", Font.BOLD, 24)
		);

		lblQuestion.setPreferredSize(
				new Dimension(400, 80)
		);

		lblQuestion.setOpaque(true);

		lblQuestion.setBackground(Color.WHITE);

		lblQuestion.setBorder(
				BorderFactory.createCompoundBorder(
						new LineBorder(
								Color.LIGHT_GRAY,
								2,
								true
						),
						new EmptyBorder(
								20,
								20,
								20,
								20
						)
				)
		);

		topPanel.add(lblQuestion);

		// 버튼 패널
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(
				new GridLayout(5, 1, 15, 15)
		);

		buttonPanel.setBorder(
				new EmptyBorder(10, 10, 10, 10)
		);

		buttonPanel.setBackground(
				new Color(245, 245, 245)
		);

		contentPane.add(buttonPanel, BorderLayout.CENTER);

		// 버튼 생성
		btnChoice1 = new JButton();
		btnChoice2 = new JButton();
		btnChoice3 = new JButton();
		btnChoice4 = new JButton();
		btnChoice5 = new JButton();

		JButton[] buttons = {
				btnChoice1,
				btnChoice2,
				btnChoice3,
				btnChoice4,
				btnChoice5
		};

		for(JButton btn : buttons) {

			btn.setFont(
					new Font("맑은 고딕", Font.BOLD, 22)
			);

			btn.setFocusPainted(false);

			btn.setBackground(
					new Color(240, 230, 120)
			);

			btn.setBorder(
					new LineBorder(
							Color.GRAY,
							2,
							true
					)
			);

			btn.setPreferredSize(
					new Dimension(400, 60)
			);

			buttonPanel.add(btn);
		}

		// 하단 패널
		JPanel bottomPanel = new JPanel();

		bottomPanel.setLayout(
				new GridLayout(3, 1, 10, 10)
		);

		bottomPanel.setBackground(
				new Color(245, 245, 245)
		);

		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		// 결과 라벨
		lblResult = new JLabel("결과");

		lblResult.setHorizontalAlignment(
				SwingConstants.CENTER
		);

		lblResult.setFont(
				new Font("맑은 고딕", Font.BOLD, 18)
		);

		bottomPanel.add(lblResult);

		// 점수 라벨
		lblScore = new JLabel("점수 : 0 / 0");

		lblScore.setHorizontalAlignment(
				SwingConstants.CENTER
		);

		lblScore.setFont(
				new Font("맑은 고딕", Font.BOLD, 18)
		);

		bottomPanel.add(lblScore);

		// 다음 문제 버튼
		btnNext = new JButton("다음 문제");

		btnNext.setFont(
				new Font("맑은 고딕", Font.BOLD, 20)
		);

		btnNext.setFocusPainted(false);

		btnNext.setBackground(
				new Color(170, 210, 230)
		);

		btnNext.setBorder(
				new LineBorder(
						Color.GRAY,
						2,
						true
				)
		);

		bottomPanel.add(btnNext);

		// 버튼 이벤트
		btnChoice1.addActionListener(e ->
				checkAnswer(btnChoice1.getText()));

		btnChoice2.addActionListener(e ->
				checkAnswer(btnChoice2.getText()));

		btnChoice3.addActionListener(e ->
				checkAnswer(btnChoice3.getText()));

		btnChoice4.addActionListener(e ->
				checkAnswer(btnChoice4.getText()));

		btnChoice5.addActionListener(e ->
				checkAnswer(btnChoice5.getText()));

		// 다음 문제 버튼
		btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				loadQuestion();
			}
		});

		// 첫 문제 출력
		loadQuestion();
	}

	// 문제 출력
	private void loadQuestion() {

		quizManager.makeQuestion();

		lblQuestion.setText(
				quizManager.getCurrentWord().getEnglish()
						+ " 뜻은?"
		);

		ArrayList<String> options =
				quizManager.getOptions();

		btnChoice1.setText(options.get(0));
		btnChoice2.setText(options.get(1));
		btnChoice3.setText(options.get(2));
		btnChoice4.setText(options.get(3));
		btnChoice5.setText(options.get(4));

		lblResult.setText("결과 : ");

		enableButtons();

		resetButtonColors();
	}

	// 정답 체크
	private void checkAnswer(String selected) {

		total++;

		if (quizManager.checkAnswer(selected)) {

			lblResult.setText("정답입니다!");

			lblResult.setForeground(
					new Color(0, 120, 0)
			);

			score++;

		} else {

			lblResult.setText(
					"오답입니다! 정답 : "
							+ quizManager.getCurrentWord().getMeaning()
			);

			lblResult.setForeground(Color.RED);
		}

		Toolkit.getDefaultToolkit().beep();

		lblScore.setText(
				"점수 : " + score + " / " + total
		);

		showCorrectAnswer();

		disableButtons();
	}

	// 버튼 비활성화
	private void disableButtons() {

		btnChoice1.setEnabled(false);
		btnChoice2.setEnabled(false);
		btnChoice3.setEnabled(false);
		btnChoice4.setEnabled(false);
		btnChoice5.setEnabled(false);
	}

	// 버튼 활성화
	private void enableButtons() {

		btnChoice1.setEnabled(true);
		btnChoice2.setEnabled(true);
		btnChoice3.setEnabled(true);
		btnChoice4.setEnabled(true);
		btnChoice5.setEnabled(true);
	}

	// 정답 표시
	private void showCorrectAnswer() {

		String correct =
				quizManager.getCurrentWord().getMeaning();

		if(btnChoice1.getText().equals(correct)) {
			btnChoice1.setBackground(
					new Color(170, 255, 170)
			);
		}

		if(btnChoice2.getText().equals(correct)) {
			btnChoice2.setBackground(
					new Color(170, 255, 170)
			);
		}

		if(btnChoice3.getText().equals(correct)) {
			btnChoice3.setBackground(
					new Color(170, 255, 170)
			);
		}

		if(btnChoice4.getText().equals(correct)) {
			btnChoice4.setBackground(
					new Color(170, 255, 170)
			);
		}

		if(btnChoice5.getText().equals(correct)) {
			btnChoice5.setBackground(
					new Color(170, 255, 170)
			);
		}
	}

	// 버튼 색 초기화
	private void resetButtonColors() {

		Color defaultColor =
				new Color(240, 230, 120);

		btnChoice1.setBackground(defaultColor);
		btnChoice2.setBackground(defaultColor);
		btnChoice3.setBackground(defaultColor);
		btnChoice4.setBackground(defaultColor);
		btnChoice5.setBackground(defaultColor);
	}
}
