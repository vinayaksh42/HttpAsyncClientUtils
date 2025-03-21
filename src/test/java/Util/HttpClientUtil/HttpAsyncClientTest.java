package Util.HttpClientUtil;

import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpAsyncClientTest {

    @Test
    void testHttpHostThrowsIllegalArgumentExceptionForEmptyHostAfterUpgrade() {
        String emptyHost = "";
        int port = 8080;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new HttpHost(emptyHost, port);
        });

        assertTrue(exception.getMessage().contains("may not be empty"),
                "Expected exception due to empty hostname after library upgrade");
    }

    @Test
    void testCreateAsyncClientThrowsExceptionForEmptyProxyHost() {
        HttpAsyncClient client = new HttpAsyncClient();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.createAsyncClient(true);
        });

        assertTrue(exception.getMessage().contains("may not be empty"),
                "Expected exception due to empty hostname after upgrading to httpcore-4.4.16");
    }
}
