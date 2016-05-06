package net.nainiubao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 配置数据管理器
 * @author Daniel
 *
 */
public final class Configuration {

	private static Configuration singleton;

	private static Properties properties = new Properties();

	private Configuration() {
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		if (Configuration.singleton == null) {
			Configuration.singleton = new Configuration();
		}
		return Configuration.singleton;
	}

	public static String getProperty(String propertyName) {
		getInstance();
		return Configuration.properties.getProperty(propertyName);
	}

	public static void setProperty(String propertyName, String value) {
		getInstance();
		Configuration.properties.setProperty(propertyName, value);
	}
}
