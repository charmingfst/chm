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
 * @author ���� 
 * @date 2015-3-28 ����11:33:55 
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
		//����JavaScriptִ��
        webView.getSettings().setJavaScriptEnabled(true);
        //�ҵ�Html�ļ���Ҳ�����������ϵ��ļ�
        webView.loadUrl("file:///android_asset/www/details.html");
        // ���һ������, ��JS���Է��ʸö���ķ���, �ö����п��Ե���JS�еķ���
        webView.addJavascriptInterface(new Contact(), "contact");
        //����ҳ���Ե���alert�Ի���
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

//        //JavaScript���ô˷�������绰
//        @JavascriptInterface
//        public void call(String phone) {
//            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
//        }
        
        //Html���ô˷�����������
        @JavascriptInterface
        public void showcontacts() {
            System.out.println("---------showcontact---------");
            final String json = "{\"cai_id\":"+cai_id+"}";
            // ����JS�еķ���
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
