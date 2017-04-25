import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class YardView extends GridWorldView {
	public YardView(YardModel model) {
		super(model, "Front Yard", 800);
		defaultFont = new Font("Arial", Font.BOLD, 18); // change default font
		setVisible(true);
		repaint();
	}
		
	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		String label = "R"+(id+1);
		c = Color.blue;
	
		/*if (id == 0) {
			c = Color.yellow;
			if (((YardModel)model).r1HasGarb) {
				label += " - G";
				c = Color.orange;
			}
		}*/
			
		super.drawAgent(g, x, y, c, -1);
		if (id == 0) { //hedgetrimmer
			g.setColor(Color.black);
		}
		else if (id == 1) { //lawnmower
			g.setColor(Color.gray);
		}
		else { //sprinkler
			g.setColor(Color.white);                
		}
		super.drawString(g, x, y, defaultFont, label);
			repaint();
		}

		public void drawWater(Graphics g, int x, int y) {
			super.drawObstacle(g, x, y);
			g.setColor(Color.blue);
			drawString(g, x, y, defaultFont, "W");
		}
		
		public void drawMow(Graphics g, int x, int y) {
			super.drawObstacle(g, x, y);
			g.setColor(Color.green);
			drawString(g, x, y, defaultFont, "M");
		}

		public void drawTrimm(Graphics g, int x, int y) {
			super.drawObstacle(g, x, y);
			g.setColor(Color.green);
			drawString(g, x, y, defaultFont, "T");
		}
	}
