#ifndef PERSON_H
#define PERSON_H
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <string>
using namespace std;

class person
{
    public:
        person();
        //setter functions for student
        void setName(string val);
        void setStatus(string val);
        void setAge(int val);
        void setID(int val);
        void setIndex(int val);
        void setResidence(string val);
        void setDepartment(string val);
        void setCourse(string val);
        void setLevel(int val);
        void listItems(vector <string> arrayVar);

        //getter functions for student
        string getName();
        string getStatus();
        int getAge();
        string getResidence();
        string getDepartment();
        string getType();
        int getID();
        int getIndex();
        void getInfo();
        vector<string> getSubjects();
        void listSubjects();
        vector<string> getRegCourses();
        string upper(string line);
        void listRegCourses();
        void setRegCourse();
        void setSubject(string val);
        void removeSubject(string val);

    protected:
        string name;
        int age;
        int index;
        int object_id;
        string status;
        string residence;
        string department;
        vector <string> subjects;
        vector <string> registrable_courses;
};

#endif // PERSON_H
