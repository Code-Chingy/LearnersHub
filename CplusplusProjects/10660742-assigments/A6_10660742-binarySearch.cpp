#include <iostream>
using namespace std;

int binarySearch(int arr[], int start_index, int arr_len, int _search){
	if (arr_len >= start_index){
		int mid = start_index + (arr_len - start_index)/2;
		if (arr[mid] == _search)
			return mid;
		if (arr[mid] > _search)
			return binarySearch(arr, start_index, mid-1, _search);
		return binarySearch(arr, mid+1, arr_len, _search);
	}
	return -1;
}

int main(){
    cout
     << "#####################################################################################" << endl
     << "###################################### 10660742 #####################################" << endl
     << "################################ THE BINARY SEARCHER... #############################" << endl
     << "#####################################################################################" << endl
     << "#####################################################################################" << endl;

   //array must be sorted in ascending order
	int Testarr[] = {8, 7, 10, 15, 20, 35, 40};
	int len = sizeof(Testarr)/ sizeof(Testarr[0]);
	int _search;
	while (true){
        cout << "\n\nInput a number to search in the array : ";
        cin >> _search;
        if (cin.fail()){
            break;
        }
        int result = binarySearch(Testarr, 0, len-1, _search);
        (result == -1)? cout << "\nElement is not present in array" : cout << "\nElement is present at index "<< result;
	}

	return 0;
}
