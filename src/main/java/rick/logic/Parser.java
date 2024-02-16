package rick.logic;

import rick.logic.command.*;

/**
 * A parser to parse user input.
 */

public class Parser {
    private String input;
    private String type;

    /**
     * Constructor for parser.
     * @param userInput a string from user input
     */
    public Parser(String userInput) {
        String[] splited = userInput.split("\\s+", 2);
        this.type = splited[0];
        this.input = splited[1];
    }

    /**
     * Parse the user input
     * @return the parsed string
     */
    public Command parse() throws RickException {
        switch (this.type) {
        case ("bye"):
            return new ByeCommand();
        case ("list"):
            return new ListCommand();
        case ("unmark"):
            return new UnmarkCommand();
        case ("mark"):
            return new MarkCommand(this.input);
        case ("todo"):
            return new TodoCommand(this.input);
        case ("event"):
            return new EventCommand(this.input);
        case ("deadline"):
            return new DeadlineCommand(this.input);
        case ("delete"):
            return new DeleteCommand(this.input);
        case ("find"):
            return new FindCommand(this.input);
        default:
            throw new RickException("Wabolabodabda! Can't understand what you're saying!");
        }
    }
}