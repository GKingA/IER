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
        logger.info(agName+" executing: "+action+"!");
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
}

