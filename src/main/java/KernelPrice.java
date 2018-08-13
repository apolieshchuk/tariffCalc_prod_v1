public class KernelPrice {


    public int getPrice(int km){
        int price;

        if(km >= 0 && km <=20){
            price = 100;
        }else if(km >= 21 && km <=25){
            price = 106;
        }else if(km >= 26 && km <=30){
            price = 113;
        }else if(km >= 31 && km <=35){
            price = 119;
        }else if(km >= 36 && km <=40){
            price = 125;
        }else if(km >= 41 && km <=45){
            price = 131;
        }else if(km >= 46 && km <=50){
            price = 138;
        }else if(km >= 51 && km <=55){
            price = 144;
        }else if(km >= 56 && km <=60){
            price = 150;
        }else if(km >= 61 && km <=65){
            price = 156;
        }else if(km >= 66 && km <=70){
            price = 163;
        }else if(km >= 71 && km <=75){
            price = 169;
        }else if(km >= 76 && km <=80){
            price = 175;
        }else if(km >= 81 && km <=85){
            price = 181;
        }else if(km >= 86 && km <=90){
            price = 188;
        }else if(km >= 91 && km <=95){
            price = 194;
        }else if(km >= 96 && km <=100){
            price = 200;
        }else if(km >= 101 && km <=105){
            price = 206;
        }else if(km >= 106 && km <=110){
            price = 214;
        }else if(km >= 111 && km <=115){
            price = 224;
        }else if(km >= 116 && km <=120){
            price = 229;
        }else if(km >= 121 && km <=125){
            price = 238;
        }else if(km >= 126 && km <=130){
            price = 241;
        }else if(km >= 131 && km <=135){
            price = 246;
        }else if(km >= 136 && km <=140){
            price = 254;
        }else if(km >= 141 && km <=145){
            price = 262;
        }else if(km >= 146 && km <=150){
            price = 268;
        }else if(km >= 151 && km <=155){
            price = 276;
        }else if(km >= 156 && km <=160){
            price = 285;
        }else if(km >= 161 && km <=165){
            price = 294;
        }else if(km >= 166 && km <=170){
            price = 302;
        }else if(km >= 171 && km <=175){
            price = 312;
        }else if(km >= 176 && km <=180){
            price = 321;
        }else if(km >= 181 && km <=185){
            price = 330;
        }else if(km >= 186 && km <=190){
            price = 338;
        }else if(km >= 191 && km <=195){
            price = 347;
        }else if(km >= 196 && km <=200){
            price = 353;
        }else if(km >= 201 && km <=205){
            price = 359;
        }else if(km >= 206 && km <= 210){
            price = 368;
        }else if(km >= 211 && km <=215){
            price = 377;
        }else if(km >= 216 && km <= 220){
            price = 385;
        }else if(km >= 221 && km <=225){
            price = 394;
        }else if(km >= 226 && km <= 230){
            price = 402;
        }else if(km >= 231 && km <=235){
            price = 412;
        }else if(km >= 236 && km <= 240){
            price = 420;
        }else if(km >= 241 && km <=245){
            price = 429;
        }else if(km >= 246 && km <= 250){
            price = 438;
        }else if(km >= 251 && km <=260){
            price = 442;
        }else if(km >= 261 && km <=270){
            price = 459;
        }else if(km >= 271 && km <=280){
            price = 477;
        }else if(km >= 281 && km <=290){
            price = 494;
        }else if(km >= 291 && km <=300){
            price = 510;
        }else if(km >= 301 && km <=310){
            price = 516;
        }else if(km >= 311 && km <=320){
            price = 522;
        }else if(km >= 321 && km <=330){
            price = 535;
        }else if(km >= 331 && km <=340){
            price = 550;
        }else if(km >= 341 && km <=350){
            price = 560;
        }else if(km >= 351 && km <=360){
            price = 570;
        }else if(km >= 361 && km <=370){
            price = 580;
        }else if(km >= 371 && km <=380){
            price = 590;
        }else if(km >= 381 && km <=390){
            price = 600;
        }else if(km >= 391 && km <=400){
            price = 620;
        }else if(km >= 401 && km <=425){
            price = 638;
        }else if(km >= 426 && km <=450){
            price = 675;
        }else if(km >= 451 && km <=475){
            price = 711;
        }else if(km >= 476 && km <=500){
            price = 749;
        }else if(km >= 501 && km <=550){
            price = 785;
        }else if(km >= 551 && km <=600){
            price = 846;
        }else if(km >= 601 && km <=650){
            price = 919;
        }else if(km >= 651 && km <=700){
            price = 984;
        }else if(km >= 701 && km <=750){
            price = 1039;
        }else if(km >= 751 && km <=800){
            price = 1104;
        }else if(km >= 801 && km <=850){
            price = 1159;
        }else if(km >= 851 && km <=900){
            price = 1215;
        }else if(km >= 901 && km <=950){
            price = 1290;
        }else if(km >= 951 && km <=1000){
            price = 1350;
        }else if(km > 1000){
            price = (int)(km*1.5);
        }else{
            price = 0;
        }

        return price;
    }

}
