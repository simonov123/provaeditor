package provaeditor;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class runtime {
	Strings ss=new Strings();
	String selang;
	private static String filename;
	private String csvpath="/home/";
	frontend inter=new frontend();
	documentruntime doc=new documentruntime();
	void runtime() throws IOException
	{
		this.setlang(ss);
		this.interfacecaller();
	}
	public String setlang(Strings ss) throws IOException {
		System.out.println("lang");
		File langfile=new File("/home/"+System.getenv("USER")+"/lang.txt");
		if(langfile.exists()==true)
		{
			Scanner scanner=new Scanner(langfile);
			String lang=scanner.nextLine();
			scanner.close();
			System.out.println(lang);
			return lang;
		}
		if(langfile.exists()==false) {
			JFrame langframe=new JFrame();
			Container langc=new Container();
			langc=langframe.getContentPane();
			langframe.setSize(200,100);
			langc.setSize(200,100);
			langframe.setTitle("language");
			String [] langlist= {"Italiano","English","Русский"};
			JComboBox<String> langbox=new JComboBox<>(langlist);
			langc.add(langbox);
			langbox.addActionListener(new ActionListener() {
           
				@Override
				public void actionPerformed(ActionEvent acb) {
					 String selectedlang=langbox.getSelectedItem().toString();
					 selang=selectedlang;
					 try {
						FileWriter lff=new FileWriter(langfile);
						lff.write(selectedlang);
						lff.flush();
						lff.close();
						JOptionPane.showMessageDialog(null,"limgua impostata,riavviare il programma/language set restart the program/установить язык, перезапустить программу");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			langframe.resize(200,100);
			langc.resize(200,100);
			langframe.show();
		}
		return selang;
	}
	int selectfile() throws InterruptedException, IOException, CsvValidationException
	{
		String[] vector=inter.fileselect();
		String path=vector[1];
		filename=vector[0];
		this.newfile(filename,path);
		return 0;
	}
	String [] selectopenfile() throws InterruptedException, IOException
	{
		JFileChooser slop=new JFileChooser(FileSystemView.getFileSystemView());
		slop.setDialogTitle("select file");
		slop.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnv=slop.showOpenDialog(null);
		if(returnv==JFileChooser.APPROVE_OPTION)
		{
		File selfile=slop.getSelectedFile();
		String selnam=selfile.getName();
		String selpat=selfile.getPath();
		String[] vector= {selnam,selpat};
		
		return vector;
		}
		else System.err.println("errore");
		String [] ver=null;
		return ver;
	}
	private int openfile(String filename,String path)
	{
		return 0;
	}
	private void interfacecaller() throws IOException
	{
		inter.home();
		
	}
	int newfile(String name,String path) throws IOException, CsvValidationException
	{
		doc.filegen(name,path);
		return 1;
	}
	public String seldir()
	{
		String dir="";
		JFileChooser sldir=new JFileChooser(FileSystemView.getFileSystemView());
		sldir.setDialogTitle("select directory");
		sldir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnd=sldir.showOpenDialog(null);
		if(returnd==JFileChooser.APPROVE_OPTION)
		{
			File seldir=sldir.getSelectedFile();
			dir=seldir.getAbsolutePath();
		}
		return dir;
	}
	public void csvcr() throws IOException {
		System.out.println(":)");
	}
		
	public boolean csvctr() {
		
			System.out.println(":)");
			return true;
	}
	public void infosaver(String pat, String filename2, String name, int size) throws IOException, CsvValidationException {
		File update=new File(csvpath+System.getenv("USER")+"/"+filename2+".csv");
		CSVWriter wr2=new CSVWriter(new FileWriter(csvpath+System.getenv("USER")+"/"+filename2+".csv"));
		String[] filer= {""};
		Integer siz=size;
		String[] newf= {pat,filename2,name,siz.toString()};
		wr2.writeNext(newf);
		wr2.close();
	}
	public Font getfontinfo(String filename2, String pat) throws CsvValidationException, IOException {
		File getinfo=new File(csvpath+System.getenv("USER")+"/"+filename2+".csv");
		if (getinfo.exists()==true)
		{
		CSVReader r1=new CSVReader(new FileReader(csvpath+System.getenv("USER")+"/"+filename2+".csv"));
		String[] info=r1.readNext();
		String name=info[2];
		String dim=info[3];
		int dimi=Integer.parseInt(dim);
		Font setf=new Font(name,Font.PLAIN,dimi);
		r1.close();
		return setf;
		}
		Font defaul=new Font("Liberation Serif",Font.PLAIN,16);
		return defaul;
	}
	

}
