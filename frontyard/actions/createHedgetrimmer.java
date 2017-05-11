// Internal action code for project frontyard.mas2j

package actions;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class createHedgetrimmer extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'actions.createHedgetrimmer'");
        
		YardView.trimm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts.getC().addAchvGoal(Literal.parseLiteral("trimm(hedge)"), null);
				YardModel.trimm();
				System.out.println("fuck");
			}
		});
		
        // everything ok, so returns true
        return true;
    }
}

