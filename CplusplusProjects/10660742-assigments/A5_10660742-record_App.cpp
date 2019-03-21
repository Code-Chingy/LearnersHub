#include <iostream>
#include <fstream>
using namespace std;


template <class var>

void print(var textline){
    cout << textline;
}

int inputInt(string show);
string inputStr(string show);
string calcGrade(int score);
string spacer(string line);

struct student{
    string student_id;
    string name;
    int age;
    string gender;
    int score;
    string grade;
};

int main(){
cout
 << "#####################################################################################" << endl
 << "##################################### Welcome To ####################################" << endl
 << "################################ THE RECORD COLLECTOR.. #############################" << endl
 << "#####################################################################################" << endl
 << "#####################################################################################" << endl;

student details[5];
int mcount=0, fcount=0, totalage=0;
float totalscore=0;

for (int i=0;i<5;i++){
    cout << "\n\n\nEnter The Details For Student "<< i+1 <<endl<<endl;

    details[i].student_id = inputStr("Students ID : ");
    while ((details[i].student_id).length() != 8){
        details[i].student_id = inputStr("Please Enter a Valid Students ID..( Should be 8 numbers long ) : ");
    };
    details[i].name = inputStr("Students Name : ");
    details[i].age = inputInt("Students Age : ");
    details[i].gender = inputStr("Students Gender(m / f) : ");
    while(details[i].gender != "m" && details[i].gender != "f"){
        details[i].gender = inputStr("Invalid Gender Value..( Please enter 'm' or 'f' ) : ");
    };
    details[i].score = inputInt("Students Score(100%) : ");
    while(-1 > details[i].score || details[i].score > 100){
        details[i].score = inputInt("Please Enter a Valid Students Score(100%) : ");
    };
    details[i].grade = calcGrade(details[i].score);

    //######  Counters  ############
     if (details[i].gender == "m"){
        mcount++;
     }
     else{
        fcount++;
     }

    totalage += details[i].age;
    totalscore += details[i].score;
}


//########  writing records to Document   ###########
ofstream writer;
writer.open("record_sheet.txt");
writer << "##############################################################################\n";
writer << "############################  Records For Students  ##########################\n";
writer << "##############################################################################\n\n\n";
writer << "Student_ID       Student_Name                    Age     Gender   Score   Grade \n";
writer << "*******************************************************************************\n";

for (int i=0;i<5;i++){
     writer << details[i].student_id<< "\t" << details[i].name << "\t\t\t" << details[i].age << "\t" << details[i].gender << "\t" << details[i].score<< "\t" << details[i].grade <<endl;
};

writer << "\n\n\n*******************************************************************************\n\n";
writer << "Average Age is    : " << "\t\t" << totalage/5 <<endl;
writer << "Average Score is  : " << "\t\t" << totalscore/5 <<endl;
writer << "Number of Males   : " << "\t\t" << mcount <<endl;
writer << "Number of Females : " << "\t\t" << fcount <<endl;
writer.close();

print("\n\nPlease go to the Directory of the Program for the Record Sheet\n\n");

return 0;

};



//###  functions  ##########
int inputInt(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value");
    }
    else{
        print(show);
    }
    int value;
    cin >> value;
    while (cin.fail()){
        cin.clear();
        cin.ignore();
        cin >> value;
    };
    return value;
}

string inputStr(string show){
    cin.clear();
    if (show == ""){
       //
    }
    else if (show == "default"){
        print("Enter a value");
    }
    else{
        print(show);
    }
    string value;
    cin >> value;
    return value;
}

string calcGrade(int score){
    string ret;
    if (79 < score && score <= 100){
        ret = "A";
    }
    else if(74 < score && score <= 79){
        ret = "B+";
    }
    else if(69 < score && score <= 74){
        ret = "B";
    }
    else if(64 < score && score <= 69){
        ret = "C+";
    }
    else if(59 < score && score <= 64){
        ret = "C";
    }
    else if(54 < score && score <= 59){
        ret = "D+";
    }
    else if(49 < score && score <= 54){
        ret = "D";
    }
    else if(44 < score && score <= 49){
        ret = "E";
    }
    else{
        ret = "F";
    }
    return ret;
}
