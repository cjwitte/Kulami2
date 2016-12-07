package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Checkbox;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import control.Board;
import control.Game;
import control.KIPlayer;
import control.Player;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class MainFrame {
	
	String[] levels = {"0" , "1" , "2"};
	JComboBox selectLevels = new JComboBox(levels);
	JButton boardLoaderBtn = new JButton ("Board laden");
	JButton boardBuilderBtn = new JButton ("Board editieren");
	JButton startGameBtn = new JButton ("Spiel starten");
	TextField nameTextfield = new TextField (20);
	TextField hostnameField = new TextField(20);
	TextField portField = new TextField(20);
	Checkbox KICheckbox = new Checkbox("KI-Modus", false);
	JSlider levelSlider = new JSlider(JSlider.HORIZONTAL, 1, 9, 1);
	JPanel KIPanel = new JPanel();
	
	
	
	Board board;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}
	

	/**;
	 * 
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		
		frame.setBounds(30, 30, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		
		JPanel hostPanel = new JPanel(new FlowLayout());
		hostPanel.add(hostnameField, FlowLayout.LEFT);
		hostPanel.add(new JLabel("Host-Name: "), FlowLayout.LEFT);
		
		JPanel portPanel = new JPanel(new FlowLayout());
		portPanel.add(portField, FlowLayout.LEFT);
		portPanel.add(new JLabel("Port-Nr: "), FlowLayout.LEFT);
		

		JPanel namePanel = new JPanel(new FlowLayout());
		namePanel.add(nameTextfield, FlowLayout.LEFT);
		namePanel.add(new JLabel("Ihr Name: "), FlowLayout.LEFT);

		JPanel textPanels = new JPanel ();
		textPanels.setLayout(new BoxLayout(textPanels, BoxLayout.LINE_AXIS));
		textPanels.add(hostPanel);
		textPanels.add(portPanel);
		textPanels.add(namePanel);
		
		frame.add(textPanels, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(boardBuilderBtn);
		buttonPanel.add(boardLoaderBtn);
		buttonPanel.add(startGameBtn);
		
		
//		frame.add(boardBuilderBtn);
//		frame.add(boardLoaderBtn);
//		frame.add(namePanel);
//		frame.add(hostPanel);
//		frame.add(portPanel);
//		frame.add(selectLevels);
//		frame.add(startGameBtn);
	
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		levelSlider.setMajorTickSpacing(1);
		levelSlider.setPaintTicks(true);
		levelSlider.setPaintLabels(true);
		KIPanel.setLayout(new FlowLayout());
		KIPanel.add(levelSlider, FlowLayout.LEFT);
		KIPanel.add(KICheckbox, FlowLayout.LEFT);
		frame.add(KIPanel, BorderLayout.LINE_END);
	
		
		frame.pack();
		
		
		boardBuilderBtn.addActionListener(new BordBuilderBtnListener());
		boardLoaderBtn.addActionListener(new BoardLoaderBtnListener());
		startGameBtn.addActionListener(new GameStartBtnListener());
	}

	public void setBoard (Board board) {
		this.board = board;
	}
	
	class BordBuilderBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("BordBuilder gestartet.");
		}
	}
	
	class BoardLoaderBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Board laden.");
		}
	}

	class GameStartBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Spiel starten.");
			int levelInt = 0;
			//parseInt!
			String level = selectLevels.getSelectedItem().toString();
			if (level.equals("0")) {
				levelInt = 0;
			} else if (level.equals("1")) {
				levelInt = 1;
			} else if (level.equals("2")) {
				levelInt = 2;
			}
	//		board = new Board(levelInt);
			Player player;
			if (KICheckbox.getState()) {
				player = new KIPlayer(nameTextfield.getText(), levelSlider.getValue());
			} else {
				player = new Player(nameTextfield.getText());
			}
			Game game = new Game (Integer.parseInt(portField.getText()), hostnameField.getText(), Integer.parseInt(selectLevels.getSelectedItem().toString()),new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1) , player);
			game.run();
			game.getBoard().print();
			System.out.println("level: " + game.getBoard().getLevel());
	//		System.out.println(player.pickMove());
			GameFrame gameFrame = new GameFrame(game);
			gameFrame.setVisible(true);
		}
	}

}
