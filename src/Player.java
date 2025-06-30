import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener{
	static int life = 5;
	public int invincibleTimer = 0;

	public void draw(MyFrame f) {
	    if (invincibleTimer == 0 || invincibleTimer % 6 < 3) {
	        // 本体（緑の体）
	        f.setColor(0, 128, 0);
	        f.fillRect(x, y + 20, 30, 10);

	        // 頭（白）
	        f.setColor(255, 255, 255);
	        f.fillRect(x + 5, y, 20, 20);

	        // 武器など
	        f.setColor(200, 200, 200);
	        f.fillRect(x + 10, y, 10, 30);
	    }
	}
		
		
	
	public  Player(double x, double y, double vx, double vy) {
		super(x,y,vx,vy);
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			vx=-5;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT|| e.getKeyCode()==KeyEvent.VK_D) {
			vx=5;
		}

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			GameWorld.playerBullets.add(
					new PlayerBullet(x,y,5,-10));
			GameWorld.playerBullets.add(
					new PlayerBullet(x,y,-5,-10));
			GameWorld.playerBullets.add(   
					new PlayerBullet(x,y,0,-10));

			System.out.println("弾の数="+GameWorld.playerBullets.size());
            System.out.println("弾を発射！");
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println("enterキーが押されました");
			
			GameWorld.enterPressed=true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) {
			vx=0;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) {
			vx=0;
		}
	}
	public void keyTyped(KeyEvent e) {

	}
	public void move() {
		
		// 無敵タイマーを毎フレーム減らす
        if (invincibleTimer > 0) {
            invincibleTimer--;
        }

		super.move();
		if(x<0) x=0;
		if(x>370) x=370;
	}
	public void damage(int amount) {
	    if (invincibleTimer == 0) {
	        life -= amount;
	        invincibleTimer = 60; // 無敵時間（約1秒）
	        System.out.println("プレイヤーのHP: " + life);
	        if (life <= 0) {
	            System.out.println("やられた！");
	            y = -1000;
	        }
	    }
	}
	public boolean isInvincible() {
        return invincibleTimer > 0;
    }

}

