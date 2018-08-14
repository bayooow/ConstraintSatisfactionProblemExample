
package csp;

import java.util.*;

/**
 *
 * @author Bayu Aji Firmansyah
 */
public class Drive {

    public MapColoring coloring;
    public Assignment initial = Assignment.blank();

    public Drive(int size) {
        initial = Assignment.blank();
        coloring = new MapColoring(size);
    }

    public Assignment Solve() {
        MRVBackTrack mb = new MRVBackTrack(coloring, initial);
        return mb.solve();
    }

    public static void main(String[] args) {
        List<String> color = new ArrayList<String>();
        color.add("Merah");
        color.add("Biru");
        color.add("Hijau");
        Drive m = new Drive(color.size());

        for (int i = 0; i < color.size(); i++) {
            System.out.println("Domain " + (i + 1) + " : " + color.get(i));
        }
        Assignment solution = m.Solve();
        if (!(solution == null)) {
            System.out.println("solusi");
            List<Variable> var = m.coloring.variables();
            for (Variable v : var) {
                System.out.printf("%-15s : %s\n", v.description(), color.get((Integer) solution.getValue(v) - 1));
            }
        } else {
            System.out.println("ga ada solusi");
        }

    }

}
