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
    public static boolean[][] grassDry;
    public static boolean[][] longPlant;
	
	public YardModel() {
		super(GSize, GSize, 7);
		grassDry = new boolean[GSize][GSize];
		longPlant = new boolean[GSize][GSize];
		for(int i = 0; i < GSize; i++) {
			Arrays.fill(grassDry[i], Boolean.TRUE);
			Arrays.fill(longPlant[i], Boolean.TRUE);
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
	
	public void checkNeighboursWater(Location l, int r) {
		for(int i = 0; i <= r; i++) {
			if(l.x+i < GSize) {
				grassDry[l.x+i][l.y] = false;
			}
			if(l.y+i < GSize) {
				grassDry[l.x][l.y+i] = false;
			}
			if(l.x-i >= 0) {
				grassDry[l.x-i][l.y] = false;
			}
			if(l.y-i >= 0) {
				grassDry[l.x][l.y-i] = false;
			}
			if(l.x+i < GSize && l.y-i >= 0) {
				grassDry[l.x+i][l.y-i] = false;
			}
			if(l.x-i >= 0 && l.y+i < GSize) {
				grassDry[l.x-i][l.y+i] = false;
			}
			if(l.x-i >= 0 && l.y-i >= 0) {
				grassDry[l.x-i][l.y-i] = false;
			}
			if(l.x+i < GSize && l.y+i < GSize) {
				grassDry[l.x+i][l.y+i] = false;
			}
		}
	}
	
	public void mow() {
		for(int i = 0; i < GSize; i++) {
			for(int j = 0; j < GSize; j++) {
				if(longPlant[i][j] && this.hasObject(GRASS, i, j)) {
					longPlant[i][j] = false;
				}
			}
		}
	}

	public void trimm() {
		for(int i = 0; i < GSize; i++) {
			for(int j = 0; j < GSize; j++) {
				if(longPlant[i][j] && this.hasObject(HEDGE, i, j)) {
					longPlant[i][j] = false;
				}
			}
		}
	}
	
	public void water() {
		Location lSprinkler1 = getAgPos(1);
		Location lSprinkler2 = getAgPos(2);
		Location lSprinkler3 = getAgPos(3);
		int r = GSize/3;
		checkNeighboursWater(lSprinkler1, r);
		checkNeighboursWater(lSprinkler2, r);
		checkNeighboursWater(lSprinkler3, r);
	}
}

