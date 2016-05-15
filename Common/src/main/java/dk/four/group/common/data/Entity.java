package dk.four.group.common.data;

import java.io.Serializable;
import java.util.UUID;

public final class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private float dx;
    private float dy;
    private float radians;
    private float maxSpeed;
    private float acceleration;
    private float deacceleration;
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private int rotationSpeed;
    private int life;
    private float radius;
    private boolean isHit = false;
    private float expiration;
    private float size;
    
    private Asset asset;
    private EntityBody body;
    private EntityPosition eP;
    
    public void setSize(float size){
        this.size = size;
    }
    
    public float getSize(){
        return size;
    }
    
    public void setEntityPosition(float x, float y){
        this.eP = new EntityPosition(x, y);
    }
    
    public EntityPosition getEntityPosition(){
        return eP;
    }
    
    public void reduceExpiration(float delta) {
        this.expiration -= delta;
    }

    public float getExpiration() {
        return expiration;
    }
    
    public EntityBody getEntityBody(){
            return body;
    }
    
    public void setEntityBody(EntityBody eb){
        this.body = eb;
    }

    public void setExpiration(float value) {
        this.expiration = value;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean hit) {
        this.isHit = hit;
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    public float getRadius() {
        return radius;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getID() {
        return ID.toString();
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeacceleration() {
        return deacceleration;
    }

    public void setDeacceleration(float deacceleration) {
        this.deacceleration = deacceleration;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    
    public Asset getAsset(){
        return asset;
    }
    
    public void setAsset(Asset a){
        this.asset = a;
    }

}
