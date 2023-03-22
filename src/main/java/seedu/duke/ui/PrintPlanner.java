package seedu.duke.ui;

import seedu.duke.userplan.Plan;
import seedu.duke.userplan.UserPlan;
import seedu.duke.userplan.Day;
import java.util.ArrayList;

//@@author Khulon
public class PrintPlanner {
    private static final Integer DAYSINAWEEK = 7;
    public static void printPlanner(UserPlan planner) {
        ArrayList<Plan>[] plan = planner.getPlan();

        System.out.println("YOUR WORKOUT PLAN:");
        for (int day  = 0; day < DAYSINAWEEK; day++) {
            System.out.println("_________");
            System.out.println(Day.intToDay(day));
            for (int j = 0; j < plan[day].size(); j++) {
                ArrayList<String> exercisePlan = plan[day].get(j).getPlan();
                String planName = j+1 + ". [";
                planName += plan[day].get(j).getStatus() + "] ";
                planName += plan[day].get(j).getPlanName();
                System.out.println(planName);
                String planFilters = "Filters:";
                for (int k = 0; k < exercisePlan.size(); k++) {
                    planFilters += " " + exercisePlan.get(k);
                }
                System.out.println(planFilters);
            }
        }
    }
}