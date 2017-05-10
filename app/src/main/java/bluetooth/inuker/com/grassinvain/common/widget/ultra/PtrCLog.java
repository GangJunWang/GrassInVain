package bluetooth.inuker.com.grassinvain.common.widget.ultra;


import com.shaojun.utils.log.Logger;

/**
 * An encapsulation of {@link Logger}, enable Logger level and print Logger with parameters.
 *
 * @author http://www.liaohuqiu.net/
 */
public class PtrCLog {

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;

    private static int sLevel = LEVEL_VERBOSE;

    /**
     * set Logger level, the level lower than this level will not be Loggerged
     *
     * @param level
     */
    public static void setLoggerLevel(int level) {
        sLevel = level;
    }

    /**
     * Send a VERBOSE Logger message.
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        Logger.v(tag, msg);
    }

    /**
     * Send a VERBOSE Logger message.
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void v(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        Logger.e(tag, throwable, msg);
    }

    /**
     * Send a VERBOSE Logger message.
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void v(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.v(tag, msg);
    }

    /**
     * Send a DEBUG Logger message
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        Logger.d(tag, msg);
    }

    /**
     * Send a DEBUG Logger message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void d(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.d(tag, msg);
    }

    /**
     * Send a DEBUG Logger message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void d(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        Logger.e(tag, throwable, msg);
    }

    /**
     * Send an INFO Logger message
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        Logger.i(tag, msg);
    }

    /**
     * Send an INFO Logger message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void i(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.i(tag, msg);
    }

    /**
     * Send an INFO Logger message
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        Logger.e(tag, throwable, msg);
    }

    /**
     * Send a WARNING Logger message
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        Logger.w(tag, msg);
    }

    /**
     * Send a WARNING Logger message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void w(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.w(tag, msg);
    }

    /**
     * Send a WARNING Logger message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void w(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        Logger.e(tag, throwable, msg);
    }

    /**
     * Send an ERROR Logger message
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        Logger.e(tag, msg);
    }

    /**
     * Send an ERROR Logger message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void e(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.e(tag, msg);
    }

    /**
     * Send an ERROR Logger message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void e(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        Logger.e(tag, throwable, msg);
    }

    /**
     * Send a FATAL ERROR Logger message
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        Logger.wtf(tag, msg);
    }

    /**
     * Send a FATAL ERROR Logger message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void f(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Logger.wtf(tag, msg);
    }

    /**
     * Send a FATAL ERROR Logger message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void f(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        Logger.wtf(tag, throwable, msg);
    }
}
