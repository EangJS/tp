package seedu.duke.logic.commandhandler;

import seedu.duke.logic.commands.ExerciseSearchCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.GenerateFilterCommand;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.QuickStartCommand;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.UserExerciseData;
import seedu.duke.data.userdata.userplan.UserPlan;

import java.util.HashMap;

public class GeneralCommandHandler implements CommandList {

    /**
     * This class handles all user commands when not in an exercise
     *
     * @param userCommands This refers to the commands given by the user
     * @param ui This allows us to output messages
     * @param exerciseGenerator This takes in filter parameters and outputs a
     *     curated exercise list
     * @param userCareerData This keeps track and allows logging of all user
     *     data
     * @param exerciseStateHandler This allows us to start workouts
     */
    // addition of user exercise history
    public void handleGeneralUserCommands (String[] userCommands, Ui ui, GenerateExercise exerciseGenerator,
                                           UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                           Storage storage, UserPlan planner) {
        Command command = null;
        boolean errorExists = false;
        try {

            switch (userCommands[0]) {
            case GENERATE_COMMAND:
                command = new GenerateFilterCommand(userCommands);
                break;
            case FILTERS_COMMAND:
                ui.printFilters();
                break;
            case EXIT_COMMAND:
                ui.byeUser();
                System.exit(0);
                break;
            case HELP_COMMAND:
                command = new HelpCommand();
                break;
            case PLANNER_EDITOR_COMMAND:
                PlannerCommandHandler.plannerCommandHandler(ui, planner, storage);
                break;
            case VIEW_PLAN_COMMAND:
                ui.showPlan(planner);
                break;
            case QUICK_START_COMMAND:
                command = new QuickStartCommand(userCommands, ui, exerciseGenerator);
                break;
            case START_COMMAND:
                exerciseStateHandler.startWorkout();
                break;
            case CURRENT_COMMAND:
            case FINISH_COMMAND:
            case CANCEL_COMMAND:
                System.out.println("No workout session active." +
                                       " Please generate a workout and use the \"start\" command!");
                break;
            case HISTORY_COMMAND:
                userCareerData.printAllFinishedWorkoutSessions();
                break;
            case FIND_COMMAND:
                command = new ExerciseSearchCommand(userCommands);
                break;
            case EXERCISE_DATA_COMMAND:
                HashMap<String, Integer> userExerciseDataMap = UserExerciseData
                    .addUserExerciseHistory(userCareerData);
                ui.printUserExerciseHistory(userExerciseDataMap);
                break;
            default:
                ui.unknownCommand();
                errorExists = true;
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists) {
            try {
                if (command != null) {
                    command.executeCommand(ui, exerciseGenerator);
                    if (command instanceof GenerateFilterCommand) {
                        exerciseStateHandler
                            .storePreviousGeneratedWorkout(((GenerateFilterCommand) command).provideExerciseList());
                    }
                }
            } catch (DukeError e) {
                System.out.println(e.getMessage());
            }
        }
        ui.splitLine();
    }

}