package ch.fhnw.GenZ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogController {

    //create logger
    public Logger newlog = LoggerFactory.getLogger(LogController.class);

}
