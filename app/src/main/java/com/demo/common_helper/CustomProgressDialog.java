package com.demo.common_helper;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.demo.R;


public class CustomProgressDialog extends AlertDialog {
public CustomProgressDialog(Context context) {
    super(context);
    getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
}

@Override
public void show() {
    super.show();
    setContentView(R.layout.custom_progress_dialog);
    setCancelable(false);
    ImageView animateView = findViewById(R.id.v1);
    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
    animateView.startAnimation(animation);
 }
}