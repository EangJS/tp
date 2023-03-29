package seedu.duke.ui;

import seedu.duke.Duke;
import seedu.duke.commons.LogMaster;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.userplan.UserPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class UiManager {
    private static final String SEPARATOR = "________________________________________";
    private static final Logger logger = LogMaster.getLogger(Duke.class);
    public void printExerciseFromList (ArrayList<ExerciseData> exerciseData) {
        PrintExercises.printExercise(exerciseData);
    }

    public void greetUser () {
        Greet.greet();
    }

    public void byeUser () {
        Bye.bye();
    }

    public void printFilters () {
        PrintHelpMessage.printFiltersAvailable();
    }

    public void unknownCommand () {
        PrintHelpMessage.unknownCommandMessage();
    }

    public void showPlan (UserPlan planner) {
        PrintPlanner.printPlanner(planner);
    }

    public void printHelp () {
        PrintHelpMessage.showAvailableCommands();
    }

    public void printPlannerHelp () {
        PrintHelpMessage.showAvailablePlannerCommands();
    }

    public void splitLine () {
        System.out.println(SEPARATOR);
    }

    public void printUserExerciseHistory (HashMap<String, Integer> userExerciseHistory) {
        PrintUserExerciseData.printUserExerciseHistory(userExerciseHistory);
    }

    public void printPlannerGreeting () {
        PrintPlanner.printPlannerGreeting();
    }

    public void printExerciseSessionHelp() {
        PrintHelpMessage.showAvailableExerciseSessionCommands();
    }

//    public void start() {
//        logger.info("Starting Fitness Duke ");
//
//    }
}

