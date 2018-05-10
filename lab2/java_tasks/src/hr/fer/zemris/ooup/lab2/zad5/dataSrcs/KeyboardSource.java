package hr.fer.zemris.ooup.lab2.zad5.dataSrcs;

import java.util.Scanner;

/**
 * Class for generating integer numbers from standard input.
 * @author Viran
 *
 */
public class KeyboardSource implements INumberSource {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public int generateNext() {
		
		while (true) {
			try {
				System.out.print("Next:");
				int i = Integer.parseInt(scanner.nextLine());
				if(i<0){
					scanner.close();
					return -1;
				}
				return i;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer number.");
				continue;
			}
		}
	}

	@Override
	public void closeSource() {
		
	}

}
