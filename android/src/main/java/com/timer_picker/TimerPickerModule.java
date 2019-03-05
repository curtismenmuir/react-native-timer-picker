// TimerPickerModule.java

package com.timer_picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.NumberPicker;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;
import java.util.Arrays;
import java.lang.reflect.Field;
import android.widget.EditText;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public class TimerPickerModule extends ReactContextBaseJavaModule {

    private Context context;

    private static final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);

    private static final String[] oneInterval = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
            "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

    private static final String[] fiveInterval = { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50",
            "55" };

    private static final String[] tenInterval = { "00", "10", "20", "30", "40", "50" };

    public TimerPickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "TimerPickerModule";
    }

    private LinearLayout generateEmptyLayout() {
        LinearLayout emptyLayout = new LinearLayout(context);
        LinearLayout.LayoutParams emptyparams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        emptyparams.gravity = Gravity.CENTER_HORIZONTAL;
        emptyparams.weight = 1;
        emptyLayout.setLayoutParams(emptyparams);
        emptyLayout.setOrientation(LinearLayout.HORIZONTAL);
        return emptyLayout;
    }

    private void setDividerColor(NumberPicker numberPicker, int color) {
        try {
            Field mSelectionDivider = numberPicker.getClass().getDeclaredField("mSelectionDivider");
            mSelectionDivider.setAccessible(true);
            ColorDrawable colorDrawable = new ColorDrawable(color);
            mSelectionDivider.set(numberPicker, colorDrawable);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        } catch (IllegalArgumentException e) {
        }
    }

    public static void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        try {
            Field selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
            selectorWheelPaintField.setAccessible(true);
            ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        } catch (IllegalArgumentException e) {
        }

        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText)
                ((EditText) child).setTextColor(color);
        }
    }

    private LinearLayout generateSpinnerSeparator() {
        TextView textView = new TextView(context);
        textView.setText("   :   ");
        textView.setTextSize(25);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        LinearLayout tvLayout = new LinearLayout(context);
        tvLayout.setOrientation(LinearLayout.VERTICAL);
        tvLayout.setLayoutParams(layoutParams);
        tvLayout.addView(textView);
        return tvLayout;
    }

    private NumberPicker generateNumberPicker(String[] displayedValues, int dialogTheme, boolean loop) {
        int color = (dialogTheme == AlertDialog.THEME_HOLO_DARK || dialogTheme == AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                ? Color.WHITE
                : Color.BLACK;
        final NumberPicker numberPicker = new NumberPicker(getCurrentActivity());
        numberPicker.setDisplayedValues(displayedValues);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(displayedValues.length - 1);
        numberPicker.setWrapSelectorWheel(loop);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerTextColor(numberPicker, color);
        setDividerColor(numberPicker, color);
        return numberPicker;
    }

    private void updateDialogTitle(AlertDialog alert, int color) {
        int textViewId = alert.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        if (textViewId != 0) {
            TextView tv = (TextView) alert.findViewById(textViewId);
            tv.setTextColor(color);
        }
    }

    private void updateDialogTitleDivider(AlertDialog alert, int color) {
        int dividerId = alert.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        if (dividerId != 0) {
            View divider = alert.findViewById(dividerId);
            divider.setBackgroundColor(color);
        }
    }

    private String[] generateDisplayValues(int interval) {
        return (interval == 1) ? oneInterval : (interval == 2) ? fiveInterval : tenInterval;
    }

    private int generateDialogTheme(int theme) {
        return (theme == 1) ? AlertDialog.THEME_HOLO_DARK
                : (theme == 2) ? AlertDialog.THEME_DEVICE_DEFAULT_DARK
                        : (theme == 3) ? AlertDialog.THEME_HOLO_LIGHT : AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
    }

    private void updateDialogStyle(AlertDialog alert, int dialogTheme) {
        int color = (dialogTheme == AlertDialog.THEME_HOLO_DARK || dialogTheme == AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                ? Color.WHITE
                : Color.BLACK;
        if (dialogTheme == AlertDialog.THEME_HOLO_DARK || dialogTheme == AlertDialog.THEME_HOLO_LIGHT) {
            updateDialogTitleDivider(alert, color);
        }
        updateDialogTitle(alert, color);
    }

    @ReactMethod
    public void showNumberPicker(final ReadableMap options, final Callback onSuccess, final Callback onFailure) {
        final String title = options.hasKey("title") ? options.getString("title") : "Timer Selection Title";
        int picker1Interval = options.hasKey("picker1Interval") ? Integer.parseInt(options.getString("picker1Interval"))
                : 1;
        int picker2Interval = options.hasKey("picker2Interval") ? Integer.parseInt(options.getString("picker2Interval"))
                : 1;
        int theme = options.hasKey("theme") ? Integer.parseInt(options.getString("theme")) : 1;
        boolean loop = options.hasKey("loop") ? Boolean.parseBoolean(options.getString("loop")) : false;

        int dialogTheme = generateDialogTheme(theme);
        layoutParams.gravity = Gravity.CENTER;

        final String[] displayedMinuteValues = generateDisplayValues(picker1Interval);
        final NumberPicker picker = generateNumberPicker(displayedMinuteValues, dialogTheme, loop);
        if (options.hasKey("picker1Value")) {
            int index = 0;
            index = Arrays.asList(displayedMinuteValues).indexOf(options.getString("picker1Value"));
            picker.setValue(index);
        }

        LinearLayout spinnerSeparatorLayout = generateSpinnerSeparator();

        final String[] displayedSecondValues = generateDisplayValues(picker2Interval);
        final NumberPicker picker2 = generateNumberPicker(displayedSecondValues, dialogTheme, loop);
        if (options.hasKey("picker2Value")) {
            int index = 0;
            index = Arrays.asList(displayedSecondValues).indexOf(options.getString("picker2Value"));
            picker2.setValue(index);
        }

        LinearLayout emptyLayout = generateEmptyLayout();
        LinearLayout emptyLayout2 = generateEmptyLayout();

        LinearLayout mainContentLayout = new LinearLayout(context);
        mainContentLayout.setLayoutParams(layoutParams);
        mainContentLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainContentLayout.addView(emptyLayout);
        mainContentLayout.addView(picker);
        mainContentLayout.addView(spinnerSeparatorLayout);
        mainContentLayout.addView(picker2);
        mainContentLayout.addView(emptyLayout2);

        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity(), dialogTheme).setTitle(title)
                .setView(mainContentLayout).setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onSuccess.invoke(displayedMinuteValues[picker.getValue()] + ":"
                                + displayedSecondValues[picker2.getValue()]);
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onSuccess.invoke(-1);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        updateDialogStyle(alert, dialogTheme);
    }
}