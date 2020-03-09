package group.thelegendofpeter;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Stellt das Menu dar
 * @author Maximilian Gilsoul
 *
 */
public class Menu {

	private JFrame frame;

	/**
	 * Startet auch alles
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Startet alles
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialiesiert den Inhalt des Fensters
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().start();
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnStart, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("");
		try {
			label.setIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("Logo.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().add(label, BorderLayout.CENTER);
	}

}
