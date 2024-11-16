package sarik.dev.foodwaveproject.annotation;

import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.generator.EventType;
import sarik.dev.foodwaveproject.generator.CurrentUserIdGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValueGenerationType(generatedBy = CurrentUserIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CurrentUserId {
    EventType[] event() default {EventType.INSERT, EventType.UPDATE};
}
