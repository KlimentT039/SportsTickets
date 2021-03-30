package project.tickets.model.exceptions;

public class InvalidTeamNameException extends RuntimeException {
    public InvalidTeamNameException(){
        super("There is no such team");
    }
}
