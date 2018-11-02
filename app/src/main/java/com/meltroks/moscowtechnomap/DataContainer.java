package com.meltroks.moscowtechnomap;

public final class DataContainer {

    // класс, содержащий в себе данные - база данных

    public static String[] SpotTitles = { "Dissident", "СМЕНА 2.0", "PLUTON", "MARS", "НИИ"}; // база Имен точек

    public static double[] SpotX = { 55.760035, 55.741571, 55.753760, 55.768973, 55.750013 }; // база Х

    public static double[] SpotY = { 37.647172, 37.660048, 37.671202, 37.627030, 37.665244 }; // база Y

    public static String[] ActiveSpots = new String[5]; // активные Имена точек (тянутся из вк)

    public static String[] Descriptions = new String[5]; // описания ивентов (тянутся из вк)

    public static String[] EventTitles = new String[5]; // имена Ивентов (активные имена, идут из вк)

    public static boolean isTurned = false; // переключатель, который мне не помог
}
