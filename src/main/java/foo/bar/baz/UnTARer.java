package foo.bar.baz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class UnTARer {

	public void unTAR(String tarFile, String untarLocation) throws IOException {
		InputStream fileInputStream = new FileInputStream(tarFile);
		TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(
				fileInputStream);

		TarArchiveEntry tarEntry;

		while ((tarEntry = tarArchiveInputStream.getNextTarEntry()) != null) {
			if (!tarEntry.isDirectory()) {
				File file = new File(untarLocation, tarEntry.getName());
				Files.createParentDirs(file);
				Files.write(ByteStreams.toByteArray(tarArchiveInputStream),
						file);
			}
		}
	}

}
