import java.util.ArrayList;
import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

/**
 * The class for creating museum time schedule.
 *
 * @author Tomasz Strzoda
 */
public class Museum {

    public static void main(String[] args) {

        Store store = new Store(); // Store for CLP variables
        ArrayList<IntVar> vars = new ArrayList<IntVar>(); // ArrayList of CLP variables to store all sets

        final int SIZE = 4; //The constant related to the number of exhibitions

        IntVar[] americans = new IntVar[SIZE];
        IntVar[] belgians = new IntVar[SIZE];
        IntVar[] czech = new IntVar[SIZE];
        IntVar[] danish = new IntVar[SIZE];

        // The maximum duration of nationality in the museum
        for (int i = 0; i < SIZE; i++) {
            americans[i] = new IntVar(store, "Americans[" + i + "]", 0, 300);
            belgians[i] = new IntVar(store, "Belgians[" + i + "]", 60, 300);
            czech[i] = new IntVar(store, "Czech[" + i + "]", 90, 300);
            danish[i] = new IntVar(store, "Danish[" + i + "]", 105, 300);
            vars.add(americans[i]);
            vars.add(belgians[i]);
            vars.add(czech[i]);
            vars.add(danish[i]);
        }


        IntVar[] drawings = new IntVar[SIZE];
        drawings[0] = new IntVar(store, "durationAmericans", 60, 60);
        drawings[1] = new IntVar(store, "durationBelgians", 20, 20);
        drawings[2] = new IntVar(store, "durationCzech", 30, 30);
        drawings[3] = new IntVar(store, "durationDanish", 45, 45);

        IntVar[] paintings = new IntVar[SIZE];
        paintings[0] = new IntVar(store, "durationAmericans", 45, 45);
        paintings[1] = new IntVar(store, "durationBelgians", 15, 15);
        paintings[2] = new IntVar(store, "durationCzech", 60, 60);
        paintings[3] = new IntVar(store, "durationDanish", 10, 10);

        IntVar[] sculptures = new IntVar[SIZE];
        sculptures[0] = new IntVar(store, "durationAmericans", 30, 30);
        sculptures[1] = new IntVar(store, "durationBelgians", 60, 60);
        sculptures[2] = new IntVar(store, "durationCzech", 20, 20);
        sculptures[3] = new IntVar(store, "durationDanish", 60, 60);

        IntVar[] photographs = new IntVar[SIZE];
        photographs[0] = new IntVar(store, "durationAmericans", 15, 15);
        photographs[1] = new IntVar(store, "durationBelgians", 60, 60);
        photographs[2] = new IntVar(store, "durationCzech", 45, 45);
        photographs[3] = new IntVar(store, "durationDanish", 30, 30);

        IntVar[][] durations = new IntVar[SIZE][];
        durations[0] = drawings;
        durations[1] = paintings;
        durations[2] = sculptures;
        durations[3] = photographs;


        IntVar one = new IntVar(store, "one", 1, 1); // Limit
        IntVar[] fourOnes = {one, one, one, one}; // List of resources
        IntVar[] startTimes = new IntVar[SIZE];

        startTimes[0] = americans[0];
        startTimes[1] = belgians[0];
        startTimes[2] = czech[0];
        startTimes[3] = danish[0];
        // Exhibition can be visited by only one group at the same time
        store.impose(new Cumulative(startTimes, drawings, fourOnes, one));

        startTimes[0] = americans[1];
        startTimes[1] = belgians[1];
        startTimes[2] = czech[1];
        startTimes[3] = danish[1];
        // Exhibition can be visited by only one group at the same time
        store.impose(new Cumulative(startTimes, paintings, fourOnes, one));

        startTimes[0] = americans[2];
        startTimes[1] = belgians[2];
        startTimes[2] = czech[2];
        startTimes[3] = danish[2];
        // Exhibition can be visited by only one group at the same time
        store.impose(new Cumulative(startTimes, sculptures, fourOnes, one));

        startTimes[0] = americans[3];
        startTimes[1] = belgians[3];
        startTimes[2] = czech[3];
        startTimes[3] = danish[3];
        // Exhibition can be visited by only one group at the same time
        store.impose(new Cumulative(startTimes, photographs, fourOnes, one));


        // Setting opening time
        IntVar openingTime = new IntVar(store, "openingTime", 0, 300);
        vars.add(openingTime);

        // Imposing order
        int[] americansPrecedence = {1, 2, 3, 4};
        for (int i = 0; i < 4; i++) {
            if (i < 3)
                store.impose(new XplusYlteqZ(americans[americansPrecedence[i] - 1],
                        durations[americansPrecedence[i] - 1][0],
                        americans[americansPrecedence[i + 1] - 1]));
            else
                store.impose(new XplusYlteqZ(americans[americansPrecedence[i] - 1],
                        durations[americansPrecedence[i] - 1][0],
                        openingTime));
        }

        int[] belgiansPrecedence = {2, 3, 1, 4};
        for (int i = 0; i < 4; i++) {
            if (i < 3)
                store.impose(new XplusYlteqZ(belgians[belgiansPrecedence[i] - 1],
                        durations[belgiansPrecedence[i] - 1][1],
                        belgians[belgiansPrecedence[i + 1] - 1]));
            else
                store.impose(new XplusYlteqZ(belgians[belgiansPrecedence[i] - 1],
                        durations[belgiansPrecedence[i] - 1][1],
                        openingTime));
        }

        int[] czechPrecedence = {3, 2, 1, 4};
        for (int i = 0; i < 4; i++) {
            if (i < 3)
                store.impose(new XplusYlteqZ(czech[czechPrecedence[i] - 1],
                        durations[czechPrecedence[i] - 1][2],
                        czech[czechPrecedence[i + 1] - 1]));
            else
                store.impose(new XplusYlteqZ(czech[czechPrecedence[i] - 1],
                        durations[czechPrecedence[i] - 1][2],
                        openingTime));
        }

        int[] danishPrecedence = {4, 1, 2, 3};
        for (int i = 0; i < 4; i++) {
            if (i < 3)
                store.impose(new XplusYlteqZ(danish[danishPrecedence[i] - 1],
                        durations[danishPrecedence[i] - 1][3],
                        danish[danishPrecedence[i + 1] - 1]));
            else
                store.impose(new XplusYlteqZ(danish[danishPrecedence[i] - 1],
                        durations[danishPrecedence[i] - 1][3],
                        openingTime));
        }


        // Time - start
        long T1 = System.nanoTime();

        // Search for solution
        Search label = new DepthFirstSearch();
        SelectChoicePoint select = new InputOrderSelect(store, vars.toArray(new IntVar[1]), new IndomainMin());
        label.labeling(store, select);

        // Time - stop
        long T2 = System.nanoTime();
        System.out.println("Time: " + Long.toString(T2-T1) + "ns");
    }

}