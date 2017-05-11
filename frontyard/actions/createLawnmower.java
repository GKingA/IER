// Internal action code for project frontyard.mas2j

package actions;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class createLawnmower extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'actions.createLawnmower'");
		
		YardView.mow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts.getC().addAchvGoal(Literal.parseLiteral("mow(grass)"), null);
				YardModel.mow();
				System.out.println("fuck");
			}
		});
        
        // everything ok, so returns true
        return true;
    }
}

