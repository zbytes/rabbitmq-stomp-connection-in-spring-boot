package io.github.zbytes.demo.server;

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
    public ZbytesMessage greeting(ZbytesMessage msg) throws Exception {
        Thread.sleep(1000); // simulated delay
        LOG.info("Received : {} from: {} ", msg.getText(), msg.getFrom());
        return msg;
    }
}
