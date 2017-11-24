
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
	static HashMap<String, String> var = new HashMap();
	static HashMap<String, Integer> warps = new HashMap();
	static String ifOne;
	static String ifTwo;
	static char[] code;

	public static void main(String[] args) {
		System.out.println("what file would you like to run");
		codeFileName = scan.nextLine();
		Scanner file = new Scanner(Main.class.getResourceAsStream("./PutCodeHere/" + codeFileName));// <-- Path to code
																									// file
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
			} else if (code[codePos] == '\\') {
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
		if (code[codePos] == '/') {
			pramTh = false;
		}
		if (pramTh) {
			pramThree = pramThree + code[codePos];
		}
	}

	public static void commandEnd() {
		if (commandType.equals("Var")) {
			if (var.containsKey(pramTwo)) {
				var.put(pramOne, var.get(pramTwo));

			} else {
				var.put(pramOne, pramTwo);
			}
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
			if (!(var.containsKey(pramOne)) && !(var.containsKey(pramTwo))) {
				ifOne = pramOne;
				ifTwo = pramTwo;
			} else if (var.containsKey(pramOne) && var.containsKey(pramTwo)) {
				ifOne = (String) var.get(pramOne);
				ifTwo = (String) var.get(pramTwo);
			} else if (!(var.containsKey(pramOne)) && var.containsKey(pramTwo)) {
				ifOne = pramOne;
				ifTwo = (String) var.get(pramTwo);
			} else if ((var.containsKey(pramOne)) && !(var.containsKey(pramTwo))) {
				ifOne = (String) var.get(pramOne);
				ifTwo = pramTwo;
			}
			if (pramThree.equals("=")) {
				if (!(ifOne.equals(ifTwo))) {
					commandBlock = true;
				}
			} else if (pramThree.equals("!")) {
				if (ifOne.equals(ifTwo)) {
					commandBlock = true;
				}
			} else if (pramThree.equals(">")) {
				if (!(Integer.parseInt(ifOne) > Integer.parseInt(ifTwo))) {
					commandBlock = true;
				}
			} else if (pramThree.equals("<")) {
				if (!(Integer.parseInt(ifOne) < Integer.parseInt(ifTwo))) {
					commandBlock = true;
				}
			}
		} else if (commandType.equals("Scan")) {
			var.put(pramThree, scan.nextLine());
		} else if (commandType.equals("ScanClose")) {
			scan.close();
		} else if (commandType.equals("SetWarp")) {
			warps.put(pramOne, (codePos) - 1);
		} else if (commandType.equals("Warp")) {
			codePos = (int) warps.get(pramOne);
		} else if (commandType.equals("Rand")) {
			var.put(pramThree, Integer.toString((int) (Math.floor(Math.random() * 100))));
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