package provaeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Sender {
	private String csvpath="/home/";
	public void sendfile(File file) throws UnknownHostException, IOException {
		JFrame putfr=new JFrame();
		Container putc=new Container();
		putc=putfr.getContentPane();
		putfr.setTitle("IP:");
		GridLayout putl=new GridLayout(1,2,10,10);
		putc.setLayout(putl);
		JTextField ipin=new JTextField();
		JButton ok=new JButton ("ok");
		putc.add(ipin);
		putc.add(ok);
		putfr.setSize(300,100);
		putc.setSize(300,100);
		putfr.show();
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				Socket socket=new Socket(ipin.getText(),9888);
				System.out.println("verifica file");
				byte[] filecontent=new byte[(int)file.length()];
				FileInputStream ver=new FileInputStream(file);
				ver.read(filecontent);
				System.out.println("content:"+new String(filecontent));
				ver.close();
				String filename=file.getName();
                System.out.println("invio di:"+filename);
				FileInputStream input=new FileInputStream(file);
				System.out.println(filecontent);
				OutputStream out=socket.getOutputStream();
				int bytesRead;
				while((bytesRead=input.read())!=-1) {
					out.write(filecontent);
				}
				System.out.println(filecontent);
				input.close();
				out.close();
				socket.close();
				System.out.println("file inviato");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
			
		});
	}

}
