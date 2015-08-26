import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import View.ChessView;



public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new ChessView();
				frame.setTitle("Chess");
				Image icona = new ImageIcon("img/Icon.png").getImage();
				frame.setIconImage(icona);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(700, 700);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
						}
		});
	}
}

