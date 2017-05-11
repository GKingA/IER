// Internal action code for project frontyard.mas2j

package actions;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class createSprinkler extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'actions.createSprinkler'");
		
		YardView.water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts.getC().addAchvGoal(Literal.parseLiteral("water(grass)"), null);
				YardModel.water();
				System.out.println("fuck");
			}
		});
        
        // everything ok, so returns true
        return true;
    }
}

