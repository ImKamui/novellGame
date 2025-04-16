package novell;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainMenu{
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu window = new mainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
			private Image background = new ImageIcon("images/1.jpg").getImage();
			
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
		
		JButton exitButton = new JButton("Выход");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit exitFromGame = new Exit();
				exitFromGame.exitToDesktop();
			}
		});
		exitButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		exitButton.setBounds(690, 500, 160, 40);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setBackground(Color.RED);
		panel.add(exitButton);
		
		JButton settingsButton = new JButton("Настройки");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsButton.setEnabled(false);
				frame.dispose();
				EventQueue.invokeLater(() -> {
					settings setting = new settings();
					setting.setVisible(true);
				});
			}
		});
		settingsButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		settingsButton.setBounds(690, 450, 160, 40);
		settingsButton.setBorderPainted(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setBackground(Color.BLUE);
		panel.add(settingsButton);
		
		JButton playButton = new JButton("Играть");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButton.setEnabled(false);
				frame.dispose();
				EventQueue.invokeLater(() -> {
					firstScene scene = new firstScene();
					scene.setVisible(true);
				});
			}
		});
		playButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		playButton.setBounds(690, 400, 160, 40);
		playButton.setBorderPainted(false);
		playButton.setFocusPainted(false);
		playButton.setBackground(Color.WHITE);
		panel.add(playButton);
		
		JLabel author = new JLabel("Author by Danil Kholmychev");
		author.setBounds(1370, 830, 160, 40);
		panel.add(author);
		
	}
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}
	public void setAlwaysOnTop(boolean onTop)
	{
		frame.setAlwaysOnTop(onTop);
	}
}
