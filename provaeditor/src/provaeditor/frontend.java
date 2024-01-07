package provaeditor;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

import javax.print.PrintException;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import com.opencsv.exceptions.CsvValidationException;

public class frontend {
	Strings lang=new Strings();
	String pat;
	String filename="";
	String buffer;
	String startbuffer="";
	String copybuffer="";
	static runtime r=new runtime();
	static documentruntime d=new documentruntime();
	static JTextField pathfield=new JTextField();
	static JTextField namefield=new JTextField();
	static JTextArea buff=new JTextArea(500,420);
	int saveflag=0;
	public int home() throws IOException
	{
		String langs=r.setlang(lang);
		System.out.println(langs);
		JFrame homeframe=new JFrame();
		homeframe.resize(200,300);
		Container c=homeframe.getContentPane();
		GridLayout layout=new GridLayout(2,1,50,50);
		JButton nuovo=new JButton(lang.newfilestr);
		JButton apri=new JButton(lang.openfilestr);
		if(langs.equals("italiano")) {
			nuovo.setText(lang.itnewfilestr);
			apri.setText(lang.itopenfilestr);
		}
		if(langs.equals("Русский")) {
			nuovo.setText(lang.runewfilestr);
			apri.setText(lang.ruopenfilestr);
		}
		nuovo.addMouseListener(new 
			       java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					c_mouseClicked(e);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CsvValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		apri.addMouseListener(new java.awt.event.MouseAdapter()
				{
			public void mouseClicked(MouseEvent op)
			{
				try {
					op_mouseClicked(op);
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CsvValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				});
		c.setLayout(layout);
		c.add(nuovo);
		c.add(apri);
		apri.setSize(40, 160);
		nuovo.setSize(40, 160);
		homeframe.show();
		return 1;
	}
	protected void op_mouseClicked(MouseEvent op) throws InterruptedException, IOException, CsvValidationException {
		String[] filpath=r.selectopenfile();
		filename=filpath[0];
		pat=filpath[1];
		d.fopen(filename,pat);
	}
	public String[] fileselect() throws InterruptedException, IOException
	{
		String langs=r.setlang(lang);
		JFrame homeframe=new JFrame();
		Container m=homeframe.getContentPane();
		homeframe.setSize(200,450);
		GridLayout layout=new GridLayout(6,1,10,10);
		m.setLayout(layout);
	    JButton committ=new JButton(lang.createstr);
	    JButton sh=new JButton(lang.choosestr);
		JLabel path=new JLabel(lang.pathstr);
		JLabel name=new JLabel(lang.filenamestr);
		m.add(name);
		m.add(namefield);
		m.add(path);
		m.add(pathfield);
		m.add(committ);
		m.add(sh);
		name.setSize(30, 150);
		path.setSize(30, 150);
		namefield.setSize(30,150);
		pathfield.setSize(30,150);
		committ.setSize(30,150);
		if(langs.equals("italiano")) {
			name.setText(lang.itfilenamestr);
			path.setText(lang.itpathstr);
			committ.setText(lang.itcreatestr);
			sh.setText(lang.itchoosestr);
		}
		if(langs.equals("Русский")) {
			name.setText(lang.rufilenamestr);
			path.setText(lang.rupathstr);
			committ.setText(lang.rucreatestr);
			sh.setText(lang.ruchoosestr);
		}
		homeframe.show();
		committ.addMouseListener(new java.awt.event.MouseAdapter()
		{
	public void mouseClicked(MouseEvent com) {
		filename=namefield.getText();
	    pat=pathfield.getText();
        try {
			e_mouseClicked(com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }}
	
		);
		sh.addMouseListener(new java.awt.event.MouseAdapter()
				{
			public void mouseClicked(MouseEvent scel)
			{
				System.out.println("selector");
				pat=r.seldir();
				pathfield.setText(pat+"/");
			}
				});
		System.out.println(filename);
		String[] vector= {filename,pat};
		return vector;
	}
	public void c_mouseClicked(MouseEvent e) throws InterruptedException, IOException, CsvValidationException
	{
		System.out.println("ciao");
		r.selectfile();
	}
	
	public void e_mouseClicked(MouseEvent com) throws IOException, CsvValidationException
	{
		filename=namefield.getText();
	    pat=pathfield.getText();
		this.saveflag=1;
		r.newfile(filename,pat);
	}
	
	public String editor_interface(File file) throws IOException, CsvValidationException
	{
		String langs=r.setlang(lang);
		System.out.println(file.getName());
		filename=file.getName();
		pat=file.getPath();
		Font setf=r.getfontinfo(filename,pat);
		JFrame editorframe=new JFrame();
		Container j=editorframe.getContentPane();
	    BoxLayout layout=new BoxLayout(j,BoxLayout.Y_AXIS);
		j.setLayout(layout);
		j.add(buff);
		JScrollPane scroll = new JScrollPane (buff);
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		String [] fontNames=ge.getAvailableFontFamilyNames();
		Integer [] fontw= {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,35,40,45,50,60,70,85,100};
		JComboBox<String> fontsel=new JComboBox<>(fontNames);
		JComboBox<Integer> wsel=new JComboBox<>(fontw);
		JMenuBar tools=new JMenuBar();
		JButton copy=new JButton(lang.copystr);
		JButton paste=new JButton(lang.paststr);
		JButton sv=new JButton(lang.savestr);
		JButton cred=new JButton("credits");
		JButton condf=new JButton(lang.sharestr);
		JButton print=new JButton(lang.recievestr);
		if(langs.equals("italiano")) {
			copy.setText(lang.itcopystr);
			paste.setText(lang.itpaststr);
			sv.setText(lang.itsavestr);
			condf.setText(lang.itsharestr);
			print.setText(lang.itrecievestr);
		}
		if(langs.equals("Русский")) {
			copy.setText(lang.rucopystr);
			paste.setText(lang.rupaststr);
			sv.setText(lang.rusavestr);
			condf.setText(lang.rusharestr);
			print.setText(lang.rurecievestr);
		}
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    editorframe.add(scroll);
	    j.add(tools);
	    editorframe.setVisible(true);
	    editorframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorframe.setResizable(true);
		fontsel.setSize(50,100);
		System.out.println(setf.getName()+setf.getSize());
		buff.setFont(setf);
		buff.setSize(770,420);
		buff.setLineWrap(true);
	    buff.setEditable(true);
	    buff.setWrapStyleWord(true);
	    buff.setVisible(true);
	    tools.add(copy);
	    tools.add(paste);
	    tools.add(sv);
	    tools.add(cred);
	    tools.add(condf);
	    tools.add(print);
	    tools.add(fontsel);
	    tools.add(wsel);
	    tools.setVisible(true);
		editorframe.setTitle("editor");
		buff.setText(startbuffer);
	    editorframe.pack();
	   sv.addMouseListener(new java.awt.event.MouseAdapter()
		{
		   public void mouseClicked(MouseEvent fl)
		   {
			   System.out.println("ciaone");
			   try {
				i_MouseClicked(fl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
			}
		);  
	   copy.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent cop)
		   {
			   buff.copy();
			   System.out.println("copia");
		   }
		        });
	   paste.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent pas)
		   {
			   buff.paste();
			   System.out.println("incolla");
		   }
		        });
	   fontsel.addActionListener(new ActionListener()
			   {
		   public void actionPerformed(ActionEvent ac)
		   {
			   String selectedFont=fontsel.getSelectedItem().toString();
			   int dim=buff.getFont().getSize();
			   Font font=new Font(selectedFont,Font.PLAIN,dim);
			   buff.setFont(font);
		   }
			   });
	   wsel.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent ac2)
			   {
		   int w=(int) wsel.getSelectedItem();
		   Font actfont=buff.getFont();
		   String act=actfont.getName();
		   Font font=new Font(act,Font.PLAIN,w);
		   buff.setFont(font);
			   }});
	   cred.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent ac3)
		   {
			   JFrame crfr=new JFrame();
			   Container crpn=new Container();
			   crpn=crfr.getContentPane();
			   GridLayout crl=new GridLayout(4,1,10,10);
			   crfr.setLayout(crl);
			   crfr.setSize(500,200);
			   crpn.setSize(500,200);
			   crfr.setTitle("credits");
			   String gitlink="https://github.com/simonov123/provaeditor";
			   JLabel credits1=new JLabel("an italian 22 y.o programmer inspired by a princess");
			   JLabel credits2=new JLabel("btw,she's a programmer too");
			   JLabel credits3=new JLabel(gitlink);
			   JButton ok=new JButton("OK");
			   crpn.add(credits1);
			   crpn.add(credits2);
			   crpn.add(credits3);
			   crpn.add(ok);
			   crfr.show();
			   ok.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent ac4) {
					   crfr.setVisible(false);
					   crfr.dispose();
				   }
			   });
		   }
	   });
	   condf.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ac5) {
			JOptionPane.showMessageDialog(null,"Make sure that the reciever has pressed recieve before");
			File sendf=new File(pat);
			System.out.println("invio:"+sendf.getName());
			Sender shr=new Sender();
			try {
				if(sendf.length()<=80000) {
					shr.sendfile(sendf);
				}
				if(sendf.length()>80000) {
					JOptionPane.showMessageDialog(null,"MAX FILE SIZE 80KB");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		   
	   });
	   print.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			recieve rec=new recieve();
			try {
				File recf=rec.recieve();
				JOptionPane.showMessageDialog(null,"recieved!");
				String tit="insert new file title";
				String newnam=JOptionPane.showInputDialog(tit);
				File recfdef=new File("/home/"+System.getenv("USER")+"/"+newnam+".txt");
				recf.renameTo(recfdef);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		   
	   });
	   editorframe.setSize(870,500);
		j.setSize(870,500);
	    sv.setSize(50,80);
	    editorframe.show();
	    return buffer;
	
	}
	
	public void i_MouseClicked(MouseEvent fl) throws IOException, CsvValidationException
	{
		System.out.println("test"+filename);
		File file=new File(pat);
		FileWriter i=new FileWriter(file);
		System.out.println(file.getName()+"editor");
		buffer=buff.getText();
		d.write(buffer,file,i);
		r.infosaver(pat,filename,buff.getFont().getName(),buff.getFont().getSize());
	}
	
	public void cop_MouseClicked(MouseEvent cop)
	{
		 buff.copy();
		 System.out.println("copia");
		
	}

	
	
	

}
