package cn.qd.peiwen.pwtools.logger;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;

import java.io.IOException;

import de.mindpipe.android.logging.log4j.LogCatAppender;

public class PWConfigurator {
    private Level level = Level.ALL; // 根等级，初始比较等级
    private boolean internalDebugging = true; // 内部调试
    private boolean resetConfiguration = true; // 重新设置配置清单

    private String logCatPattern = "%m%n"; // 日志模式
    private boolean useLogcatAppender = true; // 使用Logcat输出器

    private int maxBackupSize = 5; // 最大备份数量
    private String fileName = "log.txt"; // 文件名字
    private boolean useFileAppender = true; // 使用文件输出器
    private boolean immediateFlush = true;  // 马上刷新
    private long maxFileSize = 5 * 1024 * 1024; // 最大文件大小
    private String filePattern = "%d{yyyy/MM/dd HH:mm:ss,SSS}:%m%n";  // 文件输出模式

    public PWConfigurator() {

    }

    public PWConfigurator level(Level level) {
        this.level = level;
        return this;
    }

    public PWConfigurator internalDebugging(boolean internalDebugging) {
        this.internalDebugging = internalDebugging;
        return this;
    }

    public PWConfigurator resetConfiguration(boolean resetConfiguration) {
        this.resetConfiguration = resetConfiguration;
        return this;
    }

    public PWConfigurator logCatPattern(String logCatPattern) {
        this.logCatPattern = logCatPattern;
        return this;
    }

    public PWConfigurator useLogcatAppender(boolean useLogcatAppender) {
        this.useLogcatAppender = useLogcatAppender;
        return this;
    }

    public PWConfigurator fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public PWConfigurator filePattern(String filePattern) {
        this.filePattern = filePattern;
        return this;
    }

    public PWConfigurator maxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    public PWConfigurator maxBackupSize(int maxBackupSize) {
        this.maxBackupSize = maxBackupSize;
        return this;
    }

    public PWConfigurator immediateFlush(boolean immediateFlush) {
        this.immediateFlush = immediateFlush;
        return this;
    }

    public PWConfigurator useFileAppender(boolean useFileAppender) {
        this.useFileAppender = useFileAppender;
        return this;
    }

    public void configure() {
        Logger root = Logger.getRootLogger(); // 获取跟日志级别

        if (this.resetConfiguration) {   // 如果重设，则执行重设命令
            LogManager.getLoggerRepository().resetConfiguration();
        }
        root.setLevel(this.level);
        LogLog.setInternalDebugging(this.internalDebugging);

        if (this.useFileAppender) {
            root.addAppender(this.configureFileAppender());
        }

        if (this.useLogcatAppender) {
            root.addAppender(this.configureLogCatAppender());
        }
    }

    /**
     * 配置文件输出器
     */
    private RollingFileAppender configureFileAppender() {
        try {
            Layout fileLayout = new PatternLayout(this.filePattern);
            RollingFileAppender rollingFileAppender = new RollingFileAppender(fileLayout, this.fileName);  // 规定文件输出模式和文件名字输出模式
            rollingFileAppender.setMaxBackupIndex(this.maxBackupSize);  // 设置最大备份索引
            rollingFileAppender.setMaximumFileSize(this.maxFileSize); // 设置最大文件大小
            rollingFileAppender.setImmediateFlush(this.immediateFlush); // 设置是否立即刷新
            return rollingFileAppender;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 配置类似LogCat的日志输出
     */
    private LogCatAppender configureLogCatAppender() {
        try {
            Layout logCatLayout = new PatternLayout(this.logCatPattern);
            return new LogCatAppender(logCatLayout);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
