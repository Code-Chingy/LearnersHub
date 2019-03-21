#include <iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>
#include <fstream>

using namespace std;


//globals
vector<string> builtins;
vector<string> operators;
vector<string> datatypes;
int address = 0;


template <class varname>
void print(varname line){
    cout  << line;
}

//int val = stoi( val )
//float val = stof( val )
//double val = stod( val )
//long double val = stold( val )
//float val = strtof( val )

string toString(int val){
    stringstream ss;
    ss << val;
    return ss.str();
}

int toInt(string val){
    stringstream geek(val);
    int num = 0;
    geek >> num;
    return num;
}


float toFloat(string val){
    stringstream geek(val);
    float num = 0;
    geek >> num;
    return num;
}

double toDouble(string val){
    stringstream geek(val);
    double num = 0;
    geek >> num;
    return num;
}

//prototypes
int inputInt(string show); float inputFloat(string show); double inputDouble(string show);
vector <string> split(string line, char by); void listItems(vector <string> arrayVar);
string multiStr(string val, int num), getStr(string show), inputStr(string show), lower(string line);
string getOperator(), deString(string line), getName(string line), upper(string line);

//system fuctions
bool findStr(vector<string> _list, string key), isOperator(string word), isBuiltin(string word);
bool isDatatype(string word), isSystemKey(string word), inProgramVariables(string word);
bool isEmptyVar(string word), inputBool(string show), isSpace(string line), isString(string word);
bool alreadyExist(string word), isallNum(string line);

class var{
    public:
        string name;
        int _int;
        float _float;
        double _double;
        bool _bool;
        string _string;
        string _data;
        string type;
        int address;

        var(){
            _data = "null";
            type = "null";
        }

        string getType(){
            return type;
        }


        void setValue(string val){
            if (type == "int"){
                _int = toInt(val);
                _data = "";
            }
            else if (type == "float"){
                _float = toFloat(val);
                _data = "";
            }
            else if (type == "double"){
                _double = toDouble("");
                _data = "";
            }
            else if (type == "bool"){
                _bool = inputBool(val);
                _data = "";
            }
            else if (type == "str"){
                if (isString(val) == true){
                    _string = val;
                    _data = "";
                }
                else{
                    print("\ninvalid assignment --value not string\n");
                }
            }
            else{
                print("\nobject is null_type");
            }
        }

        void lower(){
            ::lower(toString());
        }

        void upper(){
            ::upper(toString());
        }
        //template <class def>
        float getValue(){
            toFloat(toString());
        }

        string toString(){
            if (_data == "null"){
                return "None";
            }
            else if (type == "int"){
                return toString(_int);
            }
            else if (type == "float"){
                return toString(_float);
            }
            else if (type == "double"){
                return toString(_double);
            }
            else if (type == "bool"){
                return toString(_bool);
            }
            else if (type == "str"){
                return _string;
            }
        }

        template <class classes>
        string toString(classes val){
            stringstream ss;
            ss << val;
            return ss.str();
        }
};

var getVar(string word);
void engine(var, string);
void start();
vector <var> variables;
var temp_obj;
float temp_num;
int operatorState = 0;
int assignState = 0;


int main()
{

    print("###################################################################################");
    print("####                             The Chingy Compiler                           ####");
    print("###################################################################################");

    start();
    var obj;
    engine(obj, "");

    return 0;
}

void start(){
   //builtins
    builtins.push_back("if");builtins.push_back("else");builtins.push_back("else if");builtins.push_back("when");
    builtins.push_back("for");builtins.push_back("while");builtins.push_back("return");builtins.push_back("as");
    builtins.push_back("with");builtins.push_back("is");builtins.push_back("is not");builtins.push_back("not");
    builtins.push_back("except");builtins.push_back("Exceptions");builtins.push_back("then");builtins.push_back("or");
    builtins.push_back("and");builtins.push_back("True");builtins.push_back("False");builtins.push_back("None");
    builtins.push_back("print");builtins.push_back("pass");builtins.push_back("in");builtins.push_back("display");
    //operators
    operators.push_back(">");operators.push_back("<");operators.push_back(">=");operators.push_back("<=");
    operators.push_back("==");operators.push_back("+");operators.push_back("-");operators.push_back("*");
    operators.push_back("**");operators.push_back("^");operators.push_back("%");operators.push_back("/");
    operators.push_back("//");operators.push_back("=");operators.push_back("\\");operators.push_back("&");
    operators.push_back("++");operators.push_back("--");operators.push_back("+=");operators.push_back("-=");
    operators.push_back("*=");operators.push_back("/=");operators.push_back("%=");operators.push_back("$");
    operators.push_back("[");operators.push_back("]");operators.push_back("{");operators.push_back("}");
    //datatypes
    datatypes.push_back("str");;datatypes.push_back("int");datatypes.push_back("bool");
    datatypes.push_back("float");datatypes.push_back("long");datatypes.push_back("double");

}

int inputInt(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value : ");
    }
    else{
        print(show);
    }
    int value;
    cin >> value;
    while (cin.fail()){
        print("\n( invalid input )");
        break;
    };
    return value;
}

string inputStr(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value : ");
    }
    else{
        print(show);
    }
    string value;
    cin >> value;
    return value;
}


string getStr(string show){
    cin.clear();//cin.ignore();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value : ");
    }
    else{
        print(show);
    }
    string value;
    getline(cin, value);
    return value;
}

float inputFloat(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value : ");
    }
    else{
        print(show);
    }
    float value;
    cin >> value;
    while (cin.fail()){
        print("\n( invalid input )");
        break;
    };
    return value;
}

bool inputBool(string state){
    if (state == "True"){
        return true;
    }
    else if (state == "False"){
        return false;
    }
    else{
        print("\ninvalid syntax");
    }
}


double inputDouble(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value : ");
    }
    else{
        print(show);
    }
    double value;
    cin >> value;
    while (cin.fail()){
        print("\n( invalid input )");
        break;
    };
    return value;
}


string lower(string line){
    string LOWER = "abcdefghijklmnopqrstuvwxyz";
    string UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    string ret_lower = "";
    int len = line.length();
    for (int i=0;i<len;i++){//boy
        for (int j=0;j<26;j++){
            if (line[i] == UPPER[j]){
                ret_lower += LOWER[j];
                break;
            }
            else if (j == 25){
                ret_lower += line[i];
            }
        }

    }
    return ret_lower;
}


string upper(string line){
    string ret_upper = "";
    int len = line.length();
    string LOWER = "abcdefghijklmnopqrstuvwxyz";
    string UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i=0;i<len;i++){
        for (int j=0;j<26;j++){
            if (line[i] == LOWER[j]){
                ret_upper += UPPER[j];
                break;
            }
            else if (j == 25){
                ret_upper += line[i];
            }
        }
    }
    return ret_upper;
}


bool isSpace(string line){
    int len = line.length();
    for (int i=0;i<len;i++){
        if (line[i] != ' '){
            return false;
        }
    }
    return true;
}


vector <string> split(string line, char by){
    string str = "";
    vector <string> splitVec;
    int len = line.length();
    for (int i=0;i<len;i++){
        char _line = line[i];
        if (_line == by){
            splitVec.push_back(str);
            str = "";
        }
        else if (i+1 == len){
            str += line[i];
            splitVec.push_back(str);
            str = "";
        }
        else{
            str += line[i];
        }
    }
    return splitVec;
}


bool findStr(vector<string> _list, string key){
    int len = _list.size();
    for (int i=0;i<len;i++){
        if (lower(_list[i]) == lower(key)){
            return true;
        }
    }
    return false;
}


string multiStr(string val, int num){
    string longstr = "";
    for(int i=0;i<num;i++){
        longstr += val;
    }
    return longstr;
}


void listItems(vector <string> arrayVar){
    int len = arrayVar.size();
    for (int i=0;i<len;i++){
        print(arrayVar[i]);
        if ((i+1)%3 == 0){
            print("\n");
        }else{
            print("\t\t");
        }
    }
}


//system fuctions

int opState(){
    return operatorState;
}

void opState(int val){
    if (val == 1){
        operatorState++;
    }
    else if (val == 0){
        operatorState = 0;
    }
    else{
        operatorState = val;
    }
}

int asState(){
    return assignState;
}

void asState(int val){
    if (val == 1){
        assignState++;
    }
    else if (val == 0){
        assignState = 0;
    }
    else{
        assignState = val;
    }
}

bool isOperator(string word){
    int len = operators.size();
    for (int i=0;i<len;i++){
        if (word == operators[i]){
            return true;
        }
    }
    return false;
}

bool isBuiltin(string word){
    int len = builtins.size();
    for (int i=0;i<len;i++){
        if (word == builtins[i]){
            return true;
        }
    }
    return false;
}


bool isDatatype(string word){
    int len = datatypes.size();
    for (int i=0;i<len;i++){
        if (word == datatypes[i]){
            return true;
        }
    }
    return false;
}

bool isSystemKey(string word){
    if (isBuiltin(word) == true || isOperator(word) == true || isDatatype(word) == true){
        return true;
    }
    return false;
}

string getOperator(){
    string _operator = inputStr("");
    if (isOperator(_operator)){
        return _operator;
    }
    else{
        print("invalid operator");
        return "null";
    }
}

bool inProgramVariables(string word){
    int len = variables.size();
    for (int i=0;i<len;i++){
        if (word == variables[i].name){
            return true;
        }
    }
    return false;
}

bool isEmptyVar(string word){
    int len = word.length();
    if (word[len-1] == ';'){
        return true;
    }
    return false;
}

bool isallNum(string word){
    int len = word.length();
    for (int i=0;i<len;i++){
        if (isalnum(word[i]) == false || isalpha(word[i]) == true){
            return false;
        }
    }
    return true;
}

bool alreadyExist(string word){
    int len = variables.size();
    for (int i=0;i<len;i++){
        if (variables[i].name == word){
            return true;
        }
    }
    return false;
}


var getVar(string word){
    int len = variables.size();
    for (int i=0;i<len;i++){
        if (word == variables[i].name){
            return variables[i];
        }
    }
    var _empty;
    _empty.type = "null";
    return _empty;
}

string deString(string line){
    int len = line.length();
    string ret = "";
    for (int i=1;i<len-1;i++){
        ret += line[i];
    }
    return ret;
}

string getName(string line){
    int len = line.length();
    string ret = "";
    for (int i=0;i<len-1;i++){
        ret += line[i];
    }
    return ret;
}

bool isString(string word){
     int len = word.length();
     if (word[0] == '"' && word[len-1] == '"'){
        return true;
     }
     return false;
}


void builtin_func(var obj, string command){
    if (command == "display"){
        cin.clear();cin.ignore();
        string val = getStr("");
        if (isString(val) == true){
            print(deString(val));
        }
        else if (inProgramVariables(val)){
            print(obj.toString());
        }
        else if (toString(toFloat(val)).length() == val.length()){
            print(toFloat(val));
        }
        else{
            print("\nunknown variable");
        }
    }
}


void operation(var obj, string command){
    if (obj.type != "null"){
        if (command == "="){
            if (opState() == 1){
                obj.setValue(inputStr(""));
                variables[obj.address] = obj;
                temp_obj = variables[obj.address];
                temp_num = toFloat(temp_obj.toString());
                asState(1);
            }
        }
        else if (command == "+"){
            if (opState() == 1){
                string val = inputStr("");
                if (inProgramVariables(val) == true){
                    temp_num = toFloat(getVar(val).toString()) + toFloat(obj.toString());
                    print(temp_num);
                }
                else{
                    temp_num = toFloat(obj.toString()) + toFloat(val);
                    print(temp_num);
                }
            }
            else{
                if (asState() == 0){
                    string val = inputStr("");
                    if (inProgramVariables(val) == true){
                        temp_num += toFloat(getVar(val).toString());
                        print(temp_num);
                    }
                    else{
                        temp_num += toFloat(val);
                        print(temp_num);
                    }
                }
                else{
                    string val = inputStr("");
                    if (inProgramVariables(val) == true){
                        temp_num += toFloat(getVar(val).toString());
                        temp_obj.setValue(toString(temp_num));
                    }
                    else{
                        temp_num += toFloat(val);
                        temp_obj.setValue(toString(temp_num));
                    }

                }
            }
        }
        else if (command == "-"){

        }
    }
}


void new_data(var obj, string datatype){
    string var_name = inputStr("");var _new;
    if (isEmptyVar(var_name) == true && alreadyExist(getName(var_name)) == false){
        //push var and set null
        _new.name = getName(var_name);
        _new.type = datatype;
        _new.address = address;address++;
        variables.push_back(_new);
    }
    else if (isEmptyVar(var_name) == false && alreadyExist(var_name+";") == false){
        _new.name = var_name;
        _new.type = datatype;
        _new.address = address;address++;
        variables.push_back(_new);
    }
    else{
        print("\nerror -- variable already exist\n");
    }
    temp_obj = _new;
}


void engine(var obj, string prev_word){
    string word = inputStr("\n>>>> ");
    if (isDatatype(word) == true){
        //get varname and data
        opState(0);
        new_data(obj, word);
        obj = temp_obj;
        engine(obj, word);

    }
    else if (inProgramVariables(word) == true){
        //compare word or assign var
        opState(0);
        engine(getVar(word), "word");
    }
    else if (isBuiltin(word) == true){
        //use previous word to do stuff
        opState(0);
        builtin_func(obj, word);
        engine(obj, word);
    }
    else if (isOperator(word) == true){
        //use previous word to do stuff
        if (opState() == 0){
            opState(1);
        }
        else{
            opState(2);
        }
        operation(obj, word);
        obj = temp_obj;
        engine(obj, word);
    }
    else if (isallNum(word) == true){
        //use previous word to do stuff
        temp_num = toFloat(word);
        var _obj;_obj.type = "null";
        if (toString(temp_num).length() == word.length()){
            opState(2);
            _obj._float = temp_num;
            _obj.type = "float";
        }
        else{
            print("\nsyntax error\n");
        }
        engine(_obj, word);
    }
    else if (word == "exit()"){
        //pass to exit
    }
    else if (word == "clear()"){
        system("CLS");
        engine(obj, word);
    }
    else{
        opState(0);
        print("\ncompiler error");
        engine(obj, word);
    }

}
