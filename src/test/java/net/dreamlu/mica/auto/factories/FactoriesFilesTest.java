package net.dreamlu.mica.auto.factories;

import net.dreamlu.mica.auto.common.MultiSetMap;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * FactoriesFilesTest 测试
 *
 * @author L.cm
 */
public class FactoriesFilesTest {

	@Test
	public void testRead() throws IOException {
		URL resource = FactoriesFilesTest.class.getResource("/test/spring.factories");
		InputStream inputStream = resource.openStream();
		MultiSetMap<String, String> data = FactoriesFiles.readFactoriesFile(inputStream);
		Assert.assertFalse(data.isEmpty());
	}

}
