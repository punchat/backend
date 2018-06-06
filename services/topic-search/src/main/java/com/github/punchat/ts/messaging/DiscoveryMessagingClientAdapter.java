package com.github.punchat.ts.messaging;

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;
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
    public BroadcastMessageResponse getBroadcastMessage(Long messageId) {
        return messagingClient.getBroadcastMessageById(messageId);
    }

    @Override
    public DirectMessageResponse getDirectMessage(Long messageId) {
        return messagingClient.getDirectMessageById(messageId);
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