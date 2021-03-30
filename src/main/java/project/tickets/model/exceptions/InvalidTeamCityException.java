package project.tickets.model.exceptions;

public class InvalidTeamCityException extends RuntimeException{
    public InvalidTeamCityException(){
        super("Wrong City Exception");
    }
}
