#include<iostream>
using namespace std;

int eventotal = 0;
int oddtotal = 0;


int fib(int a ,int b){
	a = a + b;
	if (a%2==0){
		eventotal += a;
	}
	else{
		oddtotal += a;
	}

	if (a > 200000){
		return eventotal;
	}
	else{
		return fib(b,a);
	}

}


int main(){

    //cout << "The even total is " << fib(1,2);
	return 0;
}
