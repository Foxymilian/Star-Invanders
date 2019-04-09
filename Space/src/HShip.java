import java.awt.Color;
import java.awt.Graphics;

public class HShip { //Hostile ship
	public int xCoor, yCoor, width, height;
	
	public HShip(int xCoor, int yCoor, int titleSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = titleSize;
		height = titleSize;
	}
	
	public void ticks() {
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(xCoor*width, yCoor*height, width, height);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
