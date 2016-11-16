import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class main extends Canvas implements KeyListener {

	ArrayList<Point> list;
	ArrayList<Point> listPom = new ArrayList<>();
	JFrame frame;
	
	static main m;

	int width = 800;
	int height = 600;
	
	int[][] board = new int[height/10][width/10];

	public main() {

		m= this;
		
		m.setBackground(Color.green);
     	m.setSize(width, height);
		
		create_board();

		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();

		p1.x = 4;
		p1.y = 2;
		p1.direction = 39;  // start gry w prawo
		
		p2.x = 3;
		p2.y = 2;
		p3.x = 2;
		p3.y = 2;

		list = new ArrayList<>();

		list.add(p1);
		list.add(p2);
		list.add(p3);

        JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(m);        
		frame.setVisible(true);
		
		frame.addKeyListener(this);
		frame.pack();
		frame.setSize(width, height);
		

	}

	void create_board() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
	}

	
	
	void updateSnake(int direction) {

		create_board();
		
		if (listPom.isEmpty()) {

			for (int i = 0; i < list.size(); i++) {

				Point p = new Point();
				p.x = list.get(i).x;
				p.y = list.get(i).y;

				listPom.add(i, p);

			}

		} else {
			for (int i = 0; i < list.size(); i++) {

				listPom.get(i).x = list.get(i).x;
				listPom.get(i).y = list.get(i).y;
				

			}
		}

		if (listPom.size() < list.size()) {

			Point p = new Point();
			p.x = list.get(list.size() - 1).x;
			p.y = list.get(list.size() - 1).y;
			listPom.add(p);
		}

		for (int i = 0; i < list.size(); i++) {

			try {

				list.get(i + 1).x = listPom.get(i).x;
				list.get(i + 1).y = listPom.get(i).y;

			} catch (Exception e) {

			}

		}

		switch (direction) {
		case 37: // lewo

			if( 39 == list.get(0).direction){
				list.get(0).x += 1;
				break;
			}
			
			list.get(0).x -= 1;
			list.get(0).direction = 37;

			break;

		case 38: // góra

			if( 40 == list.get(0).direction ) {
			
				list.get(0).y += 1;
			    break;
			    }
				
			list.get(0).y -= 1;
			list.get(0).direction=38;
			
			break;

		case 39: // prawo

			if( 37 == list.get(0).direction){ 
				
				list.get(0).x -= 1;
				
				break;}
			
			list.get(0).x += 1;
			list.get(0).direction=39;
			
			break;

		case 40: // dół
			
			if( 38 == list.get(0).direction){
				list.get(0).y -= 1;
				break;
			}

			list.get(0).y += 1;
			list.get(0).direction = 40;
			break;
		}

		
		
		
		for (int i = 0; i < list.size(); i++) {
			board[list.get(i).y][list.get(i).x] = 1;
			
		}
		
		
		
	}

	void print() {


		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
		}
	}

	public void paint (Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
			if(board[i][j] ==1){
			
				g.fillRect(j*10,i*10, 10,10);
				
				}
			}
		}
	}
	
	public static void main(String[] args) {

		m = new main();
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		m.repaint();
		m.updateSnake(e.getKeyCode());
		System.out.println("sss");
		m.print();

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}