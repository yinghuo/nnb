package net.nainiubao;


import android.app.Application;

/**
 * RKApplication管理
 * @author Daniel
 *
 */
public class NiuApplication extends Application  {
	
	public NiuApplication(){
		application=this;
	}
	
	public static NiuApplication application = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		
		//初始化配置
		//Configuration.getInstance();
	}
}
