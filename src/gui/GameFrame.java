package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Game;

public class GameFrame extends JFrame implements PropertyChangeListener {
	
	private Game game;
	private boolean active;
	int size;
	BoardPanel boardPanel;
	TextField activePlayerField;
	private JButton submitButton;
	private JPanel messagePanel;
	private JLabel playerLabel;
	
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setPlayerLabel(String playerLabelText) {
		playerLabel.setText(playerLabelText);
	}
	
	public GameFrame(Game game) {
		playerLabel = new JLabel("Spieler: " + game.getPlayer().getName() + "Farbe: " + game.getPlayer().getColorAsChar());
		TextField message = new TextField();
		submitButton = new JButton("submit Move");
		TextArea messageArea = new TextArea(5,10);
		activePlayerField = new TextField("Spieler am Zug: " + game.getActivePlayer());
		messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.add(message, BorderLayout.SOUTH);
		messagePanel.add(messageArea, BorderLayout.NORTH);
		this.game = game;
//		game.addActivePlayerChangeListener(this);
		game.addMoveListener(this);
		size = 800;
		
		boardPanel = new BoardPanel((size/4)*3);
		boardPanel.readBoard(this.game.getBoard());
		setSize(size, size);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boardPanel.setLayout(new GridLayout(10,10,0,0));
		
		for (Rectangle rectangle: boardPanel.allRectangles) {
			rectangle.addMouseListener(new RectangleMouseListener());
		}
		
		add(playerLabel, BorderLayout.WEST);
		add(activePlayerField, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
		add(submitButton, BorderLayout.SOUTH);
		add(messagePanel, BorderLayout.EAST);
		displayLegalMoves();
		setVisible(true);
	}
	
	public void displayLegalMoves () {
		for (Rectangle rectangle: boardPanel.allRectangles) {
			if (game.getBoard().legalMoves().contains(rectangle.getNumber()) && (game.getActivePlayer() == game.getPlayer().getColorAsChar())) {
				rectangle.setColor(Color.GREEN);
				rectangle.repaint();
			}
			
		}
	}
	
	public void updateActivePlayerField () {
		activePlayerField.setText("Spieler am Zug: " + game.getActivePlayer());
	}
	
	
	class SubmitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.getPlayer().setMove(game.getSelectedMove());
			System.out.println(game.getSelectedMove());
			game.getPlayer().setBlocked(false);
		//	System.out.println("Zug(" + (game.getSelectedMove() - (((game.getSelectedMove()-1)/10)*10)-1) + ", " + (game.getSelectedMove()-1)/10 + ").");
		}
	}
	
	class RectangleMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked");

			Rectangle source = (Rectangle)e.getSource();
			if (game.getBoard().legalMoves().contains(source.number)  ){
				source.setColor(game.getPlayer().getColor());
				source.repaint();
				int move = source.getNumber();
				game.setMove(move);
				System.out.println("move set in game.");
				int y = game.getBoard().yFromPosition(move);
				int x = game.getBoard().xFromPosition(move);
				if (game.getMoveNeeded()) {
					System.out.println("if (game.getMoveNeeded())");
					game.communicator.setToServer("zug("+ x + ", " + y + ").");
					System.out.println("setToServer: " + game.communicator.getToServer());
					game.communicator.setShouldWrite(true);
					game.setMoveNeeded(false);
				}	

			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
	/*	System.out.println("beim Board vor if");
		activePlayerField.setText("Spieler am Zug: " + game.getActivePlayer());
		if (arg0.getPropertyName().equals("moveNeeded")) {
			System.out.println("beim Board angekommen");
			displayLegalMoves();
			for (Rectangle rectangle: boardPanel.allRectangles) {
				rectangle.addMouseListener(new RectangleMouseListener());
			}
			submitButton.addActionListener(new SubmitButtonListener());
			System.out.println("submitButton aktiviert");
		}*/
	}

}
