package net.dreamlu.mica.auto.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.auto.annotation.AotBeanFactoryInitialization;
import net.dreamlu.mica.auto.annotation.AotBeanRegistration;
import net.dreamlu.mica.auto.annotation.AotRuntimeHintsRegistrar;

/**
 * aot
 *
 * @author L.cm
 */
@Getter
@RequiredArgsConstructor
public enum AotAutoType {

	/**
	 * RuntimeHintsRegistrar 添加到 aot.factories
	 */
	RUNTIME_HINTS_REGISTRAR(AotRuntimeHintsRegistrar.class.getName(), "org.springframework.aot.hint.RuntimeHintsRegistrar"),
	/**
	 * BeanRegistrationAotProcessor 添加到 aot.factories
	 */
	BEAN_REGISTRATION(AotBeanRegistration.class.getName(), "org.springframework.beans.factory.aot.BeanRegistrationAotProcessor"),
	/**
	 * BeanFactoryInitializationAotProcessor 添加到 aot.factories
	 */
	BEAN_FACTORY_INITIALIZATION(AotBeanFactoryInitialization.class.getName(), "org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor");

	private final String annotation;
	private final String configureKey;

}
