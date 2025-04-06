package novell;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class firstScene extends javax.swing.JFrame{

	private JFrame frame;
	private Map<Integer, DialogueScene> scenes;
	private DialogueScene currentScene;
	private JPanel choisesPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					firstScene window = new firstScene();
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
	public firstScene() {
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
			private Image background = new ImageIcon("images/firstScene.jpg").getImage();
			
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
		
		JLabel firstText = new JLabel("");
		firstText.setFont(new Font("Consolas", Font.PLAIN, 20));
		firstText.setBounds(200, 720, 1500, 40);
		firstText.setForeground(Color.WHITE);
		panel.add(firstText);
		JLabel charName = new JLabel("123");
		charName.setFont(new Font("Consolas", Font.PLAIN, 20));
		charName.setBounds(200, 680, 1000, 40);
		charName.setForeground(Color.WHITE);
		panel.add(charName);
		JLabel blackBack = new JLabel(new ImageIcon("images/textbox1.png"));
		blackBack.setFont(new Font("Consolas", Font.PLAIN, 20));
		blackBack.setBounds(0, 620, 1550, 300);
		panel.add(blackBack);
		choisesPanel = new JPanel();
		panel.add(choisesPanel, BorderLayout.SOUTH);
		scenes = DialogueLoader.loadDialogues("scenes.json");
	}
}
