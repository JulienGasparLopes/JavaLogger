package pinzen.test.javalogger;

import pinzen.utils.javalogger.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		Logger logger1 = new Logger();
		logger1.info("First message");
		logger1.warn("Second message");
		
		Logger logger2 = new Logger(Logger.LogLevel.WARN, Logger.LogLevel.FATAL, "logFile.log");
		logger2.info("Shouldn't be printed");
		logger2.warn("Warn not in logFile");
		logger2.fatal("Fatal in console and logFile");
	}
}
