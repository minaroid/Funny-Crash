/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class DialogBuilder {

    private final View view;
    private Dialog dialog;

    public DialogBuilder(Context context, @LayoutRes int layoutRes) {
        view = LayoutInflater.from(context).inflate(layoutRes, null);
        dialog = new Dialog(context);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.CENTER;
            window.setAttributes(attributes);
        }
        dialog.setContentView(view);
    }

    public DialogBuilder clickListener(@IdRes int viewId, @NonNull OnClickListener onClickListener) {
        View view = this.view.findViewById(viewId);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(v -> onClickListener.onClick(dialog, v));
        return this;
    }

    public DialogBuilder background(@DrawableRes int drawableRes) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.getAttributes();
            window.setBackgroundDrawableResource(drawableRes);
        }
        return this;
    }

    public DialogBuilder text(@IdRes int viewId, String text) {
        if (!TextUtils.isEmpty(text)) {
            View view = this.view.findViewById(viewId);
            view.setVisibility(View.VISIBLE);
            ((TextView) view).setText(text);
        } else {
            View view = this.view.findViewById(viewId);
            view.setVisibility(View.GONE);
        }
        return this;
    }

    public DialogBuilder gravity(int gravity) {
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = gravity;
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
        }
        return this;
    }

    public Dialog build() {
        return dialog;
    }

    public DialogBuilder cancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public DialogBuilder background(Drawable drawable) {
        Window window = dialog.getWindow();
        if (window != null) {
            dialog.getWindow().setBackgroundDrawable(drawable);
        }
        return this;
    }

    public interface OnClickListener {

        void onClick(Dialog dialog, View view);
    }

}
