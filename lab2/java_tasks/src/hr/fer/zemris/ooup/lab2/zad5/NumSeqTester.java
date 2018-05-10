package hr.fer.zemris.ooup.lab2.zad5;

import hr.fer.zemris.ooup.lab2.zad5.actions.PrintAvgAction;
import hr.fer.zemris.ooup.lab2.zad5.actions.PrintMedianAction;
import hr.fer.zemris.ooup.lab2.zad5.actions.PrintSumAction;
import hr.fer.zemris.ooup.lab2.zad5.dataSrcs.KeyboardSource;

/**
 * Number sequencer test class.
 * @author Viran
 *
 */
public class NumSeqTester {

	public static void main(String[] args) {
		KeyboardSource keySrc=new KeyboardSource();
		NumberSequence numSeq=new NumberSequence(keySrc);
		
		PrintSumAction sumAction=new PrintSumAction(numSeq);
		PrintAvgAction avgAction=new PrintAvgAction(numSeq);
		PrintMedianAction medianAction=new PrintMedianAction(numSeq);
		
		numSeq.run();
		
		numSeq.unregister(avgAction);
	}
}
