package novell;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.tools.DiagnosticListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class firstScene{

	private JFrame frame;
	private JPanel choicesPanel;
	private List<DialogueScene> scenes;
	private int currentSceneIndex = 1;
	private JLabel charName;
	private JLabel firstText;	
	private JPanel menuPanel;
	private JButton exitToMainMenuBtn;
	private BackgroundPanel mainPanel;

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
	 * Create the application by Danil Kholmychev.
	 */
	public firstScene() {
		initialize();
		initScenes();
		showScene(1);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setUndecorated(true);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    JLabel author = new JLabel("Author by Danil Kholmychev");
		author.setBounds(0, 820, 160, 40);
		
	    
	    mainPanel = new BackgroundPanel();
	    mainPanel.setLayout(new BorderLayout());
	    mainPanel.setBackgroundImage(new ImageIcon("images/1.jpg").getImage());
	    mainPanel.setFocusable(true);
	    mainPanel.requestFocusInWindow();
	    mainPanel.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		if (e.getKeyCode() == e.VK_SPACE)
	    		{
	    			if (currentSceneIndex >= 0 && currentSceneIndex < scenes.size()) {
	                    DialogueScene scene = scenes.get(currentSceneIndex);
	                    if (scene.getChoices() == null || scene.getChoices().isEmpty()) {
	                        nextScene();
	                    }
	                }
	    		}
	    		if (e.getKeyCode() == e.VK_ESCAPE)
	    		{
	    			if (menuPanel.isVisible() == true)
	    			{
	    				menuPanel.setVisible(false);
	    				choicesPanel.setVisible(true);
	    			}
	    			else
	    			{
	    				menuPanel.setVisible(true);
	    				choicesPanel.setVisible(false);
	    			}
	    		}
	    	}
	    });
	    mainPanel.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (currentSceneIndex >= 0 && currentSceneIndex < scenes.size()) {
                    DialogueScene scene = scenes.get(currentSceneIndex);
                    if (scene.getChoices() == null || scene.getChoices().isEmpty()) {
                        nextScene();
                    }
                }
	    	}
	    });
	    frame.setContentPane(mainPanel);
	    mainPanel.add(author);
	    JPanel textPanel = new JPanel(new BorderLayout())
	    		{
	    	private Image background = new ImageIcon("images/textbox1.jpg").getImage();
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	        }
	    		};
	    textPanel.setOpaque(false);
	    textPanel.setBackground(new Color(0, 0, 0, 180));
	    textPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
	    
	    charName = new JLabel("", SwingConstants.LEFT);
	    charName.setFont(new Font("Consolas", Font.BOLD, 24));
	    charName.setForeground(Color.WHITE);
	    
	    firstText = new JLabel("", SwingConstants.LEFT);
	    firstText.setFont(new Font("Consolas", Font.PLAIN, 20));
	    firstText.setForeground(Color.WHITE);
	    
	    textPanel.add(charName, BorderLayout.NORTH);
	    textPanel.add(firstText, BorderLayout.CENTER);
	    
	    choicesPanel = new JPanel();
	    choicesPanel.setOpaque(false);
	    choicesPanel.setLayout(new BoxLayout(choicesPanel, BoxLayout.Y_AXIS));
	    
	    JPanel centerPanel = new JPanel(new GridBagLayout());
	    centerPanel.setOpaque(false);
	    centerPanel.add(choicesPanel);
	    
	    JButton continueGameBtn = new JButton("Продолжить игру");
	    continueGameBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		menuPanel.setVisible(false);
	    		choicesPanel.setVisible(true);
	    	}
	    });
	    JButton settingsBtn = new JButton ("Настройки");
	    exitToMainMenuBtn = new JButton("Выйти в главное меню");
	    exitToMainMenuBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		exitToMainMenuBtn.setEnabled(false);
				frame.dispose();
				EventQueue.invokeLater(() -> {
					mainMenu mainMenu = new mainMenu();
					mainMenu.setVisible(true);
					//setting.setAlwaysOnTop(true);
				});
	    	}
	    });
	    
	    continueGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    settingsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    exitToMainMenuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    continueGameBtn.setFont(new Font("Arial", Font.PLAIN, 18));
	    continueGameBtn.setForeground(Color.WHITE);
	    continueGameBtn.setBackground(new Color(70, 70, 120, 200));
	    continueGameBtn.setOpaque(true);
	    continueGameBtn.setBorderPainted(false);
	    continueGameBtn.setFocusPainted(false);
	    continueGameBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
	    continueGameBtn.setMaximumSize(new Dimension(400, 50));
	    continueGameBtn.setPreferredSize(new Dimension(350, 45));
	    settingsBtn.setFont(new Font("Arial", Font.PLAIN, 18));
	    settingsBtn.setForeground(Color.WHITE);
	    settingsBtn.setBackground(new Color(70, 70, 120, 200));
	    settingsBtn.setOpaque(true);
	    settingsBtn.setBorderPainted(false);
	    settingsBtn.setFocusPainted(false);
	    settingsBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
	    settingsBtn.setMaximumSize(new Dimension(400, 50));
	    settingsBtn.setPreferredSize(new Dimension(350, 45));
	    exitToMainMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
	    exitToMainMenuBtn.setForeground(Color.WHITE);
	    exitToMainMenuBtn.setBackground(new Color(70, 70, 120, 200));
	    exitToMainMenuBtn.setOpaque(true);
	    exitToMainMenuBtn.setBorderPainted(false);
	    exitToMainMenuBtn.setFocusPainted(false);
	    exitToMainMenuBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
	    exitToMainMenuBtn.setMaximumSize(new Dimension(400, 50));
	    exitToMainMenuBtn.setPreferredSize(new Dimension(350, 45));
	    
	    menuPanel = new JPanel();
	    menuPanel.setOpaque(false);
	    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
	    menuPanel.add(Box.createVerticalGlue());
	    menuPanel.setVisible(false);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	    buttonPanel.setOpaque(true);
	    buttonPanel.setBackground(Color.BLUE);
	    buttonPanel.add(continueGameBtn);
	    buttonPanel.add(settingsBtn);
	    buttonPanel.add(exitToMainMenuBtn);
	    
	    menuPanel.add(buttonPanel);
	    menuPanel.add(Box.createVerticalGlue());
	    
	    centerPanel.add(menuPanel);
	    mainPanel.add(centerPanel, BorderLayout.CENTER);
	    mainPanel.add(textPanel, BorderLayout.SOUTH);
	    
	}
	
	private void changeBackgroundImage(String imagePath) {
        Image newBackground = new ImageIcon(imagePath).getImage();
        ((BackgroundPanel) mainPanel).setBackgroundImage(newBackground);
        mainPanel.repaint();
    }
	
	private static class BackgroundPanel extends JPanel {
        private Image background;
        
        public BackgroundPanel() {
            super(new BorderLayout());
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        }
        
        public void setBackgroundImage(Image newBackground) {
            this.background = newBackground;
        }
    }
	
	public void initScenes()
	{
		
		scenes = new ArrayList<>();
		DialogueScene scene1 = new DialogueScene();
		scene1.setId(1);
		scene1.setSpeaker(null);
		scene1.setText("Привет! Давайте узнаем, можете ли вы распознать мошенничество?");
		scene1.addChoice("Начать", 2);
		scene1.setSceneImage("images/1.jpg");
		scenes.add(scene1);
		
		DialogueScene scene2 = new DialogueScene();
		scene2.setId(2);
		scene2.setSpeaker(null);
		scene2.setText("Сотрудник банка по телефону просит назвать трёхзначный код на обратной стороне карты. Что будете делать?");
		scene2.addChoice("<html>Вежливо попрощаюсь <br>и завершу звонок.</html>", 3);
		scene2.addChoice("<html>Уточню имя и должность сотрудника, <br>с какой целью он звонит.</html>", 4);
		scene2.setSceneImage("images/2.jpg");
		scenes.add(scene2);
		
		DialogueScene scene3 = new DialogueScene();
		scene3.setId(3);
		scene3.setText("Молодцы! Нельзя никому сообщать эти данные!");
		scene3.setNextSceneId(5);
		scene3.setSceneImage("images/3.jpg");
		scenes.add(scene3);
		
		DialogueScene scene4 = new DialogueScene();
		scene4.setId(4);
		scene4.setText("Опасность! Данные карты не передавайте ни в коем случае — настоящий менеджер банка никогда не попросит назвать коды по телефону!");
		scene4.setNextSceneId(5);
		scene4.setSceneImage("images/4.png");
		scenes.add(scene4);
		
		DialogueScene scene5 = new DialogueScene();
		scene5.setId(5);
		scene5.setText("Вам пришло сообщение от вашего банка о подозрительной активности счёта и ссылка для блокировки карты. Перейдёте по ней?");
		scene5.addChoice("<html>Да перейду, иначе <br>мошенники могут <br>заблокировать счёт карты</html>", 6);
		scene5.addChoice("<html>Странное содержание смс! <br>Не буду ничего нажимать</html>", 7);
		scene5.setSceneImage("images/5.jpg");
		scenes.add(scene5);
		
		DialogueScene scene6 = new DialogueScene();
		scene6.setId(6);
		scene6.setText("Упс! Такую смс могут отправить только мошенники. Никогда не поддавайтесь на такие уловки");
		scene6.setNextSceneId(8);
		scene6.setSceneImage("images/4.png");
		scenes.add(scene6);
		
		DialogueScene scene7 = new DialogueScene();
		scene7.setId(7);
		scene7.setText("Верно! Скорее всего, это мошеннический сайт маскируется под банк и пытается украсть ваши деньги");
		scene7.setSceneImage("images/3.jpg");
		scene7.setNextSceneId(8);
		scenes.add(scene7);
		
		DialogueScene scene8 = new DialogueScene();
		scene8.setId(8);
		scene8.setText("Вам пишет друг: ему срочно нужна 1000 рублей. Просит перечислить деньги на карту по номеру телефона. Вы ему звоните, но номер недоступен. Этот друг и раньше занимал у вас деньги на один – два дня, а в этот раз обещает вернуть уже вечером. Что сделаете?");
		scene8.addChoice("Перечислю", 9);
		scene8.addChoice("<html>Не буду перечислять. <br>Это мошенники</html>", 10);
		scene8.setSceneImage("images/6.jpg");
		scenes.add(scene8);
		
		DialogueScene scene9 = new DialogueScene();
		scene9.setId(9);
		scene9.setText("Да, в этой ситуации можно поверить и отправить деньги. Ведь перевод по номеру телефона зачислится на карту, которая привязана к этому номеру. Если вы берете телефон из вашего списка контактов, риск минимален");
		scene9.setSceneImage("images/3.jpg");
		scene9.setNextSceneId(11);
		scenes.add(scene9);
		
		DialogueScene scene10 = new DialogueScene();
		scene10.setId(10);
		scene10.setText("Хорошо, что вы сохраняете осторожность! Но не все сообщения о помощи — обман. В этой ситуации перевод отправится другу по мобильному номеру из вашего списка контактов и зачислится на его карту, привязанную к этому номеру.");
		scene10.setNextSceneId(11);
		scene10.setSceneImage("images/4.png");
		scenes.add(scene10);
		
		DialogueScene scene11 = new DialogueScene();
		scene11.setId(11);
		scene11.setText("Вы продаете в интернете свой старый ноутбук за 10000 рублей. Нашелся покупатель, который готов внести предоплату 3000 рублей. Он спрашивает у вас номер и срок действия карты для перевода денег. Никакие секретные пароли, коды и три цифры с обратной стороны карты он не просит. За телефоном готов приехать в течение часа, куда скажете. Что сделаете?");
		scene11.addChoice("<html>Отправлю, потому что <br>три цифры с обратной стороны <br>карты не спрашивают</html>", 12);
		scene11.addChoice("<html>Что-то здесь не так… <br>Не буду отправлять</html>", 13);
		scene11.setSceneImage("images/7.jpg");
		scenes.add(scene11);
		
		DialogueScene scene12 = new DialogueScene();
		scene12.setId(12);
		scene12.setText("Неверно! Для перевода нужен только номер карты, счета или даже просто номер телефона (если у вас есть привязанный к нему счет), но никак не срок действия карты.");
		scene12.setSceneImage("images/4.png");
		scene12.setNextSceneId(14);
		scenes.add(scene12);
		
		DialogueScene scene13 = new DialogueScene();
		scene13.setId(13);
		scene13.setText("Верный выбор! Для перевода достаточно знать только номер карты, счета или даже просто номер телефона (если у вас есть привязанный к нему счет), но никак не срок действия карты");
		scene13.setSceneImage("images/3.jpg");
		scene13.setNextSceneId(14);
		scenes.add(scene13);
		
		DialogueScene scene14 = new DialogueScene();
		scene14.setId(14);
		scene14.setText("Вы получаете уведомление от банка о зачислении 50 000 рублей. После проверки счета через мобильное приложение банка вы видите, что сумма действительно поступила. Вскоре звонит неизвестный человек, который утверждает, что случайно перевел эти средства на ваш счёт. Незнакомец просит вернуть деньги, угрожая судебными разбирательствами, если вы этого не сделаете. Какой это вид мошенничества?");
		scene14.setSceneImage("images/8.jpg");
		scene14.addChoice("Фишинг", 15);
		scene14.addChoice("Социальная инженерия", 16);
		scenes.add(scene14);
		
		DialogueScene scene15 = new DialogueScene();
		scene15.setId(15);
		scene15.setText("Неверно. Это социальная инженерия");
		scene15.setSceneImage("images/4.png");
		scene15.setNextSceneId(17);
		scenes.add(scene15);
		
		DialogueScene scene16 = new DialogueScene();
		scene16.setId(16);
		scene16.setText("Действительно, это социальная инженерия.");
		scene16.setSceneImage("images/3.jpg");
		scene16.setNextSceneId(17);
		scenes.add(scene16);
		
		DialogueScene scene17 = new DialogueScene();
		scene17.setId(17);
		scene17.setText("Недавно вы участвовали в онлайн-викторине и выиграли 3000 рублей. Чтобы получить приз, нужно оплатить комиссию за перевод денег — 30 рублей. Вам кинули ссылку на страницу, где надо ввести все данные карты для оплаты. По этим же реквизитам должны начислить вознаграждение. Какой это вид мошенничества?");
		scene17.addChoice("Фишинг", 18);
		scene17.addChoice("Заработок в интернете", 19);
		scene17.setSceneImage("images/9.jpg");
		scenes.add(scene17);
		
		DialogueScene scene18 = new DialogueScene();
		scene18.setId(18);
		scene18.setText("Правильно! Это фишинг");
		scene18.setSceneImage("images/3.jpg");
		scene18.setNextSceneId(20);
		scenes.add(scene18);
		
		DialogueScene scene19 = new DialogueScene();
		scene19.setId(19);
		scene19.setText("Неверно. Это фишинг.");
		scene19.setSceneImage("images/4.png");
		scene19.setNextSceneId(20);
		scenes.add(scene19);
		
		DialogueScene scene20 = new DialogueScene();
		scene20.setId(20);
		scene20.setText("Клиент обратился в банк с заявлением о подозрении на мошенническое списание денежных средств с его счета. Что должен сделать сотрудник банка");
		scene20.addChoice("<html>Заблокировать счёт клиента <br>немедленно, даже если <br>есть вероятность ошибки</html>", 21);
		scene20.addChoice("<html>Провести первичную <br>проверку транзакций перед <br>блокировкой счёта</html>", 22);
		scene20.setSceneImage("images/10.jpg");
		scenes.add(scene20);
		
		DialogueScene scene21 = new DialogueScene();
		scene21.setId(21);
		scene21.setText("Это не корректное действие. Блокировка счёта должна проводиться только после подтверждения факта мошенничества.");
		scene21.setNextSceneId(23);
		scene21.setSceneImage("images/4.png");
		scenes.add(scene21);
		
		DialogueScene scene22 = new DialogueScene();
		scene22.setId(22);
		scene22.setText("Это правильный выбор.");
		scene22.setNextSceneId(23);
		scene22.setSceneImage("images/3.jpg");
		scenes.add(scene22);
		
		DialogueScene scene23 = new DialogueScene();
		scene23.setId(23);
		scene23.setText("Клиент сообщает, что получил подозрительное сообщение от имени банка с просьбой подтвердить данные карты. Клиент уверен, что это мошенничество. Как должен поступить сотрудник банка?");
		scene23.addChoice("<html>Попросить клиента переслать <br>сообщение банку для проверки наличия следов мошенничества</html>", 24);
		scene23.addChoice("<html>Заблокировать карту <br>клиента</html>", 25);
		scene23.setSceneImage("images/11.jpg");
		scenes.add(scene23);
		
		DialogueScene scene24 = new DialogueScene();
		scene24.setId(24);
		scene24.setText("Это верно! Сначала нужно убедиться, что сообщение действительно мошенническое, проверив его содержание.");
		scene24.setSceneImage("images/3.jpg");
		scenes.add(scene24);
		
		DialogueScene scene25 = new DialogueScene();
		scene25.setId(25);
		scene25.setText("Увы, это неправильный ответ! Блокирование карты может быть преждевременным действием, пока не установлено, была ли карта скомпрометирована.");
		scene25.setSceneImage("images/4.png");
		scenes.add(scene25);
		
	}
	
	private void showScene(int sceneId) {
	    int index = findSceneIndexById(sceneId);
	    if (index == -1) {
	        endGame();
	        return;
	    }
	    
	    currentSceneIndex = index;
	    DialogueScene scene = scenes.get(currentSceneIndex);
	    
	    if (scene.getSceneImage() != null)
	    {
	    	changeBackgroundImage(scene.getSceneImage());
	    }
	    
	    charName.setText(scene.getSpeaker() != null ? scene.getSpeaker() : "");
	    firstText.setText("<html><div style='width:95%;padding:10px;'>" 
	                    + scene.getText() + "</div></html>");
	    
	    choicesPanel.removeAll();
	    choicesPanel.setLayout(new BoxLayout(choicesPanel, BoxLayout.Y_AXIS));
	    
	    if (scene.getChoices() != null && !scene.getChoices().isEmpty()) {
	        JPanel centerButtonPanel = new JPanel();
	        centerButtonPanel.setLayout(new BoxLayout(centerButtonPanel, BoxLayout.Y_AXIS));
	        centerButtonPanel.setOpaque(false);
	        
	        for (DialogueChoice choice : scene.getChoices()) {
	            JButton button = createChoiceButton(choice.getText());
	            button.setAlignmentX(Component.CENTER_ALIGNMENT);
	            button.addActionListener(e -> showScene(choice.getNextSceneId()));
	            
	            centerButtonPanel.add(Box.createVerticalStrut(20));
	            centerButtonPanel.add(button);
	        }
	        
	        choicesPanel.add(Box.createVerticalGlue());
	        choicesPanel.add(centerButtonPanel);
	        choicesPanel.add(Box.createVerticalGlue());
	    }
	    
	    choicesPanel.revalidate();
	    choicesPanel.repaint();
	    frame.repaint();
	}
	
	private void nextScene()
	{
		DialogueScene scene = scenes.get(currentSceneIndex);
		showScene(scene.getNextSceneId());
	}
	private void endGame()
	{
		firstText.setText("Поздравляю! Игра завершена. Спасибо за игру!");
		choicesPanel.removeAll();
		mainPanel.setBackgroundImage(new ImageIcon("images/1.jpg").getImage());
		
		JButton exitButton = createChoiceButton("Выход");
		exitButton.addActionListener(e -> System.exit(0));
		choicesPanel.add(exitButton);
	}
	private JButton createChoiceButton(String text) {
	    JButton button = new JButton(text);
	    button.setFont(new Font("Arial", Font.PLAIN, 18));
	    button.setForeground(Color.WHITE);
	    button.setBackground(new Color(70, 70, 120, 200));
	    button.setOpaque(true);
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
	    button.setMaximumSize(new Dimension(400, 100));
	    button.setPreferredSize(new Dimension(350, 75));
	    
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(new Color(90, 90, 150, 220));
	        }
	        
	        @Override
	        public void mouseExited(MouseEvent e) {
	            button.setBackground(new Color(70, 70, 120, 200));
	        }
	    });
	    
	    return button;
	}
	private int findSceneIndexById(int sceneId) {
	    for (int i = 0; i < scenes.size(); i++) {
	        if (scenes.get(i).getId() == sceneId) {
	            return i;
	        }
	    }
	    return -1;
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
