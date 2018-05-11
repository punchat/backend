package com.github.punchat.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import java.time.LocalDateTime;

=======
>>>>>>> 5713cd9d48079c61901a6d40766473f021cb2e23
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberInChannelEvent {
    private long userId;
    private long recipientUserId;
    private long channelId;
    private long invitationId;
}
