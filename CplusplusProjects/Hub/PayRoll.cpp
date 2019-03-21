
#include <iostream>

using namespace std;


int main(){
    //declare variables
    string emp_name, emp_id;
    int hour_worked;
    double pay;

    //Accept inputs
    cout<<"Enter Employee Name: ";
    cin >> emp_name;

    cout<<"Enter Employee Id: ";
    cin >> emp_id;

    cout<<"Enter Employee work hours: ";
    cin >> hour_worked;

    //performing operation
    if( hour_worked <= 80){
        pay = hour_worked * 10.5;
    } else{
        pay = (hour_worked-80)*12;
        pay += 80 * 10.5;
    }

    //output
    cout << "Employee Name : "<< emp_name<<"\n"
         << "Employee Id: "<< emp_id << "\n"
         << "Employee hours: " << hour_worked<<"\n"
         << "Employee salary: "<< pay << "\n" ;
}