package hr.fer.zemris.ooup.lab2.zad5.dataSrcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for generating integer numbers from a file.
 * @author Viran
 *
 */
public class FileSource implements INumberSource {

	private BufferedReader inputBuffer;

	/**
	 * FileSource constructor.
	 * @param filePath Path to the file.
	 * @throws FileNotFoundException Exception raised if the file couldn't be opened.
	 */
	public FileSource(String filePath) throws FileNotFoundException {
		inputBuffer = new BufferedReader(new FileReader(filePath));
	}

	@Override
	public int generateNext() {
		try {
			int i = Integer.parseInt(inputBuffer.readLine());
			if(i<0){
				inputBuffer.close();
				return -1;
			}
			return i;
		} catch (NumberFormatException | IOException ignorable) {
			System.out.println("The next element wasn't properly proccessed.");
			try {inputBuffer.close();} catch (IOException ignorable1) {}
			return -1;
		}
		
	}

	@Override
	public void closeSource() {
		try {
			inputBuffer.close();
		} catch (IOException ignorable) {
		}
	}

	

}
