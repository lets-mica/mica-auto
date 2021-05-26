package test;

import net.dreamlu.mica.auto.annotation.AutoService;

import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
public class MyProcessor implements Processor {

	@Override
	public Set<String> getSupportedOptions() {
		return null;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return null;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return null;
	}

	@Override
	public void init(ProcessingEnvironment processingEnv) {

	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		return false;
	}

	@Override
	public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
		return null;
	}
}
