package ChatAnyWhere.chatanywhere.controller;

import ChatAnyWhere.chatanywhere.dto.CreateUserRequest;
import ChatAnyWhere.chatanywhere.dto.LoginRequest;
import ChatAnyWhere.chatanywhere.models.*;
import ChatAnyWhere.chatanywhere.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid CreateUserRequest createUserRequest) throws Exception
    {
        User user=createUserRequest.toUSer();
        return userService.create(user);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginnedUser> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception
    {
        return userService.login(loginRequest);
    }
    @GetMapping("/get/user")
    public ResponseEntity<WrappedUser> getUserByUsername(@RequestParam("username") String username ) throws Exception
    {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/addfriend")
    public ResponseEntity<String> addFriend(@RequestParam("creator") String  addFriendRequestCreator,@RequestParam("receiver") String addFriendRequestReceiver) throws Exception
    {
       return  userService.addFriend(addFriendRequestCreator,addFriendRequestReceiver);
    }

    @GetMapping("/myfriends")
    public ResponseEntity<List<Friend>> myFriends(@RequestParam("username") String username) throws Exception
    {
        return userService.myFriends(username);
    }

    @GetMapping("/validuser")
    public boolean validUser(@RequestParam("username") String username)
    {
        return userService.validUser(username);
    }

    @GetMapping("/friendsarenot")
    public boolean friendsAreNot(@RequestParam("user1") String user1 ,@RequestParam("user2") String user2)
    {
        return userService.areFriends(user1,user2);
    }

    @GetMapping("/messagesFromUser")
    public List<Message> getMyMessagesFromUser(@RequestParam("currentUser") String currentUser,@RequestParam("selectedUser") String selectedUser) throws Exception
    {
        return userService.getMyMessagesFromUser(currentUser,selectedUser);
    }
}
