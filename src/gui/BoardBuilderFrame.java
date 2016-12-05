package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
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
		 	boardPanel = new BoardPanel(false);
		 	board.print();
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
	        this.add(tilePanel, BorderLayout.SOUTH);
	        System.out.println(boardPanel.getSize());
	        
	        for (Rectangle rectangle: boardPanel.allRectangles) {
	        	rectangle.addMouseListener (new RectangleAddListener());
	        }
	        Dimension d = new Dimension(boardPanel.getPreferredSize());
	        setSize(d.width+100, d.height+250);
	        tilePanel.addMouseListener (new RectangleSelectListener());
	 //       setResizable(false);
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
         //   if (selectedTile == null) {
    			int x = me.getX();

    			if (x >= 10 && x <= 100) {
    				selectedTile = board.b;
    			}
    			
    			if (x >= 110 && x <= 200) {
    				selectedTile = board.c;
    			}
    			
    			if (x >= 210 && x <= 300) {
    				selectedTile = board.d;
    			}
    			
    			if (x >= 310 && x <= 400) {
    				selectedTile = board.e;
    			}
    			
    			if (x >= 410 && x <= 470) {
    				selectedTile = board.f;
    			}
    			
    			if (x >= 480 && x <= 540) {
    				selectedTile = board.g;
    			}
    			
    			if (x >= 550 && x <= 610) {
    				selectedTile = board.h;
    			}
    			
    			if (x >= 620 && x <= 680) {
    				selectedTile = board.i;
    			}
    			
    			if (x >= 690 && x <= 720) {
    				selectedTile = board.j;
    			}
    			
    			if (x >= 760 && x <= 790) {
    				selectedTile = board.k;
    			}
    			
    			if (x >= 800 && x <= 830) {
    				selectedTile = board.l;
    			}
    			
    			if (x >= 840 && x <= 870) {
    				selectedTile = board.m;
    			}
    			
    			if (x >= 880 && x <= 910) {
    				selectedTile = board.n;
    			}
    			
    			if (x >= 920 && x <= 980) {
    				selectedTile = board.o;
    			}
    			
    			if (x >= 990 && x <= 1050) {
    				selectedTile = board.p;
    			}
    			
    			if (x >= 1060 && x <= 1120) {
    				selectedTile = board.q;
    			}
    			
    			if (x >= 1130 && x <= 1190) {
    				selectedTile = board.r;
    			}
    			
         /*   } else {
            	System.out.println("els");
                
                selectedTile = new Tile("b", 3, 2);
           //     tilePanel.remove(b);
            }*/
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
	                String tileName = selectedTile.getName();
	                System.out.println(tileName);
	                Tile tile = board.findTile(tileName);
	                System.out.println(tile.getName());
	                
	                int position = Integer.parseInt(e.getSource().toString());
	                int y = (position - 1)/10;   //hier müssen x und y des angeklickten Feldes übernommen werden.
	                int x = position - y*10 - 1;
	                System.out.println("placed: " + tile.getPlaced());
	                board.setTile(tile, x, y);   
	                System.out.println("placed: " + tile.getPlaced());
	           //   selectedTile = null;
	           //   board.readBoard(board.toString());
	                boardPanel.readBoard(board);
	       //       boardPanel.repaint();
	                boardPanel.getParent().revalidate();
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