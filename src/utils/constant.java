package utils;

import main.Game;

public class constant {
    public  class UI {
        public static class Buttons{
            public static final int B_Width_D= 140;
            public static final int B_Height_D= 56;
            public static final int B_Width = (int) (B_Width_D * Game.Scale);
            public static final int B_Height = (int) (B_Height_D * Game.Scale);
        }
        public static class PauseButtons{
            public static final int SoundSizedDefault= 42;
            public static final int SoundSized = (int)(SoundSizedDefault*Game.Scale);
        }
        public static class PauseMenuB{
            public static final int PmB_DefaultSize = 56;
            public static final int PmB_Size = (int)(PmB_DefaultSize*Game.Scale);
        }
        public class VolumeB{
            public static final int Volume_Default_Width = 28;
            public static final int Volume_Default_Height = 44;
            public static final int Slider_Default_Width = 215;
            public static final int Volume_W = (int) (Volume_Default_Width * Game.Scale);
            public static final int Volume_H = (int) (Volume_Default_Height * Game.Scale);
            public static final int Slider_W = (int) (Slider_Default_Width * Game.Scale);
        }
    }
    public static class Directions{
        public static final int Left = 0;
        public static final int Right = 1;
        public static final int Up = 2;
        public static final int Down = 3;


    }
    public static class PlayerConstants {
        public static final int     STAND  = 0;
        public static final int     CROUCH = 4;
        public static final int     RUNNING = 1;
        public static final int     HIT = 5;
        //public static final int     SLIDE    = 14;
        public static final int     JUMP = 2;
        public static final int     ATTACK1  = 6;
        public static final int     FALL = 3;
        public static final int     ATTACK2 = 7;
        public static final int     ATTACK3 = 8;
        //public static final int     WALL_L = 9;
        //public static final int     WALL_R =8;
        //public static final int     WIN_P = 15;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case STAND:
                    return 5;
                case CROUCH:
                    return 2;

                case RUNNING:
                    return 6;
                case HIT:
                    return 4;
                case ATTACK3:
                    return 3;
                case JUMP:
                    return 3;
                case ATTACK1:
                    return 3;
                case ATTACK2:
                    return 3;
                case FALL:
                    return 1;
                //case DIE:
                  //  return 20;
                //case WIN_P:
                  //  return 20;
                //case WALL_L:
                  //  return 20;
                //case WALL_R:
                  //  return 20;
                default:
                    return 1;

            }
        }

    }
}
