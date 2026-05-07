package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizFrame frame = new QuizFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuizFrame() {

		// 단어 불러오기
		WordLoader loader = new WordLoader();

		ArrayList<Word> words = loader.loadWords();

		quizManager = new QuizManager(words);

		// JFrame 설정
		setTitle("VocaQuiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(
				new Color(245, 247, 250)
			);

		setContentPane(contentPane);
		// 상단 패널
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(3, 1));
		topPanel.setBackground(new Color(245, 247, 250));

		contentPane.add(topPanel, BorderLayout.NORTH);

		// 제목
		JLabel lblTitle = new JLabel("VocaQuiz");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
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
			new Font("맑은 고딕", Font.PLAIN, 14)
		);

		topPanel.add(lblSubTitle);
		// 문제 라벨
		lblQuestion = new JLabel("문제");
		lblQuestion.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);


		// 문제 카드 느낌
		lblQuestion.setOpaque(true);

		lblQuestion.setBackground(Color.WHITE);

		lblQuestion.setBorder(
			new LineBorder(Color.LIGHT_GRAY, 2, true)
		);

		topPanel.add(lblQuestion);

		// 버튼 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

		contentPane.add(buttonPanel, BorderLayout.CENTER);

		// 보기 버튼들
		btnChoice1 = new JButton("choice1");
		btnChoice1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnChoice2 = new JButton("choice2");
		btnChoice2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnChoice3 = new JButton("choice3");
		btnChoice3.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnChoice4 = new JButton("choice4");
		btnChoice4.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnChoice5 = new JButton("choice5");
		btnChoice5.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

		buttonPanel.add(btnChoice1);
		buttonPanel.add(btnChoice2);
		buttonPanel.add(btnChoice3);
		buttonPanel.add(btnChoice4);
		buttonPanel.add(btnChoice5);

		// 아래 패널
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		lblResult = new JLabel("결과 : ");
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblScore = new JLabel("점수 : 0 / 0");
		lblScore.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		

		btnNext = new JButton("다음 문제");
		btnNext.setFont(
				new Font("맑은 고딕", Font.BOLD, 16)
			);

			btnNext.setFocusPainted(false);

			btnNext.setBackground(Color.WHITE);

			btnNext.setBorder(
				new LineBorder(
					Color.GRAY,
					2,
					true
				)
			);

		bottomPanel.add(lblResult);
		bottomPanel.add(lblScore);
		bottomPanel.add(btnNext);

		// 버튼 이벤트 연결
		btnChoice1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(btnChoice1.getText());
			}
		});

		btnChoice2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(btnChoice2.getText());
			}
		});

		btnChoice3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(btnChoice3.getText());
			}
		});

		btnChoice4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(btnChoice4.getText());
			}
		});

		btnChoice5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(btnChoice5.getText());
			}
		});

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

			lblResult.setText("결과 : 정답입니다!");
			lblResult.setForeground(Color.BLUE);

			score++;

		} else {

			lblResult.setText(
				"결과 : 오답입니다! 정답 : "
				+ quizManager.getCurrentWord().getMeaning()
			);

			lblResult.setForeground(Color.RED);
		}

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
private void showCorrectAnswer() {

	String correct =
			quizManager.getCurrentWord().getMeaning();

	if(btnChoice1.getText().equals(correct)) {
		btnChoice1.setBackground(Color.GREEN);
	}

	if(btnChoice2.getText().equals(correct)) {
		btnChoice2.setBackground(Color.GREEN);
	}

	if(btnChoice3.getText().equals(correct)) {
		btnChoice3.setBackground(Color.GREEN);
	}

	if(btnChoice4.getText().equals(correct)) {
		btnChoice4.setBackground(Color.GREEN);
	}

	if(btnChoice5.getText().equals(correct)) {
		btnChoice5.setBackground(Color.GREEN);
	}
}
private void resetButtonColors() {

	btnChoice1.setBackground(null);
	btnChoice2.setBackground(null);
	btnChoice3.setBackground(null);
	btnChoice4.setBackground(null);
	btnChoice5.setBackground(null);
}
}
