package com.github.punchat.ts.messaging;

import com.github.punchat.ts.feign.DiscoveryMessagingClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class DiscoveryMessagingClientAdapter implements MessagingService {
    private final DiscoveryMessagingClient messagingClient;

    public DiscoveryMessagingClientAdapter(DiscoveryMessagingClient messagingClient) {
        this.messagingClient = messagingClient;
    }

    @Override
    public String getText(Long messageId) {
        return messagingClient.getBroadcastMessageById(messageId).getText();
    }
}
//@Service
//@Profile("!test")
//public class DiscoveryIdClientAdapter implements IdService {
//    private final DiscoveryIdClient idClient;
//
//    public DiscoveryIdClientAdapter(DiscoveryIdClient idClient) {
//        log.info("creating discovery id client adapter {}", idClient);
//        this.idClient = idClient;
//    }
//
//    @Override
//    public long next() {
//        return idClient.next();
//    }
//}