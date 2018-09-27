package com.test.firstjni;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editext;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉信息栏，设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
      //  editext = (EditText) findViewById(R.id.edtext);
        webView = (WebView) findViewById(R.id.webview);
        wanshareSetting();

        webView.loadUrl("http://47.75.101.221/wap/business?na=BCH_USDT");
/*        setEditTextInputSpace(editext);
        String trim = editext.getText().toString().trim();
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        String xx = sp.getString("xx", "");
        Log.e("mmmm", "onCreate: _____________________________换行了111111" );
        if (xx.contains("\r")||xx.contains("\r")){
            Log.e("mmmm", "onCreate: _____________________________换行了" );
        }
        char[] chars = {'\n'};
        editext.setText(xx);*/
    }

/*    @Override
    protected void onDestroy() {
        String trim = editext.getText().toString().trim();
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        SharedPreferences.Editor xx = edit.putString("xx", trim);
        xx.commit();
        super.onDestroy();

    }*/
    /**
     * 禁止EditText输入空格和换行符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.toString().contentEquals("\n")) {
                    return "**";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }


    public void wanshareSetting() {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView. getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setTextZoom(100);
        WebViewClient webClient = new WebViewClient() {//处理网页加载失败时
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //这里可以将异常信息也显示出来

//            super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        };
        webView.setWebViewClient(webClient);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        });
    }
}
