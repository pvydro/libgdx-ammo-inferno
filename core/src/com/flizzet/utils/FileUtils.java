package com.flizzet.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class FileUtils {

	private static String directory = "~/Users/Flizzet/Documents/JavaWorkspace/SuperCaveJumper/android/assets/";
	private static File file = Gdx.files.internal("dirs.txt").file();

	/** @return all string values of the directories in the directory defined */
	public static ArrayList<String> search(String directoryName,
			ArrayList<File> files) {

		/* Create Array of all files */
		File dir = new File(directoryName);
		File[] fList = dir.listFiles();

		for (File f : fList) {
			if (f.isFile()) {
				files.add(f);
			} else if (f.isDirectory()) {
				search(f.getAbsolutePath(), files);
			}
		}

		/* Build ArrayList of Strings of file names to return */
		ArrayList<String> names = new ArrayList<String>();
		for (File f : files) {
			if (f.getName().endsWith(".png")) {
				String name = f.getPath();
				name = name.replaceAll(directory, "");
				names.add(name);
			} else if (f.getName().endsWith(".ogg")) {
				String name = f.getPath();
				name = name.replaceAll(directory, "");
				names.add(name);
			}
		}

		return names;
	}
	/** Writes the Array of Strings to the file */
	public static void populate(ArrayList<String> arrayList) {
		try {
			PrintWriter writer = new PrintWriter(file);
			for (String s : arrayList) {
				writer.println(s);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static List<FileHandle> listf(String directoryName) { FileHandle
	 * directory = Gdx.files.internal(directoryName);
	 * 
	 * List<FileHandle> resultList = new ArrayList<FileHandle>();
	 * 
	 * // Get all the files from a directory FileHandle[] fList =
	 * directory.list();
	 * 
	 * resultList.addAll(Arrays.asList(fList)); for (FileHandle file : fList) {
	 * if (file.isDirectory()) { resultList.addAll(listf(file.path())); } }
	 * 
	 * 
	 * 
	 * // DEV - Creates a file with the directory of every asset for optimized
	 * retrieval, removes need of .list();
	 * 
	 * 
	 * String newText = ""; FileHandle refsFile = Gdx.files.local("dirs.txt");
	 * for (FileHandle f : resultList) { newText = newText + f.file().getPath()
	 * + "\n"; } refsFile.writeString(newText, true);
	 * 
	 * 
	 * return resultList; }
	 */

}
