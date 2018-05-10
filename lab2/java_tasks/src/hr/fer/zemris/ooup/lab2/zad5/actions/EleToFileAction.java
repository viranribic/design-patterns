package hr.fer.zemris.ooup.lab2.zad5.actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * Action which prints to the standard output the sum of all elements in the collection.
 * @author Viran
 *
 */
public class EleToFileAction implements IAction {

	private BufferedWriter outputBuffer;

	/**
	 * EleToFileAction constructor.
	 * @param filePath 
	 * @throws IOException
	 */
	public EleToFileAction(String filePath) throws IOException {
		this.outputBuffer=new BufferedWriter(new FileWriter(filePath));
	}
	
	@Override
	public void performAction(Collection<Integer> collection) {
		try {
			outputBuffer.write(new Date().toString()+"\n");
			for(Integer i:collection)
				outputBuffer.write(i+"\n");
			outputBuffer.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
