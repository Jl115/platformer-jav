package Entity;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.MethHelper.*;
import static utils.constant.PlayerConstants.*;


public class Player extends entity {
    private BufferedImage[][] animation;
    private int [][] lvlData;

    //Actions Player/ Enemies/ Npc
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = STAND;
    private boolean left, right, up, down, jump;
    private boolean moving = false, attack = false;

    //Grav/Jumping variables
    private float airS = 0f;
    private float grav = 0.04f * Game.Scale;
    private float jumpS = -2.25f * Game.Scale;
    private boolean inAir = false;
    private float fallSpeedAfterCollision = 0.5f * Game.Scale;

    //Mov speed Variables
    public float PlayerSpeed = 1.0f * Game.Scale;
    private float xDrawOffset = 21* Game.Scale;
    private float yDrawOffset = 4* Game.Scale;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAni();
        initHBox(x,y, (int)(20* Game.Scale),(int)( 27*Game.Scale));

    }

    public void update() {
        updatePos();//needs to be over setAni
        updateAniTick();
        setAni();

    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawImage(animation[playerAction][aniIndex],(int)(HBox.x - xDrawOffset)-xLvlOffset, (int) (HBox.y - yDrawOffset)-yLvlOffset, width, height, null);
        drawHBox(g);
    }

    private void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attack = false;
            }
        }
    }

    private void setAni() {
        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
         else
            playerAction = STAND;

        if (inAir) {
            if (airS < 0)
                playerAction = JUMP;
            else
                playerAction = FALL;
        }
        if (attack)
            playerAction = ATTACK1;

        if (startAni != playerAction)
            resetAnitick();

    }

    private void resetAnitick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;
        if (jump)
            jump();
        if (!inAir)
            if ((!left && !right) || (right&&left))
            return;

        float xSpeed = 0;


        if (left)
            xSpeed -= PlayerSpeed;
        if (right)
            xSpeed += PlayerSpeed;
        if (!inAir)
            if (!IsEntityOnFloor(HBox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveH(HBox.x, HBox.y + airS, HBox.width, HBox.height, lvlData)) {
                HBox.y += airS;
                airS += grav;
                updateXP(xSpeed);
            } else {
                HBox.y = GetEntityUnderAboveFloor(HBox, airS);
                if (airS > 0)
                    resetInAir();
                 else
                    airS = fallSpeedAfterCollision;

                updateXP(xSpeed);

            }
        } else
            updateXP(xSpeed);
        moving = true;

    }
    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airS =jumpS;
    }

    private void resetInAir() {
        inAir = false;
        airS = 0;

    }
    private void updateXP(float xSpeed) {
        if(CanMoveH(HBox.x + xSpeed, HBox.y, HBox.width, HBox.height, lvlData)){
            HBox.x+= xSpeed;
        }else{
            HBox.x = GetEntXPosNextWall(HBox, xSpeed);
        }
    }
    private void loadAni() {
            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Player_Atlas);

            //       Grid for SpritesSheet y=9 x= 6 !
            animation = new BufferedImage[9][6];
            for (int j = 0; j < animation.length; j++)
                for (int i = 0; i < animation[j].length; i++) {
                    animation[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
                }
    }
    public void loadLvlD(int [] [] lvlData){
        this.lvlData = lvlData;
        if(!IsEntityOnFloor(HBox,lvlData)){
            inAir= true;
        }
    }

    // Movment/ atacking
    public void resetDirBool() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    public void setJump(boolean jump){
        this.jump = jump;

    }
}