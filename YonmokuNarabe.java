package yonmokuNarabe;

import java.util.Scanner;

public class YonmokuNarabe {
	public static void main(String[] args) {
		String[][] field = new String[6][7];
		initializeGame(field);
		mainGame(field);
	}

	private static void mainGame(String[][] field) {
		Scanner sc = new Scanner(System.in);
		String mark = "";
		int counter = 1;
		int column = 0;
		while (true) {
			if (counter % 2 == 1)
				mark = "●";
			else
				mark = "○";
			while (true) {
				printField(field);
				System.out.print("どの列に置きますか？（１～７）：");
				column = sc.nextInt();
				if (column >= 1 && column <= 7) {
					if (field[0][column - 1].equals("・")) {
						break;
					} else {
						System.out.println("その列には置けません");
					}
				} else {
					System.out.println("入力値が不正です！");
				}
			}
			putMark(field, column, mark);
			counter++;
			if (check4Marks(field, mark)) {
				printField(field);
				System.out.println(mark + "の勝ちです！");
				break;
			}
		}
	}

	private static boolean check4Marks(String[][] field, String mark) {
		if (checkRow(field,mark) || checkColmn(field,mark) ||
				checkRightDiagonal(field, mark)||checkLeftDiagonal(field,mark)) {
			return true;
		} else {
			return false;
		}
	}


	private static boolean checkRightDiagonal(String[][] field, String mark) {
		String current = "";
		for (int i = 3; i <= 5; i++) {
			for (int j = 0; j <= 3; j++) {
				int counter  = 0;
				for (int k = 0; i <= 4; k++) {
					if (field[i-k][j+k].equals(mark)) {
						counter++;
					}
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}
	private static boolean checkLeftDiagonal(String[][] field, String mark) {
		String current = "";
		for (int i = 3; i <= 5; i++) {
			for (int j =6; j >= 3; j--) {
				int counter  = 0;
				for (int k = 0; i <= 4; k++) {
					if (field[i-k][j-k].equals(mark)) {
						counter++;
					}
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}
	private static boolean checkColmn(String[][] field,String mark) {
		for (int i = 0; i < field[0].length; i++) {
			String current = "";
			int counter = 0;
			for (int j = 0; j < field.length; j++) {
				current = field[j][i];
				if (current.equals(mark)) {
					counter++;
					if (counter == 4) {
						return true;
					}
				} else {
					counter = 0;
				}

			}
		}
		return false;
	}

	private static boolean checkRow(String[][] field,String mark) {
		for (String[] row : field) {
			String current = "";
			int counter = 0;
			for (String s : row) {
				current = s;
				if (current.equals(mark)) {
					counter++;
					if (counter == 4) {
						return true;
					}
				} else {
					counter = 0;
				}

			}
		}
		return false;
	}

	private static void putMark(String[][] field, int column, String mark) {
		int pos = 5;
		while (pos >= 0) {
			if (field[pos][column - 1].equals("・")) {
				field[pos][column - 1] = mark;
				break;
			}
			pos--;
		}

	}

	private static void initializeGame(String[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = "・";
			}
		}
		System.out.println("ーーーーーー 対戦型四目ならべ ーーーーーー");
		System.out.println("縦・横・ななめのいずれかに自分の色を4つつなげてください！");
	}

	private static void printField(String[][] field) {
		String[] title = { "１", "２", "３", "４", "５", "６", "７" };
		for (String s : title) {
			System.out.print(s);
		}
		System.out.println();
		for (String[] row : field) {
			for (String s : row) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
}
