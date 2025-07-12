import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Demo {
    private static final Logger logger = LoggerFactory.getLogger(Log4j2Demo.class);

    public static void main(String[] args) {
        logger.info("这是 info 日志");
        logger.debug("这是 debug 日志（默认不会显示）");
        logger.error("这是 error 日志");
    }
}
