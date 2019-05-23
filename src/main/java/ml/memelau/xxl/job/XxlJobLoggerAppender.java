package ml.memelau.xxl.job;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.xxl.job.core.log.XxlJobFileAppender;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.SneakyThrows;
import org.joor.Reflect;

import java.util.Optional;


/**
 * XxlJobLoggerAppender
 *
 * @author meme
 * @since 2019-05-23 10:50
 */
public class XxlJobLoggerAppender extends AppenderBase<ILoggingEvent> {

    protected void append(ILoggingEvent eventObject) {
        Optional.ofNullable(XxlJobFileAppender.contextHolder.get())
                .ifPresent(__ -> doLog(eventObject));
    }

    @SneakyThrows
    private void doLog(ILoggingEvent event) {
        Reflect.on(XxlJobLogger.class)
               .call("logDetail",
                       event.getCallerData()[0],
                       String.format("[%s] [%s] %s", event.getLevel(), event.getLoggerName(), event.getFormattedMessage()));
    }

}
