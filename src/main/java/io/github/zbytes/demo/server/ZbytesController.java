package io.github.zbytes.demo.server;

import io.github.zbytes.demo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ZbytesController {

    private static final Logger LOG = LoggerFactory.getLogger(ZbytesController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message msg) throws Exception {
        Thread.sleep(1000); // simulated delay
        LOG.info("Received : " + msg.getText() + " from : " + msg.getFrom());
        return msg;
    }
}
