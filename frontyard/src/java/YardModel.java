import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class YardModel extends GridWorldModel {
	
	public static final int GSize = 8; // grid size
    public static final int HEDGE  = 16; // hedge code in grid model
    public static final int GRASS  = 32; // grass code in grid model
    public static boolean grassDry = true; // grass code in grid model
	
	public YardModel() {
		super(GSize, GSize, 7);
			
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
}

