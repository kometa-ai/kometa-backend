package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.kometa.kometabackend.constants.RoutesConstants;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.CHATS_ROUTE, produces = "application/json")
public class ChatController {
}
