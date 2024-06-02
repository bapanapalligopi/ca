package ChatAnyWhere.chatanywhere.exceptions;

public class IncorrectUsernameException extends  Exception{
    private String resource;
    public IncorrectUsernameException(String resource)
    {

        super(String.format("%s is not a valid username",resource));
        this.resource=resource;
    }
}



