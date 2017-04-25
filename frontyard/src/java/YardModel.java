import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class YardModel extends GridWorldModel {
	public YardModel() {
		super(FrontYard.GSize, FrontYard.GSize, 7);
			
		// initial location of agents
		/*try {
			setAgPos(0, 0, 0);
			Location r2Loc = new Location(GSize/2, GSize/2);
			setAgPos(1, r2Loc);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
         
		// initial location of grass and hedge
		for (int i = 0; i < FrontYard.GSize; i++) {
			for (int j = 0; j < FrontYard.GSize; j++) {
				if(j != 3) {
					add(FrontYard.GRASS, i, j);
				}
				else {
					add(FrontYard.HEDGE, i, j);
				}
			}
		}
	}
}

