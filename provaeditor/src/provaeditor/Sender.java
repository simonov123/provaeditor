package provaeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Sender {
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
		putfr.setSize(100,300);
		putc.setSize(100,300);
		putfr.show();
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				Socket socket=new Socket(ipin.getText(),9888);
				byte[] buffer=new byte[4096];
				FileInputStream input=new FileInputStream(file);
				OutputStream out=socket.getOutputStream();
				int bytesRead;
				while((bytesRead=input.read())!=-1) {
					out.write(buffer,0,bytesRead);
					if((bytesRead=input.read())==4096) {
						System.out.println(buffer);
					}
				}
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
