package hr.fer.zemris.ooup.lab2.zad6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Database decorator for keeping a log file.
 * @author Viran
 *
 */
public class LogDBDecorator implements MyDBase{

	private MyDBase mydbase;
	private BufferedWriter outputBuffer;
	private File outputFile;
	
	/**
	 * LogDBDecorator constructor.
	 * @param mydbase MyDatabase object.
	 * @param outputFile File in which the log should be written.
	 * @throws IOException Exception raised if the given outputFile couldn't be opened.
	 */
	public LogDBDecorator(MyDBase mydbase, String outputFile) throws IOException {
		this.mydbase=mydbase;
		this.outputFile=new File(outputFile);
		this.outputBuffer=new BufferedWriter(new FileWriter(this.outputFile));
		
	}
	
	@Override
	public int query(Param p) {
		int res=this.mydbase.query(p);
		try {
			outputBuffer.write(new Date().toString()+" "+res+"\n");
		} catch (IOException e) {
			System.out.println("An error occured while writing to file "+this.outputFile);
		}
		return res;
	}

	@Override
	public void close() {
		mydbase.close();
		try {
			outputBuffer.close();
		} catch (IOException ignorable) {
		}
	}
}
