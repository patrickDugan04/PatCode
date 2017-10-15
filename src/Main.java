
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static String codeFileName;
	static String in = ""; // scanner in
	static String inO = ""; // the code that will be put into the array
	static int codePos = 0; // arrays Position
	static String commandType = "";
	static String pramThree = "";
	static String pramTwo = "";
	static String pramOne = "";
	static int numO;
	static int numT;
	static Scanner scan = new Scanner(System.in);
	static Boolean commandBlock = false; // used to block the if statements command if the if statement is false
	static Boolean commandTy = false;
	static Boolean pramTh = false;
	static Boolean pramT = false;
	static Boolean pramO = false;
	static HashMap var = new HashMap();
	static char[] code;

	public static void main(String[] args) {
		System.out.println("what file would you like to run");
		codeFileName = scan.nextLine();
		Scanner file = new Scanner(Main.class.getResourceAsStream("./PutCodeHere/" + codeFileName));// <-- Path to code
																									// file
		// TODO Auto-generated method stub
		while (!in.equals("*End*")) {
			in = file.nextLine();
			inO = inO + in;
			code = inO.toCharArray();
		}
		System.out.println("----------------------");
		sifter(code);
	}

	public static void sifter(char[] code) {
		while (codePos < code.length) {
			if (commandTy && !commandBlock) {
				commandPram(code);
			} else if (pramO && !commandBlock) {
				pramOn(code);
			} else if (pramT && !commandBlock) {
				pramTw(code);
			} else if (pramTh && !commandBlock) {
				pramThr(code);
			}
			if (code[codePos] == '{') {
				commandTy = true;
			} else if (code[codePos] == '[') {
				pramT = true;
			} else if (code[codePos] == '(') {
				pramO = true;
			} else if (code[codePos] == '<') {
				pramTh = true;
			} else if (code[codePos] == ';' || code[codePos] == '^') {
				commandEnd();
			} else if (code[codePos] == ':') {
				commandBlock = false;
			}

			codePos++;
		}
	}

	public static void commandPram(char[] code) {
		if (code[codePos] == '}') {
			commandTy = false;
		}
		if (commandTy) {
			commandType = commandType + code[codePos];
		}
	}

	public static void pramOn(char[] code) {
		if (code[codePos] == ')') {
			pramO = false;
		}
		if (pramO) {
			pramOne = pramOne + code[codePos];
		}
	}

	public static void pramTw(char[] code) {
		if (code[codePos] == ']') {
			pramT = false;
		}
		if (pramT) {
			pramTwo = pramTwo + code[codePos];
		}
	}

	public static void pramThr(char[] code) {
		if (code[codePos] == '>') {
			pramTh = false;
		}
		if (pramTh) {
			pramThree = pramThree + code[codePos];
		}
	}

	public static void commandEnd() {
		if (commandType.equals("Var")) {
			var.put(pramOne, pramTwo);
		} else if (commandType.equals("Displayln")) {
			if (pramOne.equals("var")) {
				System.out.println(var.get(pramTwo));
			} else if (pramOne.equals("txt")) {
				System.out.println(pramTwo);
			}
		} else if (commandType.equals("Display")) {
			if (pramOne.equals("var")) {
				System.out.print(var.get(pramTwo));
			} else if (pramOne.equals("txt")) {
				System.out.print(pramTwo);
			}
		} else if (commandType.equals("Add")) {
			if (var.containsKey(pramOne)) {
				numO = Integer.parseInt((String) var.get(pramOne));
			} else {
				numO = Integer.parseInt(pramOne);
			}
			if (var.containsKey(pramTwo)) {
				numT = Integer.parseInt((String) var.get(pramTwo));
			} else {
				numT = Integer.parseInt(pramTwo);
			}
			var.put(pramThree, Integer.toString(numO + numT));
		} else if (commandType.equals("Subtract")) {
			if (var.containsKey(pramOne)) {
				numO = Integer.parseInt((String) var.get(pramOne));
			} else {
				numO = Integer.parseInt(pramOne);
			}
			if (var.containsKey(pramTwo)) {
				numT = Integer.parseInt((String) var.get(pramTwo));
			} else {
				numT = Integer.parseInt(pramTwo);
			}
			var.put(pramThree, Integer.toString(numO - numT));
		} else if (commandType.equals("Multiply")) {
			if (var.containsKey(pramOne)) {
				numO = Integer.parseInt((String) var.get(pramOne));
			} else {
				numO = Integer.parseInt(pramOne);
			}
			if (var.containsKey(pramTwo)) {
				numT = Integer.parseInt((String) var.get(pramTwo));
			} else {
				numT = Integer.parseInt(pramTwo);
			}
			var.put(pramThree, Integer.toString(numO * numT));
		} else if (commandType.equals("Divide")) {
			if (var.containsKey(pramOne)) {
				numO = Integer.parseInt((String) var.get(pramOne));
			} else {
				numO = Integer.parseInt(pramOne);
			}
			if (var.containsKey(pramTwo)) {
				numT = Integer.parseInt((String) var.get(pramTwo));
			} else {
				numT = Integer.parseInt(pramTwo);
			}
			var.put(pramThree, Integer.toString(numO / numT));
		} else if (commandType.equals("If")) {
			if (!var.containsKey(pramOne) && !var.containsKey(pramTwo)) {
				if (!pramOne.equals(pramTwo)) {
					commandBlock = true;
				}
			} else if (var.containsKey(pramOne) && var.containsKey(pramTwo)) {
				if (!var.get(pramOne).equals(var.get(pramTwo))) {
					commandBlock = true;
				}
			} else if (!var.containsKey(pramOne) && var.containsKey(pramTwo)) {
				if (!pramOne.equals(var.get(pramTwo))) {
					commandBlock = true;
				}
			} else if (var.containsKey(pramOne) && !var.containsKey(pramTwo)) {
				if (!var.get(pramOne).equals(pramTwo)) {
					commandBlock = true;
				}
			}
		} else if (commandType.equals("Scan")) {
			var.put(pramThree, scan.nextLine());
		} else if (commandType.equals("ScanClose")) {
			scan.close();
		}

		commandType = "";
		pramThree = "";
		pramTwo = "";
		pramOne = "";
		commandTy = false;
		pramTh = false;
		pramT = false;
		pramO = false;
	}
}