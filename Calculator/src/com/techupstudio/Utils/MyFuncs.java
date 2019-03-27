package com.techupstudio.Utils;

import java.util.*;

public class MyFuncs {
/**
 * @author Bernard Azumah
 * @author www.techupstudio.com
 * @version 1.0
 * @since 1.0
 */

    private MyFuncs() { }

    public static Scanner Scan() { return new Scanner(System.in); }

    private static class CannotPrintObjectException extends Exception {
        public CannotPrintObjectException(){
            super("\nCannotPrintObjectException :: Object may be null or a user defined class." +
                    "(Tip : Try converting object to String... )\\n");
        }
    }

    private static class CannotConvertToObjectException extends Exception {
        public CannotConvertToObjectException() {
            super("\nCannotConvertToObjectException :: Object value may conflict\n");
        }
    }

    private static class CannotSetTimeValueException extends Exception {
        CannotSetTimeValueException() {
            super("Cannot Set A value to Time Object :: Cheack that HOURS > 24, " +
                    "MINUTES > 60, SECONDS > 60 , MILISECONDS > 1000");
        }
    }

    private static class CannotSetPrinterAlignmentException extends Exception {
        CannotSetPrinterAlignmentException(String value) {
            super("Cannot Set Alignment using "+value+" :: Cheack that the value is " +
                    "NORMAL(N), CENTER(C), LEFT(L), or RIGTH(R)");
        }
    }

    //######    Printer    ######
    //######    Printer    ######
    //######    Printer    ######
    //######    Printer    ######
    //######    Printer    ######

    public static class Printer{
        /**
        * This class makes using printStream very easy
        * When Creating new Object instance
        * @param String  startwith, seperation and endwith and Integer ConsoleLength
        * eg. Printer pprint = new Printer() for default settings
        * and  Printer pprint = new Printer("", ", ", ".\n")
        * after creating and instance you can set
        * startwith using the dot operator start(...),
        * or set set seperator with sep(...) and end(...) to change endwith
        **/
        private Integer consoleLenght = 150;
        private String printFormat = "";
        private String seperator = " ";
        private String startwith = "";
        private String endwith = "";

        Printer(){}
        Printer(String start,String sep,String end){ startwith = start;seperator = sep;endwith = end; }

        private String align(Object obj){
            if (printFormat.equals("normal")) {
                return  MyFuncs.alignNormal(obj, consoleLenght);
            } else if (printFormat.equals("center")) {
                return MyFuncs.alignCenter(obj, consoleLenght);
            } else if (printFormat.equals("left")) {
                return MyFuncs.alignLeft(obj, consoleLenght);
            } else if (printFormat.equals("right")) {
                return MyFuncs.alignRight(obj, consoleLenght);
            } else {
                return obj.toString();
            }
        }

        //##### class variable setter #####
        public Printer sep(String using){ this.seperator = using ; return this;}
        public Printer start(String using){ this.startwith = using ; return this;}
        public Printer end(String using){ this.endwith = using ;return this;}
        public Printer setConsoleLength(Integer value){ this.consoleLenght = value; return this; }
        public Printer resetAlignment(){ this.printFormat = ""; return this; }
        public Printer alignNormal(){ this.printFormat = "normal"; return this; }
        public Printer alignCenter(){ this.printFormat = "center"; return this; }
        public Printer alignLeft(){ this.printFormat = "left"; return this; }
        public Printer alignRight(){ this.printFormat = "right"; return this; }


        public void print(String[] objects){ print((Object[]) objects);}
        public void print(Integer[] objects){ print((Object[]) objects); }
        public void print(Float[] objects){ print((Object[]) objects); }
        public void print(Double[] objects){ print((Object[]) objects); }
        public void print(Long[] objects){ print((Object[]) objects); }
        public void print(Character[] objects){ print((Object[]) objects); }

        //### Main Print Functions ###

        public void print(Object object){
            String line = startwith + object + endwith;
            System.out.print(align(line));
        }

        public void print(double[] objects) {
            try {
                if(len(objects) > 0) {
                    for (int i : range(len(objects))){
                        String line = startwith + toRound(objects[i]) + ((i == len(objects)-1) ? "" : seperator);
                        System.out.print(align(line));
                    }
                    System.out.print(endwith);
                }
                else{
                    print("\n");
                }
            } catch (Exception e) {
                try {
                    throw new CannotPrintObjectException();
                } catch (CannotPrintObjectException e1) {
                    e1.printStackTrace();
                }
            }
        }

        public void print(Object... objects){
            try {
                if(len(objects) > 0) {
                    for (int i : range(len(objects))){
                        String line = startwith + objects[i] + ((i == len(objects)-1) ? "" : seperator);
                        System.out.print(align(line));
                    }
                    System.out.print(endwith);
                }
                else{
                    print("\n");
                }
            } catch (Exception e) {
                try {
                    throw new CannotPrintObjectException();
                } catch (CannotPrintObjectException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }

    //## Default Printer Onjects ##
    public static Printer printer = new Printer();
    public static Printer printerln = new Printer("", " ", "\n");

    public static void print(double[] objects){ printer.print(objects); }
    public static void print(String[] objects){ printer.print(objects); }
    public static void print(Integer[] objects){ printer.print(objects); }
    public static void print(Float[] objects){ printer.print(objects); }
    public static void print(Double[] objects){ printer.print(objects); }
    public static void print(Long[] objects){ printer.print(objects); }
    public static void print(Character[] objects){ printer.print(objects); }
    public static void print(Object objects){ printer.print(objects); }
    public static void print(Object... objects){ printer.print(objects); }

    public static void println(double[] objects){ printerln.print(objects); }
    public static void println(String[] objects){ printerln.print(objects); }
    public static void println(Integer[] objects){ printerln.print(objects); }
    public static void println(Float[] objects){ printerln.print(objects); }
    public static void println(Double[] objects){ printerln.print(objects); }
    public static void println(Long[] objects){ printerln.print(objects); }
    public static void println(Character[] objects){ printerln.print(objects); }
    public static void println(Object objects){ printerln.print(objects); }
    public static void println(Object... objects){ printerln.print(objects); }

    //##### End of Printers #####
    //##### End of Printers #####
    //##### End of Printers #####
    //##### End of Printers #####
    //##### End of Printers #####


    //##### TESTERS #####
    //##### TESTERS #####
    //##### TESTERS #####

    public static class Test{
        static int size;
        //calss with no constructor calling
        private Test(){}

        //static initializer
        static{
            size = 0;
        }

        public static void test(){
        }

        public static void exit(){
        }

    }

    public static class Tester extends Test{

    }

    //### END ###//### END ###//### END ###

    //######  Input Functions  ######
    //######  Input Functions  ######
    //######  Input Functions  ######
    //######  Input Functions  ######
    //######  Input Functions  ######

    public static Integer inputInt(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        Integer value;
        Scanner input = Scan();
        while (!input.hasNextInt()) {
            print("( invalid input )  enter number(s) : ");
            return inputInt("");
        }
        value = input.nextInt();
        return value;
    }

    public static String inputStr(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        String value;
        Scanner input = Scan();
        value = input.next();
        return value;
    }

    public static String getStr(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        String value;
        Scanner input = Scan();
        value = input.nextLine();
        return value;
    }

    public static Character inputChar(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        String value;
        Scanner input = Scan();
        value = input.next();
        return value.charAt(0);
    }

    public static Boolean inputBool(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        String value;
        Scanner input = Scan();
        value = input.next().toLowerCase();
        if (!(value.equals("true") || value.equals("false") || value.equals("t") || value.equals("f"))) {
            return inputBool("( invalid input )  enter boolean : ");
        }
        if (value.equals("true") || value.equals("t")) {
            return true;
        } else {
            return false;
        }
    }

    public static Float inputFloat(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        Float value;
        Scanner input = Scan();
        if (!input.hasNextFloat()) {
            return inputFloat("( invalid input )  enter number(s) : ");
        }
        value = input.nextFloat();
        return value;
    }

    public static Double inputDouble(String show) {
        switch (show) {
            case "":
                break;
            case "default":
                print("Enter a value : ");
                break;
            default:
                print(show);
                break;
        }
        Double value;
        Scanner input = Scan();
        if (!input.hasNextDouble()) {
            return inputDouble("( invalid input )  enter number(s) : ");
        }
        value = input.nextDouble();
        return value;
    }

    //##### End of Input Functions #####
    //##### End of Input Functions #####
    //##### End of Input Functions #####
    //##### End of Input Functions #####
    //##### End of Input Functions #####


    //#### Object Convertor Functions #####
    //#### Object Convertor Functions #####
    //#### Object Convertor Functions #####
    //#### Object Convertor Functions #####
    //#### Object Convertor Functions #####


    public static String toStrings(Object obj) { return obj.toString(); }

    public static Character toCharacter(Object obj) { return Character.valueOf((char) obj.hashCode()); }

    public static Object getNum(Object obj) {
        if (obj.toString().contains(".0")){
            return toInteger(obj);
        }
        return obj;
    }

    public static Integer toInteger(Object obj){
        try {
            if (obj.getClass().getSimpleName().equalsIgnoreCase("Boolean")){
                if (obj.toString().equals("true")){
                    return  1;
                }
                return 0;
            }
            else if (obj.getClass().getSimpleName().equalsIgnoreCase("String")){
                if (obj.toString().equals("true")){
                    return  Integer.valueOf(obj.toString());
                }
                return Integer.valueOf(obj.toString());
            }
            else{
                return toRound(Double.valueOf(toDouble(obj)));
            }

        }
        catch(Exception e) {
            try {
                throw new CannotConvertToObjectException();
            } catch (CannotConvertToObjectException cnc) {
                cnc.printStackTrace();
            }
        }
        return -1;
    }

    public static Double toDouble(Object obj){

        try {
            return Double.valueOf(obj.toString());
        }
        catch(Exception e) {
            try {
                throw new CannotConvertToObjectException();
            } catch (CannotConvertToObjectException cnc) {
                cnc.printStackTrace();
            }
        }
        return (double) -1;
    }

    public static Float toFloat(Object obj){
        try {
            return Float.valueOf(obj.toString());
        }
        catch(Exception e) {
            try {
                throw new CannotConvertToObjectException();
            } catch (CannotConvertToObjectException cnc) {
                cnc.printStackTrace();
            }
        }
        return (float) -1;
    }

    public static Boolean toBoolean(Object obj){
        try {
            if (obj.toString().equals("1")){
                return true;
            }
            return Boolean.valueOf(obj.toString());
        }
        catch(Exception e) {
            try {
                throw new CannotConvertToObjectException();
            } catch (CannotConvertToObjectException cnc) {
                cnc.printStackTrace();
            }
        }
        return false;
    }

    public static Integer toRound(Object obj){
        try {
            return (int) Math.round(toDouble(obj));
        }
        catch(Exception e) {
            try {
                throw new CannotConvertToObjectException();
            } catch (CannotConvertToObjectException cnc) {
                cnc.printStackTrace();
            }
        }
        return -1;
    }

    public static Integer[] toInteger(Object[] obj){
        Integer[] retArr = new Integer[len(obj)];
        for (int i=0;i<len(obj);i++){
           retArr[i] = toInteger(obj[i]);
        }
        return retArr;
    }

    public static Integer[] toRound(Object[] obj){
        Integer[] retArr = new Integer[len(obj)];
        for (int i=0;i<len(obj);i++){
           retArr[i] = toRound(obj[i]);
        }
        return retArr;
    }

    public static Double[] toDouble(Object[] obj){
        Double[] retArr = new Double[len(obj)];
        for (int i=0;i<len(obj);i++){
           retArr[i] = toDouble(obj[i]);
        }
        return retArr;
    }

    public static Float[] toFloat(Object[] obj){
        Float[] retArr = new Float[len(obj)];
        for (int i=0;i<len(obj);i++){
           retArr[i] = toFloat(obj[i]);
        }
        return retArr;
    }

    public static Boolean[] toBoolean(Object[] obj){
        Boolean[] retArr = new Boolean[len(obj)];
        for (int i=0;i<len(obj);i++){
           retArr[i] = toBoolean(obj[i]);
        }
        return retArr;
    }

    public static Character[] toCharacter(Object[] obj){
        Character[] retArr = new Character[len(obj)];
        for (int i=0;i<len(obj);i++){
            retArr[i] = toCharacter(obj[i]);
        }
        return retArr;
    }

    public static String[] toStrings(Object[] obj){
        String[] retArr = new String[len(obj)];
        for (int i=0;i<len(obj);i++){
            retArr[i] = toStrings(obj[i]);
        }
        return retArr;
    }

    public static List<Integer> toInteger(List obj){
        List<Integer> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
           retArr.add(toInteger(obj.get(i)));
        }
        return retArr;
    }

    public static List<Integer> toRound(List obj){
        List<Integer> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
           retArr.add(toRound(obj.get(i)));
        }
        return retArr;
    }

    public static List<Double> toDouble(List obj){
        List<Double> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
            retArr.add(toDouble(obj.get(i)));
        }
        return retArr;
    }

    public static List<Float> toFloat(List obj){
        List<Float> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
            retArr.add(toFloat(obj.get(i)));
        }
        return retArr;
    }

    public static List<Boolean> toBoolean(List obj){
        List<Boolean> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
            retArr.add(toBoolean(obj.get(i)));
        }
        return retArr;
    }

    public static List<Character> toCharacter(List obj){
        List<Character> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
            retArr.add(toCharacter(obj.get(i)));
        }
        return retArr;
    }

    public static List<String> toString(List obj){
        List<String> retArr = new ArrayList<>();
        for (int i=0;i<len(obj);i++){
            retArr.add(toStrings(obj.get(i)));
        }
        return retArr;
    }

     //##### End of Converters #####
     //##### End of Converters #####
     //##### End of Converters #####
     //##### End of Converters #####
     //##### End of Converters #####


    //##### Date Time Functions #####
    //##### Date Time Functions #####
    //##### Date Time Functions #####
    //##### Date Time Functions #####
    //##### Date Time Functions #####

    public static class Time extends Thread {
        private Integer HOURS = 0;
        private Integer MINUTES = 0;
        private Integer SECONDS = 0;
        private Integer MILISECONDS = 0;
        private Boolean SHOWTIME = true;
        private Boolean SHOWCOUNTDOWN = true;
        private Boolean RUNNER = true;
        private Boolean COUNTDOWN_RUNNER = true;

        Time() {
        }


        Time(Time object){ setTime(object); }
        Time(int hours, int minutes, int seconds) { setTime(hours,minutes,seconds); }
        Time(int hours, int minutes, int seconds, int milliseconds) {
            setTime(hours,minutes,seconds,milliseconds);
        }

        public void setTime(int hours, int minutes, int seconds, int milliseconds){
            HOURS =  (hours < 24) ? hours : null ;
            MINUTES = (minutes < 60) ? minutes : null ;
            SECONDS = (seconds < 60) ? seconds : null ;
            MILISECONDS = (milliseconds < 1000) ? milliseconds : null ;
            if (HOURS == null || MINUTES == null || SECONDS == null || MILISECONDS == null) {
                try {
                    throw new CannotSetTimeValueException();
                } catch (CannotSetTimeValueException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setTime(int hours, int minutes, int seconds){
            HOURS =  (hours < 24) ? hours : null ;
            MINUTES = (minutes < 60) ? minutes : null ;
            SECONDS = (seconds < 60) ? seconds : null ;
            if (HOURS == null || MINUTES == null || SECONDS == null || MILISECONDS == null) {
                try {
                    throw new CannotSetTimeValueException();
                } catch (CannotSetTimeValueException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setTime(Time object){
            HOURS =  (object.getHOURS() < 24) ? object.getHOURS() : null ;
            MINUTES = (object.getMINUTES() < 60) ? object.getMINUTES() : null ;
            SECONDS = (object.getSECONDS() < 60) ? object.getSECONDS() : null ;
            MILISECONDS = (object.getMILISECONDS() < 1000) ? object.getMILISECONDS() : null ;
            if (HOURS == null || MINUTES == null || SECONDS == null || MILISECONDS == null) {
                try {
                    throw new CannotSetTimeValueException();
                } catch (CannotSetTimeValueException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean showTime(){ return SHOWTIME; }
        public void showTIMEon(){ SHOWTIME = true; }
        public void showTIMEoff(){ SHOWTIME = false; }
        private boolean showCD(){ return SHOWCOUNTDOWN; }
        public void showCDon(){ SHOWTIME = true; }
        public void showCDoff(){ SHOWTIME = false; }

        public Integer getHOURS() { return HOURS; }

        public Integer getMINUTES() { return MINUTES; }

        public Integer getSECONDS() { return SECONDS; }

        public Integer getMILISECONDS() { return MILISECONDS; }

        public String getTIME() { return format("Time :: <> : <> : <> : <>",
                round(HOURS) , round(MINUTES), round(SECONDS),round(MILISECONDS)); }

        public void setHOURS(Integer hours) { HOURS = hours; }

        public void setMINUTES(Integer minutes) { MINUTES = minutes; }

        public void setSECONDS(Integer seconds) { SECONDS = seconds; }

        public void setMILISECONDS(Integer miliseconds) { MILISECONDS = miliseconds; }

        public void run(){
            resumeIt();
            while (RUNNER) {
                MILISECONDS++;
                if (MILISECONDS < 1000) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    MILISECONDS = 0;
                    SECONDS++;
                    if (SECONDS == 60) {
                        SECONDS = 0;
                        MINUTES++;
                        if (MINUTES == 60) {
                            MINUTES = 0;
                            HOURS++;
                            if (HOURS >= 24) {
                                HOURS = 0;
                            }
                        }
                    }
                    if (showTime()) {
                        println(format("CurrentTime :: <> : <> : <>",
                                round(HOURS) , round(MINUTES), round(SECONDS)));
                    }
                }
            }
        }

        public void countDown(){
            resumeCD();
            while (COUNTDOWN_RUNNER) {
                MILISECONDS++;
                if (MILISECONDS < 1000) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    MILISECONDS = 0;
                    if (SECONDS == 0) {
                        SECONDS = 59;
                        if (MINUTES == 0) {
                            MINUTES = 59 ;
                            if (HOURS == 0) {
                                println("Done");break;
                            }
                            else{
                                HOURS--;
                            }
                        }else{
                            MINUTES--;
                        }
                    }else{
                        SECONDS--;
                    }
                    if (showCD()) {
                        println(format("CountDown :: <> : <> : <>",
                                round(HOURS) , round(MINUTES), round(SECONDS)));
                    }
                }
            }
        }

        public String round(Object obj){
            if (obj.toString().length() == 1){
                return "0"+obj.toString();
            }
            return obj.toString();
        }

        public void pauseIt(){
            RUNNER = false;
        }

        public void resumeIt(){
            RUNNER = true;
        }

        public void stopIt(){
            RUNNER = false;reset();
            this.interrupt();
        }

        public void pauseCD(){
            COUNTDOWN_RUNNER = false;
        }

        public void resumeCD(){
            COUNTDOWN_RUNNER = true;
        }

        public void stopCD(){
            COUNTDOWN_RUNNER = false;reset();
            this.interrupt();
        }

        public void reset(){
            HOURS = 0; MINUTES = 0; SECONDS = 0; MILISECONDS = 0;
        }

        public String toString(){
            return format("Time(H: <> ,M: <>, S: <>, MS: <>)",
                    round(HOURS) , round(MINUTES), round(SECONDS), round(MILISECONDS));
        }

        public boolean equals(Time object){
            if (getHOURS() == object.getHOURS() && getMINUTES() == object.getMINUTES() &&
                    getSECONDS() == object.getSECONDS() && getMILISECONDS() == object.getMILISECONDS()){
                return true;
            }
            return false;
        }

    }

    public static class Timing extends Thread{
        private Time Timer = new Time();

        public void run(){
            println("\nStarting Timer...\n");
            Timer.run();
        }

        public void pause(){
            println("\nPausing Timer...\n");
            Timer.pauseIt();
        }

        public void resumeit(){
            println("\nResuming Timer...\n");
            Timer.resumeIt();
        }

        public void reset(){
            println("\nResetting Timer...\n");
            Timer.reset();
        }

        public void stopIt(){
            println("\nStopping Timer...\n");
            Timer.reset();
        }
    }

    public static class StopWatch extends Thread{
        private Time Timer = new Time();

        StopWatch(){}

        StopWatch(Time object){ Timer.setTime(object); }

        StopWatch(int hours, int minutes, int seconds){ Timer.setTime(hours, minutes, seconds); }

        public void setCountDownTime(Time object){ Timer.setTime(object); }

        public void setCountDownTime(int hours, int minutes, int seconds){
            Timer.setTime(hours, minutes, seconds);
        }

        public void run(){
            println("\nStarting CountDown Timer...\n");
            Timer.countDown();
        }

        public void pause(){
            println("\nPausing CountDown Timer...\n");
            Timer.pauseCD();
        }

        public void resumeIt(){
            println("\nResuming CountDown Timer...\n");
            Timer.resumeIt();
        }

        public void reset(){
            println("\nResetting CountDown Timer...\n");
            Timer.reset();
        }

        public void stopIt(){
            println("\nStopping CountDown Timer...\n");
            Timer.stopIt();
        }

    }

//
//    public static class runner implements Runnable{
//        public void run(){
//            println("The DateTime Thread Class");
//        }
//    }

//    public static synchronized void abortThreads()
//    {
//        Thread[] threads = new Thread[Thread.activeCount()];
//        Thread.enumerate(threads);
//        for(Thread t : threads){
//            print(t);
//            t.interrupt();
//        }
//
//    }



    public static Date datetime(){
        return new Date();
    }

    public static String datetime(String... periods){
        if (periods.length == 1){
            if (periods[0].split(" ").length > 1){
                periods = periods[0].split(" ");
            }
        }

        String Date[] = date().split(" ");
        String timezone = time().split(" ")[1];
        String Time[] = time().split(" ")[0].split(":");
        String date = date(), day = Date[0], month = Date[1], dayNum = Date[2], year = Date[3];
        String time = time(), hour = Time[0], minutes = Time[1], seconds = Time[2];

        StringBuilder periodStr = new StringBuilder();

        for (String val : periods){
            if (val.equalsIgnoreCase("datetime") || val.equals("DT")){
                periodStr.append(date);
            }
            else if (val.equalsIgnoreCase("date") || val.equals("D")){
                periodStr.append(date);
            }
            else if (val.equalsIgnoreCase("time") || val.equals("T")){
                periodStr.append(time);
            }
            else if (val.equalsIgnoreCase("day") || val.equals("d")){
                periodStr.append(day);
            }
            else if (val.equalsIgnoreCase("day.number") || val.equals("dn")){
                periodStr.append(dayNum);
            }
            else if (val.equalsIgnoreCase("month") || val.equals("M")){
                periodStr.append(month);
            }
            else if (val.equalsIgnoreCase("year") || val.equalsIgnoreCase("y")){
                periodStr.append(year);
            }
            else if (val.equalsIgnoreCase("hour") || val.equalsIgnoreCase("h")){
                periodStr.append(hour);
            }
            else if (val.equalsIgnoreCase("minute") || val.equals("m")){
                periodStr.append(minutes);
            }
            else if (val.equalsIgnoreCase("second") || val.equalsIgnoreCase("s")){
                periodStr.append(seconds);
            }
            else if (val.equalsIgnoreCase("timezone") || val.equalsIgnoreCase("tz")){
                periodStr.append(timezone);
            }
            else{
                periodStr.append("<>");
            }
            if(!val.equals(periods[periods.length - 1])){
                periodStr.append(" ");
            }
        }
        return periodStr.toString();
    }

    public static String datetime(boolean isWithFormatting, String... periods) {
        String periodStr = "";
        if (periods.length == 1) {
            periodStr = periods[0];
        }
        else{
            for (String val : periods){
                periodStr += (val+" ");
            }
        }


        if (isWithFormatting) {
            String Date[] = date().split(" ");
            String timezone = time().split(" ")[1];
            String Time[] = time().split(" ")[0].split(":");
            String date = date(), day = Date[0], month = Date[1], dayNum = Date[2], year = Date[3];
            String time = time(), hour = Time[0], minutes = Time[1], seconds = Time[2];

            if (periodStr.contains("<DT>")){
                periodStr = periodStr.replace("<DT>", datetime().toString());
            }
            if (periodStr.contains("<D>")){
                periodStr = periodStr.replace("<D>", date);
            }
            if (periodStr.contains("<T>")){
                periodStr = periodStr.replace("<T>",time);
            }
            if (periodStr.contains ("<dn>")){
                periodStr = periodStr.replace("<dn>",dayNum);
            }
            if (periodStr.contains ("<d>")){
                periodStr = periodStr.replace("<d>",day);
            }
            if (periodStr.contains ("<M>")){
                periodStr = periodStr.replace("<M>",month);
            }
            if (periodStr.contains ("<y>")){
                periodStr = periodStr.replace("<y>",year);
            }
            if (periodStr.contains ("<h>")){
                periodStr = periodStr.replace("<h>",hour);
            }
            if (periodStr.contains ("<m>")){
                periodStr = periodStr.replace("<m>",minutes);
            }
            if (periodStr.contains ("<s>")){
                periodStr = periodStr.replace("<s>",seconds);
            }
            if (periodStr.contains ("<tz>")){
                periodStr = periodStr.replace("<tz>", timezone);
            }
            return periodStr;

        } else {
            return datetime(periods);
        }
    }

    public  static String date(){
        String[] datetime = datetime().toString().split(" ");
        return datetime[0] + " " + datetime[1] + " " + datetime[2] + " " + datetime[5];
    }

    public  static String time(){
        String[] datetime = datetime().toString().split(" ");
        return datetime[3] + " " + datetime[4];
    }

    //##### End of Date Time Functions #####
    //##### End of Date Time Functions #####
    //##### End of Date Time Functions #####
    //##### End of Date Time Functions #####
    //##### End of Date Time Functions #####


    //##### Math Functions #####
    //##### Math Functions #####
    //##### Math Functions #####
    //##### Math Functions #####
    //##### Math Functions #####

    public static Object power(Object number, Object exponent){
        Object obj = Math.pow(toDouble(number), toDouble(exponent));
        if (obj.toString().contains(".0")){
            return toInteger(obj);
        }
        return obj;
    }

    public static class raise_to_power{
        private double exponent;
        raise_to_power(double x){ exponent = x; }
        Object num(double number) {
            Object num = power(number, exponent);
            return getNum(num);
        }
    }

    //#####   len --- return length of oblject #####
    public static int len(double[] obj){ return obj.length; }
    public static int len(String[] obj){ return obj.length; }
    public static int len(Integer[] obj){ return obj.length; }
    public static int len(Float[] obj){ return obj.length; }
    public static int len(Double[] obj){ return obj.length; }
    public static int len(Long[] obj){ return obj.length; }
    public static int len(Character[] obj){ return obj.length; }
    public static int len(Object obj){ return obj.toString().length(); }
    public static int len(Object... obj){ return obj.length; }
    public static int len(List<?> obj){ return obj.size(); }
    public static int len(Map<?,?> obj){ return obj.size(); }


    public static Object squareRoot(double number){ return new raise_to_power(2).num(number); }

    public static Object cubeRoot(double number){ return new raise_to_power(3).num(number); }

    public static Object[] getFIB(double num_a, double num_b, double end){
        double sum = num_a + num_b;double first_num = num_b;double next_num = sum;
        List<Object> ret_list = new ArrayList<>();ret_list.add(num_a);ret_list.add(num_b);
        while (sum < end){
            ret_list.add(sum);
            sum = first_num + next_num;
            first_num = next_num;
            next_num = sum;
        }
        return ret_list.toArray();
    }

    public static Object getHCF(double num_a, double num_b){
        if (num_b != 0){
            return getHCF(num_b, num_a % num_b);
        }
        Object obj =  num_a;
        return getNum(num_a);
    }

    public static Object getLCM(double num_a, double num_b){
        Object ret_value;
        if (num_a == num_b){
            ret_value = num_a;
        }
        else if ((num_a % num_b == 0) || (num_b % num_a == 0)){
            if (num_a > num_b){ ret_value = num_a; }
            else{ ret_value = num_b; }
        }
        else{
            ret_value = (num_a * num_b)/toDouble(getHCF(num_a, num_b));
        }
        return getNum(ret_value);
    }

    public static Object getHCF(double... numbers){
        double hcf = toDouble(getHCF(numbers[0],numbers[1]));
        for (int i : range(2,numbers.length)){
            hcf = toDouble(getHCF(hcf, numbers[i]));
        }
        return getNum(hcf);
    }

    public static Object getLCM(double... numbers){
        double lcm = toDouble(getHCF(numbers[0],numbers[1]));
        for (int i : range(2,numbers.length)){
            lcm = toDouble(getLCM(lcm, numbers[i]));
        }
        return getNum(lcm);
    }

    protected static Object getHCF(Double[] numbers){
        double hcf = toDouble(getHCF(numbers[0],numbers[1]));
        for (int i : range(2,numbers.length)){
            hcf = toDouble(getHCF(hcf, numbers[i]));
        }
        return getNum(hcf);
    }

    protected static Object getLCM(Double[] numbers){
        double lcm = toDouble(getHCF(numbers[0],numbers[1]));
        for (int i : range(2,numbers.length)){
            lcm = toDouble(getLCM(lcm, numbers[i]));
        }
        return getNum(lcm);
    }

    public static double random(){ return Math.random(); }

    public static int randint(){
        //new Random().next...()
        int random1 = randint((int)(random() * 100) + 1,(int)(random() * 1000) + 1);
        int random2 = randint((int)(random() * 100) + 1,(int)(random() * 10000) + 1);
        return randint((int)(random() * random1) + 1,(int)(random() * random2) + 1);
    }

    public static int randint(int max){ return randint(0,max); }

    public static int randint(int min, int max){
        int result = (int) (random() * (max - min + 1))+min;
        return result;
    }

    public static Integer[] range(int max){
        if (max < 0){ return new Integer[]{}; }
        Integer[] ret_range = new Integer[max];
        for (int i=0;i<max;i++){
            ret_range[i] = i;
        }
        return ret_range;
    }

    public static Integer[] range(int min, int max){
        if (min < 0 || max < 0){ return new Integer[]{}; }
        int size = max-min;
        Integer[] ret_range = new Integer[size];
        for (int i=0;i<size;i++){
            ret_range[i] = i+min;
        }
        return ret_range;
    }

    public static Integer[] range(int min, int max, int step){
        if (min < 0 || max < 0 || step < 0){ return new Integer[]{}; }
        List<Integer> ret_list = new ArrayList<>();int i = 0;
        while ( (i+min+(i*(step-1))) < max){
            ret_list.add(i+min+(i*(step-1))); i++;
        }

        Integer[] ret_range = new Integer[len(ret_list)] ;
        ret_list.toArray(ret_range);
        return ret_range;
    }

    public static Integer[] randrange(int size){
        Integer[] ret_range = new Integer[size];
        for (int i=0;i<size;i++){
            ret_range[i] = randint();
        }
        return ret_range;
    }

    public static Integer[] randrange(int max, int size){
        Integer[] ret_range = new Integer[size];
        for (int i=0;i<size;i++){
            ret_range[i] = randint(max);
        }
        return ret_range;
    }

    public static Integer[] randrange(int min, int max, int size){
        Integer[] ret_range = new Integer[size];
        for (int i=0;i<size;i++){
            ret_range[i] = randint(min,max);
        }
        return ret_range;
    }

    public static Object[] randsample(Object[] list, int sample_size){
        int size = len(list);
        if (!(sample_size > size)) {
            Integer index;
            Object[] ret_sample = new Object[sample_size];
            List<Object> Indexed = new ArrayList<>();
            for (int i = 0; i < sample_size; i++) {
                index = randint(size - 1);
                while (findIn(Indexed, index)) {
                    index = randint(size - 1);
                }
                Indexed.add(index);
                ret_sample[i] = list[index];
            }
            return ret_sample;
        }
        else{
            return null;
        }
    }

    public static Object[][] randsample(Object[] list, int sample_size, int groups){
        Object[][] ret_sample = new Object[groups][sample_size];
        for (int i=0;i<groups;i++){
            ret_sample[i] = randsample(list, sample_size);
        }
        return ret_sample;
    }

    public static Object[] shuffle(Object[] obj){
        Object ret_arr[] = new Object[len(obj)];
        Integer[] indexed = new Integer[len(obj)];
        for (int i : range(len(ret_arr))){
            int index = randint(len(obj)-1);
            while (findIn(indexed, index)){
                index = randint(len(obj)-1);
            }
            ret_arr[i] = obj[index];
            indexed[i] = index;
        }
        return ret_arr;
    }


    public static Integer[] swapSort(Integer[] array){
        if (len(array) > 1){
            for (int i : range(len(array)))
                for (int j : range(1,len(array)-i))
                    if (toDouble(array[j-1]) > toDouble(array[j]))
                        array = swap(array, j-1, j);
        }
        return array;
    }

    public static Integer[] pushSort(Integer[] obj){
        if (len(obj) > 1) {
            for (int i : range(len(obj))) {
                for (int j : range(1,len(obj))) {
                    if (obj[i] > obj[len(obj)-j] && i < len(obj)-j) {
                        obj = (Integer[]) pushArray(obj, i, len(obj)-j);
                    }
                }
            }
        }
        return obj;
    }


    public static Integer[] pushArray(Integer[] obj, int old_index,int new_index){
        if (old_index < new_index) {
            int len = len(obj);Integer[] ret_arr = new Integer[len];
            for (int i : range(len)) {
                if (i == new_index) {
                    ret_arr[i] = obj[old_index];
                } else if (i >= old_index && i < new_index) {
                    ret_arr[i] = obj[i + 1];
                } else { ret_arr[i] = obj[i]; }
            }
            return ret_arr;
        }
        return obj;
    }

    public static Integer[] swap(Integer[] obj, int index1, int index2){
        int temp = obj[index1];
        obj[index1] = obj[index2];
        obj[index2] = temp;
        return obj;
    }

    public static class SortedNumArray{

        private List<Double> DATA = new ArrayList<>();

        SortedNumArray(double... numbers){ for (double i: numbers){ DATA.add(i); } }
        SortedNumArray(int[] numbers){ for (double i: numbers){ DATA.add(i); } }
        SortedNumArray(float[] numbers){ for (double i: numbers){ DATA.add(i); } }

        SortedNumArray(Double[] numbers){ for (double i: numbers){ DATA.add(i); } }
        SortedNumArray(Float[] numbers){ for (double i: numbers){ DATA.add(i); } }
        SortedNumArray(Integer[] numbers){ for (double i: numbers){ DATA.add(i); }}

        private Double[] sort(){ return swapSort(MyFuncs.toDouble(DATA.toArray())); }

        private static Double[] swapSort(Double[] array){
            if (len(array) > 1){
                for (int i : range(len(array)))
                    for (int j : range(1,len(array)-i))
                        if (array[j-1] > array[j])
                            array = swap(array, j-1, j);
            }
            return array;
        }

        private static Double[] swap(Double[] obj, int index1, int index2){
            double temp = obj[index1];
            obj[index1] = obj[index2];
            obj[index2] = temp;
            return obj;
        }

        public Object[] getSorted(){return sort();}
        public Double[] toDouble(){ return sort(); }
        public Integer[] toInteger(){ return MyFuncs.toInteger(sort()); }
        public Float[] toFloat(){ return MyFuncs.toFloat(sort()); }

    }

    public static class QuickSort {

        private static int[] a;

        private QuickSort(){}

        public static void run(int[] unsorted) {
            println("Unsorted array:");
            printArray(unsorted);
            int[] sorted = sort(unsorted);
            println("\n\nSorted array:");
            printArray(sorted);
        }

        public static void printArray(int[] array) {
            println();
            for (int i = 0; i < len(array); i++) {
                if (array[i] < 10)
                    System.out.print(" ");
                System.out.print(array[i] + " ");
                if ((i + 1) % 20 == 0)
                    System.out.println();
            }
        }

        public static void printArray(Object[] array) {
            println();
            for (int i = 0; i < len(array); i++) {
                System.out.print(array[i] + " ");
                if ((i + 1) % 20 == 0)
                    System.out.println();
            }
        }



        public static int[] sort(int[] array) {
            a = array;
            sort(0, a.length - 1);
            return a;
        }

        private static void sort(int low, int high) {
            if (low >= high)
                return;
            int p = partition(low, high);
            sort(low, p);
            sort(p + 1, high);
        }

        private static int partition(int low, int high) {
            int pivot = a[low];
            int i = low - 1;
            int j = high + 1;
            while (i < j) {
                for (i++; a[i] < pivot; i++) ;
                for (j--; a[j] > pivot; j--) ;
                if (i < j)
                    swap(i, j);
            }
            return j;
        }

        private static void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            printArray(a);
        }
    }

    public static class MergeSort {

        private Integer[] ARRAY;

        MergeSort(Integer[] array){ ARRAY = array; }

        private Integer[] mergeSort(Integer[] array, int start, int end) {
            if(end-start > 1) {
                int middle = start + ((end-start) / 2);

                if (end-start == 2) {
                    if (array[start] > array[end-1]) { array = swap(array, start, end-1); }
                }
                else{
                    array = mergeSort(array,0,middle);
                    array = mergeSort(array,middle+1,end);
                }
                array =  mergeArray(array,start,middle,end);
            }
            return array;
        }


        private Integer[] mergeArray(Integer[] array, int start, int middle, int end ){

            Integer[] new_array = array;

            for (int i : range(start,end)){
                if (array[i] > array[middle]){ new_array = swap(new_array,i,middle); }
                else{ if (middle < end-1){ middle++; } }
            }

            return new_array;
        }

        public Integer[] getSORTED(){ return mergeSort(ARRAY, 0,ARRAY.length); }
    }


    //##### End of Math Functions #####
    //##### End of Math Functions #####
    //##### End of Math Functions #####
    //##### End of Math Functions #####
    //##### End of Math Functions #####


    //##### Text Processing Functions #####
    //##### Text Processing Functions #####
    //##### Text Processing Functions #####
    //##### Text Processing Functions #####
    //##### Text Processing Functions #####

    public static String[] split(Object line, Object at){
        List<String> ret_list = new ArrayList<>();
        if (line.toString().contains("<>".replace("<>", at.toString()))){
            boolean match;String ret = "";line = line.toString() + at.toString();
            for (char i : line.toString().toCharArray()){
                match = false;
                if (i == toCharacter(at)){
                    match = true;
                    if (!ret.equals("")){
                        ret_list.add(ret);
                        ret = "";
                    }
                }
                if(!match){
                    ret += i;
                }
            }
            String[] ret_arr = new String[len(ret_list)];
            if (len(ret_list) != 0){
                ret_list.toArray(ret_arr);
                return ret_arr;
            }
        }
        String[] split = new String[1];
        split[0] = line.toString();
        return split;
    }

    public static String trim(Object line) {
        String trimmed = line.toString().trim();
        line = line.toString().replace(trimmed, " f|b ");
        String front =(split(line, "|"))[0];
        String back =(split(line, "|"))[1];
        while (front.contains("\n")){
            trimmed = "\n"+trimmed;
            front = front.replaceFirst("\n","");
        }
        while (back.contains("\n")){
            trimmed = trimmed+"\n";
            back = back.replaceFirst("\n","");
        }
        return trimmed;
    }

    private static Map<Integer, Integer> getNewlineIndex(Object line) {
        Map<Integer, Integer> newlineIndex= new HashMap<>();
        Integer memory = 0,index=0;String temp = line.toString();
        while(temp.contains("\n")){
            if (temp.indexOf("\n") == index){
                if (newlineIndex.containsKey(memory)) {
                    newlineIndex.replace(memory, toInteger(newlineIndex.get(memory)) + 1);
                } else {
                    newlineIndex.put(memory, 1);
                }
                temp = temp.replaceFirst("\n","");
                if (temp.indexOf("\n") > index){
                    memory++;
                }
                index = temp.indexOf("\n");
            }
            else{
                newlineIndex.put(memory,0);
                if (temp.replaceFirst("\n", "").indexOf("\n") > index){
                    memory++;
                }
                index = temp.indexOf("\n");
            }
        }
        return newlineIndex;
    }

    private static String wordwrap(Object _line, Integer length){
        String line = _line.toString();
        if (line.length() > length){
            line = line.substring(0,length)+wordwrap("\n"+line.substring(length),length);
        }
        return line;
    }

    private  static  String wrap(Object _line, Integer length){
        String line = _line.toString();
        String ret = "";String full_ret = "";
        if (line.length() > length){
            if (line.split(" ").length != 1) {
                line = line.replace("\t", " |----| ");
                for (String word : line.split(" ")) {
                    if ((ret + word).length() < length) {
                        ret += (word + " ");
                    } else {
                        ret += ("\n");
                        full_ret += ret;
                        ret = word + " ";
                    }
                }
                full_ret += ret;
            }
            else {
                full_ret = wordwrap(line, length);
            }
        }
        else{
            full_ret = line;
        }
        while(full_ret.contains("|----|")){
            full_ret = full_ret.replace("|----|", "\t");
        }
        return  full_ret;
    }

    public static String wrapLine(Object line, Integer wraplenght){
        Map<Integer, Integer> newlineIndex = getNewlineIndex(line);
        String newline = "\n";String ret_Str = "";
        for (int j=0;j<len(newlineIndex);j++){
            try {
                String wrapped = wrap(split(line.toString().trim(),"\n")[j], wraplenght);
                ret_Str += newline.repeat(newlineIndex.get(j))+wrapped;
            }catch (Exception e){
                ret_Str = ret_Str + newline.repeat(newlineIndex.get(j));
            }
        }
        if (newlineIndex.isEmpty()){
            ret_Str = wrap(line,wraplenght);
        }
        return ret_Str;
    }

    public static String alignCenter(Object line, Integer barlength){
        if (line.toString().split("\n").length == 1 && line.toString().split(" ").length == 1 ){
            if (line.toString().length() > barlength){
                line = line+"\n";
            }
        }
        line = wrapLine(line, barlength);String ret_Str = "";
        Map<Integer, Integer> newlineIndex = getNewlineIndex(line);
        for (int j=0;j<len(newlineIndex);j++){
            String tabbing = "\t", spacing = "", newline = "\n";
            try {
                String phrase = split(line.toString().trim(), "\n")[j].trim();

                Object tab = (barlength - phrase.replace("\n","")
                                                .replace("\t", "|--|")
                                                .length())/2;
                tab = toDouble(tab)/4;
                tabbing = tabbing.repeat(toDouble(tab).intValue());
                if (tab.toString().contains(".")){
                    String ret = "0."+split(tab,".")[1];
                    Object ret_num = toDouble(ret)*5;
                    spacing = " ".repeat(toInteger(ret_num));
                }
                ret_Str += format("<><><>",
                           format(newline.repeat(newlineIndex.get(j))+"<>",tabbing),
                           spacing,phrase.trim());
            }catch (Exception e){
                ret_Str = ret_Str + newline.repeat(newlineIndex.get(j));
            }
        }
        if (newlineIndex.isEmpty()){
            String tabbing = "\t", spacing = "", newline = "\n";
            String phrase = line.toString();

            Object tab = (barlength - phrase.replace("\n","")
                    .replace("\t", "|--|")
                    .length())/2;
            tab = toDouble(tab)/4;
            tabbing = tabbing.repeat(toDouble(tab).intValue());
            if (tab.toString().contains(".")){
                String ret = "0."+split(tab,".")[1];
                Object ret_num = toDouble(ret)*5;
                spacing = " ".repeat(toInteger(ret_num));
            }
            ret_Str += format("<><><>",
                    format("<>",tabbing),spacing,phrase.trim());
        }
        return  ret_Str;
    }

    public static String alignLeft(Object line, Integer barlength){
        if (line.toString().split("\n").length == 1 && line.toString().split(" ").length == 1 ){
            if (line.toString().length() > barlength){
               line = line+"\n";
            }
        }
        line = wrapLine(line, barlength);
        Map<Integer, Integer> newlineIndex = getNewlineIndex(line);
        String ret_Str = "";String newline = "\n";String phrase;
        for (int j=0;j<len(newlineIndex);j++){
            try {
                phrase = split(line.toString().trim(), "\n")[j].trim();
                ret_Str += newline.repeat(newlineIndex.get(j))+phrase;
            }catch (Exception e){
                ret_Str = ret_Str + newline.repeat(newlineIndex.get(j));
            }
        }
        if (newlineIndex.isEmpty()){
            ret_Str += trim(line);
        }
        return  ret_Str;
    }

    public static String alignRight(Object line, Integer barlength){
        if (line.toString().split("\n").length == 1 && line.toString().split(" ").length == 1 ){
            if (line.toString().length() > barlength){
                line = line+"\n";
            }
        }
        line = wrapLine(line, barlength);String ret_Str = "";String newline = "\n";
        Map<Integer, Integer> newlineIndex = getNewlineIndex(line);

        for (int j=0;j<len(newlineIndex);j++){
            try {
                String phrase = split(line.toString().trim(), "\n")[j].trim();
                ret_Str += newline.repeat(newlineIndex.get(j)) +
                        " ".repeat(barlength-phrase.length())+phrase;
            }catch (Exception e){
                ret_Str = ret_Str + newline.repeat(newlineIndex.get(j));
            }
        }
        if (newlineIndex.isEmpty()){
            String phrase = trim(line);
            ret_Str += " ".repeat(barlength-phrase.length())+phrase;
        }
        return  ret_Str;
    }

    public static String alignNormal(Object line, Integer barlength){
        line = wrapLine(line, barlength);
        return  line.toString().trim();
    }

    //##### End of Text Processing Functions #####
    //##### End of Text Processing Functions #####
    //##### End of Text Processing Functions #####
    //##### End of Text Processing Functions #####
    //##### End of Text Processing Functions #####

    //##### General Functions #####
    //##### General Functions #####
    //##### General Functions #####
    //##### General Functions #####
    //##### General Functions #####

    public static String format(String line, Object... with){
        //this function replaces the  <> in the String line
        //it assigns the first item in the words to the first <>
        //and so on until there are no more <> to replace
        //Note if words are more than the <> in the String line
        //then the rest are ignored

        Integer i = 1;
        for (Object word : with) {
            if (line.contains("<>")) {
                line = line.replaceFirst("<>", word.toString());
            }
            else if (line.contains(format("<<>>",i))){
                line = line.replace(format("<<>>",i), word.toString());
                i++;
            }
            else{
                break;
            }
        }
        return line;
    }

    public static String reverse(String line){
        StringBuilder reversed_line = new StringBuilder();
        for (int i=len(line);i>0;i--){
            reversed_line.append(line.charAt(i - 1));
        }
        return reversed_line.toString();
    }

    public static Object[] reverse(Object[] objs){
        Object[] reversed_obj = new Object[len(objs)];
        for (int i=len(objs);i>0;i--){
            reversed_obj[len(objs) - i] = objs[i-1];
        }
        return reversed_obj;
    }

    public static Integer[] reverse(Integer[] list) {
        Integer[] result = new Integer[len(list)];
        for (int i = 0, j = result.length - 1; i < len(list); i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

    public static String choice(){
        String v = inputStr("").toLowerCase();
        switch (v) {
            case "y":
                return "y";
            case "n":
                return "n";
            default:
                print("( invalid ) please choose y or n");
                break;
        }
        return choice();
    }

    public static Boolean findIn(String[] words, String key, boolean matchcase){
        if (matchcase){
            for (String word : words) {
                if (word.equals(key)){
                    return true;
                }
            }
            return false;
        }
        else {
            for (String word : words) {
                if (word.equalsIgnoreCase(key)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Boolean findIn(List<?> objects, Object key, boolean matchcase){
        if (matchcase){
            for (Object obj : objects) {
                if (obj.equals(key)){
                    return true;
                }
            }
            return false;
        }
        else {
            for (Object obj : objects) {
                if (obj.toString().equalsIgnoreCase(key.toString())){
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean findIn(List<?> objects, Object key){
        for (Object obj : objects) {
            if (obj.equals(key)){
                return true;
            }
        }
        return false;
    }

    public static Boolean findIn(Object[] objs, Object key){
        if (objs != null) {
            for (Object obj : objs) {
                if (obj.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void listItems(Object[] arrayVar){
        print();
        for (int i = 0; i < len(arrayVar); i++) {
            print(arrayVar[i], ((i + 1) % 3 == 0) ? "\n" : "\t\t");
        }
        print();
    }

    public static void listItems(Object[] arrayVar, Integer ElementsOnRow, String... RowHeaders){
        print();
        for (String words : RowHeaders){
            print(words, "\t\t");
        }
        print();
        if (ElementsOnRow > 0) {
            for (int i = 0; i < len(arrayVar); i++) {
                print(arrayVar[i], ((i + 1) % ElementsOnRow == 0) ? "\n" : "\t\t");
            }
        }
        else{
            for (Object anArrayVar : arrayVar) {
                print(anArrayVar, "\t");
            }
        }
    }

    
}