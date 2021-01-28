package com.example.demo.controller;

import com.example.demo.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    // Annotation for messages
    @GetMapping("/message") //http://localhost:8080/message
    Message send() {
        return new Message("Hello this is a first message");
    }

    // @RequestBody => annotation maps the HttpRequest body to a transfer or domain object,
    // enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
    @PostMapping("/message") //http://localhost:8080/message
    Message post(@RequestBody Message message) {
        return message;
    }
}

