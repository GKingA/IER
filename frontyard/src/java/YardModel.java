import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.Arrays;
import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class YardModel extends GridWorldModel {
	
	public static final int GSize = 18; // grid size
    public static final int HEDGE  = 16; // hedge code in grid model
    public static final int GRASS  = 32; // grass code in grid model
    public static boolean[][] grassDry; // grass code in grid model
	
	public YardModel() {
		super(GSize, GSize, 7);
		grassDry = new boolean[GSize][GSize];
		for(int i = 0; i < GSize; i++) {
			Arrays.fill(grassDry[i], Boolean.TRUE);
		}
			
		// initial location of agents
		try {
			for(int i = 0; i < 7; i++) {
				setAgPos(i, i, 0); //AgentID, x, y
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// initial location of grass and hedge
		for (int i = 0; i < GSize; i++) {
			for (int j = 0; j < GSize; j++) {
				if(j != 3) {
					add(GRASS, i, j);
				}
				else {
					add(HEDGE, i, j);
				}
			}
		}
	}
	
	public void water() {
		Location lSprinkler1 = getAgPos(1);
		Location lSprinkler2 = getAgPos(2);
		Location lSprinkler3 = getAgPos(3);
		int r = GSize/3;
		for(int i = 0; i <= r; i++) {
			
			//Sprinkler 1
			if(lSprinkler1.x+i < GSize) {
				grassDry[lSprinkler1.x+i][lSprinkler1.y] = true;
			}
			if(lSprinkler1.y+i < GSize) {
				grassDry[lSprinkler1.x][lSprinkler1.y+i] = true;
			}
			if(lSprinkler1.x-i >= 0) {
				grassDry[lSprinkler1.x-i][lSprinkler1.y] = true;
			}
			if(lSprinkler1.y-i >= 0) {
				grassDry[lSprinkler1.x][lSprinkler1.y-i] = true;
			}
			if(lSprinkler1.x+i < GSize && lSprinkler1.y-i >= 0) {
				grassDry[lSprinkler1.x+i][lSprinkler1.y-i] = true;
			}
			if(lSprinkler1.x-i >= 0 && lSprinkler1.y+i < GSize) {
				grassDry[lSprinkler1.x-i][lSprinkler1.y+i] = true;
			}
			if(lSprinkler1.x-i >= 0 && lSprinkler1.y-i >= 0) {
				grassDry[lSprinkler1.x-i][lSprinkler1.y-i] = true;
			}
			if(lSprinkler1.x+i < GSize && lSprinkler1.y+i < GSize) {
				grassDry[lSprinkler1.x+i][lSprinkler1.y+i] = true;
			}
			
			//Sprinkler 2
			if(lSprinkler2.x+i < GSize) {
				grassDry[lSprinkler2.x+i][lSprinkler2.y] = true;
			}
			if(lSprinkler2.y+i < GSize) {
				grassDry[lSprinkler2.x][lSprinkler2.y+i] = true;
			}
			if(lSprinkler2.x-i >= 0) {
				grassDry[lSprinkler2.x-i][lSprinkler2.y] = true;
			}
			if(lSprinkler2.y-i >= 0) {
				grassDry[lSprinkler2.x][lSprinkler2.y-i] = true;
			}
			if(lSprinkler2.x+i < GSize && lSprinkler2.y-i >= 0) {
				grassDry[lSprinkler2.x+i][lSprinkler2.y-i] = true;
			}
			if(lSprinkler2.x-i >= 0 && lSprinkler2.y+i < GSize) {
				grassDry[lSprinkler2.x-i][lSprinkler2.y+i] = true;
			}
			if(lSprinkler2.x-i >= 0 && lSprinkler2.y-i >= 0) {
				grassDry[lSprinkler2.x-i][lSprinkler2.y-i] = true;
			}
			if(lSprinkler2.x+i < GSize && lSprinkler2.y+i < GSize) {
				grassDry[lSprinkler2.x+i][lSprinkler2.y+i] = true;
			}
			
			//Sprinkler 3
			if(lSprinkler3.x+i < GSize) {
				grassDry[lSprinkler3.x+i][lSprinkler3.y] = true;
			}
			if(lSprinkler3.y+i < GSize) {
				grassDry[lSprinkler3.x][lSprinkler3.y+i] = true;
			}
			if(lSprinkler3.x-i >= 0) {
				grassDry[lSprinkler3.x-i][lSprinkler3.y] = true;
			}
			if(lSprinkler3.y-i >= 0) {
				grassDry[lSprinkler3.x][lSprinkler3.y-i] = true;
			}
			if(lSprinkler3.x+i < GSize && lSprinkler3.y-i >= 0) {
				grassDry[lSprinkler3.x+i][lSprinkler3.y-i] = true;
			}
			if(lSprinkler3.x-i >= 0 && lSprinkler3.y+i < GSize) {
				grassDry[lSprinkler3.x-i][lSprinkler3.y+i] = true;
			}
			if(lSprinkler3.x-i >= 0 && lSprinkler3.y-i >= 0) {
				grassDry[lSprinkler3.x-i][lSprinkler3.y-i] = true;
			}
			if(lSprinkler3.x+i < GSize && lSprinkler3.y+i < GSize) {
				grassDry[lSprinkler3.x+i][lSprinkler3.y+i] = true;
			}
		}
	}
}

