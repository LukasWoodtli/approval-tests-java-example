package fancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fancy {
    private final static Logger logger = LoggerFactory.getLogger("approval_test");

    public String hello() {
        String msg = "Hello";
        logger.debug("Message is: {}", msg);
        return msg;
    }
    public String hello(String who) {
        String msg = String.format("%s, %s", hello(), who);
        logger.debug("Complex message is: {}", msg);
        return msg;
    }
}
