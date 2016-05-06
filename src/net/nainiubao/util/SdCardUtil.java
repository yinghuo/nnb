package net.nainiubao.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.nainiubao.NiuApplication;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

public class SdCardUtil {

	public static String getSavedDir(String dir) {
		String savedDir = null;

		if (SdCardUtil.SDCardIsExist()) {
			savedDir = SdCardUtil.getExternalStoragePath() +"/"+ dir;
		} else {
			savedDir = getLocalSaveDir(dir);
		}

		return savedDir;
	}

	private static String getLocalSaveDir(String dir) {
		return NiuApplication.application.getFilesDir().getPath() +"/"+ dir;
	}

	public static String getLocalDir() {
		return NiuApplication.application.getFilesDir().getParent();
	}

	public static String getExternalStoragePath() {
		// 获取SdCard状�?
		String state = android.os.Environment.getExternalStorageState();

		// 判断SdCard是否存在并且是可用的
		if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
			if (android.os.Environment.getExternalStorageDirectory().canWrite()) {
				return android.os.Environment.getExternalStorageDirectory()
						.getPath();
			}
		}
		return null;
	}

	private static boolean SDCardIsExist() {
		return getExternalStoragePath() != null ? true : false;
	}

	/**
	 * 获取存储卡的剩余容量，单位为字节
	 */
	public static long getSdCardAvailableStore() {
		String filePath = getExternalStoragePath();
		StatFs statFs = new StatFs(filePath);

		long blocSize = statFs.getBlockSize();
		long availaBlock = statFs.getAvailableBlocks();

		return availaBlock * blocSize;
	}

	public static long getSystemAvailableStore() {
		String filePath = Environment.getRootDirectory().getPath();
		StatFs statFs = new StatFs(filePath);

		long blockSize = statFs.getBlockSize();
		long availaBlock = statFs.getAvailableBlocks();

		return availaBlock * blockSize;
	}

	/**
	 * 获取外置SdCard路径，待测试
	 */
	public static String getExtSdCardPath() {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("mount");
			InputStream is = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			String line;
			String mount = new String();
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line.contains("secure"))
					continue;
				if (line.contains("asec"))
					continue;

				if (line.contains("fat")) {
					String columns[] = line.split(" ");
					if (columns != null && columns.length > 1) {
						mount = mount.concat(columns[1]);
					}
				}
			}

			return mount;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return getExternalStoragePath();
	}

	public static String getIconPath(String iconName) {
		if (TextUtils.isEmpty(iconName)) {
			return null;
		}

		return getSavedDir(ConstantFiled.SAVED_ICON_DIR + "/" + iconName);
	}
}
