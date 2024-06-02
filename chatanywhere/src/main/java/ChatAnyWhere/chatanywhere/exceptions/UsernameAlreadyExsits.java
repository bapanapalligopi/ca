package ChatAnyWhere.chatanywhere.exceptions;

public class UsernameAlreadyExsits extends Exception{
    private String resource;
    private String field;
    private String value;
    public UsernameAlreadyExsits(String resource,String field,String value)
    {
        super(String.format("%s with %s  %s is already found!!",resource,field,value));
    }

}
