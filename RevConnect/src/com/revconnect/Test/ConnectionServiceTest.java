package com.revconnect.Test;

import com.revconnect.model.Connection1;
import com.revconnect.service.ConnectionService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionServiceTest {

    ConnectionService connectionService = new ConnectionService();

    @Test
    void testSendRequest() {
        boolean result = connectionService.sendRequest(3, 4);
        assertTrue(result);
    }

    @Test
    void testAcceptRequest() {
        boolean result = connectionService.accept(1);   // real connection_id
        assertTrue(result);
    }

    @Test
    void testRejectRequest() {
        boolean result = connectionService.reject(2);   // real connection_id
        assertTrue(result);
    }

    @Test
    void testIncomingRequests() {
        List<Connection1> list = connectionService.incoming(3);
        assertNotNull(list);
    }

    @Test
    void testMyConnections() {
        List<Connection1> list = connectionService.myConnections(3);
        assertNotNull(list);
    }

    @Test
    void testSendRequestToSelf() {
        boolean result = connectionService.sendRequest(2, 2);
        assertFalse(result);
    }
}
