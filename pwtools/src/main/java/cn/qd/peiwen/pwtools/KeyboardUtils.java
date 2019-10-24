package cn.qd.peiwen.pwtools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by nick on 2017/8/30.
 */

public class KeyboardUtils {
    /**
     * 打开软键盘
     *
     * @param view
     */
    public static void showSoftInput(View view) {
        if (EmptyUtils.isEmpty(view)) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setVisibility(View.VISIBLE);
        view.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param view t输入框
     */
    public static void hideSoftInput(View view) {
        if (EmptyUtils.isEmpty(view)) {
            return;
        }
        if (!isSoftInputShow(view.getContext())) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 判断当前软键盘是否打开
     *
     * @param context
     * @return
     */
    public static boolean isSoftInputShow(Context context) {
        InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        return inputmanger.isActive();
    }

}
