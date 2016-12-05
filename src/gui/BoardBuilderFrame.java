package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Board;
import control.Tile;
import control.TileNotPlacableException;

public class BoardBuilderFrame extends JFrame {


    private MainFrame mainFrame;
    private Board board;
    private BoardPanel boardPanel;
    private Tile selectedTile;
    private JPanel tilePanel;
    RectForBuilder b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;

	
	public BoardBuilderFrame (MainFrame mainFrame) {
		 	this.board = new Board(1);
		 	boardPanel = new BoardPanel(600, true);
		 	board.print();
	    //    this.setLayout(new BorderLayout());
	        
	   //  boardPanel.setEditable(true);  //TODO Variable einführen ...?
	        TilePanel tilePanel = new TilePanel();
	        JButton saveBtn = new JButton("save");
	        selectedTile = null;
	     //   tilePanel.setLayout(new FlowLayout());
	    /*   
	        int size = 30;
	        
	        b = new RectForBuilder("b", 60, 90);
	        c = new RectForBuilder("c", 60, 90);
	        d = new RectForBuilder("d", 60, 90);
	        e = new RectForBuilder("e", 60, 90);
	        f = new RectForBuilder("f", 60, 60);
	        g = new RectForBuilder("g", 60, 60);
	        h = new RectForBuilder("h", 60, 60);
	        i = new RectForBuilder("i", 60, 60);
	        j = new RectForBuilder("j", 60, 60);
	        k = new RectForBuilder("k", 30, 90);
	        l = new RectForBuilder("l", 30, 90);
	        m = new RectForBuilder("m", 30, 90);
	        n = new RectForBuilder("n", 30, 90);
	        o = new RectForBuilder("o", 30, 60);
	        p = new RectForBuilder("p", 30, 60);
	        q = new RectForBuilder("q", 30, 60);
	        r = new RectForBuilder("r", 30, 60);
	        
	        b.repaint();
	        
	        add(b);
	        add(c);
	        add(d);
	        add(e);
	        add(f);
	        add(g);
	        add(h);
	        add(i);
	        add(j);
	        add(k);
	        add(l);
	        add(m);
	        add(n);
	        add(o);
	        add(p);
	        add(q);
	        add(r);
	       
	        
	        b.addMouseListener (new RectangleSelectListener());
	        c.addMouseListener (new RectangleSelectListener());
	        d.addMouseListener (new RectangleSelectListener());
	        e.addMouseListener (new RectangleSelectListener());
	        f.addMouseListener (new RectangleSelectListener());
	        g.addMouseListener (new RectangleSelectListener());
	        h.addMouseListener (new RectangleSelectListener());
	        i.addMouseListener (new RectangleSelectListener());
	        j.addMouseListener (new RectangleSelectListener());
	        k.addMouseListener (new RectangleSelectListener());
	        l.addMouseListener (new RectangleSelectListener());
	        m.addMouseListener (new RectangleSelectListener());
	        n.addMouseListener (new RectangleSelectListener());
	        o.addMouseListener (new RectangleSelectListener());
	        p.addMouseListener (new RectangleSelectListener());
	        q.addMouseListener (new RectangleSelectListener());
	        r.addMouseListener (new RectangleSelectListener());
	           
	        saveBtn.addActionListener( new SaveBtnListener());
	       
	        boardPanel.addMouseListeners(new RectangleAddListener());
	       */
	        boardPanel.readBoard(board);
	        
	        boardPanel.setVisible(true);
	        this.add(saveBtn);
	        this.add(boardPanel);
	   //     this.add(b);
	        this.add(tilePanel, BorderLayout.SOUTH);
	        System.out.println(boardPanel.getSize());
	        
	        setSize(1000,750);
	        tilePanel.addMouseListener (new RectangleSelectListener());
	        setResizable(false);
	    
	        setVisible(true);
	    }
		
	public static void main (String[] args) {
		MainFrame mf = new MainFrame();
		BoardBuilderFrame bbf = new BoardBuilderFrame(mf);
		bbf.setVisible(true);
	}
    class RectangleSelectListener implements MouseListener {
  
		@Override
		public void mouseClicked(MouseEvent me) {
            if (selectedTile == null) {
    			int x = me.getX();

    			if (x >= 10 && x <= 100) {
    				selectedTile = new Tile( "b" , 3,2);
    				try {
    					board.setTile(selectedTile, 1,1);
        				boardPanel.readBoard(board);
        				boardPanel.repaint();
    				} catch (TileNotPlacableException e) {
    					System.err.println("not placable");
    				}
    				
    				
    			}
            } else {
            	System.out.println("els");
                
                selectedTile = new Tile("b", 3, 2);
           //     tilePanel.remove(b);
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
   
    class RectangleAddListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			 try {
	                String tileName = e.getSource().toString();
	                Tile tile = board.findTile(tileName);
	                int position = Integer.parseInt(e.getSource().toString());
	                int y = (position - 1)/10;   //hier müssen x und y des angeklickten Feldes übernommen werden.
	                int x = position - y*10 - 1;
	                board.setTile(tile, x, y);   
	                selectedRectangle = null;
	                boardPanel.repaint();
	            } catch (TileNotPlacableException te) {
	                JOptionPane.showMessageDialog(boardPanel, "An dieser Stelle kann die Platte nicht platziert werden", "Inane error", JOptionPane.ERROR_MESSAGE );
	            }
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
   
    class SaveBtnListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    	//	mainFrame.setBoard(board);   //das Board übernehmen
            String name = JOptionPane.showInputDialog("Name des Boards");
            saveBoard(name);
    	}	
    	
    	
    }
   
    class LoadBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
	        String loadName = JOptionPane.showInputDialog("Name des Boards");
	        board = loadBoard(loadName);
		}
    }
    
    public Board loadBoard(String fileName) {
        try{
            FileInputStream loadFile = new FileInputStream(fileName);
            ObjectInputStream is = new ObjectInputStream(loadFile);
            return (Board)is.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Board(1);
        }
    }
   
    public void saveBoard(String name){
        try {
            FileOutputStream saveFile = new FileOutputStream(name + ".brd");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(board);
            save.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}