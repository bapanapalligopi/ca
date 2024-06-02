package ChatAnyWhere.chatanywhere.exceptions;

public class UserNotFoundException extends Exception{
    private String resource;
    private String field;
    private String value;
    public UserNotFoundException(String resource,String field,String value)
    {
        super(String.format("%s with %s: %s is not found!!",resource,field,value));
        this.resource=resource;
        this.field=field;
        this.value=value;
    }
}
