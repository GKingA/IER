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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontYard extends Environment {

	public static final Literal waterGrass  = Literal.parseLiteral("water(grass)");
	public static final Literal mowGrass  = Literal.parseLiteral("mow(grass)");
	public static final Literal trimmHedge  = Literal.parseLiteral("trimm(hedge)");
	public static final Literal move = Literal.parseLiteral("move(slot)");
	
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
		
		view.mow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPercept(mowGrass);
				//model.mow();
				view.repaint();
			}
		});
		
		view.water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPercept(waterGrass);
				//model.water();
				view.repaint();
			}
		});
		
		view.trimm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPercept(trimmHedge);
				//model.trimm();
				view.repaint();
			}
		});
		
		updatePercepts();
    }
	
	void updatePercepts() {
		clearPercepts();
		Location lHedgetrimmer = model.getAgPos(0);
		Location lSprinkler1 = model.getAgPos(1);
		Location lSprinkler2 = model.getAgPos(2);
		Location lSprinkler3 = model.getAgPos(3);
		Location lLawnmower1 = model.getAgPos(4);
		Location lLawnmower2 = model.getAgPos(5);
		Location lLawnmower3 = model.getAgPos(6);
		
		Literal posHedgetrimmer = Literal.parseLiteral("pos(hedgetrimmer,"+lHedgetrimmer.x+","+lHedgetrimmer.y+")");
		Literal posLawnmower1 = Literal.parseLiteral("pos(lawnmower1,"+lLawnmower1.x+","+lLawnmower1.y+")");
		Literal posLawnmower2 = Literal.parseLiteral("pos(lawnmower2,"+lLawnmower2.x+","+lLawnmower2.y+")");
		Literal posLawnmower3 = Literal.parseLiteral("pos(lawnmower3,"+lLawnmower3.x+","+lLawnmower3.y+")");
		
		addPercept(posHedgetrimmer);
		addPercept(posLawnmower1);
		addPercept(posLawnmower2);
		addPercept(posLawnmower3);
	}

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info(agName+" executing: "+action+"!");
        try {
			if(action.equals(waterGrass)) {
				model.water();
			}
			if(action.equals(trimmHedge)) {
				model.trimm();
			}
			if(action.equals(mowGrass)) {
				model.mow();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		updatePercepts();
		try {
            Thread.sleep(200);
        }
		catch (Exception e) {}
        informAgsEnvironmentChanged();
		view.repaint();
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

