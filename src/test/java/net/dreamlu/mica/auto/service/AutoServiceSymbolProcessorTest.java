package net.dreamlu.mica.auto.service;

import com.tschuchort.compiletesting.CompilationResult;
import com.tschuchort.compiletesting.KotlinCompilation;
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode;
import com.tschuchort.compiletesting.KspKt;
import com.tschuchort.compiletesting.SourceFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

@RunWith(Parameterized.class)
public class AutoServiceSymbolProcessorTest {
	private final boolean incremental;

	public AutoServiceSymbolProcessorTest(boolean incremental) {
		this.incremental = incremental;
	}

	@Parameters(name = "incremental={0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{{false}, {true}});
	}

	@Test
	public void smokeTest() {
		SourceFile source = SourceFile.Companion.kotlin("CustomCallable.kt",
			"package test;\n" +
				"import net.dreamlu.mica.auto.annotation.AutoService;\n" +
				"import java.util.concurrent.Callable;\n" +
				"\n" +
				"@AutoService(Callable.class)\n" +
				"class CustomCallable : Callable<String> {\n" +
				"    override fun call(): String = \"Hello world!\"\n" +
				"}", true);

		KotlinCompilation compilation = new KotlinCompilation();
		compilation.setSources(Collections.singletonList(source));
		compilation.setInheritClassPath(true);
		KspKt.setSymbolProcessorProviders(compilation, Collections.singletonList(new AutoServiceSymbolProcessorProvider()));
		KspKt.setKspIncremental(compilation, incremental);

		CompilationResult result = compilation.compile();
		String messages = result.getMessages();
		System.out.println(messages);

		assertThat(result.getExitCode()).isEqualTo(ExitCode.OK);
		File generatedSourcesDir = KspKt.getKspSourcesDir(compilation);
		File generatedFile = new File(generatedSourcesDir, "resources/META-INF/services/java.util.concurrent.Callable");
		assertThat(generatedFile.exists()).isTrue();
	}

	@Test
	public void smokeTestForJava() {
		SourceFile source = SourceFile.Companion.java("CustomCallable.java",
			"package test;\n" +
				"import net.dreamlu.mica.auto.annotation.AutoService;\n" +
				"import java.util.concurrent.Callable;\n" +
				"\n" +
				"@AutoService(Callable.class)\n" +
				"public class CustomCallable implements Callable<String> {\n" +
				"    @Override public String call() { return \"Hello world!\"; }\n" +
				"}", false);

		KotlinCompilation compilation = new KotlinCompilation();
		compilation.setSources(Collections.singletonList(source));
		compilation.setInheritClassPath(true);
		KspKt.setSymbolProcessorProviders(compilation, Collections.singletonList(new AutoServiceSymbolProcessorProvider()));
		KspKt.setKspIncremental(compilation, incremental);

		CompilationResult result = compilation.compile();
		assertThat(result.getExitCode()).isEqualTo(ExitCode.OK);

		File generatedSourcesDir = KspKt.getKspSourcesDir(compilation);
		File generatedFile = new File(generatedSourcesDir, "resources/META-INF/services/java.util.concurrent.Callable");
		assertThat(generatedFile.exists()).isTrue();
	}

	@Test
	public void errorOnNoServiceInterfacesProvided() {
		SourceFile source = SourceFile.Companion.kotlin("CustomCallable.kt",
			"package test;\n" +
				"import net.dreamlu.mica.auto.annotation.AutoService;\n" +
				"import java.util.concurrent.Callable;\n" +
				"\n" +
				"@AutoService\n" +
				"class CustomCallable : Callable<String> {\n" +
				"    override fun call(): String = \"Hello world!\"\n" +
				"}", false);

		KotlinCompilation compilation = new KotlinCompilation();
		compilation.setSources(Collections.singletonList(source));
		compilation.setInheritClassPath(true);
		KspKt.setSymbolProcessorProviders(compilation, Collections.singletonList(new AutoServiceSymbolProcessorProvider()));
		KspKt.setKspIncremental(compilation, incremental);

		CompilationResult result = compilation.compile();
		assertThat(result.getExitCode()).isEqualTo(ExitCode.COMPILATION_ERROR);

		assertThat(result.getMessages())
			.contains("No service interfaces specified by @AutoService annotation!\n" +
				"You can provide them in annotation parameters: @AutoService(YourService::class)");
	}
}
