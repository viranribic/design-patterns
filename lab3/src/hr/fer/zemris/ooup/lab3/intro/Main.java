package hr.fer.zemris.ooup.lab3.intro;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.add(new TestComponent());
		frame.setVisible(true);
	}
}
