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

package net.dreamlu.mica.auto.aot;

import com.google.auto.service.AutoService;
import net.dreamlu.mica.auto.annotation.AotBeanFactoryInitialization;
import net.dreamlu.mica.auto.annotation.AotBeanRegistration;
import net.dreamlu.mica.auto.annotation.AotRuntimeHintsRegistrar;
import net.dreamlu.mica.auto.common.AbstractMicaProcessor;
import net.dreamlu.mica.auto.common.AotAutoType;
import net.dreamlu.mica.auto.common.MultiSetMap;
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor;
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * spring boot 自动配置处理器
 *
 * @author L.cm
 */
@AutoService(Processor.class)
@SupportedOptions("debug")
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.AGGREGATING)
public class AotFactoriesProcessor extends AbstractMicaProcessor {
	/**
	 * The location to look for factories.
	 * <p>Can be present in multiple JAR files.
	 */
	private static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring/aot.factories";
	/**
	 * 数据承载
	 */
	private final MultiSetMap<String, String> factories = new MultiSetMap<>();

	/**
	 * 元素辅助类
	 */
	private Elements elementUtils;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		elementUtils = processingEnv.getElementUtils();
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Stream.of(
			AotRuntimeHintsRegistrar.class.getName(),
			AotBeanRegistration.class.getName(),
			AotBeanFactoryInitialization.class.getName()
		).collect(Collectors.toSet());
	}

	@Override
	protected boolean processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (roundEnv.processingOver()) {
			// 1. 生成 aot.factories
			generateFactoriesFiles();
		} else {
			processAnnotations(annotations, roundEnv);
		}
		return false;
	}

	private void processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 日志 打印信息 gradle build --debug
		log(annotations.toString());
		Set<? extends Element> elementSet = roundEnv.getRootElements();
		log("All Element set: " + elementSet.toString());

		// 过滤 TypeElement
		Set<TypeElement> typeElementSet = elementSet.stream()
			.filter(this::isClassOrInterface)
			.filter(TypeElement.class::isInstance)
			.map(TypeElement.class::cast)
			.collect(Collectors.toSet());
		// 如果为空直接跳出
		if (typeElementSet.isEmpty()) {
			log("Annotations elementSet is isEmpty");
			return;
		}
		for (TypeElement typeElement : typeElementSet) {
			// 1. 生成 aot.factories
			for (AotAutoType autoType : AotAutoType.values()) {
				String annotation = autoType.getAnnotation();
				if (isAnnotation(elementUtils, typeElement, annotation)) {
					log("Found @" + annotation + " Element: " + typeElement.toString());

					String factoryName = typeElement.getQualifiedName().toString();
					if (factories.containsVal(factoryName)) {
						continue;
					}

					log("读取到新配置 aot.factories factoryName：" + factoryName);
					factories.put(autoType.getConfigureKey(), factoryName);
				}
			}
		}
	}

	private void generateFactoriesFiles() {
		if (factories.isEmpty()) {
			return;
		}
		Filer filer = processingEnv.getFiler();
		try {
			// aot.factories 配置
			MultiSetMap<String, String> allFactories = new MultiSetMap<>();
			// 1. 用户手动配置项目下的 aot.factories 文件
			try {
				FileObject existingFactoriesFile = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", FACTORIES_RESOURCE_LOCATION);
				// 查找是否已经存在 aot.factories
				log("Looking for existing aot.factories file at " + existingFactoriesFile.toUri());
				MultiSetMap<String, String> existingFactories = FactoriesFiles.readFactoriesFile(existingFactoriesFile, elementUtils);
				log("Existing aot.factories entries: " + existingFactories);
				allFactories.putAll(existingFactories);
			} catch (IOException e) {
				log("aot.factories resource file not found.");
			}
			// 2. 增量编译，已经存在的 aot.factories 文件
			try {
				FileObject existingFactoriesFile = filer.getResource(StandardLocation.CLASS_OUTPUT, "", FACTORIES_RESOURCE_LOCATION);
				// 查找是否已经存在 aot.factories
				log("Looking for existing aot.factories file at " + existingFactoriesFile.toUri());
				MultiSetMap<String, String> existingFactories = FactoriesFiles.readFactoriesFile(existingFactoriesFile, elementUtils);
				log("Existing aot.factories entries: " + existingFactories);
				allFactories.putAll(existingFactories);
			} catch (IOException e) {
				log("aot.factories resource file did not already exist.");
			}
			// 3. 处理器扫描出来的新的配置
			allFactories.putAll(factories);
			log("New aot.factories file contents: " + allFactories);
			FileObject factoriesFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", FACTORIES_RESOURCE_LOCATION);
			try (OutputStream out = factoriesFile.openOutputStream()) {
				FactoriesFiles.writeFactoriesFile(allFactories, out);
			}
		} catch (IOException e) {
			fatalError(e);
		}
	}

}
