package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TilePanel extends JPanel{
	
    RectForBuilder b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;
    
    public TilePanel () {
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
    }
    
    public static void main (String[] args) {
    	JFrame f = new JFrame("sdf");
    	TilePanel t = new TilePanel();
    	f.add(t);
    	f.setSize(600, 600);
    	f.setVisible(true);
    }

}
