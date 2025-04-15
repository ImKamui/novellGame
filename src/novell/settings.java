package novell;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class settings{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					settings frame = new settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public settings() {
		initialize();

	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		Rectangle bounds = env.getMaximumWindowBounds();
		frame.setSize(725, 485);
		device.setFullScreenWindow(frame);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel()
				{
			private Image background = new ImageIcon("images/mainMenu.jpg").getImage();
			
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			}
				};
		panel.setLayout(null);
		panel.setBounds(0, 0, 725, 485);
		frame.setContentPane(panel);
		
		JButton exitButton = new JButton("Выйти в главное меню");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButton.setEnabled(false);
				frame.dispose();
				EventQueue.invokeLater(() -> {
					mainMenu menu = new mainMenu();
					menu.setVisible(true);
					//menu.setAlwaysOnTop(true);
				});
			}
		});
		exitButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		exitButton.setBounds(580, 450, 350, 40);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setBackground(Color.WHITE);
		panel.add(exitButton);
		
	}
	
	public void setAlwaysOnTop(boolean onTop)
	{
		frame.setAlwaysOnTop(onTop);
	}
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

}
