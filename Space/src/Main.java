import javax.swing.JFrame;

public class Main extends JFrame {
	
	public Main() {
		JFrame f = new JFrame();
		Panel p = new Panel();
		
		f.getContentPane().add(p);
		p.setLayout(null);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Star Invaders");
		f.setResizable(false);
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
