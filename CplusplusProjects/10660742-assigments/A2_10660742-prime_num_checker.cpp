#include <iostream>
using namespace std;


int prime_numberChecker(int n);


int main(){
int number;
cout
 << "################################################################" << endl
 << "######################## Welcome To ############################" << endl
 << "################## Is it a PRIME NUMBER?.. #####################" << endl
 << "################################################################" << endl
 << "================================================================" << endl
 << "######## to QUIT the program enter any non_numeric key #########" << endl
 << "################################################################\n" << endl;

while(true){
    cout << "Enter any number"<< endl;
    cin >> number;
    if (cin){
        prime_numberChecker(number);
    }
    else{
        cout << "Program Exited, GOODBYE." << endl;
        break;
    }
}

return 0;

};


//function to check prime number
int prime_numberChecker(int n){
    int x = 2;
    bool prime = true;
    for (x; x<=(n/2); x++){
        if ((n % x) == 0){
            prime = false;
            break;
        }
    };
    if (prime == true){
        cout << "status = True \nResult: That's a prime number\n" << endl;
    }
    else{
        cout << "status = False \nnumber is divisible by "<< x <<"\nResult: Not a prime number\n" << endl;
    }
};


