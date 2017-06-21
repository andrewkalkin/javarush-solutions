package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by kalinnikov_al on 06.06.2017.
 */
public class SecurityProxyConnector implements Connector {
    SecurityChecker securityChecker;
    SimpleConnector simpleConnector;

    public SecurityProxyConnector(String connectionString) {
        simpleConnector = new SimpleConnector(connectionString);
        securityChecker = new SecurityCheckerImpl();
    }

    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck()) simpleConnector.connect();
    }
}
