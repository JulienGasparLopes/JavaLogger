# JavaLogger

Simple and Intuitive library to manage logs (Console &amp; File) in Java projects

## Why this library ?

This isn't a new library, others already exists comming with more features and community.
The only purpose of this JavaLogger is to offer a really easy to use logging system.
With just 1 line of code you can set up a Logger to output on Console and/or File.

## Features

* Logger creation with one line of code
* Multiple log levels to filter messages (INFO, WARN, ERROR, FATAL, SILENT)
* Enable/Disable a logger at any time (useful when working on dev mode)
* Display Timestamp and message level

## How to use it ?

To create a simple logger that will output anything on Console :
```java
Logger logger = new Logger();
```

Then use the functions to log messages :
```java
logger.info("This is an information message !"); //Will output [INFO]  Mon Oct 22 17:24:00 CEST 2018 || This is an information message !
logger.warn("This is an warning message !");     //Will output [WARN]  Mon Oct 22 17:24:00 CEST 2018 || This is a warning message !
logger.error("This is an error message !");      //Will output [ERROR] Mon Oct 22 17:24:00 CEST 2018 || This is an error message !
logger.fatal("This is an fatal message !");      //Will output [FATAL] Mon Oct 22 17:24:00 CEST 2018 || This is a fatal message !
```

You can specify a minimum log level to output :
```java
Logger logger = new Logger(Logger.ERROR); //info and warn messages won't be displayed
```

A good practice is to declare a public static Logger in one of your main classes so you can log messages everywhere !

## What about Files ?

If you want to output on a log File just create the logger as following :
```java
Logger logger = new Logger(Logger.WARN, "myLogFile.log");
```
Note that you can specify only log File path, the log level will be Logger.INFO by default

## More control

You can use the full constructor to specify Console & File log level and the log File path
You can use the log level `Logger.SILENT` to prevent Console or File outputs
```java
Logger logger = new Logger(Logger.WARN, Logger.ERROR, "myLogFile.log");//WARN, ERROR and FATAL on Console, ERROR and FATAL on File
```
You can disable/enable a logger at any time (a Logger is enable after creation by default) :
```java
logger.disable(); //Pretty self-explanatory
logger.enable();  //Same here ;)
```

## What's next ?

This library aims to be really simple and intuitive with only few functions.
But here are some features that will be added :
* Console output coloration depending on message level (using ANSI)
* Getters and Setters for log levels
* Enable/disable timestamp
* Display name of the class that generated the log message

## Contribution

Feel free to send me a message if you see any bug, or if you have any suggestion !
