package dk.four.group.player;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;

public class Bullet {
	Entity entity;
        GameData gameData;
        
        private float x = entity.getX();
        private float y = entity.getY();
        private float radians = entity.getRadians();
        private float dx = entity.getDx();
        private float dy = entity.getDy();
        private float width = gameData.getDisplayWidth();
        private float height = gameData.getDisplayHeight();
	private float lifeTime;
	private float lifeTimer;
	      
	private boolean remove;
	
	public Bullet() {
		
		float speed = 350;
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
		
		width = height = 2;
		
		lifeTimer = 0;
		lifeTime = 1;
		
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update(float dt) {
		
		x += dx * dt;
		y += dy * dt;
		
                if(x > gameData.getDisplayWidth()){
                x = gameData.getDisplayWidth();
                }else if(x < 0){
                    x = 0;
                }

                y += dy * dt;
                if(y > gameData.getDisplayHeight()){
                    y = gameData.getDisplayHeight();
                }else if(y < 0){
                    y = 0;
                }
		
		lifeTimer += dt;
		if(lifeTimer > lifeTime) {
			remove = true;
		}
		
	}
	
	public void draw(ShapeRenderer sr) {
		sr.setColor(1, 1, 1, 1);
		sr.begin(ShapeType.Filled);
		sr.circle(x - width / 2, y - height / 2, width / 2);
		sr.end();
	}
	
}


















