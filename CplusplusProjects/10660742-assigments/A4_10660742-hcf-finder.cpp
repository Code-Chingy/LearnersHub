
#include <iostream>
using namespace std;


int process(int n1, int n2){
    if (n2 != 0)
        return process(n2, n1 % n2);
    else
        return n1;
}

int main(){
    cout
     << "################################################################" << endl
     << "######################## Welcome To ############################" << endl
     << "####################### H.C.F FINDER.. #########################" << endl
     << "################################################################" << endl
     << "================================================================" << endl
     << "######## to QUIT the program enter any non_numeric key #########" << endl
     << "################################################################\n\n" << endl;

    while (true){
        int num1, num2;
        cout << "Enter first number: ";
        cin >> num1;
        cout << "Enter second number: ";
        cin >> num2;
        if (!cin.fail()){
            cout << "The H.C.F of " << num1 << " and " <<  num2 << " is: " << process(num1, num2) << "\n\n";
            continue;
        }
        else{
            cout << "\n\nProgram Exited, GOODBYE." << endl;
            break;
        }

        return 0;
    }


}
