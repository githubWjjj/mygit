import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JFrame;

class MyPanel extends JPanel implements Runnable{
	ThreadDemo td=null;
	public MyPanel() {
		td=new ThreadDemo();
		Thread t=new Thread(td);
		t.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.white);
		setForeground(Color.white);
		g.fillOval(0, 0, 20, 20);
		this.drawBall(td.b.getX(),td.b.getY(),td.b.getWidth(),td.b.getHeight(),g);
		this.drawBall(td.bb.getX(),td.bb.getY(),td.bb.getWidth(),td.bb.getHeight(),g);
	}
	public void drawBall(int x,int y,int width,int height,Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, width, height);
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(5);
			}catch(Exception e) {
				e.printStackTrace();
			}
			this.repaint();
	}
}
class Ball{
	int x,y;
	int width,height;
	int xv=2;
	int yv=2;
	public int getX() {return x;}
	public void setX(int x) {this.x=x;}
	public int getY() {return y;}
	public void setY(int y) {this.y=y;}
	public int getWidth() {return width;}
	public void setWidth(int w) {this.width=w;}
	public int getHeight() {return height;}
	public void setHeight(int h) {this.height=h;}
	public Ball(int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
}
class ThreadDemo implements Runnable{
	Ball b=new Ball(100,200,20,20);
	Ball bb=new Ball(200,200,50,50);
	public void run(){
		while(true) {
			try {
				Thread.sleep(5);
			}catch(Exception e) {
				e.printStackTrace();
			}
			b.x=b.x+b.xv;
			b.y=b.y+b.yv;
			if(b.getX()>400||b.getX()<0)
				b.xv=-b.xv;
			if(b.getY()>300||b.getY()<0)
				b.yv=-b.yv;
			bb.x=bb.x+bb.xv;
			bb.y=bb.y+bb.yv;
			if(bb.getX()>400||bb.getX()<0)
				bb.xv=-bb.xv;
			if(bb.getY()>300||bb.getY()<0)
				bb.yv=-bb.yv;
			if(b.getX()==bb.getX()&&b.getY()==bb.getY()) {
				int mid;
				mid=b.xv;
				b.xv=bb.xv;
				bb.xv=mid;
				mid=b.yv;
				b.yv=bb.yv;
				bb.yv=mid;
			}
			}
		}
	}
}

public class ball_bounce extends JFrame{
	MyPanel p=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ball_bounce exp=new ball_bounce();
	}
	public ball_bounce(){
		setTitle("·´µ¯Çò");
		p = new MyPanel();
		Thread ud = new Thread(p);
		ud.start();
		this.add(p);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
