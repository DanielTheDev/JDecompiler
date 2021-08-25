package io.github.danielthedev.jdecompiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClassInputStream extends InputStream {

	private final InputStream in;

	public ClassInputStream(InputStream in) {
		this.in = in;
	}
	
	public Number read(int size) throws IOException {
		if(size > 8 || size < 0) {
			
		}
		
		long number = 0;
		
		for(int t = 0; t < size; t++) {
			
			int read = this.read();
			number |= (read << ((size - t - 1) * 8));
		}
		
		return switch (size) {
			case 1: {
				yield (byte)number;
			}
			case 2: {
				yield (short)number;
			}
			case 3, 4: {
				yield (int)number;
			}
			case 5, 6, 7, 8: {
				yield (long)number;
			}
			default: {
				yield null;
			}
		};
	}
	
	@Override
	public int read() throws IOException {
		return this.in.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return this.in.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return this.in.read(b, off, len);
	}

	@Override
	public byte[] readAllBytes() throws IOException {
		return this.in.readAllBytes();
	}

	@Override
	public byte[] readNBytes(int len) throws IOException {
		return this.in.readNBytes(len);
	}

	@Override
	public int readNBytes(byte[] b, int off, int len) throws IOException {
		return this.in.readNBytes(b, off, len);
	}

	@Override
	public long skip(long n) throws IOException {
		return this.in.skip(n);
	}

	@Override
	public void skipNBytes(long n) throws IOException {
		this.in.skipNBytes(n);
	}

	@Override
	public int available() throws IOException {
		return this.in.available();
	}

	@Override
	public void close() throws IOException {
		this.in.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		this.in.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		this.in.reset();
	}

	@Override
	public boolean markSupported() {
		return this.in.markSupported();
	}

	@Override
	public long transferTo(OutputStream out) throws IOException {
		return this.in.transferTo(out);
	}	
}
