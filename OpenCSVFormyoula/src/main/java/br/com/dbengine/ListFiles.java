package br.com.dbengine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFiles {

	public static void main(String[] args) {

		// final File folder = new File("C:\\projects");
		// final File folder = new File("E:/OneDrive - Infosys
		// Limited/Syngenta/Formyoula/Complaints");
		// OK : final File folder = new File("C://Users/rodrigo.bisterco/Downloads");
		final File folder = new File("E://OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria");

		List<String> result = new ArrayList<>();

		//search(".*\\.csv", folder, result);
		search("F.*\\.csv", folder, result);

		for (String s : result) {
			System.out.println(s);
		}

	}

	public static void search(final String pattern, final File folder, List<String> result) {
		for (final File f : folder.listFiles()) {

			if (f.isDirectory()) {
				search(pattern, f, result);
			}

			if (f.isFile()) {
				if (f.getName().matches(pattern)) {
					//result.add(f.getAbsolutePath()); //full path
					result.add(f.getName());
				}
			}

		}
	}

}
