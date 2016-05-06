package net.nainiubao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends Activity {

	private WebView mainWebView=null;
	private WebSettings mainWBSettings=null;
	
	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
       
        this.mainWebView=(WebView)this.findViewById(R.id.wbview);
        mainWBSettings=this.mainWebView.getSettings();
        mainWBSettings.setJavaScriptEnabled(true);
        mainWebView.addJavascriptInterface(new JsInterface(this.getApplicationContext()), "injectedObject");
//        mainWebView.addJavascriptInterface(new aaa(), "injectedObject");
        
        this.mainWebView.loadUrl("http://192.168.0.253:8080/yhfwk/mlogin.jsp");
//        this.mainWebView.loadUrl("http://192.168.1.107:8080/yhfwk/mlogin.jsp");
        this.mainWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
			}
        	
        });
        this.mainWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				
//				System.out.println("页面加载完成！");
//				mainWebView.loadUrl("javascript:say()");
			}

        });
        
    }

    //重写返回按键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() ==0){
//			if(this.mainWebView.canGoBack()){
//				this.mainWebView.goBack();
//			}else{
//				//无操作
//			}
			
			new AlertDialog.Builder(this)
			.setTitle("系统提示").setMessage("确认退出系统吗？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					android.os.Process.killProcess(android.os.Process.myPid());
					finish();
				}				
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		return new AlertDialog.Builder(WebViewActivity.this)
		.setMessage("是否退出程序?")
		.setTitle("用户退出")
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {
						dialog.dismiss();
						android.os.Process
								.killProcess(android.os.Process
										.myPid());
						finish();

					}
				})
		.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {
						dialog.dismiss();

					}
				}).create();
	}
    
    
    
}
