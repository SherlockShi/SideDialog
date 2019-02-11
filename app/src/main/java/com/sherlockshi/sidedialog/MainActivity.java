package com.sherlockshi.sidedialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    // 右侧弹出框
    private PopupWindow mPopupWindow;

    private ImageView ivClose;
    private Button btnShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowDialog = findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRightDialog();
            }
        });

        initRightDialog();
    }

    private void initRightDialog() {
        View popupWindowView = getLayoutInflater().inflate(R.layout.dialog_right, null);
        ColorDrawable dw = new ColorDrawable(0x00ffffff);
        ivClose = popupWindowView.findViewById(R.id.iv_close);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideRightDialog();
            }
        });

        mPopupWindow = new PopupWindow(popupWindowView, ScreenUtils.getScreenWidth() * 4 / 5, LinearLayout.LayoutParams.MATCH_PARENT, true);

        // 右侧弹出框
        mPopupWindow.setAnimationStyle(R.style.RightFade);
        // 左侧弹出框
//        mPopupWindow.setAnimationStyle(R.style.LeftFade);

        mPopupWindow.setBackgroundDrawable(dw);

        mPopupWindow.setOnDismissListener(new PopupDismissListener());
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void showRightDialog() {
        // 右侧弹出框
        mPopupWindow.showAtLocation(btnShowDialog, Gravity.RIGHT, 0, 500);
        // 左侧弹出框
//        mPopupWindow.showAtLocation(btnShowDialog, Gravity.LEFT, 0, 0);
        backgroundAlpha(0.5f);
    }

    private void hideRightDialog() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    private class PopupDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }
}
