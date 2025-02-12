package provaeditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

public class documentruntime {
	static String buffer;
	static editor ed=new editor();
	static frontend inter=new frontend();
	public int filegen(String filename,String path) throws IOException, CsvValidationException
	{
		if (filename==(""))
		{
			return 0;
		}
		System.out.println(filename);
		System.out.println(path);
		String name;
		name=filename;
		File file=new File(path+name+".txt");
		file.createNewFile();
		if(file.exists()!=false)
		{
			FileWriter i=new FileWriter(file);
			System.out.println(file.getName());
			i.close();
			ed.editor_runtime(file);
			return 1;
		}
		else
		{
			System.err.println("errore");
		}
		return 0;
	}
	int write(String b,File file,FileWriter i) throws IOException
	{
		CharBuffer verstring = null;
		System.out.println(file.getName());
		System.out.println("buffer="+b+"\nname="+file.getName()+"\npath="+file.getPath());
		if(file.canWrite())
		{
		    i.write(b);
		    i.flush();
		    i.close();
		    System.out.println("entre");
		}
		if(file.canWrite()==false)
		{
			System.out.println("err");
		}
		return 1;
	}
	

public void fopen(String filename,String path) throws IOException, CsvValidationException
{
	if (filename==(""))
	{
		System.err.println(".");
	}
	System.out.println(filename);
	System.out.println(path);
	String name;
	name=filename;
	String actual;
	actual=this.recover(path,filename);
	System.out.println("content:"+actual);
	inter.startbuffer=actual;
	File file=new File(path);
	inter.editor_interface(file);
}
public String recover(String pat,String filename) throws IOException
{
	System.out.println("benvenuti in recover)))");
	String result="";
	File opoint=new File(pat);
	FileReader red=new FileReader(opoint);
    BufferedReader o=new BufferedReader(red);
    FileInputStream input=new FileInputStream(opoint);
    Scanner scanner=new Scanner(input);
    scanner.useDelimiter("\\A");
    result=scanner.hasNext()?scanner.next():"";
	System.out.println(result);
	scanner.close();
	input.close();
	return result;
}
}
