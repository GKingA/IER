// Environment code for project frontyard.mas2j

import jason.asSyntax.*;
import jason.asSyntax.parser.ParseException;
import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FrontYard extends Environment {

	public static final int GSize = 7; // grid size
    public static final int HEDGE  = 16; // hedge code in grid model
    public static final int BADHEDGE  = 17; //  badhedge code in grid model
    public static final int GRASS  = 32; // grass code in grid model
    public static final int BADGRASS  = 33; // bad grass code in grid model
	
	private YardModel model;
	private YardView view;
	
    private Logger logger = Logger.getLogger("frontyard.mas2j."+FrontYard.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
		model = new YardModel();
		view = new YardView(model);
		model.setView(view);
		//updatePercepts();
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+"!");
        if (true) { // you may improve this condition
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success 
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
	
	class YardModel extends GridWorldModel {
		private YardModel() {
			super(GSize, GSize, 2);
		}
	}
	
	class YardView extends GridWorldView {
		public YardView(YardModel model) {
			super(model, "Front Yard", 600);
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
				g.setColor(Color.red);
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
}

