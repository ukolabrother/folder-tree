package ru.hukola.foldertree;

import java.nio.file.Path;

public class FileView {
	private final String name;
	private final Path path;
	
	public FileView(String name, Path path) {
		this.name = name;
		this.path = path;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Path getPath() {
		return this.path;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
