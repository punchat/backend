package com.github.punchat.emails.events;

import com.github.punchat.emails.domain.emails.EmailMessage;
import com.github.punchat.emails.domain.emails.EmailService;
import com.github.punchat.events.NewEmailCreatedEvent;
import com.github.punchat.log.Trace;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Trace
@Component
@EnableBinding(Channels.class)
public class NewEmailsSubscriber {
    private final EmailService service;

    public NewEmailsSubscriber(EmailService service) {
        this.service = service;
    }

    @StreamListener(Channels.NEW_EMAILS_EVENTS)
    public void newEmail(NewEmailCreatedEvent event) {
        EmailMessage msg = new EmailMessage();
        msg.setEmail(event.getEmail());
        msg.setSubject(event.getSubject());
        msg.setText(event.getText());
        service.send(msg);
    }
}
