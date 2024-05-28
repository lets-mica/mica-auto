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

package net.dreamlu.mica.auto.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.devtools.ksp.processing.*;
import com.google.devtools.ksp.symbol.*;
import kotlin.Pair;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * ksp AutoService 处理器
 *
 * @author L.cm
 */
public class AutoServiceSymbolProcessor implements SymbolProcessor {
	private final SymbolProcessorEnvironment environment;
	private final CodeGenerator codeGenerator;
	private final KSPLogger logger;

	/**
	 * Maps the class names of service provider interfaces to the class names of the concrete classes
	 * which implement them plus their KSFile (for incremental processing).
	 * <p>
	 * For example,
	 * ```
	 * "com.google.apphosting.LocalRpcService" -> "com.google.apphosting.datastore.LocalDatastoreService"
	 * ```
	 */
	private final Multimap<String, Pair<String, KSFile>> providers = HashMultimap.create();

	private final boolean verify;
	private final boolean verbose;

	public AutoServiceSymbolProcessor(SymbolProcessorEnvironment environment) {
		this.environment = environment;
		this.codeGenerator = environment.getCodeGenerator();
		this.logger = environment.getLogger();
		this.verify = getOrDefault(environment, "autoserviceKsp.verify");
		this.verbose = getOrDefault(environment, "autoserviceKsp.verbose");
	}

	@NotNull
	@Override
	public List<KSAnnotated> process(@NotNull Resolver resolver) {
		KSName autoServiceKsName = resolver.getKSNameFromString(AutoServiceProcessor.AUTO_SERVICE_NAME);
		KSClassDeclaration classDeclaration = resolver.getClassDeclarationByName(autoServiceKsName);
		if (classDeclaration == null) {
			String message = "@AutoService type not found on the classpath, skipping processing.";
			if (verbose) {
				logger.warn(message, null);
			} else {
				logger.info(message, null);
			}
			return Collections.emptyList();
		}
		KSType autoServiceType = classDeclaration.asType(Collections.emptyList());
		Sequence<KSAnnotated> annotatedSequence = resolver.getSymbolsWithAnnotation(AutoServiceProcessor.AUTO_SERVICE_NAME, false);
		Iterator<KSAnnotated> it = annotatedSequence.iterator();
		if (it.hasNext()) {
			KSAnnotated ksAnnotated = it.next();
			if (ksAnnotated instanceof KSClassDeclaration) {
				System.out.println(ksAnnotated);
			}
			System.out.println(ksAnnotated);
		}
		return Collections.emptyList();
	}

	private void log(String message) {
		if (verbose) {
			logger.logging(message, null);
		}
	}

	private static boolean getOrDefault(SymbolProcessorEnvironment environment, String key) {
		Map<String, String> options = environment.getOptions();
		String value = options.get(key);
		return value == null || "false".equals(value);
	}

}
