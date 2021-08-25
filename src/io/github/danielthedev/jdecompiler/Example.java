package io.github.danielthedev.jdecompiler;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Example {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\danie\\Desktop\\ecalendar-server\\ecalendar.jar");
		try(ZipFile zip = new ZipFile(file)) {
			
			zip.entries().asIterator().forEachRemaining(entry->{
				
				
				if(entry.getName().endsWith(".class")) {
					System.out.println(entry);
					try(ClassInputStream in = new ClassInputStream(zip.getInputStream(entry))) {						
						int magic = (int) in.read(4);
						if(magic == 0xCAFEBABE) {
							System.out.println("magic correct");
								
							short minor_version = (short) in.read(2);
							short major_version = (short) in.read(2);
							short constant_pool_count = (short) in.read(2);
							
							System.out.println("minor_version: " + minor_version);
							System.out.println("major_version: " + major_version);
							System.out.println("constant_pool_count: " + constant_pool_count);
							
							
							for(int x = 0; x < 10; x++) {
								System.out.println(in.read(1));
							}
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				}
			});
			

		}
	}
	
}
