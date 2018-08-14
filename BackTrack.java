
package csp;

import java.util.*;

/**
 *
 * @author Bayu Aji Firmansyah
 */
public class BackTrack {

    CSPProblem problem;
    Assignment start;

    public BackTrack(CSPProblem prob) {
        this(prob, Assignment.blank());
    }

    public BackTrack(CSPProblem prob, Assignment initial) {
        problem = prob;
        start = initial;
    }

    public Assignment solve() {
        return recursiveSolve(start);
    }

    public Assignment recursiveSolve(Assignment assign) {
        if (problem.satisfiedByAssignment(assign)) {
            return assign;
        }
        Variable v = unassignedVar(assign);
        if (v == null) {
            return null;
        }
        List<Object> values = problem.domainValues(assign, v);
        values = orderValues(assign, values, v);
        for (Object value : values) {
            Assignment newAssign = assign.assign(v, value);
            try {
                newAssign = problem.interference(newAssign, v);
            } catch (IllegalStateException e) {
                continue;
            }
            if (!problem.consistentAssignment(newAssign, v)) {
                continue;
            }
            newAssign = recursiveSolve(newAssign);
            if (newAssign != null) {
                return newAssign;
            }
        }
        return null;
    }

    protected Variable unassignedVar(Assignment assign) {
        for (Variable v : problem.variables()) {
            if (assign.getValue(v) == null) {
                return v;
            }
        }
        return null;
    }

    protected List<Object> orderValues(Assignment asign, List<Object> domain, Variable v) {
        return domain;
    }
}
