package hr.fer.zemris.ooup.lab3.intro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class TestComponent extends JComponent {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	public TestComponent() {
		this.registerKeyboardAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        JFrame frame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
		        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER	, 0, false), JComponent.WHEN_FOCUSED);
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width=this.getWidth();
		int height=this.getHeight();
		g.drawLine(0, height/2, width, height/2);
		g.drawLine(width/2, 0, width/2 , height );
		g.setFont(new Font("Serif", Font.PLAIN, 20));
		g.setColor(Color.RED);
		g.drawString("First test row.", 0, 20);
		g.drawString("Second test row.", 0, 40);
		
	}

	
} 
