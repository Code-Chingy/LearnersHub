package com.techupstudio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


abstract class command{
    public abstract void function ();
}

//interface command{
//    void function ();
//}


public class Myvar implements Cloneable {

    private Object obj;
    private Object[] arrobj;
    private boolean BoolStatus = false;
    private boolean wlooperStatus =false;
    private int looper = 0;
    private String isRun = "";
    private Map<String, Object> varcontainer = new HashMap<>();
    private Map<String, Object[]> arrcontainer = new HashMap<>();


    public Myvar() { }

    public Myvar(Object value) { setValue(value); }
    public Myvar(Object[] value) { setValue(value); }
    public Myvar(ArrayList value) { setValue(value.toArray()); }

    public Myvar setValue(Object obj) { this.obj = obj;arrobj = null; return this; }
    public Myvar setValue(Object[] obj) { this.arrobj = obj;obj = null; return this; }
    public Myvar setValue(ArrayList obj) { this.arrobj = obj.toArray();obj = null; return this; }

    public String getType() {
        return (this.obj == null) ? this.arrobj.getClass().getSimpleName() : this.obj.getClass().getSimpleName();
    }

    private void on(){ this.BoolStatus = true; }

    private void off(){ this.BoolStatus = false; }

    private boolean isOn(){ return this.BoolStatus; }

    private boolean whileOn(){ return this.wlooperStatus = true; }

    private boolean whileOff(){ return this.wlooperStatus = false; }

    private boolean iswhileOn(){ return this.wlooperStatus; }

    public Myvar format(Object... with) { return this.setValue(MyFuncs.format(this.toString(), with)); }

    public Object toObject() { return this.obj; }
    public String toString() { return MyFuncs.toStrings(this.obj); }
    public Float toFloat() { return MyFuncs.toFloat(this.obj); }
    public Character toCharacter() { return MyFuncs.toCharacter(this.obj); }
    public Integer toInteger() { return MyFuncs.toInteger(this.obj); }
    public Double toDouble() { return MyFuncs.toDouble(this.obj); }
    public Boolean toBoolean() { return MyFuncs.toBoolean(this.obj); }

    public Object[] toArrObject() { return this.arrobj; }
    public String[] toArrString() { return MyFuncs.toStrings(this.arrobj); }
    public Float[] toArrFloat() { return MyFuncs.toFloat(this.arrobj); }
    public Character[] toArrCharacter() { return MyFuncs.toCharacter(this.arrobj); }
    public Integer[] toArrInteger() { return MyFuncs.toInteger(this.arrobj); }
    public Double[] toArrDouble() { return MyFuncs.toDouble(this.arrobj); }
    public Boolean[] toArrBoolean() { return MyFuncs.toBoolean(this.arrobj); }

    public Myvar clone() {
        try {
            return (Myvar) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Myvar reverse() {
        if (getType().equals("String")){
            this.setValue(MyFuncs.reverse(this.obj.toString()));
        }
        else if(getType().contains("[]")){
            this.setValue(MyFuncs.reverse(this.arrobj));
        }
        return this;
    }

    public Myvar split(Object obj) { return this.setValue(MyFuncs.split(this.obj, obj)); }

    public int hashCode() { return (this.obj == null) ? this.arrobj.hashCode() : this.obj.hashCode(); }

    public boolean equals(Object obj) { return (this.obj == null) ? this.arrobj.equals(obj) : this.obj.equals(obj) ; }

    public int length() { return (this.obj == null) ? this.arrobj.toString().length() : this.obj.toString().length() ; }

    public Myvar endl(){ print("\n");return this; }

    public Myvar endl(Integer obj){ print("\n".repeat(obj));return this; }

    public Myvar tab(){ print("\t");return this; }

    public Myvar tab(Integer obj){ print("\t".repeat(obj));return this; }

    private MyFuncs.Printer MyPrinter = new MyFuncs.Printer();

    public Myvar printer(String start,String sep,String end){
        MyPrinter.start(start);MyPrinter.sep(sep);MyPrinter.end(end);
        return this;
    }

    public Myvar print() {
        if (this.obj != null){
            println(this);
        }
        else{
            for (Object i : arrobj){
                println(i);
            }
        }
        return this;
    }


    public Myvar print(Object... objs) {
        for (Object obj : objs){
            if (obj.getClass().getSimpleName().contains("[]")){
                MyFuncs.print(obj);
            }
            else {
                MyPrinter.print(obj);
            }
        }
        return this;
    }

    public Myvar println(Object... objs){
        for (Object obj : objs){
            if (obj.getClass().getSimpleName().contains("[]")){
                MyFuncs.println(obj);
            }
            else{
                MyPrinter.print(obj);MyPrinter.print();
            }
        }
        return this;
    }

    
    //Object obj
    public Myvar add(Object obj){
        if(this.obj.getClass().getSimpleName().equals("String")){
            return this.setValue(this.obj.toString().concat(obj.toString()));
        }
        return this.setValue(this.toFloat() + MyFuncs.toDouble(obj));
    }
    public Myvar subtract(Object obj){ return this.setValue(this.toDouble() - MyFuncs.toDouble(obj)); }
    public Myvar divide(Object obj){ return this.setValue(this.toDouble() / MyFuncs.toDouble(obj)); }
    public Myvar multiply(Object obj){
        if(this.obj.getClass().getSimpleName().equals("String")) {
            return this.setValue(this.obj.toString().repeat(MyFuncs.toInteger(obj)));
        }
        return this.setValue(this.toDouble() * MyFuncs.toDouble(obj));
    }
    public Myvar mod(Object obj){ return this.setValue(this.toFloat() % MyFuncs.toDouble(obj)); }
    public Myvar pow(Object obj){ return this.setValue(MyFuncs.power(this.obj, obj)); }

    //int obj
    public Myvar add(int obj){
        if(this.obj.getClass().getSimpleName().equals("String")){
            Object Obj = obj;
            return this.setValue(this.obj.toString().concat(Obj.toString()));
        }
        return this.setValue(this.toDouble() + obj);
    }
    public Myvar subtract(int obj){ return this.setValue(this.toDouble() - obj); }
    public Myvar divide(int obj){ return this.setValue(this.toDouble() / obj); }
    public Myvar multiply(int obj){
        if(this.obj.getClass().getSimpleName().equals("String")) {
            return this.setValue(this.obj.toString().repeat(obj));
        }
        return this.setValue(this.toDouble() * obj);
    }
    public Myvar modulus(int obj){ return this.setValue(this.toDouble() % obj); }
    public Myvar power(int obj){ return this.setValue(MyFuncs.power(this.obj, obj)); }

    //float obj
    public Myvar add(float obj){
        if(this.obj.getClass().getSimpleName().equals("String")){
            Object Obj = obj;
            return this.setValue(this.obj.toString().concat(Obj.toString()));
        }
        return this.setValue(this.toDouble() + obj);
    }
    public Myvar subtract(float obj){ return this.setValue(this.toDouble() - obj); }
    public Myvar divide(float obj){ return this.setValue(this.toDouble() / obj); }
    public Myvar multiply(float obj){
        if(this.obj.getClass().getSimpleName().equals("String")) {
            return this.setValue(this.obj.toString().repeat((int) obj));
        }
        return this.setValue(this.toDouble() * obj);
    }
    public Myvar modulus(float obj){ return this.setValue(this.toDouble() % obj); }
    public Myvar power(float obj){ return this.setValue(MyFuncs.power(this.obj, obj)); }

    //double obj
    public Myvar add(double obj){
        if(this.obj.getClass().getSimpleName().equals("String")){
            Object Obj = obj;
            return this.setValue(this.obj.toString().concat(Obj.toString()));
        }
        return this.setValue(this.toDouble() + obj);
    }
    public Myvar subtract(double obj){ return this.setValue(this.toDouble() - obj); }
    public Myvar divide(double obj){ return this.setValue(this.toDouble() / obj); }
    public Myvar multiply(double obj){
        if(this.obj.getClass().getSimpleName().equals("String")) {
            return this.setValue(this.obj.toString().repeat((int) obj));
        }
        return this.setValue(this.toDouble() * obj);
    }
    public Myvar modulus(double obj){ return this.setValue(this.toDouble() % obj); }
    public Myvar power(double obj){ return this.setValue(MyFuncs.power(this.obj, obj)); }

    //char obj
    public Myvar add(char obj){
        if(this.obj.getClass().getSimpleName().equals("String")){
            Object Obj = obj;
            return this.setValue(this.obj.toString().concat(Obj.toString()));
        }
        Object Obj = obj;
        return this.setValue(MyFuncs.toDouble(this.obj)+MyFuncs.toDouble(Obj));
    }

    public Myvar inputInt(String show){ return this.setValue(MyFuncs.inputInt(show)); }
    public Myvar getStr(String show){ return this.setValue(MyFuncs.getStr(show)); }
    public Myvar inputStr(String show){ return this.setValue(MyFuncs.inputStr(show)); }
    public Myvar inputFloat(String show){ return this.setValue(MyFuncs.inputFloat(show)); }
    public Myvar inputDouble(String show){ return this.setValue(MyFuncs.inputDouble(show)); }
    public Myvar inputChar(String show){ return this.setValue(MyFuncs.inputChar(show)); }
    public Myvar inputBool(String show){ return this.setValue(MyFuncs.inputBool(show)); }

    public Myvar random(){ return this.setValue(MyFuncs.random()); }
    public Myvar randint(){ return this.setValue(MyFuncs.randint()); }
    public Myvar randint(int max){ return this.setValue(MyFuncs.randint(max)); }
    public Myvar randrange(int size){ return this.setValue(MyFuncs.randrange(size)); }
    public Myvar randrange(int max, int size){ return this.setValue(MyFuncs.randrange(max, size)); }
    public Object[] randsample(int sample_size){ return MyFuncs.randsample(arrobj, sample_size); }


    //##### Console Text Manipulatuon #####

    public Myvar setConsoleLength(Integer value){ MyPrinter.setConsoleLength(value); return this; }
    public Myvar alignNormal(){ MyPrinter.alignNormal(); return this; }
    public Myvar alignCenter(){ MyPrinter.alignCenter(); return this; }
    public Myvar alignLeft(){ MyPrinter.alignLeft(); return this; }
    public Myvar alignRight(){ MyPrinter.alignRight(); return this; }
    public Myvar alignReset(){ MyPrinter.resetAlignment(); return this; }



    public Myvar andprint(Object obj){
        print(obj.toString().repeat(looper));
        looper = 0;
        return this;
    }

    public Myvar andrun(command usercommand){
        for (int i=0;i<looper;i++){
            usercommand.function();
        }
        looper = 0;
        return this;
    }

    public Myvar andtotal(String show){
        if (looper != 0) {
            for (int i = 0; i < looper; i++) {
                this.obj = this.toDouble() + MyFuncs.inputDouble(show);
            }
            looper = 0;
            return this;
        }
        if (iswhileOn()){
            Object num;
            while(iswhileOn()) {
                num = MyFuncs.inputStr(show);
                if (num.toString().equals(":q")){
                    this.obj = this.toDouble() + MyFuncs.toDouble(num);
                }else{
                    whileOff();
                }
            }
        }
        return this;
    }

    public Myvar andsubtract(String show){
        for (int i=0;i<looper;i++){
            this.obj = this.toDouble() - MyFuncs.inputDouble(show);
        }
        looper = 0;
        return this;
    }

    public Myvar andmultiply(String show){
        for (int i=0;i<looper;i++){
            this.obj = this.toDouble() * MyFuncs.inputDouble(show);
        }
        looper = 0;
        return this;
    }

    public Myvar anddivide(String show){
        for (int i=0;i<looper;i++){
            this.obj = this.toDouble() / MyFuncs.inputDouble(show);
        }
        looper = 0;
        return this;
    }

    public Myvar isnull(){
        if (this.obj == null && this.arrobj == null) {
            this.on();
        }
        this.isRun = "ready";
        return  this;
    }

    public Myvar isequal(Object obj){
        if(obj != null) {
            if ((obj.hashCode() == (this.obj.hashCode()))) {
                this.on();
            }
            this.isRun = "ready";
            return this;
        }
        else{
            if ((obj.equals(this.arrobj.hashCode()))) {
                this.on();
            }
            this.isRun = "ready";
            return this;
        }
    }

    public Myvar isnot_equal(Object obj){
        if(obj != null) {
            if (!(obj.hashCode() == (this.obj.hashCode()))) {
                this.on();
            }
            this.isRun = "ready";
            return this;
        }
        else{
            if (!(obj.equals(this.arrobj.hashCode()))) {
                this.on();
            }
            this.isRun = "ready";
            return this;
        }
    }

    public Myvar isgreater(Object obj){
        if ((obj.hashCode() < (this.obj.hashCode()))) {
            this.on();
        }
        this.isRun = "ready";
        return  this;
    }

    public Myvar islesser(Object obj){
        if ((obj.hashCode() > (this.obj.hashCode()))) {
            this.on();
        }
        this.isRun = "ready";
        return  this;
    }

    public Myvar isgreater_or_equal(Object obj){
        if ((obj.hashCode() <= (this.obj.hashCode()))) {
            this.on();
        }
        this.isRun = "ready";
        return  this;
    }

    public Myvar islesser_or_equal(Object obj){
        if ((obj.hashCode() >= (this.obj.hashCode()))) {
            this.on();
        }
        this.isRun = "ready";
        return  this;
    }

    //##### Variable Management #####

    public boolean existVar(String varname){
        //check if var exist
        return varcontainer.containsKey(varname);
    }

    public Myvar assign(String varname, Object obj){
        if (!existVar(varname)){
            varcontainer.putIfAbsent(varname, obj);
        }
        else{
            varcontainer.replace(varname, obj);
        }
        return this;
    }


    public Object getVar(String varname){
        if (existVar(varname)){
            return varcontainer.get(varname);
        }
        return new Object();
    }

    public Myvar delVar(String varname){
        if (existVar(varname)){
            varcontainer.remove(varname);
        }
        return this;
    }

    public Myvar clearVars(){
        varcontainer.clear();//empty variable from variable map
        return this;
    }

    private String listItems(Object[] arrayVar, Integer... varOnRow){
        Integer onRow;
        if(varOnRow.length == 1){
            onRow = varOnRow[0];
        }
        else{
            onRow = 3;
        }
        String string_arr = "\nvalues   : \n\t\t\t";
        for (int i = 0; i < arrayVar.length; i++) {
            string_arr = string_arr + arrayVar[i] + (((i + 1) % onRow == 0) ? "\n\t\t\t" : "\t\t");
        }
        return string_arr + "\n";
    }

    public Myvar listVars(){
        varcontainer.forEach((s, o) -> print("\nvaraible :\t",s, "\nvalue    :\t",o,"\n"));
        return this;//empty variable from variable map
    }

    public Myvar listArrs(){
        arrcontainer.forEach((s, o) -> print("variable : ",s, " list", listItems(o)));
        return this;//empty variable from variable map
    }

    public Myvar listArrs(int varOnRow){
        arrcontainer.forEach((s, o) -> print("variable : ",s, " list", listItems(o, varOnRow)));
        return this;//empty variable from variable map
    }

    public Map<String, Object> getVarContainer(){ return varcontainer; }

    public Map<String, Object[]> getArrContainer(){ return arrcontainer; }

    public Myvar increamentVar(String varname){
        if (existVar(varname)){
            Object new_ = MyFuncs.toDouble(getVar(varname)) + 1;
            varcontainer.replace(varname, getVar(varname), new_);
        }
        return this;//++ variable from variable map
    }

    public Myvar increamentVar(String varname, int by){
        if (existVar(varname)){
            Object new_ = MyFuncs.toDouble(getVar(varname)) + by;
            varcontainer.replace(varname, getVar(varname), new_);
        }
        return this;//++by variable from variable map
    }

    public Myvar decrementVar(String varname){
        if (existVar(varname)){
            Object new_ = MyFuncs.toDouble(getVar(varname)) - 1;
            varcontainer.replace(varname, getVar(varname), new_);
        }
        return this;//-- variable from variable map
    }

    public Myvar decrementVar(String varname, int by){
        if (existVar(varname)){
            Object new_ = MyFuncs.toDouble(getVar(varname)) - by;
            varcontainer.replace(varname, getVar(varname), new_);
        }
        return this;//--by variable from variable map
    }

    public boolean existArr(String varname){
        //check if var exist
        return arrcontainer.containsKey(varname);
    }

    public Myvar assign(String varname, Object[] obj){
        if (!existVar(varname)){
            arrcontainer.putIfAbsent(varname, obj);
        }
        else{
            arrcontainer.replace(varname, obj);
        }
        return this;
    }

    public Object[] getArr(String varname){
        if (existArr(varname)){
            return arrcontainer.get(varname);
        }
        return new Object[0];
    }

    public Myvar delArr(String varname){
        if (existArr(varname)){
            arrcontainer.remove(varname);
        }
        return this;
    }

    public Myvar clearArrs(){
        arrcontainer.clear();
        return this;//empty variable from variable map
    }

    //##### Process Flows #####

    public Myvar when(Object function, boolean result){
        isRun = "ready";
        if(result == MyFuncs.toBoolean(function)){
            on();
        }
        else{
            off();
        }
        return this;
    }

    public Myvar when(boolean result){
        isRun = "ready";
        BoolStatus = result;
        return this;
    }

    public Myvar elif(Object function, boolean result){
        if(!isOn()) {
            if (result == MyFuncs.toBoolean(function)) {
                on();
            } else {
                off();
            }
        }
        return this;
    }

    public Myvar elif(boolean result){
        BoolStatus = result;
        return this;
    }

    public Myvar orelse(command usercommand){
        if(!isOn() && isRun.equals("ready")){
            on();
            usercommand.function();
            isRun = "pass";
        }
        off();
        return this;
    }

    public Myvar loop(int obj){
        if (looper == 0){
            this.looper = obj;
        }
        else{
            print("\nloop is still on : "+looper+" runs left.\n");
        }
        return this;
    }

    public void loopwhile(){
        whileOn();
    }


    public Myvar run(command usercommand){
        if(isOn() && isRun.equals("ready")){
            usercommand.function();
            isRun = "pass";
            off();
        }
        return this;
    }

    public Myvar dothis(command usercommand){
        usercommand.function();
        return this;
    }

}
