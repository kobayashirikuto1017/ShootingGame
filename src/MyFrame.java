import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
// import java.awt.Image; // Image クラスは不要になるかもしれません
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
 
/**
* グラフィックスを簡単に表示するウィンドウクラス
* @author Cho Shinya
*
*/
public class MyFrame extends Frame implements Runnable {
	BufferedImage im; // オフスクリーンバッファ
	/**
	 * fillRect 等で用いる描画色
	 */
	Color col = Color.BLACK;
	/**
	 * アニメーション用のスレッド
	 */
	Thread t;
	public Color bgColor = new Color(255, 255, 255);
 
	/**
	 * ウィンドウを作成し、表示する。
	 */
	public MyFrame() {
		super();
		setSize(400, 400);
		im = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(1);
			}
		});
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
 
	public synchronized void saveImage(File dst) throws IOException {
		ImageIO.write(im, "png", dst);
	}
 
	/**
	 * update メソッドをオーバーライドして、デフォルトの背景クリアを防ぎます。
	 */
	@Override
	public void update(Graphics g) {
		paint(g); // updateから直接paintを呼び出す
	}
 
	/**
	 * 描画処理はすべてオフスクリーンバッファ (im) に行い、
	 * paint メソッドでそのバッファを画面に転送します。
	 * ここでのバッファクリアは行わず、run メソッド側でクリアします。
	 */
	@Override
	public void paint(Graphics g) {
		// オフスクリーンバッファの内容を画面に描画するだけにする
		// ここでの im.getGraphics() を使ったクリア処理は削除！
		g.drawImage(im, 0, 0, null);
	}
 
	/**
	 * 四角形を描画する。色はsetColor で指定。
	 * 描画はオフスクリーンバッファ (im) に対してのみ行う。
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public synchronized void fillRect(double x, double y, double w, double h) {
		fillRect((int) x, (int) y, (int) w, (int) h);
	}
 
	public synchronized void fillRect(int x, int y, int w, int h) {
		Graphics g = getImageGraphics();
		if (g != null) {
			g.setColor(col);
			g.fillRect(x, y, w, h);
		}
	}
 
	public synchronized void clear() {
		// clear はオフスクリーンバッファをクリアするように変更
		// このメソッドは GameFrame の run メソッド内で呼び出される
		Graphics g = getImageGraphics();
		if (g != null) {
			g.setColor(bgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		// ここでは repaint() を呼び出さない
		// repaint() は GameFrame の run メソッドの描画ループの最後に一度だけ呼び出す
	}
 
	public synchronized void fillOval(int x, int y, int w, int h) {
		Graphics g = getImageGraphics();
		if (g != null) {
			g.setColor(col);
			g.fillOval(x, y, w, h);
		}
	}
 
	private Graphics getImageGraphics() {
		return im.getGraphics();
	}
 
	/**
	 * 描画色を指定する。
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void setColor(int red, int green, int blue) {
		if (red < 0)
			red = 0;
		if (red > 255)
			red = 255;
		if (green < 0)
			green = 0;
		if (green > 255)
			green = 255;
		if (blue < 0)
			blue = 0;
		if (blue > 255)
			blue = 255;
		col = new Color(red, green, blue);
	}
 
	/**
	 * 一定時間待つ
	 * @param time
	 */
	public void sleep(double time) {
		try {
			Thread.sleep((int) (time * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * アニメーションを行う時は、学習者がrunメソッドをオーバライドする
	 */
	public void run() {
		// この MyFrame の run はデフォルトの実装。
		// GameFrame でオーバーライドされているため、通常はここの処理は実行されない。
	}
 
	public synchronized void fillOval(double x, double y, double w, double h) {
		fillOval((int) x, (int) y, (int) w, (int) h);
	}
 
	public synchronized void drawString(String str, int x, int y, int size) {
		Graphics g = getImageGraphics();
		if (g != null) {
			g.setColor(col);
			g.setFont(new Font("Monospaced", 0, size));
			g.drawString(str, x, y);
		}
	}
}
 