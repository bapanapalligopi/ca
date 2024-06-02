package ChatAnyWhere.chatanywhere.exceptions;

public class NotFriendsException extends Exception{
    private String user1;
    private String user2;

    public NotFriendsException(String user1,String user2)
    {
        super(String.format("%s and %s are not friends",user1,user2));
        this.user1=user1;
        this.user2=user2;
    }

}
