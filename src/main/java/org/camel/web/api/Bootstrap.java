package org.camel.web.api;

import org.apache.camel.spring.Main;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Bootstrap {

    private static final Logger LOGGER = getLogger(Bootstrap.class);

    private Main main;

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.boot();
    }

    public void boot() throws Exception {
        main = new Main();
        main.enableHangupSupport();
        main.setFileApplicationContextUri("classpath:application-context.xml");

        LOGGER.info("Camel is starting, Use ctr + c to finish.");

        main.run();
    }
}