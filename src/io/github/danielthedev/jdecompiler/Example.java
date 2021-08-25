package io.github.danielthedev.jdecompiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import io.github.danielthedev.jdecompiler.classes.ClassFile;
import io.github.danielthedev.jdecompiler.constants.*;
import io.github.danielthedev.jdecompiler.constants.Constant;


public class Example {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\danie\\Desktop\\server\\server.jar");
		//File file = new File("C:\\Users\\danie\\Desktop\\ecalendar-server\\ecalendar.jar");
		try(ZipFile zip = new ZipFile(file)) {
			zip.entries().asIterator().forEachRemaining(entry->{
				if(entry.getName().endsWith(".class")) {
					System.out.println(entry);
					try {
						ClassFile cfile = ClassFile.fromInputStream(zip.getInputStream(entry));
						System.out.println(cfile + "");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					System.exit(0);
				}
			});
			

		}
	}
	
}
