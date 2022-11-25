package net.dreamlu.mica.auto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * aot org.springframework.aot.hint.RuntimeHintsRegistrar
 *
 * @author L.cm
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
public @interface AotRuntimeHintsRegistrar {

}
