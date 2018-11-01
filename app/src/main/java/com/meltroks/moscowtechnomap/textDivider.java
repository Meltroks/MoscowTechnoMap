package com.meltroks.moscowtechnomap;

final public class textDivider {

    public static String text;
    public static String[] lines = new String[50];
    static int i = 0;

    public static void parse(){
        for(String retval : text.split("\n")){
            if(retval != "") {
                lines[i] = retval;
                i++;
            }
        }

        int j = 0;
        for(int i = 2; i < 20; i+=4){
                DataContainer.EventTitles[j] = lines[i];
                DataContainer.Descriptions[j] = lines[i+1];
                DataContainer.ActiveSpots[j] = lines[i+2];
                j++;
        }

    }
}
