import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.*;

import javax.swing.*;

class YardView extends GridWorldView {
	
	private YardModel yardModel;
	
	final JButton water;
	final JButton mow;
	final JButton trimm;
	
	public YardView(YardModel model) {
		super(model, "Front Yard", 800);
		
		water = new JButton("Water");
		mow = new JButton("Mow the grass");
		trimm = new JButton("Trimm the hedges");
		
		yardModel = model;
		defaultFont = new Font("Arial", Font.BOLD, 18); // change default font
		this.add(Buttons(), BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}
	
	public JPanel Buttons() {
		JPanel buttons = new JPanel();
		buttons.add(new JLabel("Use the following buttons to control the agents: "));
		buttons.add(water);
		buttons.add(mow);
		buttons.add(trimm);
		return buttons;
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
		super.drawAgent(g, lLawnmower1.x, lLawnmower1.y, Color.gray, 4);
		Location lLawnmower2 = yardModel.getAgPos(5);
		super.drawAgent(g, lLawnmower2.x, lLawnmower2.y, Color.gray, 5);
		Location lLawnmower3 = yardModel.getAgPos(6);
		super.drawAgent(g, lLawnmower3.x, lLawnmower3.y, Color.gray, 6);
		
		switch(object) {
			case YardModel.GRASS:
				if(YardModel.grassDry[x][y] && YardModel.longPlant[x][y]) {
					drawDryLongGrass(g, x, y);
				}
				else if(!YardModel.grassDry[x][y] && YardModel.longPlant[x][y]){
					drawWetLongGrass(g, x, y);
				}
				else if(YardModel.grassDry[x][y] && !YardModel.longPlant[x][y]) {
					drawDryShortGrass(g, x, y);
				}
				else if(!YardModel.grassDry[x][y] && !YardModel.longPlant[x][y]){
					drawWetShortGrass(g, x, y);
				}
				break;
			case YardModel.HEDGE:
				if(YardModel.longPlant[x][y]) {
					drawLongHedge(g, x, y);
				}
				else {
					drawShortHedge(g, x, y);
				}
				break;
		}
		//repaint();
		//update(x, y); -> csak egy négyzetet frissít
	}
	
	@Override
	public void drawObstacle(Graphics g, int x, int y){
		g.fillRect(x * cellSizeW + 1, y * cellSizeH+1, cellSizeW-1, cellSizeH-1);
		g.setColor(Color.black);
	}
	
	public void drawDryLongGrass(Graphics g, int x, int y) {
		g.setColor(Color.yellow);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "L");
	}
	
	public void drawWetLongGrass(Graphics g, int x, int y) {
		g.setColor(Color.green);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "L");
	}
	
		public void drawDryShortGrass(Graphics g, int x, int y) {
		g.setColor(Color.yellow);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "S");
	}
	
	public void drawWetShortGrass(Graphics g, int x, int y) {
		g.setColor(Color.green);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "S");
	}
	
	public void drawLongHedge(Graphics g, int x, int y) {
		g.setColor(Color.darkGray);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "L");
	}
	
		public void drawShortHedge(Graphics g, int x, int y) {
		g.setColor(Color.darkGray);
		drawObstacle(g, x, y);
		drawString(g, x, y, defaultFont, "S");
	}
}
