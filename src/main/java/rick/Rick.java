package rick;

import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import rick.logic.Executer;
import rick.logic.Parser;
import rick.logic.RickException;
import rick.ui.DialogBox;
import rick.ui.Ui;
import rick.util.Storage;
import rick.util.TaskList;

import java.util.Scanner;


/**
 * A Rick chatbot.
 */
public class Rick {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of the Rick chatbot with specified filePath to store data on hard drive.
     */

    public Rick() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (RickException e) {
            //to delete
            this.ui.showLoadingError();
            Ui.reply(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Gets response for specified input
     * @param input user input
     * @return a string representing the response of the chatbot
     */
    @FXML
    public String getResponse(String input) throws RickException {
        Parser parser = new Parser(input);
        try {
            Executer executer = new Executer(this.tasks, this.storage);
            return executer.execute(parser.parse());
        } catch (RickException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "ERROR: Congratulations! "
                    + "You have input a message that the developer did not expect. "
                    + "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.";
        }
    }

    public static void main(String[] arg) {
        try {
            Rick rick = new Rick();
            Ui.hello();
            Scanner scan = new Scanner(System.in);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                System.out.println(rick.getResponse(input));
            }
        } catch (Exception e) {
            System.out.println("cause: " + e.getMessage());
        }
    }
}
