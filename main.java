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
import java.util.Random;

import javax.swing.JFrame;

public class main extends Canvas implements KeyListener {

	ArrayList<Point> list;
	ArrayList<Point> listPom = new ArrayList<>();
	JFrame frame;

	static main m;

	int width = 800;
	int height = 600;

	int direction; // kierunek pomocnczy snake

	int[][] board = new int[height / 10][width / 10];

	public main() {

		m = this;

		m.setBackground(Color.green);
		m.setSize(width, height);

		create_board();

		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();

		p1.x = 4;
		p1.y = 2;
		p1.direction = 39; // start gry w prawo
		this.direction = 39;

		p2.x = 3;
		p2.y = 2;
		p3.x = 2;
		p3.y = 2;

		list = new ArrayList<>();

		list.add(p1);
		list.add(p2);
		list.add(p3);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(m);
		frame.setVisible(true);

		frame.addKeyListener(this);
		frame.pack();
		frame.setSize(width, height);

	}

	void create_board() {

		int a = -1;
		int b = -1;
		boolean bool = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 2) {
					a = i;
					b = j;
					bool = true;
				}

				board[i][j] = 0;

				if (bool)
					board[a][b] = 2;
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

		} 
		
		if (listPom.size() < list.size()) {

			Point p = new Point();
			p.x = list.get(list.size() - 1).x;
			p.y = list.get(list.size() - 1).y;

			listPom.add(p);
		}
		
		
		for (int i = 0; i < list.size(); i++) {

			listPom.get(i).x = list.get(i).x;
			listPom.get(i).y = list.get(i).y;

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

			if (this.direction == 39) {
				list.get(0).x += 1;
				break;
			}

			if( board[list.get(0).y][list.get(0).x-1] == 2 ){
				incrementSnake(list.get(0).y, list.get(0).x-1);
				board[list.get(0).y][ list.get(0).x-1] =0;
			}
			list.get(0).x -= 1;
			list.get(0).direction = 37;
			this.direction = 37;

			break;

		case 38: // góra

			if (this.direction == 40) {

				list.get(0).y += 1;

				break;
			}

			if( board[list.get(0).y-1][list.get(0).x] == 2 ) {
				incrementSnake(list.get(0).y-1, list.get(0).x);
				board[list.get(0).y-1][ list.get(0).x] =0;
			}
			
			
			
			
			list.get(0).y -= 1;
			list.get(0).direction = 38;
			this.direction = 38;

			break;

		case 39: // prawo

			if (this.direction == 37) {

				list.get(0).x -= 1;

				break;
			}

			if( board[list.get(0).y][list.get(0).x+1] == 2 ){
				incrementSnake(list.get(0).y, list.get(0).x+1);
				board[list.get(0).y][ list.get(0).x+1] =0;
			}
			
			list.get(0).x += 1;
			list.get(0).direction = 39;
			this.direction = 39;

			break;

		case 40: // dół

			if (this.direction == 38) {

				list.get(0).y -= 1;

				break;
			}

			if( board[list.get(0).y+1][list.get(0).x] == 2 ){
				incrementSnake(list.get(0).y+1, list.get(0).x);
				board[list.get(0).y+1][ list.get(0).x] =0;
			}
			list.get(0).y += 1;
			list.get(0).direction = 40;
			this.direction = 40;
			break;
		}

		for (int i = 0; i < list.size(); i++) {
			board[list.get(i).y][list.get(i).x] = 1;
		}
		
		
	}

	void incrementSnake(int y, int x){
		
		Point p = new Point();
		p.x= x;
		p.y =y;
		
		list.add(0, p);
		board[y][x]=0;
		
		setBonus();
	};
	
	void print() {

		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
		}
	}

	public void paint(Graphics g) {

		g.setColor(Color.BLACK);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 1) {
					g.fillRect(j * 10, i * 10, 10, 10);
				}
				if (board[i][j] == 2) {
					g.fillOval(j * 10, i * 10, 10, 10);

				}

			}
		}
	}

	void setBonus() {

		boolean czyUstawionoBonus = false;
		Random r = new Random();
		while (!czyUstawionoBonus) {

			int width = r.nextInt(this.width / 10 - 20);
			int height = r.nextInt(this.height / 10 - 20);

			if (board[height][width] != 1) {

				board[height][width] = 2;
				czyUstawionoBonus = true;
				System.out.println(height + " " + width);

			}
		}
	}

	void gra() {

		boolean czyKoniecGry = false;

		while (!czyKoniecGry) {

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			m.updateSnake(m.list.get(0).direction);
			m.repaint();

			if (m.list.get(0).x * 10 == width - 10) {
				czyKoniecGry = true;
			}
			if (m.list.get(0).x * 10 == 0) {
				czyKoniecGry = true;
			}

			if (m.list.get(0).y * 10 == height - 30) {
				czyKoniecGry = true;
			}

			if (m.list.get(0).y * 10 == 0) {
				czyKoniecGry = true;
			}

		}

	};

	public static void main(String[] args) {

		m = new main();

		m.setBonus();

		m.gra();

	}

	@Override
	public void keyPressed(KeyEvent e) {

		m.list.get(0).direction = e.getKeyCode();
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