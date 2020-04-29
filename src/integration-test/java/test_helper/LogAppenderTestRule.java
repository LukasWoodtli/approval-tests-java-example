package test_helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.rules.ExternalResource;

import java.io.CharArrayWriter;

/**
 * JUnit rule for testing log entries in Log4j2.
 * It sets up and tears down an Appender resource on a given Logger
 * for each test.
 */
public class LogAppenderTestRule extends ExternalResource {

    private static final String APPENDER_NAME = "log4j2UnitTestAppender";
    private static final String DEFAULT_PATTERN = "%logger %level %msg%n";

    private Logger logger;
    private Appender appender;
    private String pattern = DEFAULT_PATTERN;
    private final CharArrayWriter outContent = new CharArrayWriter();

    public LogAppenderTestRule(String loggerName) {
        logger = (org.apache.logging.log4j.core.Logger)LogManager.getLogger(loggerName);
        this.logger.setLevel(Level.ALL);
    }

    public LogAppenderTestRule(String loggerName, String loggerPattern) {
        this(loggerName);
        pattern = loggerPattern;
    }

    @Override
    protected void before() {
        StringLayout layout = PatternLayout.newBuilder().withPattern(pattern).build();
        appender = WriterAppender.newBuilder()
                .setTarget(outContent)
                .setLayout(layout)
                .setName(APPENDER_NAME).build();
        appender.start();
        logger.addAppender(appender);
    }

    @Override
    protected void after() {
        logger.removeAppender(appender);
    }

    public String getOutput() {
        return outContent.toString();
    }
}