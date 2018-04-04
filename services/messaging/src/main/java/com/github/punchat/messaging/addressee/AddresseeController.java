package com.github.punchat.messaging.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AddresseeController {
    @Autowired
    private AddresseeService addresseeService;
}
