package com.kebaiwei.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;

/** 
 * @author 陈明 
 * @date 2015-3-28 上午11:33:55 
 * @description TODO
 */
public class DetailsActivity extends Activity {
	
	private WebView webView;
	private int cai_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_kw);
		cai_id = getIntent().getIntExtra("cai_id", 0);
		webView = (WebView)findViewById(R.id.myweb);
		//允许JavaScript执行
        webView.getSettings().setJavaScriptEnabled(true);
        //找到Html文件，也可以用网络上的文件
        webView.loadUrl("file:///android_asset/www/details.html");
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        webView.addJavascriptInterface(new Contact(), "contact");
        //让网页可以弹出alert对话框
        webView.setWebChromeClient(new WebChromeClient() {});
		
        ImageButton back = (ImageButton)findViewById(R.id.goback);
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				startActivity(new Intent(DetailsActivity.this, MainActivity.class));
				DetailsActivity.this.finish();
			}
		});
		
	}
	private final class Contact {

//        //JavaScript调用此方法拨打电话
//        @JavascriptInterface
//        public void call(String phone) {
//            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
//        }
        
        //Html调用此方法传递数据
        @JavascriptInterface
        public void showcontacts() {
            System.out.println("---------showcontact---------");
            final String json = "{\"cai_id\":"+cai_id+"}";
            // 调用JS中的方法
            webView.post(new Runnable() {
                @Override
                public void run() {
//                    webView.loadUrl("javascript: alert(" + data +")");
                    webView.loadUrl("javascript:show(" + json + ")");
                }
            });
        }
	}
}
