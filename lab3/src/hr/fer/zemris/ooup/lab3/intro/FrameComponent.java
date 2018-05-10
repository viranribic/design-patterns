package hr.fer.zemris.ooup.lab3.intro;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameComponent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	public FrameComponent() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		add(new TestComponent());
		
	}
	
	public static void main(String[] args) {
		FrameComponent frame=new FrameComponent();
		frame.setVisible(true);		
	}
}
