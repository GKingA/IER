import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class YardView extends GridWorldView {
	
	private YardModel yardModel;
	
	public YardView(YardModel model) {
		super(model, "Front Yard", 800);
		yardModel = model;
		defaultFont = new Font("Arial", Font.BOLD, 18); // change default font
		setVisible(true);
		repaint();
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		Location lHedgetrimmer = yardModel.getAgPos(0);
		super.drawAgent(g, lHedgetrimmer.x, lHedgetrimmer.y, Color.lightGray, 0);
		
		Location lSrinkler1 = yardModel.getAgPos(1);
		super.drawAgent(g, lSrinkler1.x, lSrinkler1.y, Color.blue, 1);
		Location lSrinkler2 = yardModel.getAgPos(2);
		super.drawAgent(g, lSrinkler2.x, lSrinkler2.y, Color.blue, 2);
		Location lSrinkler3 = yardModel.getAgPos(3);
		super.drawAgent(g, lSrinkler3.x, lSrinkler3.y, Color.blue, 3);
		
		Location lLawnmower1 = yardModel.getAgPos(4);
		super.drawAgent(g, lLawnmower1.x, lLawnmower1.y, Color.magenta, 4);
		Location lLawnmower2 = yardModel.getAgPos(5);
		super.drawAgent(g, lLawnmower2.x, lLawnmower2.y, Color.magenta, 5);
		Location lLawnmower3 = yardModel.getAgPos(6);
		super.drawAgent(g, lLawnmower3.x, lLawnmower3.y, Color.magenta, 6);
		
		switch(object) {
			case YardModel.GRASS:
				if(YardModel.grassDry) {
					drawDryGrass(g, x, y);
				}
				else {
					drawWetGrass(g, x, y);
				}
				break;
			case YardModel.HEDGE:
				drawHedge(g, x, y);
				break;
		}
		//repaint();
	}
	
	@Override
	public void drawObstacle(Graphics g, int x, int y){
		g.fillRect(x * cellSizeW + 1, y * cellSizeH+1, cellSizeW-1, cellSizeH-1);
		g.setColor(Color.black);
		g.drawRect(x * cellSizeW + 2, y * cellSizeH+2, cellSizeW-4, cellSizeH-4);
	}
	
	public void drawDryGrass(Graphics g, int x, int y) {
		g.setColor(Color.yellow);
		drawObstacle(g, x, y);
	}
	
	public void drawWetGrass(Graphics g, int x, int y) {
		g.setColor(Color.green);
		drawObstacle(g, x, y);
	}
	
	public void drawHedge(Graphics g, int x, int y) {
		g.setColor(Color.black);
		drawObstacle(g, x, y);
	}
}
