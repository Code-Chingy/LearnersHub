#include <iostream>
#include <vector>
#include <stdlib.h>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

template <class var>

void print(var textline){
    cout << textline;
}


//###  functions prototypes  ##########
vector <string> split(string line, char by);
bool findStr(vector<string> _list, string key);
string multiStr(string val, int num), gradeCalc(double score);
void help(string command), listItems(string arrayVar[]), setItems(string val);
void _add(), _edit(), _remove(), _list(), _search(), _start(), exit();
string choice(), engine(), inputStr(string show), getStr(string show), upper(string line), lower(string line);
int inputInt(string show);float inputFloat(string show);double inputDouble(string show);void printer(string val);


//structs
struct course {
    int level;
    string subjects[10];
};

struct Department {
    string dep_code;
    string dep_name;
    course dep_courses[4];
};

struct record{
    int semester;
    int credit_hr;
    string course_name;
    int mark;
    string grade;
};

//computerscience department..
Department comp_science = {
    "CSCD",
    "Department Of Computer Science",
    {
     100, {"CSCD 101","CSCD 102","CSCD 103","CSCD 104","CSCD 105","CSCD 106","CSCD 107","CSCD 108","CSCD 109","CSCD 110"},
     200, {"CSCD 201","CSCD 202","CSCD 203","CSCD 204","CSCD 205","CSCD 206","CSCD 207","CSCD 208","CSCD 209","CSCD 210"},
     300, {"CSCD 301","CSCD 302","CSCD 303","CSCD 304","CSCD 305","CSCD 306","CSCD 307","CSCD 308","CSCD 309","CSCD 310"},
     400, {"CSCD 401","CSCD 402","CSCD 403","CSCD 404","CSCD 405","CSCD 406","CSCD 407","CSCD 408","CSCD 409","CSCD 410"},
    }
};

vector <Department> DEPARTMENTS;
vector <string> DEP_CODES;
string view = "normal";
int helptips = 0;
int semester = 0;
int year;



class person
{
    public:
        person()
        {
            //set empty student name = ""
            status = "null";
        }
        //setter functions for student
        void setName(string val){
            name = val;
        }

        void setStatus(string val){
            status = val;
        }

        void setID(int val){
            userect_id = val;
        }

        void setIndex(int val){
            index = val;
        }

        void setResidence(string val){
            residence = val;
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


        //getter functions for student
        string getName(){
            return name;
        }

        string getStatus(){
            return status;
        }

        int getAge(){
            return age;
        }

        string getResidence(){
            return residence;
        }

        string getType(){
            return "person";
        }

        int getID(){
            return userect_id;
        }

        int getIndex(){
            return index;
        }

        void getInfo(){
            cout << "\nname       : " << getName() << "\nage        : " << getAge() << "\nperson ID    : " << getID() <<endl;
        }

        vector<string> getSubjects(){
            return subjects;
        }

        void listSubjects(){
            listItems(subjects);
        }

        vector<string> getRegCourses(){
            return registrable_courses;
        }

        void listRegCourses(){
            listItems(registrable_courses);
        }

        void setSubject(string val){
            //check if subject is legit in registrable then
            subjects.push_back(val);
        }

        void removeSubject(string val){
            int len = subjects.size();
            for (int i=0;i<len;i++){
                if (upper(subjects[i]) == upper(val)){
                    subjects.erase(subjects.begin()+i);
                    print("\nSubject successfully removed...");
                    break;
                }
                if (i == len-1){
                    print("\ncant find subject index...\n");
                }
            }
        }


    protected:
        string name;
        int age;
        int index;
        int userect_id;
        string status;
        string residence;
        string department;
        vector <string> subjects;
        vector <string> registrable_courses;
};

class student : public person{
    private:
        int level;
        vector<record> semester_records;
        vector<record> academic_records;

    public:
        void setDepartment(string val){
            //if DEP_CODES.find(val)
            department = val;
            setRegCourse();
        }

        void setLevel(int val){
            level = val;
        }

        void setSubject(string val){
            record temp;
            temp.semester = semester;
            temp.course_name = val;
            temp.mark = -1;
            semester_records.push_back(temp);
            subjects.push_back(temp.course_name);
        }

        void recordMark(int index, int credit_h, int score){
            string grade = gradeCalc(score);
            semester_records[index].mark = score;
            semester_records[index].grade = grade;
            semester_records[index].credit_hr = credit_h;
        }

        vector<record> getSemRecords(){
            return semester_records;
        }

        vector<record> getAcaRecords(){
            return academic_records;
        }

        void moveSemester(){
            if (getStatus() == "active"){
                if (semester  == 1){
                    if (getLevel() != 400){
                        setLevel(getLevel()+100);
                    }
                    else{
                        setStatus("completed");
                    }
                    age += 1;
                }
                int len = semester_records.size();
                for (int i=0;i<len;i++){
                   academic_records.push_back(semester_records[i]);
                }
                subjects.clear();
                semester_records.clear();
                registrable_courses.clear();
                setRegCourse();
            }
        }

        void refresh(){
            subjects.clear();
            registrable_courses.clear();
            setRegCourse();
        }

        //getter functions for student
        string getDepartment(){
            string dep;
            int dep_count = DEPARTMENTS.size();
            for (int i=0;i<dep_count;i++){
                if (DEPARTMENTS[i].dep_code == department){
                    dep = DEPARTMENTS[i].dep_name;
                }
            }
            return dep;
        }

        string getType(){
            return "student";
        }

        int getLevel(){
            return level;
        }

        void getInfo(){
            cout << "\n\nStudent ID : " << getID()  << "\nstatus     : " << getStatus()  << "\nin level   : " << getLevel() <<"\n";
        }

        void setRegCourse(){
            int dep_count = DEPARTMENTS.size();
            for (int i=0;i<dep_count;i++){
                //check null pointer DEPARTMENTS[i] != nullptr
                if (DEPARTMENTS[i].dep_code == department){
                    //setting department courses for student at a certain level
                    for (int j=0;j<4;j++){
                        if (DEPARTMENTS[i].dep_courses[j].level == level){
                            for (int k=0;k<10;k++){
                                try{
                                    //print(DEPARTMENTS[i].dep_courses[j].subjects[k]);print(" ");
                                    registrable_courses.push_back(DEPARTMENTS[i].dep_courses[j].subjects[k]);
                                }
                                catch(...){
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

    void removeSubject(string val){
        int len = semester_records.size();
        for (int i=0;i<len;i++){
            if (upper(semester_records[i].course_name) == upper(val)){
                semester_records.erase(semester_records.begin()+i);
                subjects.erase(subjects.begin()+i);
                printer("success");
                break;
            }
            if (i == len-1){
                printer("error");
            }
        }
    }
};

student user;


int main(){

    user.setID(0);

    //starting the engine
    _start();

    return 0;
}

//######  functions  ######
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
    if (cin.fail()){
        cin.clear();cin.ignore();
        return inputInt("\n( invalid input )  enter number(s) : ");
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
    if (cin.fail()){
        cin.clear();cin.ignore();
        return inputFloat("\n( invalid input )  enter number(s) : ");
    };
    return value;
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
    if (cin.fail()){
        cin.clear();cin.ignore();
        return inputDouble("\n( invalid input )  enter number(s) : ");
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

bool findStr(string subjs[], int length, string key){
    for (int i=0;i<length;i++){
        try{
            if (lower(subjs[i]) == lower(key)){
                return true;
            }
        }
        catch(...){
            break;
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


string gradeCalc(double score){
    if (79 < score && score <= 100){
        return "A";
    }
    else if(74 < score && score <= 79){
        return "B+";
    }
    else if(69 < score && score <= 74){
        return "B";
    }
    else if(64 < score && score <= 69){
        return "C+";
    }
    else if(59 < score && score <= 64){
        return "C";
    }
    else if(54 < score && score <= 59){
        return "D+";
    }
    else if(49 < score && score <= 54){
        return "D";
    }
    else if(44 < score && score <= 49){
        return "E";
    }
    else if(-1 < score && score <= 44){
        return "F";
    }
    else{
        return "invalid mark";
    }
}


void printer(string val){
    if (val == "start"){
        cout
         << "##################################################################################################################" << endl
         << "##########                                                                             ###########################" << endl
         << "##########   ########  ########    #####         #######  #######  ##       #######    ###########################" << endl
         << "##########   ##    ##  ##    ##   ##   ##        ##       ##   ##  ##       ##         ###    GPA CALCULATOR   ###" << endl
         << "##########   ##        ##    ##  ##     ##       ##       ##   ##  ##       ##         ###         SYSTEM      ###" << endl
         << "##########   ##  ####  ########  #########       ##       #######  ##       ##         ###########################" << endl
         << "##########   ##  # ##  ##        ##     ##       ##       ##   ##  ##       ##         ###   ATINGA BERNARD    ###" << endl
         << "##########   ##    ##  ##        ##     ##  ###  ##       ##   ##  ##       ##         ######   @10660742   ######" << endl
         << "##########   ########  ##        ##     ##  ###  #######  ##   ##  #######  #######    ###########################" << endl
         << "##########                                                                             ###########################" << endl
         << "##################################################################################################################" << endl
         << "###############     USE the keyword 'commands' to view available command options.      ###########################" << endl
         << "##################################################################################################################\n" << endl;
    }
    else if (val == "commands"){
        print("\n\n##################################################################################################################\n");
        print("##\t Command              -----------------------------                Function\t\t\t\t##\n");
        print("##\t ============================================================================================= \t\t##\n");
        print("##\t q                    -----------------------------    use 'q' to leave any command center.....\t\t##\n");
        print("##\t exit                 -----------------------------    end the program.........................\t\t##\n");
        print("##\t list                 -----------------------------    query list student, dep. or staff.......\t\t##\n");
        print("##\t edit                 -----------------------------    change student, dep. or staff details...\t\t##\n");
        print("##\t done                 -----------------------------    to save changes made to any userect......\t\t##\n");
        print("##\t clear                -----------------------------    reset the screen to a clean screen......\t\t##\n");
        print("##\t remove               -----------------------------    delete staff, department or student.....\t\t##\n");
        print("##\t readme               -----------------------------    shows documentation of the app..........\t\t##\n");
        print("##\t help\\h               -----------------------------    get help with commands..................\t\t##\n");
        print("##\t records              -----------------------------    record or view student academic records.\t\t##\n");
        print("##\t settings             -----------------------------    change console view, turn off help tips.\t\t##\n");
        print("##################################################################################################################\n\n");
    }
    else if (val == "y/n"){
        print("\n##################################################################################################################\n");
        print("###################################### invalid choice..please enter ( y/n ) ######################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "invh"){
        print("\n\n############################################################################################");
        print("\n##                     invalid command..use 'help' or 'h' for tips.                       ##");
        print("\n############################################################################################");
    }
    else if (val == "invcm"){
        print("\n\n############################################################################################");
        print("\n##                  invalid command method..please enter a valid command.                 ##");
        print("\n############################################################################################");
    }
    else if (val == "sb"){
        print("\n\n############################################################################################");
        print("\n##                        you can search userect by 'id' or 'name'                         ##");
        print("\n############################################################################################");
    }
    else if (val == "invsb"){
        print("\n\n############################################################################################");
        print("\n##                    invalid search method..please enter 'id', 'name                     ##");
        print("\n############################################################################################");
    }
    else if (val == "invid"){
        print("\n\n############################################################################################");
        print("\n##              the ID you entered is invalid!...please check and try again.              ##");
        print("\n############################################################################################");
    }
    else if (val == "careful"){
        print("\n\n############################################################################################");
        print("\n##***************************#*#*# Enter Details Carefully.. #*#*#************************##");
        print("\n############################################################################################\n");
    }
    else if (val == "removed"){
        print("\n\n############################################################################################");
        print("\n##***********************#*#*# This Student Has Been Removed.. #*#*#**********************##");
        print("\n############################################################################################\n");
    }
    else if (val == "helptips"){
        print("\n##################################################################################################################\n");
        print("###############################   Do you want help tips at every route? (y/n)   ##################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "addmore"){
        print("\n\n##################################################################################################################\n");
        print("####################################    Do you want to add again? (y/n).     #####################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "choice"){
        print("\n\n##################################################################################################################\n");
        print("################################     Are you sure you want to proceed? (y/n).     ################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "explore"){
        print("\n\n######################################################################################################");
        print("\n##     you can now search the found userects details...use 'help' to get more info on how to use.    ##");
        print("\n######################################################################################################");
    }
    else if (val == "error"){
        print("\n\n############################################################################################");
        print("\n#############*****************#*#*#      ERROR!!!      #*#*#******************##############");
        print("\n############################################################################################");
    }
    else if (val == "found"){
        print("\n\n############################################################################################");
        print("\n#############*****************#*#*#    Found userect    #*#*#******************##############");
        print("\n############################################################################################");
    }
    else if (val == "movesem"){
        print("\n\n############################################################################################");
        print("\n#######*****#*#  please set students school fees and subjects for next sem.  #*#*****#######");
        print("\n############################################################################################");
    }
    else if (val == "success"){
        print("\n\n############################################################################################");
        print("\n#############******************#*#*#   Successful :-)   #*#*#*****************##############");
        print("\n############################################################################################");
    }
    else if (val == "saved"){
        print("\n\n############################################################################################");
        print("\n#############*******************#   Successfully Saved.    #******************##############");
        print("\n############################################################################################");
    }
    else if (val == "ignored"){
        print("\n\n############################################################################################");
        print("\n#############***************#*#*#    No Changes Made.     #*#*#***************##############");
        print("\n############################################################################################");
    }
    else if (val == "cantfind"){
        print("\n\n############################################################################################");
        print("\n##              Cannot find userect...please check detail and search again.                ##");
        print("\n############################################################################################");
    }
    else if (val == "enterid"){
        print("\n\n############################################################################################");
        print("\n##                          Please Enter The ID or use 0 to leave                         ##");
        print("\n############################################################################################");
        print("\n\t\t\t\t\t['-'] : ");
    }
    else if (val == "entername"){
       print("\n\n############################################################################################");
        print("\n##                                  Please Enter The Name                                 ##");
        print("\n############################################################################################");
        print("\n\t\t\t\t\t['-'] : ");
    }
    else if (val == "activate"){
       print("\n\n############################################################################################");
        print("\n##                         Do yo want to Activate Student? (y/n)                          ##");
        print("\n############################################################################################");
        print("\n\t\t\t\t\t['-'] : ");
    }
    else if (val == "view"){
       print("\n\n############################################################################################");
        print("\n##                      What View Do You Want To Use ?  ( normal/list )                   ##");
        print("\n############################################################################################");
        print("\n\t\t\t\t\t['-'] : ");
    }
    else if (val == "chol"){
        print("\n\n############################################################################################");
        print("\n##                            choose from the following list                              ##");
        print("\n############################################################################################\n\n");
    }
    else if (val == "dchol"){
        print("\n\n############################################################################################");
        print("\n##                        do not choose from the following list                           ##");
        print("\n############################################################################################\n\n");
    }
    else if (val == "welcome"){
        print("\n##################################################################################################################\n");
        print("################***********#*#*#    Welcome...Please set the year and semester    #*#*#************###############\n");
        print("##################################################################################################################\n");
    }
    else if (val == "enty"){
        print("\n##################################################################################################################\n");
        print("######################################### Enter the year eg. ( 2018 ) ############################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "inventy"){
        print("\n##################################################################################################################\n");
        print("########################################### Enter the year ( > 2017 ) ############################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "ents"){
        print("\n##################################################################################################################\n");
        print("######################################### Enter the Semester ( '1'/'2' ) #########################################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "exit"){
        print("\n##################################################################################################################\n");
        print("################################# Are you sure you want to quit the program ( y/n ) ##############################\n");
        print("##################################################################################################################\n");
        print("\t\t\t\t\t\t['-'] : ");
    }
    else if (val == "help"){
        print("\n##################################################################################################################\n");
        print("##############################      Enter the [command] name for information.      ###############################\n");
        print("#####################################  Use help 'all' for all help tips. #########################################\n");
        print("##################################################################################################################\n");

    }
    else{
        print("\nprinter : error!!!\n");
    }
}

void lister(string command){
    if (command == "commands"){
        print("\n\n##################################################################################################################\n");
        print("##\t 0. q                    -----------------------------    use 'q' to leave any command center.....\t##\n");
        print("##\t 1. add                  -----------------------------    create new student, dep. or staff.......\t##\n");
        print("##\t 2. list                 -----------------------------    query list student, dep. or staff.......\t##\n");
        print("##\t 3. edit                 -----------------------------    change student, dep. or staff details...\t##\n");
        print("##\t 4. search               -----------------------------    look for student, dep. or staff info....\t##\n");
        print("##\t 5. remove               -----------------------------    delete staff, department or student.....\t##\n");
        print("##\t 6. manage               -----------------------------    pay or set student school fees etc .....\t##\n");
        print("##\t 7. records              -----------------------------    record or view student academic records.\t##\n");
        print("##\t 8. readme               -----------------------------    shows documentation of the app..........\t##\n");
        print("##\t 9. help\\h               -----------------------------    get help with commands..................\t##\n");
        print("##\t 00. exit                -----------------------------    end the program.........................\t##\n");
        print("##\t 99. clear               -----------------------------    reset the screen to a clean screen......\t##\n");
        print("##\t 10. settings            -----------------------------    set app variables, turn off help tips...\t##\n");
        print("##################################################################################################################\n");
    }
    else if (command == "choose_user"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Select userect      ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##      1. Student         ##");
        print("\n\t\t\t\t##      2. Staff           ##");
        print("\n\t\t\t\t##      3. Department      ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "settings"){
        print("\n\n\t\t\t\t##############################");
        print("\n\t\t\t\t##      Select Option       ##");
        print("\n\t\t\t\t##--------------------------##");
        print("\n\t\t\t\t## 1. activate help tips    ##");
        print("\n\t\t\t\t## 2. change console view   ##");
        print("\n\t\t\t\t## 3. re-start application  ##");
        print("\n\t\t\t\t## 4. wipe all data         ##");
        print("\n\t\t\t\t## 5. move to next semester ##");
        print("\n\t\t\t\t##############################\n");
    }
    else if (command == "list_students_by"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##    List Students By     ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. All Students      ##");
        print("\n\t\t\t\t##    2. Level             ##");
        print("\n\t\t\t\t##    3. Residence         ##");
        print("\n\t\t\t\t##    4. Department        ##");
        print("\n\t\t\t\t#############################\n");
    }
    else if (command == "list_staffs_by"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      List Staffs By     ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##      1. All Staffs      ##");
        print("\n\t\t\t\t##      2. Job Title       ##");
        print("\n\t\t\t\t##      3. Department      ##");
        print("\n\t\t\t\t#############################\n");
    }
    else if (command == "search_by"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##       Search By         ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. userects ID        ##");
        print("\n\t\t\t\t##    2. userects Name      ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "selectRouteS"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Select Route       ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Explore Student     ##");
        print("\n\t\t\t\t##  2. Edit Student        ##");
        print("\n\t\t\t\t##  3. Manage Student      ##");
        print("\n\t\t\t\t##  4. Records Center      ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "selectRouteL"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Select Route       ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Explore Student     ##");
        print("\n\t\t\t\t##  2. Edit Student        ##");
        print("\n\t\t\t\t##  3. Manage Student      ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "exploreS"){
        print("\n\n\t\t\t\t###############################");
        print("\n\t\t\t\t##       Exlore Student      ##");
        print("\n\t\t\t\t##---------------------------##");
        print("\n\t\t\t\t##  1. Students Infomation   ##");
        print("\n\t\t\t\t##  2. Students Name         ##");
        print("\n\t\t\t\t##  3. Students Age          ##");
        print("\n\t\t\t\t##  4. Students Level        ##");
        print("\n\t\t\t\t##  5. Students Residence    ##");
        print("\n\t\t\t\t##  7. Students Status       ##");
        print("\n\t\t\t\t##  8. Students Department   ##");
        print("\n\t\t\t\t##  9. Students Subjects     ##");
        print("\n\t\t\t\t##  10.Registrable Subjects  ##");
        print("\n\t\t\t\t###############################");
    }
    else if (command == "exploreL"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Exlore Staff       ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Staffs Infomation   ##");
        print("\n\t\t\t\t##  2. Staffs Name         ##");
        print("\n\t\t\t\t##  3. Staffs Age          ##");
        print("\n\t\t\t\t##  4. Staffs Job Title    ##");
        print("\n\t\t\t\t##  5. Staffs Residence    ##");
        print("\n\t\t\t\t##  6. Staffs status       ##");
        print("\n\t\t\t\t##  7. Staffs department   ##");
        print("\n\t\t\t\t##  8. Staffs subjects     ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "exploreD"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##   Exlore Department     ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Department Code     ##");
        print("\n\t\t\t\t##  2. Department Name     ##");
        print("\n\t\t\t\t##  3. Department Subject  ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "editS"){
        print("\n\n\t\t\t\t###############################");
        print("\n\t\t\t\t##       Edit Student        ##");
        print("\n\t\t\t\t##---------------------------##");
        print("\n\t\t\t\t##  1. Students Name         ##");
        print("\n\t\t\t\t##  2. Students Age          ##");
        print("\n\t\t\t\t##  3. Students Level        ##");
        print("\n\t\t\t\t##  4. Students Residence    ##");
        print("\n\t\t\t\t##  5. Students Course       ##");
        print("\n\t\t\t\t##  6. Students Department   ##");
        print("\n\t\t\t\t##  7. Students Subjects     ##");
        print("\n\t\t\t\t###############################");
    }
    else if (command == "editL"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##       Edit Staff        ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Staffs Name         ##");
        print("\n\t\t\t\t##  2. Staffs Age          ##");
        print("\n\t\t\t\t##  3. Staffs Job Title    ##");
        print("\n\t\t\t\t##  4. Staffs Residence    ##");
        print("\n\t\t\t\t##  5. Staffs department   ##");
        print("\n\t\t\t\t##  6. Staffs subjects     ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "editD"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##     Edit Department     ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##  1. Department Code     ##");
        print("\n\t\t\t\t##  2. Department Name     ##");
        print("\n\t\t\t\t##  3. Department Subject  ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "arsubj"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##    Add/Remove Subject   ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. Add Subject       ##");
        print("\n\t\t\t\t##    2. Remove Subject    ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "addsubj"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##       Add Subject       ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. Main Course       ##");
        print("\n\t\t\t\t##    2. Free Elective     ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "manageS"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Manage Student     ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. Pay school fees   ##");
        print("\n\t\t\t\t##    2. Set school fees   ##");
        print("\n\t\t\t\t##    3. Add school fees   ##");
        print("\n\t\t\t\t##    4. Remove Student    ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "manageL"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##       Manage Staff      ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##    1. Remove Staff      ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "fors"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##      Get Records        ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##     1. Semester         ##");
        print("\n\t\t\t\t##     2. Full Records     ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "records"){
        print("\n\n\t\t\t\t#############################");
        print("\n\t\t\t\t##       Records Book      ##");
        print("\n\t\t\t\t##-------------------------##");
        print("\n\t\t\t\t##     1. Enter Records    ##");
        print("\n\t\t\t\t##     2. Edit Records     ##");
        print("\n\t\t\t\t##     3. Show Records     ##");
        print("\n\t\t\t\t#############################");
    }
    else if (command == "showRecords"){
        print("\n\n\t\t\t\t#################################");
        print("\n\t\t\t\t##       Show Records          ##");
        print("\n\t\t\t\t##-----------------------------##");
        print("\n\t\t\t\t## 1. show full records        ##");
        print("\n\t\t\t\t## 2. show semester records    ##");
        print("\n\t\t\t\t## 3. export academic records  ##");
        print("\n\t\t\t\t## 4. calculate students GPA   ##");
        print("\n\t\t\t\t#################################");
    }
    else{
        print("\nprinter : error!!!\n");
    }
}

void help(string command){
    command = lower(command);
    if(command == ""){
        cin.clear();
        print("\n\nHelp >> ");
        command = lower(inputStr(""));
    }
    if (command == "exit"){
        print("\n\n############################################################################################");
        print("\n##              use 'exit' as command CHOOSE 'y' to exit or 'n' to continue               ##");
        print("\n############################################################################################ ");
    }
    else if (command == "help" || command == "h"){
        print("\n\n############################################################################################");
        print("\n##      use 'help' [command] e.g. help all , help edit or [command] 'h' e.g. search h     ##");
        print("\n############################################################################################");
    }
    else if (command == "add"){
        print("\n\n############################################################################################");
        print("\n##  TO ADD NEW STUDENT......: use option 's' or 'student'                                 ##");
        print("\n##  [option] [firstname] [lastname] [othername] [age] [level] [Department Code] [course]  ##");
        print("\n############################################################################################ ");
        print("\n##  TO ADD NEW STAFF........: use 'l' or 'staff'                                          ##");
        print("\n##  [option] [firstname] [lastname] [othername] [age] [Department Code]                   ##");
        print("\n############################################################################################");
        print("\n##  TO ADD NEW DEPARTMENT...: use 'd' or 'department'                                     ##");
        print("\n##  [option] [Department Code] [Department Name]                                          ##");
        print("\n############################################################################################");
    }
    else if (command == "list"){
        print("\n\n############################################################################################");
        print("\n##  TO LIST STUDENT......: use option 's' or 'student'                                    ##");
        print("\n##  [option] [by] 'all', 'level', 'dep' [department code], 'residence' [residence name]   ##");
        print("\n############################################################################################ ");
        print("\n##  TO LIST STAFF........: use 'l' or 'staff'                                             ##");
        print("\n##  [option] [by] 'all' , 'dep' [department code] , 'job' [Job Title]                     ##");
        print("\n############################################################################################");
        print("\n##  TO LIST DEPARTMENT...: use 'd' or 'department'                                        ##");
        print("\n############################################################################################");
    }
    else if (command == "search"){
        print("\n\n############################################################################################");
        print("\n##  TO SEARCH STUDENT......: use option 's' or 'student'                                  ##");
        print("\n##  [option] 'id' [student id] or [option] 'name' [student firstname and lastname]        ##");
        print("\n############################################################################################ ");
        print("\n##  TO SEARCH STAFF........: use 'l' or 'staff'                                           ##");
        print("\n##  [option] 'id' [staff id] or [option] 'name' [staff firstname and lastname]            ##");
        print("\n############################################################################################");
        print("\n##  TO SEARCH DEPARTMENT...: use 'd' or 'department'                                      ##");
        print("\n##  [option] [Department Code]   e.g. search d [cscd/MATHS].. list dep_code with 'ldc'    ##");
        print("\n############################################################################################");
        print("\n##  with GOTO..............: use 'explore' or 'edit' or 'remove' or 'academics            ##");
        print("\n##  e.g. Route Goto >> explore .. Goto >> edit ... Goto >> remove ...Goto >> academics    ##");
        print("\n############################################################################################");
    }
    else if (command == "explorestudent"){
        print("\n\n############################################################################################");
        print("\n#############################   USE the following keywords   ###############################");
        print("\n##**********                    'reg_subj' or 'reg_subjects'                 *************##");
        print("\n##                        'info' , 'name', 'age', 'level','residence'                     ##");
        print("\n##                       'course', 'status', dep', 'subj' or 'subjects'                   ##");
        print("\n############################################################################################");
    }
    else if (command == "explorestaff"){
        print("\n\n############################################################################################");
        print("\n#############################   USE the following keywords   ###############################");
        print("\n##**********                        'name', 'age', 'job'                     *************##");
        print("\n##                       'dep', 'subj', 'subjects' or 'residence'                         ##");
        print("\n############################################################################################");
    }
    else if (command == "editstudent"){
        print("\n\n############################################################################################");
        print("\n#############################   USE the following keywords   ###############################");
        print("\n##**********               'name', 'age', 'level', 'residence'               *************##");
        print("\n##                        'course', 'dep', 'subj' or 'subjects'                           ##");
        print("\n############################################################################################");
    }
    else if (command == "editstaff"){
        print("\n\n############################################################################################");
        print("\n#############################   USE the following keywords   ###############################");
        print("\n##**********                       'name', 'age', 'job',                     *************##");
        print("\n##                       'residence', 'dep', 'subj' or 'subjects'                         ##");
        print("\n############################################################################################");
    }
    else if (command == "exploredep"){
        print("\n\n############################################################################################");
        print("\n#############################   USE the following keywords   ###############################");
        print("\n##**********                     'code', 'name', 'subject'                   *************##");
        print("\n############################################################################################");
    }
    else if (command == "remove"){
        print("\n\n############################################################################################");
        print("\n##  TO REMOVE STUDENT......: use option 's' or 'student'                                  ##");
        print("\n##  [option] 'id' [student id] or [option] 'name' [student firstname and/or lastname]     ##");
        print("\n############################################################################################");
        print("\n##  TO REMOVE STAFF........: use option 'l' or 'staff'                                    ##");
        print("\n##  [option] 'id' [staff id] or [option] 'name' [staff firstname and/or lastname]         ##");
        print("\n############################################################################################");
        print("\n##  TO REMOVE DEPARTMENT...: use 'd' or 'department'                                      ##");
        print("\n##  [option] [Department Code]   e.g. remove d [cscd/MATHS].. list dep_code with 'ldc'    ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
        print("\n##          search user with [search] command first then enter 'remove' at Goto..          ##");
        print("\n############################################################################################");
    }
    else if (command == "edit"){
        print("\n\n############################################################################################");
        print("\n##  TO EDIT STUDENT........: use option 's' or 'student'                                  ##");
        print("\n##  [option] 'id' [student id] or [option] 'name' [student firstname and/or lastname]     ##");
        print("\n############################################################################################");
        print("\n##  TO EDIT STAFF..........: use option 'l' or 'staff'                                    ##");
        print("\n##  [option] 'id' [staff id] or [option] 'name' [staff firstname and/or lastname]         ##");
        print("\n############################################################################################");
        print("\n##  TO EDIT DEPARTMENT.....: use 'd' or 'department'                                      ##");
        print("\n##  [option] [Department Code]   e.g. edit d [cscd/MATHS].. list dep_code with 'ldc'      ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
        print("\n############################################################################################");
        print("\n##          search user with [search] command first then enter 'edit' at Goto..            ##");
        print("\n############################################################################################");
    }
    else if (command == "arsubject"){
        print("\n\n############################################################################################");
        print("\n##  TO ADD SUBJECTS.........: use option 'add' or 'a'                                     ##");
        print("\n##  [option] ['main' or 'm'|| 'free' or 'f'] [full subject name]                          ##");
        print("\n############################################################################################ ");
        print("\n##  TO REMOVE SUBJECTS......: use option 'remove' or 'r'                                  ##");
        print("\n##  [option] [enter subject from list] [y or n]                                           ##");
        print("\n############################################################################################");
        print("\n##  USE the exact Subjects names from the list...                                         ##");
        print("\n##  eg.. CSCD 201 or cscd 201 or FE-CHEM 201                                              ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
    }
    else if (command == "showrecords"){
        print("\n\n############################################################################################");
        print("\n##  TO VIEW FULL STUDENT ACADEMIC RECOREDS..........: use option 'full' or 'f'            ##");
        print("\n############################################################################################ ");
        print("\n##  TO VIEW SEMESTER STUDENT ACADEMIC RECOREDS......: use option 'sem' or 's'             ##");
        print("\n############################################################################################");
        print("\n##  TO EXPORT STUDENT RECORDS.......................: use option 'exp' or 'e'             ##");
        print("\n############################################################################################");
        print("\n##  TO CHECK STUDENT GPA............................: use the option 'gpa'                ##");
        print("\n############################################################################################");
    }
    else if (command == "records"){
        print("\n\n############################################################################################");
        print("\n##  TO ENTER STUDENT ACADEMIC RECOREDS..........: use option 'entry' or 'en'              ##");
        print("\n############################################################################################ ");
        print("\n##  TO EDIT STUDENT ACADEMIC RECOREDS...........: use option 'edit' or 'ed'               ##");
        print("\n############################################################################################");
        print("\n##  TO SHOW STUDENT ACADEMIC RECORDS............: use option 'show' or 's'                ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
    }
    else if (command == "routes"){
        print("\n\n############################################################################################");
        print("\n##  TO EDIT DETAILS ......................: use the option 'edit'                         ##");
        print("\n############################################################################################ ");
        print("\n##  TO EXPLORE DETIALS ...................: use the option 'explore'                      ##");
        print("\n############################################################################################");
        print("\n##  TO MANAGE userECT......................: use the option 'manage'                       ##");
        print("\n############################################################################################");
        print("\n##  TO GOTO STUDENT ACADEMIC RECORDS......: use the option 'records'   (student's only)   ##");
        print("\n############################################################################################");
    }
    else if (command == "view"){
        print("\n\n############################################################################################");
        print("\n##  TO USE NORMAL CONSOLE VIEW ......................: use the option 'normal' or 'l'     ##");
        print("\n############################################################################################ ");
        print("\n##  NORMAL PREVIEW :                                                                      ##");
        print("\n##                                                                                        ##");
        print("\n##  1.                                                                                    ##");
        print("\n##     Enter A Command >>>> add                                                           ##");
        print("\n##                                                                                        ##");
        print("\n##     Add >> student                                                                     ##");
        print("\n##                                                                                        ##");
        print("\n##  2.                                                                                    ##");
        print("\n##     Enter A Command >>>> search student name bernard                                   ##");
        print("\n##                                                                                        ##");
        print("\n##  3.                                                                                    ##");
        print("\n##     Enter A Command >>>> manage s id 10640000                                          ##");
        print("\n##                                                                                        ##");
        print("\n############################################################################################");
        print("\n##  TO USE LIST CONSOLE VIEW ...................: use the option 'list' or 'l'            ##");
        print("\n############################################################################################");
        print("\n##  LIST PREVIEW :                                                                        ##");
        print("\n##                                                                                        ##");
        print("\n##  1.                                                                                    ##");
        print("\n##     Enter A Command >>>> 1                                                             ##");
        print("\n##                                                                                        ##");
        print("\n##     Add >> 1                                                                           ##");
        print("\n##                                                                                        ##");
        print("\n##  2.                                                                                    ##");
        print("\n##     Enter A Command >>>> 4 1 2 bernard                                                 ##");
        print("\n##                                                                                        ##");
        print("\n##  3.                                                                                    ##");
        print("\n##     Enter A Command >>>> 6 1 1 10640000                                                ##");
        print("\n##                                                                                        ##");
        print("\n############################################################################################");
    }
    else if (command == "manage"){
        print("\n\n############################################################################################");
        print("\n##  USE 'pay' or 'p' if student wants to pay SCHOOL FEES                                  ##");
        print("\n############################################################################################");
        print("\n##  USE 'add' or 'a'  to add SCHOOL FEES ..::Adds the amount to the students schoolfees   ##");
        print("\n############################################################################################");
        print("\n##  USE 'set' or 's'  to set SCHOOL FEES ..::reset the student school fees to the amount  ##");
        print("\n############################################################################################");
        print("\n##  USE 'remove' or 'r'  to REMOVE userECT..::removes (student or staff) userect            ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
    }
    else if (command == "settings"){
        print("\n\n############################################################################################");
        print("\n##  USE 'tips' or 't'      ........: to TURN OFF HELP TIPS AT ROUTES                      ##");
        print("\n############################################################################################");
        print("\n##  USE 'view' or 'v'      ........: to CHANGE VIEW TO LIST VIEW or CONSOLE VIEW          ##");
        print("\n############################################################################################");
        print("\n##  USE 'start' or 's'     ........: to RESET ACADEMIC CALLENDER                          ##");
        print("\n############################################################################################ ");
        print("\n##  USE 'wipe' or 'w'      ........: to CLEAR userECTS AND RESET APP                      ##");
        print("\n############################################################################################");
        print("\n##  USE 'nextsem' or 'n'   ........: to MOVE STUDENT OBJECTS TO NEXT SEMESTER             ##");
        print("\n############################################################################################");
    }
    else if (command == "find"){
        print("\n\n############################################################################################");
        print("\n##  TO FIND STUDENT......: use option 's' or 'student'                                    ##");
        print("\n##  [option] 'id' [student id] or [option] 'name' [student firstname and lastname]        ##");
        print("\n############################################################################################");
        print("\n##  TO SAVE EDITED DETAILS......................: use 'done' or use 'q' ot ignore changes ##");
        print("\n############################################################################################");
    }
    else if (command == "all"){
        help("add");help("search");help("routes");help("remove");help("edit");help("arsubject");
        help("records");help("showrecords");help("manage");help("settings");
    }
    else if (command == "q" || command == "0"){
        //pass to end help
    }
    else{
        printer("invh");
        help("");
    }
}

float getGpaScore(string grade){
    if (grade == "A"){
        return 4.0;
    }
    else if (grade == "B+"){
        return 3.5;
    }
    else if (grade == "B"){
        return 3.0;
    }
    else if (grade == "C+"){
        return 2.5;
    }
    else if (grade == "C"){
        return 2.0;
    }
    else if (grade == "D+"){
        return 1.5;
    }
    else if (grade == "D"){
        return 1.0;
    }
    else if (grade == "E"){
        return 0.5;
    }
    else if (grade == "F"){
        return 0.0;
    }
    return -1;
}

string GpaCalc(string type){
    float gpa = 0.0;int tch=0;bool empty_score = false;
    if (type == "sem"){
        int len = user.getSemRecords().size();
        vector<record> temp_record = user.getSemRecords();
        for (int i=0;i<len;i++){
            if (temp_record[i].mark == -1){
                empty_score = true;
                return "**N/A**";
                break;
            }
            else{
                tch += temp_record[i].credit_hr;
                gpa += getGpaScore(temp_record[i].grade)*temp_record[i].credit_hr;
            }
        }
        if (empty_score == false){
            if (len != 0){
                stringstream ss;
                ss << gpa/tch;
                return ss.str();
            }
            else{
                return "**N/A**";
            }
        }
    }
    else if (type == "full"){
        int len = user.getAcaRecords().size();
        vector<record> temp_record = user.getAcaRecords();
        for (int i=0;i<len;i++){
            if (temp_record[i].mark == -1){
                empty_score = true;
                return "**N/A**";
                break;
            }
            else{
                tch += temp_record[i].credit_hr;
                gpa += getGpaScore(temp_record[i].grade)*temp_record[i].credit_hr;
            }
        }
        if (empty_score == false){
            if (len != 0){
                stringstream ss;
                ss << gpa/tch;
                return ss.str();
            }
            else{
                return "**N/A**";
            }
        }
    }
}


string choice(){
    cin.clear();
    string v = lower(inputStr(""));
    if (v=="y"){
        return "y";
    }
    else if (v=="n"){
        return "n";
    }
    else{
        printer("y/n");
    }
    return lower(choice());
}

void exit(){
    printer("exit");
    string ans = choice();
    if (ans == "y"){
        //pass to exit
    }
    else{
        engine();
    }
}

void addremoveSubject(){
    string command = lower(inputStr("\n\nAdd\\Remove Course : "));
    vector<string> list = user.getRegCourses();
    if (command == "add" || command == "a" || command == "1"){
        string ans = inputStr("Is it a main or free course? (main/free) : ");
        if (ans == "main" || ans == "m"){
            printer("chol");user.listRegCourses();print("\n");
            cin.ignore();string subj = getStr("\nEnter the subject :\t");
            while (findStr(list, subj) != true || findStr(user.getSubjects(), subj) == true){
                printer("chol");user.listRegCourses();print("\n");
                subj = getStr("\n(invalid choice) Enter the subject :\t");
            }
            user.setSubject(upper(subj));
        }
        else if (ans == "free" || ans == "f" || ans == "2"){
            cin.ignore();string subj = getStr("\nEnter the subject :\t");
            while (findStr(list, subj) == true){
                printer("chol");user.getRegCourses();print("\n");
                subj = getStr("\n(not a free elective) Enter a different subject :\t");
            }
            user.setSubject("FE-" + upper(subj));
        }
        else{
            help("arsubject");
        }
        addremoveSubject();
    }
    else if (command == "remove" || command == "r" || command == "2"){
        printer("chol");user.listSubjects();print("\n");
        cin.ignore();string subj = getStr("\nselect subject from list to remove : ");
        while (findStr(list, subj) != true){
            printer("chol");user.listSubjects();print("\n");
            subj = getStr("\nplease select subject from list to remove : ");
        }
        user.removeSubject(upper(subj));
        addremoveSubject();
    }
    else if(command == "help" || command == "h"){
        help("arsubject");
        addremoveSubject();
    }
    else if(command == "done"){
        //commit changes to global Students
        printer("saved");
        //pass to search
    }
    else if(command == "q" || command == "0"){
        printer("ignored");
        //pass to exit
    }
    else{
        printer("invcm");
        addremoveSubject();
    }
}

void _edit(){
    if (user.getStatus() != "null"){
        string edit = lower(inputStr("\n\nEdit Student's > "));
        if (edit == "name" || edit == "1"){
            print("\nStudents name : ");print(user.getName());
            cin.ignore();user.setName(getStr("\n\nChange to : "));
            _edit();
        }
        else if (edit == "level" || edit == "3"){
            print("\nStudents level : ");print(user.getLevel());
            int level = inputInt("\n\nChange to : ");
            while (level != 100 && level != 200 && level != 300 && level != 400){
               level = inputInt("\nChange to 100,200,300 or 400: ");
            }
            print("\nStudents subjects will reseusert... Student will Have To Register Subjects Again !!!\n");
            printer("choice");
            string ans = choice();
            if (ans == "y"){
                user.setLevel(level);
                user.refresh();
            }
            _edit();
        }
        else if (edit == "dep" || edit == "department" || edit == "6"){
            print("\nStudents Department : ");print(user.getDepartment());
            printer("chol");listItems(DEP_CODES);print("\n");
            string code = inputStr("\n\nChange to : ");
            while (findStr(DEP_CODES, code) != true){
                code = inputStr("\n\n(select code from list above) Change to : ");
            }
            user.setDepartment(code);
            _edit();
        }
        else if (edit == "subjects" || edit == "subj" || edit == "7"){
            if (helptips == 1){
                help("arsubject");
            }
            print("\n\n\nStudents subject : \n");user.listSubjects();print("\n");
            if (view == "list"){
                lister("arsubj");
            }
            addremoveSubject();
            _edit();
        }
        else if (edit == "residence" || edit == "4"){
            print("\nStudents Residence : ");print(user.getResidence());
            cin.ignore();user.setResidence(getStr("\n\nChange to : "));
            _edit();
        }
        else if (edit == "help" || edit == "h"){
            help("editstudent");
            _edit();
        }
        else if (edit == "done"){
            //commit changes and push
            printer("saved");
            //pass to search
        }
        else if (edit == "q" || edit == "0"){
            //forget changes and quit route
            printer("ignored");
            //pass to exit
        }
        else{
            printer("invcm");
            _edit();
        }
    }
}


void explore(string status){
    //check if userect is NULL
    if (user.getStatus() != "null"){
        if (status == "new"){
            printer("explore");
        }
        string get = lower(inputStr("\n\nGet Student's > "));
        if (get == "id"){
            print("\n");print(user.getID());
            explore("pass");
        }
        else if (get == "name" || get == "2"){
            print("\n");print(user.getName());
            explore("pass");
        }
        else if (get == "age" || get == "3"){
            print("\n");print(user.getAge());print(" years.");
            explore("pass");
        }
        else if (get == "level" || get == "4"){
            print("\n");print(user.getLevel());
            explore("pass");
        }
        else if (get == "status" || get == "7"){
            print("\n");print(user.getStatus());
            explore("pass");
        }
        else if (get == "residence" || get == "5"){
            print("\n");print(user.getResidence());
            explore("pass");
        }
        else if (get == "dep" || get == "department" || get == "8"){
            print("\n");print(user.getDepartment());
            explore("pass");
        }
        else if (get == "subjects" || get == "subj" || get == "9"){
            print("\n");user.listSubjects();
            explore("pass");
        }
        else if (get == "reg_subjects" || get == "reg_subj" || get == "10"){
            print("\n");user.listRegCourses();
            explore("pass");
        }
        else if (get == "info" || get == "1"){
            user.getInfo();
            explore("pass");
        }
        else if (get == "help" || get == "h"){
            help("explorestudent");
            explore("pass");
        }
        else if (get == "q" || get == "0"){
            //pass
        }
        else{
            printer("invcm");
            explore("pass");
        }
    }
}


void entryRecords(){
    int len = user.getSubjects().size();
    print("Record Entry for Year ");print(year);print(" Semester ");print(semester);
    for (int i=0;i<len;i++){
        if (user.getSemRecords()[i].mark == -1){
            print("\n\nSubject :\t");print(user.getSemRecords()[i].course_name);
            print("\nDo you want to record marks for this subject (y/n) : ");
            string ans = choice();
            if (ans == "y"){
                int credit_h = inputInt("Credit Hour(s)\t:\t");
                while (0 > credit_h || credit_h > 3){
                    credit_h = inputInt("(invalid) Credit Hour(s)\t:\t");
                }
                int score = inputInt("Enter Mark\t:\t");
                while (0 > score || score >100){
                    score = inputInt("(invalid) Enter Mark\t:\t");
                }
                user.recordMark(i, credit_h, score);
            }
        }
    }
}

void editRecords(){
    int len = user.getSubjects().size();
    print("Record Entry for Year ");print(year);print(" Semester ");print(semester);
    for (int i=0;i<len;i++){
        if (user.getSemRecords()[i].mark != -1){
            print("\n\nSubject :\t");print(user.getSemRecords()[i].course_name);
            print("\nDo you want to edit marks for this subject ( y/n ) : ");
            string ans = choice();
            if (ans == "y"){
                int credit_h = inputInt("Credit Hour(s)\t:\t");
                while (0 > credit_h || credit_h > 3){
                    credit_h = inputInt("(invalid) Credit Hour(s)\t:\t");
                }
                int score = inputInt("Enter Mark\t:\t");
                while (0 > score || score >100){
                    score = inputInt("(invalid) Enter Mark\t:\t");
                }
                user.recordMark(i, credit_h, score);
            }
        }
    }
}


void exportRecords(, string opt){
    //display academic records
    if (user.getStatus() != "null"){
        ofstream writer;
        stringstream ss;
        ss << user.getID();
        if (opt == "full"){
            string filename = ss.str() + "_full_records.txt";
            writer.open(filename.c_str());
            int len = user.getAcaRecords().size();int sem;
            writer << "\n\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=";
            writer << "\n********************************************************************************************";
            writer << "\nName : " << "STUDENT" << "\t\tDepartment : " << user.getDepartment();
            writer << "\nCourse : " << "*****" << "\tSemester : " << semester;
            writer << "\tLevel : " << user.getLevel() << "\t\tGPA :\t" << GpaCalc(user,"full");
            writer << "\n********************************************************************************************";
            writer << "\n============================================================================================";
            if (len != 0) sem = user.getAcaRecords()[0].semester;bool checker;
            for (int i=0;i<len;i++){
                checker = (user.getAcaRecords()[i].semester == sem);
                if (checker == false){
                    sem = user.getAcaRecords()[i].semester;
                    writer << "\n\n--------------------------------------------------------------------------------------------";
                }
                if (user.getAcaRecords()[i].mark != -1){
                    writer << "\n\nSubject :\t" << user.getAcaRecords()[i].course_name;
                    writer << "\t\tMark :\t" << user.getAcaRecords()[i].mark;
                    writer << "\t\tGrade :\t" << user.getAcaRecords()[i].grade;
                }
                else{
                    writer << "\n\nSubject :\t" << user.getAcaRecords()[i].course_name;
                    writer << "\t\tMark :\t" << "**N/A**";
                    writer << "\t\tGrade :\t" << "**N/A**";
                }
            }
            writer << "\n\n============================================================================================";
            writer << "\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=";
            writer.close();
            printer("success");
        }
        else if (opt == "sem"){
            string filename = ss.str() + "_sem_records.txt";
            writer.open(filename.c_str());
            int len = user.getSubjects().size();
            writer << "\n\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=";
            writer << "\n********************************************************************************************";
            writer << "\nName : " << "STUDENT" << "\tCourse : " << "*****" << "\tLevel : " << user.getLevel();
            writer << "\nRecords for Year " << year << "\\" << year+1 << "\t\tSemester " << semester << "\t\tGPA :\t" << GpaCalc(user,"sem");
            writer << "\n********************************************************************************************";
            writer << "\n============================================================================================";
            for (int i=0;i<len;i++){
                if (user.getSemRecords()[i].mark != -1){
                    writer << "\n\nSubject :\t" << user.getSemRecords()[i].course_name;
                    writer << "\t\tMark :\t" << user.getSemRecords()[i].mark;
                    writer << "\t\tGrade :\t" << user.getSemRecords()[i].grade;
                }
                else{
                    writer << "\n\nSubject :\t" << user.getSemRecords()[i].course_name;
                    writer << "\t\tMark :\t" << "**N/A**";
                    writer << "\t\tGrade :\t" << "**N/A**";
                }
            }
            writer << "\n\n============================================================================================";
            writer << "\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=";
            writer.close();
            printer("success");
        }
    }
}



void showRecords(){
    //display academic records
    if (user.getStatus() != "null"){
        cin.clear();
        print("\n\n\nShow Records (f/s) >> ");
        string command = lower(inputStr(""));
        if (command == "full" || command == "f" || command == "1"){
            int len = user.getAcaRecords().size();int sem;
            print("\n\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=");
            print("\n********************************************************************************************");
            print("\nName : ");print(user.getName());print("\t\tDepartment : ");print(user.getDepartment());
            print("\nCourse : ");print("*****");print("\tSemester : ");print(semester);
            print("\tLevel : ");print(user.getLevel());print("\t\tGPA :\t");print(GpaCalc(user,"full"));
            print("\n********************************************************************************************");
            print("\n============================================================================================");
            if (len != 0) sem = user.getAcaRecords()[0].semester;bool checker;
            for (int i=0;i<len;i++){
                checker = (user.getAcaRecords()[i].semester == sem);
                if (checker == false){
                    sem = user.getAcaRecords()[i].semester;
                    print("\n\n--------------------------------------------------------------------------------------------");
                }
                if (user.getAcaRecords()[i].mark != -1){
                    print("\n\nSubject :\t");print(user.getAcaRecords()[i].course_name);
                    print("\t\tMark :\t");print(user.getAcaRecords()[i].mark);
                    print("\t\tGrade :\t");print(user.getAcaRecords()[i].grade);
                }
                else{
                    print("\n\nSubject :\t");print(user.getAcaRecords()[i].course_name);
                    print("\t\tMark :\t");print("**N/A**");
                    print("\t\tGrade :\t");print("**N/A**");
                }
            }
            print("\n\n============================================================================================");
            print("\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=");
            showRecords(user);
        }
        else if (command == "sem" || command == "s" || command == "2"){
            int len = user.getSubjects().size();
            print("\n\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=");
            print("\n********************************************************************************************");
            print("\nName : ");print("STUDENT");print("\tCourse : ");print("*****");print("\tLevel : ");print(user.getLevel());
            print("\nRecords for Year ");print(year);print("\\");print(year+1);print("\t\tSemester ");print(semester);print("\t\tGPA :\t");print(GpaCalc(user,"sem"));
            print("\n********************************************************************************************");
            print("\n============================================================================================");
            for (int i=0;i<len;i++){
                if (user.getSemRecords()[i].mark != -1){
                    print("\n\nSubject :\t");print(user.getSemRecords()[i].course_name);
                    print("\t\tMark :\t");print(user.getSemRecords()[i].mark);
                    print("\t\tGrade :\t");print(user.getSemRecords()[i].grade);
                }
                else{
                    print("\n\nSubject :\t");print(user.getSemRecords()[i].course_name);
                    print("\t\tMark :\t");print("**N/A**");
                    print("\t\tGrade :\t");print("**N/A**");
                }
            }
            print("\n\n============================================================================================");
            print("\n8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=8=");
            showRecords(user);
        }
        else if (command == "gpa" || command == "4"){
            print("\nStudents GPA for the Semester is :\t");GpaCalc(user, "sem");
            print("\nStudents Total GPA is :\t\t");GpaCalc(user, "full");
           showRecords(user);
        }
        else if (command == "exp" || command == "e" || command == "3"){
            if (view == "list"){
                lister("fors");
           }
           string opt = lower(inputStr("\nExport Records (f/s) : "));

           while (opt != "f" && opt != "s" && opt != "1" && opt != "2"){
                opt = lower(inputStr("( invalid ) enter full records - 'f' or semester records - 's'   : "));
           }
           if (opt == "f" || opt == "2"){
                exportRecords(user, "full");
           }
           else{
                exportRecords(user, "sem");
           }
        }
        else if (command == "help" || command == "h"){
           help("showrecords");
           showRecords(user);
        }
        else if (command == "q" || command == "0"){
            //pass to end help
        }
        else{
            printer("invh");
            showRecords(user);
        }
    }
}


void _records(){
    if (user.getStatus() != "null" && user.getStatus() != "removed"){
        cin.clear();
        print("\n\nRecords Goto >> ");
        string command = lower(inputStr(""));
        if (command == "entry" || command == "en" || command == "1"){
            entryRecords(user);

            _records(user);
        }
        else if (command == "edit" || command == "ed" || command == "2"){
            editRecords(user);

            _records(user);
        }
        else if (command == "show" || command == "s" || command == "3"){
            if (helptips == 1){
                help("showrecords");
            }
            if (view == "list"){
                lister("showRecords");
            }
            showRecords(user);
            _records(user);
        }
        else if (command == "help" || command == "h"){
           help("records");
           _records(user);
        }
        else if (command == "done"){
            Students[user.getIndex()] = user;
            printer("saved");
        }
        else if (command == "q" || command == "0"){
            //pass to end help
            printer("ignored");
        }
        else{
            printer("invh");
            _records(user);
        }
    }
    else if (user.getStatus() == "removed"){
        printer("error");
        printer("removed");
    }
}


void selectRoute(){
    string route = lower(inputStr("\n\nRoute Goto >> "));
    if (route == "explore" || route == "1"){
        if (helptips == 1){
            help("explorestudent");
        }
        if (view == "list"){
            lister("exploreS");
        }
        explore(user, "pass");
        selectRoute(user);
    }
    else if (route == "edit" || route == "2"){
        if (helptips == 1){
            help("editstudent");
        }
        if (view == "list"){
            lister("editS");
        }
        _edit(user);

        selectRoute(user);
    }
    else if (route == "records" || route == "4"){
        if (helptips == 1){
            help("records");
        }
        if (view == "list"){
            lister("records");
        }
        _records(user);
        selectRoute(user);
    }
    else if (route == "help" || route == "h"){
        help("routes");
        selectRoute(user);
    }
    else if (route == "q" || route == "0"){
        //pass to search
    }
    else{
        printer("invcm");
        selectRoute(user);
    }
}

void _search(){
    print("\n\nSearch >> ");
    string command = lower(inputStr(""));
    cin.clear();
    if (command == "s" || command == "student" || command == "1"){
        student found;
        if (view == "normal"){
            printer("sb");
        }
        else{
            lister("search_by");
        }

        string s = lower(inputStr("\n\nSearch by > "));
        while(s != "id" && s != "name" && s != "1" && s != "2"){
            printer("invsb");
            s = lower(inputStr("\n\nSearch by  > "));
        }
        if (s == "id" || s == "1"){
            printer("enterid");
            int val = inputInt("");
            if (val != 0){
                while (val - stu_ID < 0 && stu_ID - val > 0){
                    printer("invid");
                    printer("enterid");
                    val = inputInt("");
                    if (val == 0){
                        break;
                    }
                }
                if (val != 0){
                    found = searchStudent(val);
                }
            }
        }
        else if (s == "name" || s == "2"){
            printer("entername");
            cin.ignore();string val = getStr("");
            found = searchStudent(val);
        }
        else{
            printer("invcm");
            _search();
        }
        if (found.getStatus() != "null"){
            if (helptips == 1){
                help("routes");
            }
            if (view == "list"){
                lister("selectRouteS");
            }
            selectRoute(found);
        }
        _search();
    }
    else if (command == "help" || command == "h"){
       help("search");
       _search();
    }
    else if (command == "q" || command == "0"){
        //pass to end help
    }
    else{
        printer("invh");
        _search();
    }
}


void _remove(){
    print("\n\nRemove >> ");
    string command = lower(inputStr(""));
    cin.clear();
    if (command == "s" || command == "student" || command == "1"){
        student found;
        if (view == "normal"){
            printer("sb");
        }
        else{
            lister("search_by");
        }
        string s = lower(inputStr("\n\nSearch by > "));
        while(s != "id" && s != "name" && s != "1" && s != "2"){
            printer("invsb");
            s = lower(inputStr("\n\nSearch by  > "));
        }
        if (s == "id" || s == "1"){
            printer("enterid");
            int val = inputInt("");
            if (val != 0){
                while (val - stu_ID < 0 && stu_ID - val > 0){
                    printer("invid");
                    printer("enterid");
                    val = inputInt("");
                    if (val == 0){
                        break;
                    }
                }
                if (val != 0){
                    found = searchStudent(val);
                }
            }
        }
        else if (s == "name" || s == "2"){
            printer("entername");
            cin.ignore();string val = getStr("");
            found = searchStudent(val);
        }
        else{
            printer("invcm");
            _remove();
        }
        if (found.getStatus() != "null"){
            _remove(found);
        }
        _remove();
    }
    else if (command == "help" || command == "h"){
       help("remove");
       _remove();
    }
    else if (command == "q" || command == "0"){
        //pass to end help
    }
    else{
        printer("invh");
        _remove();
    }
}


void _edit(){
    print("\n\nEdit >> ");
    string command = lower(inputStr(""));
    cin.clear();
    if (command == "s" || command == "student" || command == "1"){
        student found;
        if (view == "normal"){
            printer("sb");
        }
        else{
            lister("search_by");
        }
        string s = lower(inputStr("\n\nSearch by > "));
        while(s != "id" && s != "name" && s != "1" && s != "2"){
            printer("invsb");
            s = lower(inputStr("\n\nSearch by  > "));
        }
        if (s == "id" || s == "1"){
            printer("enterid");
            int val = inputInt("");
            if (val != 0){
                while (val - stu_ID < 0 && stu_ID - val > 0){
                    printer("invid");
                    printer("enterid");
                    val = inputInt("");
                    if (val == 0){
                        break;
                    }
                }
                if (val != 0){
                    found = searchStudent(val);
                }
            }
        }
        else if (s == "name" || s == "2"){
            printer("entername");
            cin.ignore();string val = getStr("");
            found = searchStudent(val);
        }
        else{
            printer("invcm");
            _edit();
        }
        if (found.getStatus() != "null"){
            if (helptips == 1){
                help("editstudent");
            }
            if (view == "list"){
                lister("editS");
            }
            _edit(found);
        }
        _edit();
    }
    else if (command == "help" || command == "h"){
       help("edit");
       _edit();
    }
    else if (command == "q" || command == "0"){
        //pass to end help
    }
    else{
        printer("invh");
        _edit();
    }
}


void _records(){
    print("\n\nRecords >> ");
    string command = lower(inputStr(""));
    cin.clear();
    if (command == "s" || command == "student" || command == "1"){
        student found;
        if (view == "normal"){
            printer("sb");
        }
        else{
            lister("search_by");
        }
        string s = lower(inputStr("\n\nSearch by > "));
        while(s != "id" && s != "name" && s != "1" && s != "2"){
            printer("invsb");
            s = lower(inputStr("\n\nSearch by > "));
        }
        if (s == "id" || s == "1"){
            printer("enterid");
            int val = inputInt("");
            if (val != 0){
                while (val - stu_ID < 0 && stu_ID - val > 0){
                    printer("invid");
                    printer("enterid");
                    val = inputInt("");
                    if (val == 0){
                        break;
                    }
                }
                if (val != 0){
                    found = searchStudent(val);
                }
            }
        }
        else if (s == "name" || s == "2"){
            printer("entername");
            cin.ignore();string val = getStr("");
            found = searchStudent(val);
        }
        else{
            printer("invcm");
            _records();
        }
        if (found.getStatus() != "null"){
            if (helptips == 1){
                help("records");
            }
            if  (view == "list"){
                lister("records");
            }
            _records(found);
        }
        _records();
    }
    else if (command == "help" || command == "h"){
       help("find");
       help("records");
       _records();
    }
    else if (command == "q" || command == "0"){
        //pass to end help    }
    else{
        printer("invh");
        _records();
    }
}


void _settings(string command){
    if (command == ""){
        cin.clear();
        print("\n\nSetting >> ");
        string command = lower(inputStr(""));
        if (command == "nextsem" || command == "n" || command == "5"){
//          set move to next semester /year
            printer("choice");string ans = choice();
            if (ans == "y"){
                if (semester == 1){
                    semester = 2;
                }
                else{
                    semester = 1;
                    year += 1;
                }
                int len = Students.size();
                for (int i=0;i<len;i++){
                    Students[i].moveSemester();
                }
                printer("movesem");
                _settings("");
            }
        }
        else if (command == "wipe" || command == "w" || command == "4"){
            printer("choice");string ans = choice();
            if (ans == "y"){
               Students.clear();
               system("CLS");
               _start();
            }
        }
        else if (command == "start" || command == "s" || command == "3"){
            printer("choice");string ans = choice();
            if (ans == "y"){
               _settings("start");
               _settings("");
            }
        }
        else if (command == "view" || command == "v" || command == "2"){
            help("view");printer("view");string ans = lower(inputStr(""));
            while (ans != "list" && ans != "l" && ans != "normal" && ans != "n"){
                printer("view");
                ans = lower(inputStr(""));
            }
            if (ans == "list" || ans == "l"){
                view = "list";helptips = 0;
            }
            else if(ans == "normal" || ans == "n"){
                view = "normal";helptips = 1;
            }
            system("CLS");
            printer("start");
        }
        else if (command == "tips" || command == "t" || command == "1"){
            printer("helptips");
            string ans = choice();
            if (ans == "y"){
                if (view == "normal"){
                    helptips = 1;
                }
                else{
                    print("\nplease change console view to normal first\n");
                }
            }
            else{
                helptips = 0;
            }
        }
        else if (command == "help" || command == "h"){
           help("settings");
           _settings("");
        }
        else if (command == "q" || command == "0" || command == "0"){
            //pass to end help
        }
        else{
            printer("invh");
            _settings("");
        }
    }
    else if (command == "start"){
        printer("welcome");
        printer("enty");
        year = inputInt("");
        while (year < 2018){
            printer("inventy");
            year = inputInt("");
        }
        printer("ents");
        semester = inputInt("");
        while (semester != 1 && semester != 2){
            printer("ents");
            semester = inputInt("");
        }
        string ans;
        help("view");printer("view");
        ans = lower(inputStr(""));
        while (ans != "l" && ans != "n" && ans != "list" && ans != "normal"){
            printer("view");
            ans = lower(inputStr(""));
        }
        if (ans == "n" || ans == "normal"){
            printer("helptips");
            ans = choice();
            if (ans == "y"){
                helptips = 1;
            }
        }
        else{
            helptips = 0;
            view = "list";
        }
    }
}


void _readme(){
    system("notepad readme.txt");
}

void _start(){
    if (semester == 0){
        printer("start");
        _settings("start");
        system("CLS");//system("clear");
    }
    printer("start");
    engine();
}


string engine(){
    cin.clear();
    if (helptips == 1){
        printer("commands");
    }
    if (view == "list"){
        lister("commands");
    }
    print("\n\nEnter A Command >>>> ");
    string command = lower(inputStr(""));
    if (command == "commands"){
        if (view == "normal"){
            printer("commands");
        }
        else{
            lister("commands");
        }
        engine();
    }
    else if (command == "help" || command == "h" || command == "9"){
        printer("help");
        help("");
        engine();
    }
    else if (command == "add" || command == "1"){
        if (helptips == 1){
            help("add");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _add();
        engine();
    }
    else if (command == "edit" || command == "3"){
        if (helptips == 1){
            help("edit");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _edit();
        engine();
    }
    else if (command == "remove" || command == "5"){
        if (helptips == 1){
            help("remove");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _remove();
        engine();
    }
    else if (command == "list" || command == "2"){
        if (helptips == 1){
            help("list");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _list();
        engine();
    }
    else if (command == "records" || command == "7"){
        if (helptips == 1){
            help("find");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _records();
        engine();
    }
    else if (command == "settings" || command == "10"){
        if (helptips == 1){
            help("settings");
        }
        if (view == "list"){
            lister("settings");
        }
        _settings("");
        engine();
    }
    else if (command == "search" || command == "4"){
        if (helptips == 1){
            help("search");
        }
        if (view == "list"){
            lister("choose_user");
        }
        _search();
        engine();
    }
    else if (command == "clear" || command == "99"){
        system("CLS");//system("clear");
        _start();
    }
    else if (command == "readme" || command == "8"){
        _readme();
        engine();
    }
    else if (command == "exit" || command == "00"){
        exit();
    }
    else{
        printer("invh");
        engine();
    }
    return "done";
}
