package io.github.danielthedev.jdecompiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JClassInputStream extends InputStream {

	private final InputStream in;

	public JClassInputStream(InputStream in) {
		this.in = in;
	}
	
	public byte readByte() throws IOException {
		return (byte) this.read();
	}
	
	public int readShort() throws IOException {
		return (int) ((this.read() << 16) | this.read());
	}
	
	public int readInteger() throws IOException {
		return (this.read() << 24) | (this.read() << 16) | (this.read() << 8) | this.read();
	}
	
	public float readFloat() throws IOException {
		return (this.read() << 24) | (this.read() << 16) | (this.read() << 8) | this.read();
	}
	
	public short readUnsignedByte() throws IOException {
		return (short) (0xff & this.read());
	}
	
	public int readUnsignedShort() throws IOException {
		return (int) (0xffff & ((this.read() << 8) | this.read()));
	}
	
	public long readUnsignedInt() throws IOException {
		return (long) (0xffffffff & ((this.read() << 24) | (this.read() << 16) | (this.read() << 8) | this.read()));
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