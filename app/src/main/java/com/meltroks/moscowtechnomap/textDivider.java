package com.meltroks.moscowtechnomap;

final public class textDivider { // класс парсинга текста и анализа его

    public static String text;
    public static String[] lines = new String[50];
    static int i = 0;

    public static void parse(){ // разделяет текст по \n на отдельные лайны
        for(String retval : text.split("\n")){
            if(retval != "") {
                lines[i] = retval;
                i++;
            }
        }

        int j = 0;
        for(int i = 2; i < 20; i+=4){ // отсюда в DataContainer идет
                DataContainer.EventTitles[j] = lines[i]; // линия с названием мероприятия
                DataContainer.Descriptions[j] = lines[i+1]; // линия с описанием мероприятия
                DataContainer.ActiveSpots[j] = lines[i+2]; // линия с названием актвной точки, на которой будет проходить ивент
                j++;
        }

    }
}
