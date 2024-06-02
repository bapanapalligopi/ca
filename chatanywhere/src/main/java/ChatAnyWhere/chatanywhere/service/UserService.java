package ChatAnyWhere.chatanywhere.service;

import ChatAnyWhere.chatanywhere.config.Config;
import ChatAnyWhere.chatanywhere.dto.CreateUserRequest;
import ChatAnyWhere.chatanywhere.dto.LoginRequest;
import ChatAnyWhere.chatanywhere.exceptions.*;
import ChatAnyWhere.chatanywhere.models.*;
import ChatAnyWhere.chatanywhere.repository.FriendRepository;
import ChatAnyWhere.chatanywhere.repository.MessageRepository;
import ChatAnyWhere.chatanywhere.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    MessageRepository messageRepository;

    private Logger logger= LoggerFactory.getLogger(UserService.class);
    public ResponseEntity<String> create(User user) throws Exception {
        try {
            userRepository.save(user);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new IncorrectUsernameException(user.getUsername());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // DataIntegrityViolationException is typically thrown for database constraint violations
            throw new UsernameAlreadyExsits("User", "username", user.getUsername());
        } catch (Exception e) {
            // Catching generic Exception should be used carefully; consider handling specific exceptions
            throw new Exception("An unexpected exception occurred: " + e.getMessage());
        }
        return new ResponseEntity<>("User created successfully", HttpStatus.OK);
    }

    public ResponseEntity<LoginnedUser> login(LoginRequest loginRequest) throws Exception
    {
        User dbLoginUser=userRepository.findByUsername(loginRequest.getUsername());
        if(dbLoginUser==null) throw  new UserNotFoundException("User","Username",loginRequest.getUsername());
        if(!dbLoginUser.getPassword().equals(Config.encrypt(loginRequest.getPassword())))
        {
            throw  new AuthenticationException();
        }
        LoginnedUser loginnedUser=LoginnedUser.builder().username(loginRequest.getUsername()).build();
        return new ResponseEntity<>(loginnedUser,HttpStatus.OK);
    }
    public ResponseEntity<WrappedUser> getUserByUsername(String username) throws  Exception
    {
        User user=userRepository.findByUsername(username);
        if(user==null) throw  new UserNotFoundException("User","username",username);
        WrappedUser wrappedUser=WrappedUser.toWrappedUser(user);
        return new ResponseEntity<>(wrappedUser,HttpStatus.OK);
    }

    public ResponseEntity<String> addFriend(String creator,String receiver) throws Exception
    {
        //check whether user found with username or not
        User createrUser=userRepository.findByUsername(creator);
        User receiverUser=userRepository.findByUsername(receiver);
        if(createrUser==null) throw  new UserNotFoundException("User","username",creator);
        if(receiverUser==null)throw  new UserNotFoundException("User","username",receiver);
        Friend receiveFriend= Friend.toFriend(receiver);
        Friend creatorFriend=Friend.toFriend(creator);
        //check already friends or not
        List<Friend> creatorFriends=createrUser.getFriendsList().stream().map((friend) -> {
            if (friend.getUsername().equals(receiver)) {
                return friend;
            } else {
                return null; // Or any other appropriate action
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        List<Friend> receiverFriends=receiverUser.getFriendsList().stream().map((friend -> {
            if(friend.getUsername().equals(creator))
            {
                return friend;
            }
            else{
                return  null;
            }
        })).filter(Objects::nonNull).collect(Collectors.toList());

        if(creatorFriends.size()>0 || receiverFriends.size()>0 )
        {
            throw  new AlreadyFriendException(creator,receiver);
        }


        //if not already friends update the both users friends lists
        //modify the friend list of creator

        receiveFriend.setUser(createrUser);
        friendRepository.save(receiveFriend);
        createrUser.getFriendsList().add(receiveFriend);

        //modify the receivers friend list

        creatorFriend.setUser(receiverUser);
        friendRepository.save(creatorFriend);
        receiverUser.getFriendsList().add(creatorFriend);

        return new ResponseEntity<>("successfully added as friend",HttpStatus.OK);
    }

    public  boolean validUser(String username)
    {
        return userRepository.existsByUsername(username);
    }
//    public boolean friendsAreNot(String user1 ,String user2)
//    {
//        List<Friend> dbUser1Friends=new ArrayList<>();
//        List<Friend> dbUser2Friends=new ArrayList<>();
//        //check they are valid users or not
//        if(validUser(user1) & validUser(user2))
//        {
//            logger.debug("valid users");
//            //if they are valid check there friends list
//            User dbuser1=userRepository.findByUsername(user1);
//            User dbuser2=userRepository.findByUsername(user2);
//            dbUser1Friends=dbuser1.getFriendsList().stream().map((friend) -> {
//                if (friend.getUsername().equals(user2)) {
//                    return friend;
//                } else {
//                    return null; // Or any other appropriate action
//                }
//            }).filter(Objects::nonNull).collect(Collectors.toList());
//            dbUser2Friends=dbuser2.getFriendsList().stream().map((friend) -> {
//                if (friend.getUsername().equals(user1)) {
//                    return friend;
//                } else {
//                    return null; // Or any other appropriate action
//                }
//            }).filter(Objects::nonNull).collect(Collectors.toList());
//            logger.info("dbuser1 friends:"+dbUser1Friends);
//            logger.info("dbuser1 friends:"+dbUser2Friends);
//            if(dbUser1Friends.size()>0 & dbUser2Friends.size()>0)
//            {
//                logger.info("dbuser1 friends:"+dbUser1Friends);
//                logger.info("dbuser1 friends:"+dbUser2Friends);
//                return true;
//            }
//            else{
//                logger.info("dbuser1 friends:"+dbUser1Friends);
//                logger.info("dbuser1 friends:"+dbUser2Friends);
//                return false;
//            }
//        }
//        else{
//            logger.info("dbuser1 friends:"+dbUser1Friends);
//            logger.info("dbuser1 friends:"+dbUser2Friends);
//            return  false;
//        }
//    }
public boolean areFriends(String user1, String user2) {
    // Check if both users are valid
    if (validUser(user1) && validUser(user2)) {
        // Retrieve User objects from the repository
        User dbUser1 = userRepository.findByUsername(user1);
        User dbUser2 = userRepository.findByUsername(user2);

        // Filter their friends lists to check if they are friends
        boolean user1IsFriendOfUser2 = dbUser2.getFriendsList()
                .stream()
                .anyMatch(friend -> friend.getUsername().equals(user1));

        boolean user2IsFriendOfUser1 = dbUser1.getFriendsList()
                .stream()
                .anyMatch(friend -> friend.getUsername().equals(user2));

        // Logging for debugging
        logger.info("Are {} and {} friends? {} / {}", user1, user2, user1IsFriendOfUser2, user2IsFriendOfUser1);

        // Return true if they are friends, false otherwise
        return user1IsFriendOfUser2 && user2IsFriendOfUser1;
    } else {
        // If any user is invalid, they cannot be friends
        return false;
    }
}

    public ResponseEntity<List<Friend>> myFriends(String username) throws Exception
    {
        //check user exists or not
        User user=userRepository.findByUsername(username);
        if(user==null) throw new UserNotFoundException("User","username",username);

        //get friends
        return new ResponseEntity<>(user.getFriendsList(),HttpStatus.OK);
    }


    public  List<Message> getMyMessagesFromUser(String currentUser, String selectedUser) throws Exception
    {
        //check they are friends are not
        if(areFriends(currentUser, selectedUser))
        {
            //they are friends
            //load messages
            User dbCurrentUser=userRepository.findByUsername(currentUser);
            User dbSelectedUser=userRepository.findByUsername(selectedUser);
            return messageRepository.findBySenderAndReceiver(dbCurrentUser,dbSelectedUser);
        }
        throw  new NotFriendsException(currentUser,selectedUser);
    }
}
