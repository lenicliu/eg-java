package com.lenicliu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileIO {

	static final Logger LOGGER = LoggerFactory.getLogger(FileIO.class);

	public static void writeByWriter(String filepath, String content) {
		try (FileWriter writer = new FileWriter(filepath)) {
			writer.write(content);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
	}

	public static void writeByStream(String filepath, byte[] content) {
		try (FileOutputStream o = new FileOutputStream(filepath)) {
			o.write(content);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
	}

	public static String readByReader(String filepath) {
		try (FileReader reader = new FileReader(filepath)) {
			StringBuffer content = new StringBuffer();
			char[] buffer = new char[1024];
			int read = -1;
			while (true) {
				read = reader.read(buffer);
				if (read == -1) {
					break;
				}
				content.append(buffer, 0, read);
			}
			return content.toString();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	public static byte[] readByStream(String filepath) {
		try (FileInputStream i = new FileInputStream(filepath)) {
			byte[] content = new byte[i.available()];
			i.read(content);
			return content;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
}