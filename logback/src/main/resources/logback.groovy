//
// Built on Wed Aug 06 05:06:17 CEST 2014 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.WARN

def default_pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def default_maxFileSize = "10MB"

scan("3 seconds")
//statusListener(OnConsoleStatusListener)
appender("STDOUT", ConsoleAppender) {
	encoder(PatternLayoutEncoder) { pattern = default_pattern }
}
appender("FILE", RollingFileAppender) {
	file = "logFile.log"
	rollingPolicy(FixedWindowRollingPolicy) {
		fileNamePattern = "logFile.%i.log.zip"
		minIndex = 1
		maxIndex = 10
	}
	triggeringPolicy(SizeBasedTriggeringPolicy) { maxFileSize = default_maxFileSize }
	encoder(PatternLayoutEncoder) { pattern = default_pattern }
}
root(WARN, ["STDOUT", "FILE"])