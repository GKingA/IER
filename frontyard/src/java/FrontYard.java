// Environment code for project frontyard.mas2j

import jason.asSyntax.*;
import jason.asSyntax.parser.ParseException;
import jason.environment.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FrontYard extends Environment {

	public static final int GSize = 8; // grid size
    public static final int HEDGE  = 16; // hedge code in grid model
    public static final int GRASS  = 32; // grass code in grid model
	
	public static final Literal wg  = Literal.parseLiteral("water(grass)");
	public static final Literal mg  = Literal.parseLiteral("mow(grass)");
	public static final Literal th  = Literal.parseLiteral("trimm(hedge)");
	
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
		updatePercepts();
    }
	
	void updatePercepts() {
		clearPercepts("hedgetrimmer");
		clearPercepts("lawnmower");
		clearPercepts("sprinkler");
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
			super(GSize, GSize, 3);
			
			// initial location of agents
            /*try {
                setAgPos(0, 0, 0);
            
                Location r2Loc = new Location(GSize/2, GSize/2);
                setAgPos(1, r2Loc);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            
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
}

