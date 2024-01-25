package provaeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;


public class recieve {
	public File recieve() throws IOException {
		ServerSocket server=new ServerSocket(9888);
		System.out.println("server in attesa...");
		Socket socket=server.accept();
		String add=socket.getInetAddress().toString();
		System.out.println("connesso con:"+add);
		InputStream input=socket.getInputStream();
		File recfile=new File("C:\\Users\\Public\\Documents\\recfile.txt");
		FileOutputStream out=new FileOutputStream(recfile);
		byte[] buffer=new byte[100000000];
		System.out.println(buffer);
		int bytesRead;
		while((bytesRead=input.read(buffer))!=-1) {
			out.write(buffer,0,bytesRead);
		}
		String buff=new String(buffer);
		buff=buff.replaceAll("\u0000", "");
		System.out.println(buff);
		FileWriter wr2=new FileWriter("C:\\Users\\Public\\Documents\\recfile.txt");
		wr2.write(buff);
		wr2.flush();
		wr2.close();
		input.close();
		out.close();
		socket.close();
		server.close();
		System.out.println("trasferimento completato");
		return recfile;
	}
	


}
