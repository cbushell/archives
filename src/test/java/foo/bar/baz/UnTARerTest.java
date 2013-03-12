package foo.bar.baz;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;

public class UnTARerTest {

	private String tarFile;
	private String tempDir;

	@Before
	public void setup() throws IOException {
		File src = new File("src/test/java/foo/bar/baz/foo.tar");

		tempDir = Files.createTempDir().getAbsolutePath();
		File destination = new File(tempDir + "/" + "foo.tar");
		tarFile = destination.getAbsolutePath();

		Files.copy(src, destination);
	}

	@Test
	public void shouldUnTARFile() throws IOException {
		UnTARer unTARer = new UnTARer();
		unTARer.unTAR(tarFile, tempDir);

		assertTrue(new File(tempDir + "/" + "a.out").exists());
		assertTrue(new File(tempDir + "/" + "b.out").exists());
		assertTrue(new File(tempDir + "/" + "blah/blah/c.out").exists());
	}

}
