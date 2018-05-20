package com.github.punchat.am;

import com.github.punchat.am.domain.invite.WorkspaceInvite;
import com.github.punchat.am.domain.invite.WorkspaceInviteController;
import com.github.punchat.am.domain.invite.WorkspaceInviteRepository;
import com.github.punchat.am.domain.invite.WorkspaceInviteService;
import com.github.punchat.am.domain.invite.properties.DefaultInviteProperties;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.dto.am.invite.WorkspaceInvitation;
import com.github.punchat.events.InviteToWorkspaceEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultInviteCreator implements CommandLineRunner {
    private final DefaultInviteProperties properties;
    private final WorkspaceInviteRepository repository;
    private final EventBus eventBus;

    public DefaultInviteCreator(DefaultInviteProperties properties,
                                WorkspaceInviteRepository repository,
                                EventBus eventBus) {
        this.properties = properties;
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Override
        public void run(String... args) {
            if (repository.count() == 0) {
                WorkspaceInvite defaultInvite = new WorkspaceInvite();
                defaultInvite.setId(0L);
                defaultInvite.setEmail(properties.getAdmin().getEmail());
                repository.save(defaultInvite);
                eventBus.publish(new InviteToWorkspaceEvent(
                        defaultInvite.getId(), defaultInvite.getEmail()));
            }
        }
}
