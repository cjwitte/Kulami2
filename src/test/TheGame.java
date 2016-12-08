package test;

public class TheGame {
	
	private TheRunnable theRunnable;
	
	public TheGame() {
		Thread gameThread = new Thread(new TheRunnable("oje"));
		gameThread.start();
	}

}
