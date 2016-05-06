package net.nainiubao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.nainiubao.NiuApplication;
import net.nainiubao.util.SdCardUtil;

/**
 * 账户服务
 * @author Daniel
 *
 */
public class AccountService {
	
	/**
	 * 保存已存在的账号密码
	 * @param name
	 * @param pwd
	 * @return
	 */
	public static boolean saveAccount(String name,String pwd) throws Exception {
		try{
			//System.out.println("文件保存："+NiuApplication.application.getFilesDir().getAbsolutePath());
//			File file = new File(NiuApplication.application.getFilesDir(),"niu.cfg");
			String sd=SdCardUtil.getSavedDir("nainiubao");
			
			File folder=new File(sd);
			if(!folder.exists())
				folder.mkdirs();
			
			File file = new File(sd,"niu.cfg");
			if(file.exists()){
				file.delete();
			}
			
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write((name+"/"+pwd).getBytes());
	        fos.flush();
	        fos.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return true;
	}
	
	/**
	 * 加载已存在的账号密码
	 * @return
	 */
	public static String loadAccount(){
		try{
//			File file = new File(NiuApplication.application.getFilesDir(),"niu.cfg");
			String sd=SdCardUtil.getSavedDir("nainiubao");
			File file = new File(sd,"niu.cfg");
			if(file.exists()){
				FileInputStream fis=new FileInputStream(file);
				byte[] readbytes=new byte[1024];
				StringBuffer sbuffer=new StringBuffer();
				
				while(fis.read(readbytes)>0){
					sbuffer.append(new String(readbytes,"utf-8"));
				}
				fis.close();
				return sbuffer.toString().trim();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
}
