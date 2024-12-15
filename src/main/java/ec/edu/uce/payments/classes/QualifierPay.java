package ec.edu.uce.payments.classes;

import jakarta.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface QualifierPaymentMethod {
    String value() default "";
}
