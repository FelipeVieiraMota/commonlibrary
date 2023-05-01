package org.commonlibrary.custom;

import org.apache.logging.log4j.Logger;

public class CustomLogger
{
    private synchronized static String getCompleteLog(Object uniqueKey, String message)
    {
        Throwable processDetails = new Throwable();

        String  className   = processDetails.getStackTrace()[2].getClassName();
        String  methodName  = processDetails.getStackTrace()[2].getMethodName();
        int     lineNumber  = processDetails.getStackTrace()[2].getLineNumber();

        return messageFormatter
                (
                        className,
                        methodName,
                        lineNumber,
                        uniqueKey,
                        message
                )
                .toString();
    }

    private synchronized static StringBuilder messageFormatter
            (
                    String  className,
                    String methodName,
                    int lineNumber,
                    Object uniqueKey,
                    String message
            ){
        StringBuilder completeLog = new StringBuilder();
        completeLog
                .append(className)
                .append(".")
                .append(methodName)
                .append("(")
                .append(lineNumber)
                .append(")")
                .append(" [")
                .append(uniqueKey)
                .append("] ")
                .append(": ")
                .append(message);

        return completeLog;
    }

    public synchronized static void error
            (
                    Logger logger,
                    Object uniqueKey,
                    String message
            ) {
        String completeLog = getCompleteLog(uniqueKey, message);
        logger.error(completeLog);
    }

    public synchronized static void info
            (
                    Logger logger,
                    Object uniqueKey,
                    String message
            ) {
        String completeLog = getCompleteLog(uniqueKey, message);
        logger.info(completeLog);
    }

    public synchronized static void debug
            (
                    Logger logger,
                    Object uniqueKey,
                    String message
            ) {
        String completeLog = getCompleteLog(uniqueKey, message);
        logger.debug(completeLog);
    }
}
