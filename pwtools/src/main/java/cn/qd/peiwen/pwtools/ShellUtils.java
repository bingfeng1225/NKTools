package cn.qd.peiwen.pwtools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author DasonYu
 * @date 2020/6/19  17:29
 * @descprition
 */
public class ShellUtils {
    private static final String COMMAND_SU = "su";
    private static final String COMMAND_SH = "sh";
    private static final String COMMAND_EXIT = "exit\n";
    private static final String COMMAND_LINE_END = "\n";

    private ShellUtils() {
        throw new AssertionError();
    }


    /**
     * 查看是否有了root权限
     *
     * @return
     */
    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param command command
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String command) {
        return execCommand(new String[]{command}, false, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command array
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String[] commands) {
        return execCommand(commands, false, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command list
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(List<String> commands) {
        return execCommand(commands == null ? null : commands.toArray(new String[]{}), false, true);
    }


    /**
     * 执行shell命令，默认返回结果
     *
     * @param command command
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execRootCommand(String command) {
        return execCommand(new String[]{command}, true, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command array
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execRootCommand(String[] commands) {
        return execCommand(commands, true, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command list
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execRootCommand(List<String> commands) {
        return execCommand(commands == null ? null : commands.toArray(new String[]{}), true, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param command command
     * @param root    运行是否需要root权限
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String command, boolean root) {
        return execCommand(new String[]{command}, root, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command array
     * @param root     运行是否需要root权限
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String[] commands, boolean root) {
        return execCommand(commands, root, true);
    }

    /**
     * 执行shell命令，默认返回结果
     *
     * @param commands command list
     * @param root     运行是否需要root权限
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(List<String> commands, boolean root) {
        return execCommand(commands == null ? null : commands.toArray(new String[]{}), root, true);
    }

    /**
     * execute shell command
     *
     * @param command    command
     * @param root       运行是否需要root权限
     * @param needResult whether need result msg
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String command, boolean root, boolean needResult) {
        return execCommand(new String[]{command}, root, needResult);
    }


    /**
     * execute shell commands
     *
     * @param commands   command list
     * @param root       运行是否需要root权限
     * @param needResult 是否需要返回运行结果
     * @return
     * @see ShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(List<String> commands, boolean root, boolean needResult) {
        return execCommand(commands == null ? null : commands.toArray(new String[]{}), root, needResult);
    }


    /**
     * execute shell commands
     *
     * @param commands   command array
     * @param root       运行是否需要root权限
     * @param needResult 是否需要返回运行结果
     * @return <ul>
     * <li>if isNeedResultMsg is false, {@link CommandResult#success}
     * is null and {@link CommandResult#error} is null.</li>
     * <li>if {@link CommandResult#result} is -1, there maybe some
     * excepiton.</li>
     * </ul>
     */
    public static CommandResult execCommand(String[] commands, boolean root, boolean needResult) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }

        Process process = null;

        StringBuilder errorMsg = null;
        StringBuilder successMsg = null;

        BufferedReader errorResult = null;
        BufferedReader successResult = null;

        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(root ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                // donnot use os.writeBytes(commmand), avoid chinese charset error
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();
            result = process.waitFor();
            // get command result
            if (needResult) {
                errorMsg = new StringBuilder();
                successMsg = new StringBuilder();
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(result, errorMsg == null ? null : errorMsg.toString(), successMsg == null ? null : successMsg.toString());
    }

    public static class CommandResult {
        /**
         * 运行结果
         **/
        public int result;
        /**
         * 运行失败结果
         **/
        public String error;
        /**
         * 运行成功结果
         **/
        public String success;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String error, String success) {
            this.error = error;
            this.result = result;
            this.success = success;
        }
    }
}
