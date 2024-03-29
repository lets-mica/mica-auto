package net.dreamlu.mica.auto.service;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.tools.StandardLocation;

import static com.google.testing.compile.CompilationSubject.assertThat;

/**
 * use google compile-testing Tests the {@link AutoServiceProcessor}.
 *
 * @author L.cm
 */
@RunWith(JUnit4.class)
public class AutoServiceProcessorTest {

	@Test
	public void autoService() {
		Compilation compilation =
			Compiler.javac()
				// 选择注解处理器
				.withProcessors(new AutoServiceProcessor())
				// 选择需要处理的代码
				.compile(JavaFileObjects.forResource("test/MyProcessor.java"));
		// 断言是否成功
		assertThat(compilation).succeededWithoutWarnings();
		// 断言生成的内容， compile-testing 默认是生成到了内存中
		assertThat(compilation)
			// 选择生成的配置文件，如果是生成 java 文件可以使用 generatedSourceFile 方法
			.generatedFile(StandardLocation.CLASS_OUTPUT, "META-INF/services/javax.annotation.processing.Processor")
			// 转换成 utf-8 字符串然后进行比较
			.contentsAsUtf8String()
			// 由于生成的文件尾部有个 writer.newLine() 在各个平台不一样，所以采用 startsWith
			.contains("test.MyProcessor");
	}

}
