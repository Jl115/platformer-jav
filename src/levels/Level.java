package levels;

public class Level {
    private int [][] lvlDat;

    public Level(int[][] lvlDat){
        this.lvlDat = lvlDat;
    }
    public int getSpriteI(int x, int y){
        return lvlDat[y][x];
    }
    public int [][]getLvlDat(){
        return lvlDat;
    }
}
