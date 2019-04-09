import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Panel extends JPanel implements KeyListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread t;
	
	public boolean runnable;
	
	public boolean right=false, left=false, up=true, down=true;
	
	private int xCoor=25, yCoor=40, size=1;
	private int tick = 0;
	
	private Ship s;
	private ArrayList<Ship> ship;
	
	private FBullet fb;
	private ArrayList<FBullet> friendly;
	
	private HBullet hb;
	private ArrayList<HBullet> hostile;
	
	private HShip hs;
	private ArrayList<HShip> hship;
	
	private Stars st;
	private ArrayList<Stars> star;
	
	private BStar bs;
	private ArrayList<BStar> big;
	
	private Random r;
	
	private int b;
		
	public Panel() {
		
		r = new Random();
		
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
		friendly = new ArrayList<FBullet>();
		hostile = new ArrayList<HBullet>();
		hship = new ArrayList<HShip>();
		star = new ArrayList<Stars>();
		ship = new ArrayList<Ship>();
		big = new ArrayList<BStar>();
		

	}
	
	public void start() {
		runnable = true;
		t = new Thread(this);
		t.start();
	}
	public void stop() {
		runnable = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ticks() {
		if(ship.size()==0) {
			s = new Ship(xCoor, yCoor, 10);
			ship.add(s);
		}
		
		if(big.size()<1) {
			int xCoor=0;
			int yCoor=0;
			
			bs = new BStar(xCoor, yCoor, 100);
			big.add(bs);
		}
		
		if(hship.size()<1) {
			int xCoor = r.nextInt(50);
			int yCoor = r.nextInt(20);
			
			hs = new HShip(xCoor, yCoor, 10);
			hship.add(hs);
		}
		if(hostile.size()==0) {
			hb = new HBullet(hs.xCoor, hs.yCoor, 10);
			hostile.add(hb);
		}
		
		if(star.size()<1000) {
			int xCoor=r.nextInt(500);
			int yCoor=r.nextInt(500);
			
			st = new Stars(xCoor, yCoor, 1);
			star.add(st);
		}
		
		tick++;
		
		if(tick>250000) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(down) hb.yCoor++;
			if(friendly.size()>0) {
				if(up) fb.yCoor--;
			}
			
			tick = 0;
			
			s = new Ship(xCoor, yCoor, 10);
			ship.add(s);
			
			if(ship.size()>size) {
				ship.remove(0);
			}
			
			if(friendly.size()>0) {
				fb = new FBullet(fb.xCoor, fb.yCoor, 10);
					friendly.add(fb);

					if(friendly.size()>size) {
						friendly.remove(0);
						}	
					}
			
			if(hostile.size()>0) {
				hb = new HBullet(hb.xCoor, hb.yCoor, 10);
				hostile.add(hb);

				if(hostile.size()>size) {
					hostile.remove(0);
				}
			}
			
			if(xCoor>49) {
				xCoor=0;
			}
			if(xCoor<0) {
				xCoor=50;
				}

			for(int i=0; i<friendly.size(); i++) {
				if(friendly.size()>0) {
					if(friendly.get(i).getyCoor()<0) {
						friendly.remove(i);
						i++;
						}
					}
				}
			
			for(int i=0; i<hostile.size(); i++) {
				if(hostile.size()>0) {
					if(hostile.get(i).getyCoor()>49) {
						hostile.remove(i);
						i++;
						}
					}
				}
			
			for(int i = 0; i<hship.size(); i++) {
				if(friendly.size()>0) {
					if(fb.xCoor == hship.get(i).getxCoor() && fb.yCoor == hship.get(i).getyCoor()) {
							hship.remove(i);
							friendly.remove(0);
							i++;
							b=b+5;
						}
					}
				}
			
			for(int i = 0; i<ship.size(); i++) {
				if(hostile.size()>0) {
					if(hb.xCoor == ship.get(i).getxCoor() && hb.yCoor == ship.get(i).getyCoor()) {
						if(b>=150) {
							JOptionPane.showMessageDialog(null, "Вы проиграли! \n"
									+ "Вы заработали "+b+" очков. \n"
									+ "★★★★", "ПОРАЖЕНИЕ", JOptionPane.WARNING_MESSAGE);
							System.exit(1);
							stop();
						}else if(b>=100 && b<150) {
							JOptionPane.showMessageDialog(null, "Вы проиграли! \n"
									+ "Вы заработали "+b+" очков. \n"
									+ "★★★", "ПОРАЖЕНИЕ", JOptionPane.WARNING_MESSAGE);
							System.exit(1);
							stop();
						}else if(b>=50 && b<100) {
							JOptionPane.showMessageDialog(null, "Вы проиграли! \n"
									+ "Вы заработали "+b+" очков. \n"
									+ "★★", "ПОРАЖЕНИЕ", JOptionPane.WARNING_MESSAGE);
							System.exit(1);
							stop();
						}else if(b>=10 && b<50) {
							JOptionPane.showMessageDialog(null, "Вы проиграли! \n"
									+ "Вы заработали "+b+" очков. \n"
									+ "★", "ПОРАЖЕНИЕ", JOptionPane.WARNING_MESSAGE);
							System.exit(1);
							stop();
							}else {
								JOptionPane.showMessageDialog(null, "Вы проиграли! \n"
										+ "Вы заработали "+b+" очков.", "ПОРАЖЕНИЕ", JOptionPane.WARNING_MESSAGE);
								System.exit(1);
								stop();
							}
						}
					}
				}
			
			for(int i = 0; i<friendly.size(); i++) {
				if(friendly.size()>0 && hostile.size()>0) {
					if(fb.xCoor == hostile.get(i).getxCoor() && fb.yCoor == hostile.get(i).getyCoor()) {
							hostile.remove(i);
							friendly.remove(0);
							i++;
							b++;
						}
					}
				}
			}
		}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i=0; i<WIDTH/10; i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i=0; i<HEIGHT; i++) {
			g.drawLine(0, i*10, HEIGHT, i*10);
		}
		for(int i=0; i<star.size(); i++) {
			star.get(i).draw(g);
		}
		for(int i=0; i<big.size(); i++) {
			big.get(i).draw(g);
		}
		for(int i=0; i<friendly.size(); i++) {
			friendly.get(i).draw(g);
		}
		for(int i=0; i<hship.size(); i++) {
			hship.get(i).draw(g);	
		}
		for(int i=0; i<ship.size(); i++) {
			ship.get(i).draw(g);
		}
		for(int i=0; i<hostile.size(); i++) {
			hostile.get(i).draw(g);
		}
		
	}
	
	@Override
	public void run() {
		while(runnable) {
			ticks();
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			if(runnable==false) {
				start();
			}
			
			left=false;
			right=true;
		}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			if(runnable==false) {
				start();
			}
			
			right=false;
			left=true;
		}
		if(key == KeyEvent.VK_SPACE) {
			if(runnable==false) {
				start();
			}
			
			if(friendly.size()==0) {
				fb = new FBullet(xCoor, yCoor, 10);
				friendly.add(fb);
			}
		}
		if(key == KeyEvent.VK_P) {
			if(runnable==false) {
				start();
			}else if(runnable==true) {
				stop();
			}
		}
		
		if(key == KeyEvent.VK_P) {
			if(runnable=false) {
				start();
			}else if(runnable=true) {
				stop();
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(2);
		}
		
		if(key == KeyEvent.VK_I) {
			JOptionPane.showMessageDialog(null, "1. Для начала и отключения паузы нажать любую используемую клавишу. \n"
					+ "2. Для паузы нажать P(англ.). \n"
					+ "3. Для управление используйте WASD или стрелочки. \n"
					+ "4. Для стрельбы используйте ПРОБЕЛ. \n"
					+ "5. Для выхода нажать ESCAPE.", "ИНСТРУКЦИЯ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			left=false;
			right=false;
		}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			right=false;
			left=false;
		}
	}
}
