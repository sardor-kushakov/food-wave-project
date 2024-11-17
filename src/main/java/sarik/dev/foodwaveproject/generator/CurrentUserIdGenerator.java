package sarik.dev.foodwaveproject.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import sarik.dev.foodwaveproject.annotation.CurrentUserId;
import sarik.dev.foodwaveproject.configuration.security.UserSession;

import java.util.EnumSet;

public class CurrentUserIdGenerator implements BeforeExecutionGenerator {
    private final EnumSet<EventType> eventTypes;

    public CurrentUserIdGenerator(CurrentUserId annotation) {
        EventType[] events = annotation.event();
        if (events.length == 2) eventTypes = EnumSet.of(events[0], events[1]);
        else eventTypes = EnumSet.of(events[0]);
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return eventTypes;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return UserSession.requireUserId();
    }
}