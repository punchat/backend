package com.github.punchat.messaging.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddresseeService {
    @Autowired
    private AddresseeRepository addresseeRepository;
}
