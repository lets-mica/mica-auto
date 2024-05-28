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

import com.google.auto.service.AutoService;
import com.google.devtools.ksp.processing.SymbolProcessor;
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment;
import com.google.devtools.ksp.processing.SymbolProcessorProvider;
import org.jetbrains.annotations.NotNull;

/**
 * AutoService SymbolProcessorProvider
 *
 * @author L.cm
 */
@AutoService(SymbolProcessorProvider.class)
public class AutoServiceSymbolProcessorProvider implements SymbolProcessorProvider {

	@NotNull
	@Override
	public SymbolProcessor create(@NotNull SymbolProcessorEnvironment environment) {
		return new AutoServiceSymbolProcessor(environment);
	}

}
