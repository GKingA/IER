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
			setAgPos(0, 3, 0); //hedgetrimmer
			setAgPos(1, 3, 3); //sprinkler1
			setAgPos(2, GSize-3, GSize-3); //sprinkler2
			setAgPos(3, 3, GSize-3); //sprinkler3
			setAgPos(4, GSize/3, 4); //lawnmower1
			setAgPos(5, GSize/3, 5); //lawnmower2
			setAgPos(6, GSize/3, 6); //lawnmower3
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
			for(int j = 0; j <= r; j++) {
				if(l.x + i < GSize && l.y + j < GSize) {
					grassDry[l.x+i][l.y+j] = false;
				}
				if(l.x - i >= 0 && l.y + j < GSize) {
					grassDry[l.x-i][l.y+j] = false;
				}
				if(l.x - i >= 0 && l.y - j >= 0) {
					grassDry[l.x-i][l.y-j] = false;
				}
				if(l.x + i < GSize && l.y - j >= 0) {
					grassDry[l.x+i][l.y-j] = false;
				}
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
	
	public void move(int agent) {
		Location l = getAgPos(agent);
		for(int i = 0; i < 7; i++){
			if(i != agent && i != 1 && i != 2 && i != 3) {
				if(l.equals(getAgPos(i))){
					l.x++;
					if(l.x == GSize) {
						l.x = 0;
						l.y++;
						if(l.y == GSize) {
							l.y = GSize - 1;
						}
					}
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

