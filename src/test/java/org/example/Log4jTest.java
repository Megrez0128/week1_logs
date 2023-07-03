package org.example;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

    //创建日志对象 Logger
    static final Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void testLog4j(){

        //显示警告级别以上的信息
//        logger.setLevel(Level.WARN);
        // debug level
        logger.debug("DEBUG(调试)");
        // info level
        logger.info("INFO(消息)");
        // warning level
        logger.warn("WARN(警告)");
        // error level
        logger.error("ERROR(错误)");
    }

}