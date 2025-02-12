package provaeditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

public class editor {
	frontend edint=new frontend();
	public void editor_runtime(File file) throws IOException, CsvValidationException
	{
		System.out.println(file.getName());
		edint.editor_interface(file);
		
	}

}
