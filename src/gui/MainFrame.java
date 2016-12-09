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

import control.Board;
import control.Game;
import control.KIPlayer;
import control.Player;

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
		
		board = new Board(); 
	}

	public void setBoard (Board board) {
		this.board = board;
	}
	
	public void startBoardBuilderFrame () {
		BoardBuilderFrame bbf = new BoardBuilderFrame(this);
		bbf.setVisible(true);
	}
	class BordBuilderBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("BordBuilder gestartet.");
			startBoardBuilderFrame();
		}
	}
	
	public String loadBoard(String filename) {
		return "a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0";
		//TODO das Board tats�chlich laden.
	}
	
	class BoardLoaderBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Board laden.");
			String loadedBoard = loadBoard(e.getActionCommand());
			setBoard(new Board(loadedBoard));
		}
	}

	class GameStartBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Spiel starten.");
			Player player = new Player(nameTextfield.getText());
			int levelInt = Integer.parseInt(selectLevels.getSelectedItem().toString());
			Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0");

			board.setLevel(levelInt);
			if (KICheckbox.getState()) {
				player = new KIPlayer(nameTextfield.getText(), levelSlider.getValue());
			} 
				
			
			Game game = new Game (Integer.parseInt(portField.getText()), hostnameField.getText(), Integer.parseInt(selectLevels.getSelectedItem().toString()), board , player);
			game.getBoard().print();
			GameFrame gameFrame = new GameFrame(game);
			gameFrame.setVisible(true);
			game.addObserver(gameFrame);
		}
	}

}
