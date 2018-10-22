package pinzen.utils.javalogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Library to simply manage logs in projects
 * 
 * @author Julien GASPAR LOPES
 *
 */
public class Logger {

	/** Log levels enumeration **/
	public enum LogLevel{
		/** Informations and debug messages **/
		INFO,
		/** Warning messages (not dangerous) **/
		WARN,
		/** 
		 * Messages for Errors that unable application to run
		 * (loading fail, communication error ...)
		 **/
		ERROR,
		/** 
		 * Messages for Fatal Errors making the application to crash **/
		FATAL,
		/** Level to indicate that messages shouldn't be logged **/
		SILENT;
	}
	
	private LogLevel logLevelConsole, logLevelFile;
	private String logFile;
	private boolean disabled;
	
	/**
	 * Create a Logger specifying console and file log levels
	 * @param levelConsole : minimum message level to log in console
	 * @param levelFile : minimum message level to log in file
	 * @param logFile : path to the log file (won't be overwrite)
	 */
	public Logger(LogLevel levelConsole, LogLevel levelFile, String logFile) {
		this.logLevelConsole = levelConsole;
		this.logLevelFile = levelFile;
		this.logFile = logFile;
		this.disabled = false;
	}
	
			/** ----- ----- Only Console Loggers ----- ----- **/
	
	/**
	 * Create Logger with only console output with specified logLevel
	 * @param levelConsole : minimum message level to log in console
	 */
	public Logger(LogLevel levelConsole) {
		this(levelConsole, LogLevel.SILENT, null);
	}
	
	/**
	 * Create default logger with only console output set to INFO level
	 */
	public Logger() {
		this(LogLevel.INFO, LogLevel.SILENT, null);
	}
	
	
			/** ----- ----- Only File Loggers ----- ----- **/
	
	/**
	 * Create Logger with only file output with specified logLevel
	 * @param levelFile : minimum message level to log in file
	 * @param logFile : path to the log file (won't be overwrite)
	 */
	public Logger(LogLevel levelFile, String logFile) {
		this(LogLevel.SILENT, levelFile, logFile);
	}
	
	/**
	 * Create Logger with only file output set to INFO level
	 * @param logFile : path to the log file (won't be overwrite)
	 */
	public Logger(String logFile) {
		this(LogLevel.SILENT, LogLevel.INFO, logFile);
	}
	
	
			/** ----- ----- Managing Functions ----- ----- **/
	
	/**
	 * Disable this logger (no log message will be output)
	 */
	public void disable() {
		this.disabled = true;
	}
	
	/**
	 * Enable this logger (log messages will be output)
	 * Logger is enabled by default
	 */
	public void enable() {
		this.disabled = false;
	}
	
	
			/** ----- ----- Log Functions ----- ----- **/
	
	/**
	 * Generate a log message with level INFO
	 * @param msg : message to log
	 */
	public void info(String msg) {
		String line = "[INFO]  " + new Date().toString() + " || ";
		this.logInConsole(line + msg, LogLevel.INFO);
		this.logInFile(line + msg, LogLevel.INFO);
	}
	
	/**
	 * Generate a log message with level WARN
	 * @param msg : message to log
	 */
	public void warn(String msg) {
		String line = "[WARN]  " + new Date().toString() + " || ";
		this.logInConsole(line + msg, LogLevel.WARN);
		this.logInFile(line + msg, LogLevel.WARN);
	}
	
	/**
	 * Generate a log message with level ERROR
	 * @param msg : message to log
	 */
	public void error(String msg) {
		String line = "[ERROR] " + new Date().toString() + " || ";
		this.logInConsole(line, LogLevel.ERROR);
		this.logInFile(line, LogLevel.ERROR);
	}
	
	/**
	 * Generate a log message with level FATAL
	 * @param msg : message to log
	 */
	public void fatal(String msg) {
		String line = "[FATAL] " + new Date().toString() + " || ";
		this.logInConsole(line + msg, LogLevel.FATAL);
		this.logInFile(line + msg, LogLevel.FATAL);
	}
	
	
			/** ----- ----- Private Functions ----- ----- **/
	
	
	private void logInConsole(String line, LogLevel lvl) {
		if(!disabled && lvl.ordinal() >= this.logLevelConsole.ordinal())
			System.out.println(line);
	}
	
	private void logInFile(String line, LogLevel lvl) {
		if(!disabled && lvl.ordinal() >= this.logLevelFile.ordinal()) {
			File f = new File(this.logFile);
			try {
				FileWriter fw = new FileWriter(f, true);
				fw.append(line + "\n");
				fw.close();
			} catch (IOException e) {
				String errLine = "[ERROR] " + new Date().toString() + " || ";
				String msg = "Can't write in log file <" + this.logFile + ">";
				System.out.println(errLine + msg);
			}
		}
	}
}
