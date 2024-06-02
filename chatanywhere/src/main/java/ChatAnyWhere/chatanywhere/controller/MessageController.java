package ChatAnyWhere.chatanywhere.controller;

import ChatAnyWhere.chatanywhere.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://bapanapalligopi.github.io")

public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("/send")
    private boolean send(@RequestParam("sender") String sender,@RequestParam("receiver") String receiver,@RequestParam("message") String content) throws Exception
    {
        return messageService.send(sender,receiver,content);
    }
}
