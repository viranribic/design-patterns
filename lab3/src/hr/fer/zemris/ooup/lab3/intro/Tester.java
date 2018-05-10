package hr.fer.zemris.ooup.lab3.intro;

public class Tester {

	public static void main(String[] args) {
		String string="\na\nb\nc\n";
		for(String s:string.split("\n"))
			System.out.println(s.length());
		System.out.println(string.endsWith("\n"));
	}
}
