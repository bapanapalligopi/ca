package ChatAnyWhere.chatanywhere.exceptions;

public class AlreadyFriendException extends  Exception{
    private String creator;
    private String receiver;

    public AlreadyFriendException(String creator,String receiver)
    {
        super(String.format("%s and %s are already friends",creator,receiver));
        this.creator=creator;
        this.receiver=receiver;
    }
}
