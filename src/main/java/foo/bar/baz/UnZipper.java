package foo.bar.baz;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class UnZipper {

	public void unzip(String zipFilePath, String unzipLocation)
			throws IOException {
		ZipFile zipFile = new ZipFile(zipFilePath);
		Enumeration<? extends ZipEntry> zipFileEntries = zipFile.entries();

		while (zipFileEntries.hasMoreElements()) {
			ZipEntry zipEntry = zipFileEntries.nextElement();

			if (!zipEntry.isDirectory()) {
				File file = new File(unzipLocation, zipEntry.getName());
				Files.createParentDirs(file);
				Files.write(ByteStreams.toByteArray(zipFile
						.getInputStream(zipEntry)), file);
			}
		}
	}

}
