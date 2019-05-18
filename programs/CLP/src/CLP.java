import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

/**
 * Class  for CLP problems.
 *
 * @author Tomasz Strzoda
 */
public class CLP {

    public static void main(String[] args) {

        // Store for CLP variables
        Store store = new Store();

        // Array of sets
        IntVar[] vars = new IntVar[2];

        // Defining sets
        vars[0] = new IntVar(store, "A", 4, 10);
        vars[1] = new IntVar(store, "B", 3, 6);

        // Imposing constraints
        store.impose(new XgtY(vars[0],vars[1]));
        store.impose(new XplusYeqC(vars[0],vars[1],9));

        // Search only one solution - first one
        Search label = new DepthFirstSearch();
        SelectChoicePoint select = new InputOrderSelect(store, vars, new IndomainMin());
        label.labeling(store, select);


        // Search only one solution - second one
        /*
        Search label = new DepthFirstSearch();
        SelectChoicePoint select = new InputOrderSelect(store, vars, new IndomainMax());
        label.labeling(store, select);
        */

        // Search all solutions
        /*
        Search label = new DepthFirstSearch();
        label.getSolutionListener().searchAll(true);
        SelectChoicePoint select = new InputOrderSelect(store, vars, new IndomainMin());
        label.getSolutionListener().recordSolutions(true);
        label.labeling(store, select);
        label.printAllSolutions();
        */
    }

}
