import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

/**
 * The class for solving map coloring problem.
 *
 * @author Tomasz Strzoda
 */
public class MapColoring {

    public static void main(String[] args) {

        // Store for CLP variables
        Store store = new Store();

        // The variable for storing number of regions
        int noOfRegions = 6;

        // The array for storing the domain of each region
        IntVar[] regions = new IntVar[noOfRegions];
        for (int i=0; i<noOfRegions; i++) {
            regions[i] = new IntVar(store, "regions"+i, 1, noOfRegions);
        }


    /*  Method 1  */
        // Neighboring regions (A, B, C, D)
        IntVar[] group1 = {regions[0], regions[1], regions[2], regions[3]};
        store.impose(new Alldifferent(group1));

        // Neighboring regions (A, D, F)
        IntVar[] group2 = {regions[0], regions[3], regions[5]};
        store.impose(new Alldifferent(group2));

        // Neighboring regions (D, E, F)
        IntVar[] group3 = {regions[3], regions[4], regions[5]};
        store.impose(new Alldifferent(group3));
    /*  END  */


//    /*  Method 2  */
//        IntVar[] group = new IntVar[2];
//
//        // Neighboring regions (A, B)
//        group[0] = regions[0];
//        group[1] = regions[1];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (A, C)
//        group[0] = regions[0];
//        group[1] = regions[2];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (A, D)
//        group[0] = regions[0];
//        group[1] = regions[3];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (A, F)
//        group[0] = regions[0];
//        group[1] = regions[5];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (B, C)
//        group[0] = regions[1];
//        group[1] = regions[2];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (B, D)
//        group[0] = regions[1];
//        group[1] = regions[3];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (C, D)
//        group[0] = regions[2];
//        group[1] = regions[3];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (C, E)
//        group[0] = regions[2];
//        group[1] = regions[4];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (D, E)
//        group[0] = regions[3];
//        group[1] = regions[4];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (D, F)
//        group[0] = regions[3];
//        group[1] = regions[5];
//        store.impose(new Alldifferent(group));
//
//        // Neighboring regions (E, F)
//        group[0] = regions[4];
//        group[1] = regions[5];
//        store.impose(new Alldifferent(group));
//    /*  END  */


        // Time - start
        long T1 = System.nanoTime();

        // Search for solution
        Search label = new DepthFirstSearch();
        SelectChoicePoint select = new InputOrderSelect(store, regions, new IndomainMin());
        label.labeling(store, select);

        // Time - stop
        long T2 = System.nanoTime();
        System.out.println("Time: " + Long.toString(T2-T1) + "ns");
    }

}