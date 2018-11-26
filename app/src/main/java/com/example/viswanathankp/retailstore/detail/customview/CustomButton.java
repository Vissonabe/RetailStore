package com.example.viswanathankp.retailstore.detail.customview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.Button;

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior.class)
public class CustomButton extends android.support.v7.widget.AppCompatButton {
  public CustomButton(Context context) {
    super(context);
  }

  public CustomButton(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
}
