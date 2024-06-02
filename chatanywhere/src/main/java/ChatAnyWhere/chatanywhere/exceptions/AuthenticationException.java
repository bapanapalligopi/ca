package ChatAnyWhere.chatanywhere.exceptions;

public class AuthenticationException extends Exception{
    public AuthenticationException()
    {
        super("Invalid Credentials");
    }
}
