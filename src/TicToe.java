import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicToe {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		printGameBoard(gameBoard);

		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("In quale casella inserire? (1-9)");
			int pos = scan.nextInt();
			while (playerPositions.contains(pos) || cpuPositions.contains(pos)) {
				System.out.println("Posizione occupata!");
				pos = scan.nextInt();
			}

			placePiece(gameBoard, pos, "giocatore");

			String result = checkWinner();
			
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			

			Random random = new Random();
			int cpuPos = random.nextInt(9) + 1;
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = random.nextInt(9) + 1;
			}

			placePiece(gameBoard, cpuPos, "cpu");

			printGameBoard(gameBoard);

			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}

		}

	}

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}

	}

	public static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';

		if (user.equalsIgnoreCase("giocatore")) {
			symbol = 'O';
			playerPositions.add(pos);
		} else if (user.equalsIgnoreCase("cpu")) {
			symbol = 'X';
			cpuPositions.add(pos);
		}

		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;

		}

	}

	public static String checkWinner() {

		List topRow = Arrays.asList(1, 2, 3);
		List middleRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List middleCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List crossOne = Arrays.asList(1, 5, 9);
		List crossTwo = Arrays.asList(3, 5, 7);

		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(middleRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftCol);
		winningConditions.add(middleCol);
		winningConditions.add(rightCol);
		winningConditions.add(crossOne);
		winningConditions.add(crossTwo);

		for (List ls : winningConditions) {
			if (playerPositions.containsAll(ls)) {
				return "Complimenti hai vinto!";
			} else if (cpuPositions.containsAll(ls)) {
				return "Oh no, hai perso.";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Pareggio.";
			}
		}
		return "";

	}

}
