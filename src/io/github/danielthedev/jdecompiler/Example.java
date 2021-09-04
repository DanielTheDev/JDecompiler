package io.github.danielthedev.jdecompiler;

import java.io.File;
import java.io.IOException;

import java.util.zip.ZipException;
import java.util.zip.ZipFile;



public class Example {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\danie\\Desktop\\server\\server.jar");
		//File file = new File("C:\\Users\\danie\\Desktop\\ecalendar-server\\ecalendar.jar");
		try(ZipFile zip = new ZipFile(file)) {
			zip.entries().asIterator().forEachRemaining(entry->{
				if(entry.getName().endsWith("com/mojang/brigadier/tree/CommandNode.class")) {
					System.out.println(entry);
					try(JClassInputStream in = new JClassInputStream(zip.getInputStream(entry))) {
						
						ClassFile cfile = new ClassFile(in);
						
					} catch (Exception e) {
						System.err.println(entry);
						e.printStackTrace();
						System.exit(0);
					}
				}
			});
			

		} catch (ZipException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
