#include <iostream>
using namespace std;



int hcf(int n1, int n2){
    if (n2 != 0)
        return hcf(n2, n1 % n2);
    else
        return n1;
}

int findLCM(int a, int b){
    int lcm;
    if (a%b == 0 or b%a == 0){
        if (a==b or a>b){
            lcm=a;
        }
        else{
            lcm=b;
        }
    }
    else{
        lcm = a*b/hcf(a,b);
    }
    return lcm;
}



int main(){
    int arr[20]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    int length =sizeof(arr)/sizeof(arr[0]);
    int y;
    int x = findLCM(arr[0],arr[1]);

    for (int i=2;i<length;i++){
        y=arr[i];
        int lcm = findLCM(x,y);
        x=lcm;
        cout << "\nlcm"<< i+1 << " is " << x;
    }

    cout << "\n\n\nFinal LCM" << x;
};
