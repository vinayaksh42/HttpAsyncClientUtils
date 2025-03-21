package Util.HttpClientUtil;

import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpAsyncClientTest {

    @Test
    void testHttpHostThrowsIllegalArgumentExceptionForEmptyHostAfterUpgrade() {
        // Step 1: Setup inputs with an empty hostname
        String emptyHost = ""; // Empty string triggers the new validation
        int port = 8080;

        // Step 2: Expect an IllegalArgumentException to be thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new HttpHost(emptyHost, port);
        });

        // Step 3: Verify the exception message matches the expected new behavior
        assertTrue(exception.getMessage().contains("may not be empty"),
                "Expected exception due to empty hostname after library upgrade");
    }

    @Test
    void testCreateAsyncClientThrowsExceptionForEmptyProxyHost() {
        // Step 1: Create instance with default (empty) host and port 0
        HttpAsyncClient client = new HttpAsyncClient(); // host = ""

        // Step 2: Expect createAsyncClient(true) to throw due to invalid HttpHost
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            client.createAsyncClient(true);
        });

        // Step 3: Validate that the exception message corresponds to empty hostname
        assertTrue(exception.getMessage().contains("may not be empty"),
                "Expected exception due to empty hostname after upgrading to httpcore-4.4.16");
    }
}
