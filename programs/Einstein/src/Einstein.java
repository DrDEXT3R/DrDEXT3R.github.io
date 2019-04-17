import java.util.ArrayList;
import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

/**
 * Class for solving Einstein's Riddle.
 *
 * @author Tomasz Strzoda
 */
public class Einstein {

    enum Pet { DOGS , BIRDS, HORSES, CATS, FISH; }
    enum Drink { TEA, COFFEE, MILK, BEER, WATER; }
    enum Color { RED, GREEN, WHITE, YELLOW, BLUE; }
    enum Nation { ENGLISHMAN, SWEDE, DANE, NORWEGIAN, GERMAN; }
    enum Cigarette { PALLMALL, DUNHILL, BLUEMASTER, PRINCE, BLEND; }
    enum OrderOfCategories { COLOR, NATION, PET, DRINK, CIGARETTE; }


    public static void main(String[] args) {

        Store store = new Store();  // Store for CLP variables
        ArrayList<IntVar> vars = new ArrayList<IntVar>(); // ArrayList of CLP variables

        final int SIZE = 5;
        final IntVar DIST_1 = new IntVar(store, 1, 1);

        IntVar pet[] = new IntVar[SIZE];
        IntVar drink[] = new IntVar[SIZE];
        IntVar color[] = new IntVar[SIZE];
        IntVar nation[] = new IntVar[SIZE];
        IntVar cigarette[] = new IntVar[SIZE];

        // Determination of possible values
        for (int i = 0; i < SIZE; i++) {
            pet[i] = new IntVar(store, Pet.values()[i].toString(), 1, SIZE);
            drink[i] = new IntVar(store, Drink.values()[i].toString(), 1, SIZE);
            color[i] = new IntVar(store, Color.values()[i].toString(), 1, SIZE);
            nation[i] = new IntVar(store, Nation.values()[i].toString(), 1, SIZE);
            cigarette[i] = new IntVar(store, Cigarette.values()[i].toString(), 1, SIZE);
            vars.add(pet[i]);
            vars.add(drink[i]);
            vars.add(color[i]);
            vars.add(nation[i]);
            vars.add(cigarette[i]);
        }

        // Constraints
        store.impose(new Alldifferent(pet));
        store.impose(new Alldifferent(drink));
        store.impose(new Alldifferent(color));
        store.impose(new Alldifferent(nation));
        store.impose(new Alldifferent(cigarette));

        store.impose(new XeqY( nation[Nation.ENGLISHMAN.ordinal()], color[Color.RED.ordinal()] ));                  // 1
        store.impose(new XeqY( nation[Nation.SWEDE.ordinal()], pet[Pet.DOGS.ordinal()] ));                          // 2
        store.impose(new XeqY( nation[Nation.DANE.ordinal()], drink[Drink.TEA.ordinal()] ));                        // 3
        store.impose(new XplusCeqZ( color[Color.GREEN.ordinal()], 1, color[Color.WHITE.ordinal()] ));            // 4
        store.impose(new XeqY( color[Color.GREEN.ordinal()], drink[Drink.COFFEE.ordinal()] ));                      // 5
        store.impose(new XeqY( cigarette[Cigarette.PALLMALL.ordinal()], pet[Pet.BIRDS.ordinal()] ));                // 6
        store.impose(new XeqY( color[Color.YELLOW.ordinal()], cigarette[Cigarette.DUNHILL.ordinal()] ));            // 7
        store.impose(new XeqC( drink[Drink.MILK.ordinal()], 3 ));                                                // 8
        store.impose(new XeqC( nation[Nation.NORWEGIAN.ordinal()], 1 ));                                         // 9
        store.impose(new Distance( cigarette[Cigarette.BLEND.ordinal()], pet[Pet.CATS.ordinal()], DIST_1 ));        // 10
        store.impose(new Distance( cigarette[Cigarette.DUNHILL.ordinal()], pet[Pet.HORSES.ordinal()], DIST_1 ));    // 11
        store.impose(new XeqY( cigarette[Cigarette.BLUEMASTER.ordinal()], drink[Drink.BEER.ordinal()] ));           // 12
        store.impose(new XeqY( nation[Nation.GERMAN.ordinal()], cigarette[Cigarette.PRINCE.ordinal()] ));           // 13
        store.impose(new Distance( nation[Nation.NORWEGIAN.ordinal()], color[Color.BLUE.ordinal()], DIST_1 ));      // 14
        store.impose(new Distance( cigarette[Cigarette.BLEND.ordinal()], drink[Drink.WATER.ordinal()], DIST_1 ));   // 15

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
