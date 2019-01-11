/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dreamlu.mica.auto.factories;

import com.google.auto.service.AutoService;
import net.dreamlu.mica.auto.common.AbstractMicaProcessor;
import net.dreamlu.mica.auto.common.MultiSetMap;
import net.dreamlu.mica.auto.common.Sets;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * spring boot 自动配置处理器
 *
 * @author L.cm
 */
@AutoService(Processor.class)
@SupportedOptions("debug")
public class AutoFactoriesProcessor extends AbstractMicaProcessor {

	/**
	 * 处理的注解
	 */
	private static final String CONFIGURE_ANNOTATION = "org.springframework.context.annotation.Configuration";
	/**
	 * spring boot 自动配置注解
	 */
	private static final String AUTO_CONFIGURE_KEY = "org.springframework.boot.autoconfigure.EnableAutoConfiguration";
	/**
	 * The location to look for factories.
	 * <p>Can be present in multiple JAR files.
	 */
	private static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
	/**
	 * devtools，有 Configuration 注解的 jar 一般需要 devtools 配置文件，目前不支持
	 */
	private static final String DEVTOOLS_RESOURCE_LOCATION = "META-INF/spring-devtools.properties";
	/**
	 * 数据承载
	 */
	private MultiSetMap<String, String> factories = new MultiSetMap<>();

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Sets.ofImmutableSet(CONFIGURE_ANNOTATION);
	}

	@Override
	protected boolean processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (roundEnv.processingOver()) {
			generateFactoriesFiles();
		} else {
			processAnnotations(annotations, roundEnv);
		}
		return true;
	}

	private void processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		Set<? extends Element> elements = roundEnv.getRootElements();
		// 日志 打印信息 gradlew build --info
		log(annotations.toString());
		log(elements.toString());

		for (Element type : elements) {
			if (!(type instanceof TypeElement)) {
				continue;
			}
			if (!isAnnotatedWithConfig(type)) {
				continue;
			}
			TypeElement providerImplementer = (TypeElement) type;
			String factoryName = providerImplementer.getQualifiedName().toString();
			if (factories.containsVal(factoryName)) {
				continue;
			}
			log("读取到新配置 spring.factories factoryName：" + factoryName);
			factories.put(AUTO_CONFIGURE_KEY,  factoryName);
		}
	}

	private void generateFactoriesFiles() {
		Filer filer = processingEnv.getFiler();
		try {
			// 1. spring.factories
			FileObject factoriesFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", FACTORIES_RESOURCE_LOCATION);
			FactoriesFiles.writeFactoriesFile(factories, factoriesFile.openOutputStream());
			String classesPath = factoriesFile.toUri().toString().split("classes")[0];
			Path projectPath = Paths.get(new URI(classesPath)).getParent();
			// 2. devtools 配置，因为有 @Configuration 注解的需要 devtools
			String projectName = projectPath.getFileName().toString();
			FileObject devToolsFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", DEVTOOLS_RESOURCE_LOCATION);
			FactoriesFiles.writeDevToolsFile(projectName, devToolsFile.openOutputStream());
		} catch (IOException | URISyntaxException e) {
			fatalError(e);
		}
	}

	private boolean isAnnotatedWithConfig(Element type) {
		for (AnnotationMirror annotation : type.getAnnotationMirrors()) {
			if (isConfigAnnotation(annotation)) {
				return true;
			}
		}
		return false;
	}

	private boolean isConfigAnnotation(AnnotationMirror annotation) {
		return CONFIGURE_ANNOTATION.equals(annotation.getAnnotationType().toString());
	}
}
