package de.renespeck.swissknife.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.stanford.nlp.io.StringOutputStream;

public class Compress {
    public static final Logger LOG = LogManager.getLogger(Compress.class);

    public static String gunzipIt(Path zipPath) {
        return gunzipIt(zipPath.toAbsolutePath().toString());
    }

    public static String gunzipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        StringOutputStream sos = new StringOutputStream();
        try {
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(zipFile));
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                sos.write(buffer, 0, len);
            }

            gzis.close();
            sos.close();

        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        return sos.toString();
    }
}
