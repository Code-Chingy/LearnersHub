#include <iostream>
using namespace std;


int main(){
    cout
     << "################################################################" << endl
     << "######################## Welcome To ############################" << endl
     << "####################### Grade Checker ##########################" << endl
     << "################################################################" << endl
     << "================================================================" << endl
     << "######## to QUIT the program enter any non_numeric key #########" << endl
     << "################################################################\n" << endl;

    while(true){
        int score;
        cout << "\nPlease enter your score"<< endl;
        cin >> score;
        if (!cin.fail()){
            if (79 < score && score <= 100){
                cout << "Your Grade is : A" << endl;
                cout << "Excellent." << endl;
            }
            else if(74 < score && score <= 79){
                cout << "Your Grade is : B+" << endl;
                cout << "Very Good." << endl;
            }
            else if(69 < score && score <= 74){
                cout << "Your Grade is : B" << endl;
                cout << "Good." << endl;
            }
            else if(64 < score && score <= 69){
                cout << "Your Grade is : C+" << endl;
                cout << "Credit." << endl;
            }
            else if(59 < score && score <= 64){
                cout << "Your Grade is : C" << endl;
                cout << "Credit." << endl;
            }
            else if(54 < score && score <= 59){
                cout << "Your Grade is : D+" << endl;
                cout << "Pass." << endl;
            }
            else if(49 < score && score <= 54){
                cout << "Your Grade is : D" << endl;
                cout << "Pass." << endl;
            }
            else if(44 < score && score <= 49){
                cout << "Your Grade is : E" << endl;
                cout << "Fail." << endl;
            }
            else if(-1 < score && score <= 44){
                cout << "Your Grade is : F" << endl;
                cout << "Fail." << endl;
            }
            else{
                cout << "please enter valid numbers only!!!" << endl;
            }
        }
        else{
            cout << "Program Exited, GoodBye.." << endl;
            break;
        }
    }

return 0;
};
