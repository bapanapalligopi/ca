package ChatAnyWhere.chatanywhere.service;

import ChatAnyWhere.chatanywhere.config.Config;
import ChatAnyWhere.chatanywhere.exceptions.NotFriendsException;
import ChatAnyWhere.chatanywhere.models.Message;
import ChatAnyWhere.chatanywhere.models.User;
import ChatAnyWhere.chatanywhere.repository.MessageRepository;
import ChatAnyWhere.chatanywhere.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    private Logger logger= LoggerFactory.getLogger(MessageService.class);
//    public boolean send(String sender, String receiver, String content) throws Exception {
//        //check sender and receiver exists are not
//        if (userService.validUser(sender) & userService.validUser(receiver)) {
//            //if they valid users check friends are not
//            logger.info("they are valid users");
//            if (userService.areFriends(sender, receiver)) {
//                logger.info("they are friends");
//                //if they are friends get users from db by username
//                User senderUser = userRepository.findByUsername(sender);
//                User receiverUser = userRepository.findByUsername(receiver);
//                //build message
//                Message message = Message.builder()
//                        .message(Config.encrypt(content))
//                        .senderUsername(sender)
//                        .receiverUsername(receiver)
//                        .messageTxnId(UUID.randomUUID().toString())
//                        .sender(senderUser)
//                        .receiver(receiverUser)
//                        .build();
//                //store message
//                messageRepository.save(message);
//
//                return true;
//            } else {
//                //raise exception of not friends
//                logger.info("they are not friends");
//                throw new NotFriendsException(sender, receiver);
//            }
//
//        }
//        return false;
//    }
public boolean send(String sender, String receiver, String content) throws Exception {
    // Check if both sender and receiver exist
    if (userService.validUser(sender) && userService.validUser(receiver)) {
        logger.info("Both sender and receiver are valid users.");

        // Check if they are friends
        if (userService.areFriends(sender, receiver)) {
            logger.info("Sender and receiver are friends.");

            // Retrieve user objects from the database by username
            User senderUser = userRepository.findByUsername(sender);
            User receiverUser = userRepository.findByUsername(receiver);

            // Build message
            Message message = Message.builder()
                    .message(Config.encrypt(content))
                    .senderUsername(sender)
                    .receiverUsername(receiver)
                    .messageTxnId(UUID.randomUUID().toString())
                    .sender(senderUser)
                    .receiver(receiverUser)
                    .build();

            // Store message
            messageRepository.save(message);
            logger.info("Message sent successfully.");
            return true;
        } else {
            // If they are not friends, raise exception
            logger.info("Sender and receiver are not friends.");
            throw new NotFriendsException(sender, receiver);
        }
    } else {
        // If either sender or receiver does not exist, return false
        logger.info("Sender or receiver does not exist.");
        return false;
    }
}

}
