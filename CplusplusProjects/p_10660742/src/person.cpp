#include "person.h"
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <string>

using namespace std;

template <class var>

void print(var textline){
    cout << textline;
}

string person::upper(string line){
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



person::person()
{
    //set empty student name = ""
    status = "null";
}
//setter functions for student
void person::setName(string val){
    name = val;
}

void person::setStatus(string val){
    status = val;
}

void person::setAge(int val){
    age = val;
}

void person::setID(int val){
    object_id = val;
}

void person::setIndex(int val){
    index = val;
}

void person::setResidence(string val){
    residence = val;
}

void person::listItems(vector <string> arrayVar){
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
string person::getName(){
    return name;
}

string person::getStatus(){
    return status;
}

int person::getAge(){
    return age;
}

string person::getResidence(){
    return residence;
}

string person::getType(){
    return "student";
}

int person::getID(){
    return object_id;
}

int person::getIndex(){
    return index;
}

void person::getInfo(){
    cout << "\nname       : " << getName() << "\nage        : " << getAge() << "\nperson ID    : " << getID() <<endl;
}

vector<string> person::getSubjects(){
    return subjects;
}

void person::listSubjects(){
    listItems(subjects);
}

vector<string> person::getRegCourses(){
    return registrable_courses;
}

void person::listRegCourses(){
    listItems(registrable_courses);
}

void person::setSubject(string val){
    //check if subject is legit in registrable then
    subjects.push_back(val);
}

void person::removeSubject(string val){
    int len = subjects.size();
    for (int i=0;i<len;i++){
        if (upper(subjects[i]) == upper(val)){
            subjects.erase(subjects.begin()+i);
            print("\nSubject succesfully removed...");
            break;
        }
        if (i == len-1){
            print("\ncant find subject index...\n");
        }
    }
}
