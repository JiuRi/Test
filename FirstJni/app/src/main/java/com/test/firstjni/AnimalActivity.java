package com.test.firstjni;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalActivity extends AppCompatActivity {


    private LinearLayout concent1;
    private LinearLayout concent2;
    private LinearLayout concent3;
    private int a;
    //用于模仿 上面第一个view 的控件，用来做动画
    private CircleImageView mirrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annimal);
        concent1 = (LinearLayout) findViewById(R.id.concent1);
        concent2 = (LinearLayout) findViewById(R.id.concent2);
        concent3 = (LinearLayout) findViewById(R.id.concent3);
        a = DpPxUtil.dp2px(50);
    }

    public void dianJi(View view) {

        if (concent1.getChildCount() < 3) {
            CircleImageView circleImageView = new CircleImageView(this);
            circleImageView.setImageDrawable(getResources().getDrawable(R.mipmap.timg));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(a, a);//
            circleImageView.setLayoutParams(params);
            concent1.addView(circleImageView);
        } else {
            Log.e("dddd", "dianJi: ____________________数量超过3个了，需要把上面第一个移到下面去");
            final View childAt = concent1.getChildAt(0);
            Log.e("mmmm", "dianJi: _____________" + childAt.getLeft());
            test(concent3,childAt);
            TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                    -(childAt.getLeft()), 0, 0);
            translateAnimationX.setInterpolator(new LinearInterpolator()); //让动画已均匀的速度改变
            translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true); //执行完毕，利用视图setLayoutParams来重新定位

            TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                    0, a);
            translateAnimationY.setInterpolator(new AccelerateInterpolator());
            translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            AnimationSet set = new AnimationSet(false);
            set.setFillAfter(false);
            set.addAnimation(translateAnimationY);
            set.addAnimation(translateAnimationX);

            set.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    concent1.removeView(childAt);
                    CircleImageView circleImageView = new CircleImageView(AnimalActivity.this);
                    circleImageView.setImageDrawable(getResources().getDrawable(R.mipmap.timg));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(a, a);//两个400分别为添加图片的大小                imageView.setLayoutParams(params);
                    circleImageView.setLayoutParams(params);
                    concent1.addView(circleImageView);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    concent3.setVisibility(View.GONE);
                    concent2.addView(childAt, 0);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
            set.setFillAfter(true);
            set.setDuration(1000);

            mirrorView.clearAnimation();
            mirrorView.startAnimation(set);


        }

    }

    public void test(ViewGroup group,View childAt ) {
        concent3.setVisibility(View.VISIBLE);

        if (mirrorView == null) {
            mirrorView = new CircleImageView(group.getContext());
            mirrorView.setImageResource(R.mipmap.timg);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(a, a);
            params.setMargins(childAt.getLeft(), 0, 0, 0);
            mirrorView.setLayoutParams(params);
            concent3.addView(mirrorView);


        }
    }


}
