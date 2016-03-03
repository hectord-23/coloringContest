package view;

import java.awt.Toolkit;

import javax.swing.JFrame;
	
public class ContestGUI extends JFrame {
	
	public ContestGUI() {
		super("TCSS 360 - Fist Full of Java");
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((int) (kit.getScreenSize().getWidth() / 2 - getWidth() / 2),
				(int) (kit.getScreenSize().getHeight() / 2 - getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setVisible(true);
	}

	public static void main(String... theArgs) {
		ContestGUI gui = new ContestGUI();
	}
}
