package de.renespeck.swissknife.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Compress {
    public static final Logger LOG = LogManager.getLogger(Compress.class);

    public static String gunzipIt(Path zipPath) {
        return gunzipIt(zipPath.toAbsolutePath().toString());
    }

    public static String gunzipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(zipFile));
            int len;
            while ((len = gzis.read(buffer)) > 0)
                baos.write(buffer, 0, len);
            gzis.close();
            baos.close();
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        return baos.toString();
    }
}
