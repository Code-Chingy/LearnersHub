#include <iostream>
#include<string>


using namespace std;


string _reverse(string line){
    int len = line.length();
    string reversed = "";
    for (int i=0;i=len;len--){
        reversed += line[len-1];
    }
    return reversed;
}


int main(){

if (_reverse("madam") == "madam"){
    cout<<"parlindom";
}

return 0;
}
