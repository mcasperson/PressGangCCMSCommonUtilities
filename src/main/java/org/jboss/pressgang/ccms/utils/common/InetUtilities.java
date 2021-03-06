package org.jboss.pressgang.ccms.utils.common;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InetUtilities {
    private static final Logger LOG = LoggerFactory.getLogger(InetUtilities.class);

    /**
     * Downloads a file as a byte array
     *
     * @param url The URL of the resource to download
     * @return The byte array containing the data of the downloaded file
     */
    public static byte[] getURLData(final String url) {
        final int readBytes = 1000;

        URL u;
        InputStream is = null;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        // See http://www.exampledepot.com/egs/javax.net.ssl/TrustAll.html

        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        // FIXME from Lee: This doesn't seem like a wise idea to install an all-trusting cert manager by default
        try {
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (final Exception ex) {
            LOG.error("Unable to install the all-trust SSL Manager", ex);
        }

        try {
            u = new URL(url);
            is = u.openStream(); // throws an IOException

            int nRead;
            byte[] data = new byte[readBytes];

            while ((nRead = is.read(data, 0, readBytes)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (final Exception ex) {
            LOG.error("Unable to read data from URL", ex);
        } finally {
            try {
                buffer.flush();

                if (is != null) is.close();
            } catch (final Exception ex) {
                LOG.error("Unable to flush and close URL Input Stream", ex);
            }
        }

        return buffer.toByteArray();
    }
}
