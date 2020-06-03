import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame{
	private JLabel title, confirmSize;
	private JButton btn, btnIntro, btConfig, btConf;
	private JPanel p1,p2,p3;
	private Draw gp;
	private JComboBox choice;
	private JComboBox brush;
	private Mouse myMouse;
	private JDialog tweaks;
	private ImageIcon imgPlay, imgCon;
	private JSlider slide;
	private JRadioButton radio, radio1;
	private boolean state;
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public static void main(String args[]) {
		Screen intro = new Screen("Welcome", true);
		intro.setSize(500, 300);
		intro.setVisible(true);
		intro.setResizable(false);
		intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Screen(String t, boolean x) {
		setTitle(t);
		imgPlay = new ImageIcon("play.jpg");
		btnIntro = new JButton("Play", imgPlay);
		btnIntro.setPreferredSize(new Dimension(200, 50));
		btnIntro.setBackground(Color.LIGHT_GRAY);
		imgCon = new ImageIcon("settings.jpg");
		btConfig = new JButton("Configure", imgCon);
		btConfig.setPreferredSize(new Dimension(200, 50));
		btConfig.setBackground(Color.LIGHT_GRAY);
		title = new JLabel("RANDOM COLOR GENERATOR PAINT");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		p2 = new JPanel();
		p3 = new JPanel();
		btnIntro.addActionListener(new Login());
		btConfig.addActionListener(new Config());
		setState(x);
		p2.add(btnIntro);
		p2.add(btConfig);
		p3.add(title);
		add(p2, BorderLayout.SOUTH);
		add(p3, BorderLayout.CENTER);
	}
	public Screen(String t) {
		setTitle(t);
		setState(true);
		btn = new JButton("Refresh");
		slide = new JSlider(JSlider.HORIZONTAL, 10, 50, 10);
		choice = new JComboBox(new Object[] {"Square", "Circle", "Eraser"});
		brush = new JComboBox(new Object[] {"Mozaik", "Air"});
		p1 = new JPanel();
		btn.addActionListener(new Click());
		slide.addChangeListener(new Change());
		myMouse = new Mouse();
		addMouseMotionListener(myMouse);
		p1.add(btn);
		p1.add(choice);
		p1.add(slide);
		p1.add(brush);
		gp = new Draw();
		gp.setSizeOfBrush(10);
		gp.setOpt(3);
		gp.setColor(Color.WHITE);
		add(p1, BorderLayout.SOUTH);
		add(gp, BorderLayout.CENTER);
	}
	
	private class Click implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gp.setOpt(3);
			gp.setColor(Color.WHITE);
		}
	}
	
	private class Config implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Screen configScr = new Screen("Configure");
			tweaks = new JDialog(configScr, "Configure", true);
			confirmSize = new JLabel("Enter your desired window mode");
			radio = new JRadioButton("Minimized");
			radio1 = new JRadioButton("Maximized");
			radio.setBounds(75, 50, 100, 30);
			radio1.setBounds(75, 100, 100, 30);
			ButtonGroup bg = new ButtonGroup();
			bg.add(radio);
			bg.add(radio1);
			btConf = new JButton("OK");
			btConf.addActionListener(new Confirm());
			tweaks.setLayout(new FlowLayout());
			tweaks.setResizable(false);
			tweaks.add(confirmSize);
			tweaks.add(radio);
			tweaks.add(radio1);
			tweaks.add(btConf);
			tweaks.setSize(300, 200);
			tweaks.setVisible(true);
		}
	}
	
	private class Confirm implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(radio.isSelected()) {
				setState(false);
			}
			tweaks.setVisible(false);
		}
	}
	
	private class Change implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			int size = (int)source.getValue();
			gp.setSizeOfBrush(size);
		}
	}
	
	private class Login implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(state == false){
				Screen mainScr = new Screen("Playy");
				mainScr.setSize(500, 300);
				mainScr.setVisible(true);
				mainScr.setResizable(false);
				dispose();
				mainScr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else{
				Screen mainScr = new Screen("Playy");
				mainScr.setExtendedState(JFrame.MAXIMIZED_BOTH);
				mainScr.setVisible(true);
				dispose();
				mainScr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainScr.setResizable(false);
			}
		}
	}
	
	private class Mouse extends MouseInputAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			String item = (String)choice.getSelectedItem();
			String brushType = (String)brush.getSelectedItem();
			if(brushType.equals("Mozaik")) {
				gp.x = x-25;
				gp.y = y-50;
				if(item.equals("Square")){
					gp.setOpt(1);
					gp.setColor(gp.getColor());
				}
				else if(item.equals("Circle")) {
					gp.x = x-25;
					gp.y = y-50;
					gp.setOpt(2);
					gp.setColor(gp.getColor());
				}
				else if(item.equals("Eraser")) {
					gp.x = x-25;
					gp.y = y-50;
					gp.setOpt(4);
					gp.setColor(gp.getColor());
				}
			}
			else if(brushType.equals("Air")) {
				if(item.equals("Square")){
					int n = 50;
					while(n-- > 0) {
						gp.x = x-25 + n*50;
						gp.y = y-50 + n*50;
						gp.setOpt(1);
						gp.setColor(gp.getColor());
					}
				}
				else if(item.equals("Circle")) {
					int n = 50;
					while(n-- > 0) {
						gp.x = x-25 + n*50;
						gp.y = y-50 + n*50;
						gp.setOpt(2);
						gp.setColor(gp.getColor());
					}
				}
				else if(item.equals("Eraser")) {
					int n = 50;
					while(n-- > 0) {
						gp.x = x-25 + n*50;
						gp.y = y-50 + n*50;
						gp.setOpt(4);
						gp.setColor(gp.getColor());
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
	}
	
	private class Draw extends JPanel{
		
		private Color randomColor;
		private int opt;
		private int x, y;
		private int size;
		
		public void setSizeOfBrush(int size) {
			this.size = size;
		}
		
		public void paintComponent(Graphics g) {
		    if(this.opt == 1) {
		    	g.setColor(getColor());
			    g.fillRect(x, y, size, size);
		    }
		    else if(this.opt == 2) {
		    	g.setColor(getColor());
		    	g.fillOval(x, y, size, size);
		    }
		    else if(this.opt == 4) {
		    	g.setColor(Color.WHITE);
		    	g.fillOval(x, y, size, size);
		    }
		    if(this.opt == 3) {
		    	g.setColor(Color.WHITE);
		    	g.fillRect(0, 0, getWidth(), getHeight());
		    }
		}
		
		public void setColor(Color randomColor) {
			this.randomColor = randomColor;
			repaint();
		}
		public void setOpt(int opt) {
			this.opt = opt;
		}
		public Color getColor() {
			Random rand = new Random();
			
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			
			randomColor = new Color(r, g, b);
			return randomColor;
		}
		
	}
	
	public void speed(int n) {
		int i = 0;
		while(i < n) {
			i++;
		}
	}
}
