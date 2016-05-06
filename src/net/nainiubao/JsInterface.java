package net.nainiubao;

import net.nainiubao.service.AccountService;
import net.nainiubao.util.SdCardUtil;
import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * 与js交互接口
 * @author Daniel
 *
 */
public class JsInterface {
	
	private Context mContext;
	
	public JsInterface(Context context){
		this.mContext=context;
	}
	
	/**
	 * 保存用户名和密码
	 * @param name
	 * @param pwd
	 */
	@JavascriptInterface
	public String save(String name,String pwd){
		try {
			boolean ret = AccountService.saveAccount(name, pwd);
			return "{\"error\":"+(ret?0:1)+"}";
		} catch (Exception e) {
			return "{\"error\":1,\"msg\":\""+e.getMessage()+"\"}";
		}
//		System.out.println("保存完成："+ret);
		
	}
	
	/**
	 * 加载用户名和密码
	 * @return
	 */
	@JavascriptInterface
	public String load(){
		return AccountService.loadAccount();
	}

	@JavascriptInterface
	public String show(){
		String sd=SdCardUtil.getSavedDir("nainiubao");
		return sd;
	}
	
}
