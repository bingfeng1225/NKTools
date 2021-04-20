package cn.qd.peiwen.pwtools;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
        hideSoftInput(view, false);
    }

    public static void hideSoftInput(View view, boolean clearFocus) {
        if (EmptyUtils.isEmpty(view)) {
            return;
        }
        if (clearFocus) {
            view.clearFocus();
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

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击Et时则不能隐藏
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getRawX() > left && event.getRawX() < right && event.getRawY() > top && event.getRawY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
