package my.json_receiver.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4J2 Properties
 */
public class Log4J2PropertiesConf {
    private static Logger logger = LogManager.getLogger();
    public void performSomeTask(){
        logger.debug("This is a debug message for test");
        logger.info("This is an info message for test");
        logger.warn("This is a warn message for test");
        logger.error("This is an error message for test");
        logger.fatal("This is a fatal message for test");
    }
}
