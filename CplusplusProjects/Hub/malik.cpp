#include <iostream>
#include <vector>
using namespace std;

template<class var>
void print(var line){
    cout << line;
}


//add ascending or descending property
void bubbleSort(int list[], int length){
    int temp, iteration, index;
    for (iteration = 1; iteration < length; iteration++){
        for (index = 0; index < length - iteration; index++){
            if (list[index] > list[index + 1]){
                //swapping
                temp = list[index];
                list[index] = list[index + 1];
                list[index + 1] = temp;
            }
        }
    }

    //printing output
    for (int x=0;x<length;x++){
        print(list[x]);
    }
}


void selectionSort(int list[], int length){
    int index, smallestIndex, location, temp;
    for (index = 0; index < length - 1; index++){
        //Step a
        smallestIndex = index;
        for (location = index + 1; location < length; location++)
            if (list[location] < list[smallestIndex])
            smallestIndex = location;
            //Swapping
            temp = list[smallestIndex];
            list[smallestIndex] = list[index];
            list[index] = temp;
    }

    //printing output
    for (int x=0;x<length;x++){
        print(list[x]);
    }
}


void insertionSort(int list[], int length){
    for (int i=0;i<length;i++){
        int temp = list[i];
        int j=i;

        while (j > 0 && list[j-1] > temp){
            list[j] = list[j-1];
            j--;
        }

        list[j] = temp;
    }

    //printing output
    for (int i=0;i<length;i++){
        print(list[i]);
    }
}



int sequencialSearch(int list[], int listLength, int _search){
    int index;
    int temp;
    for (index = 1; index < listLength;index++){
        if (list[index] == _search){
            return index;
        }
    }
    return -1;
} //end insertionSort


int binarySearch(const int list[], int listLength, int searchItem){
    int first = 0;
    int last = listLength - 1;
    int mid;
    bool found = false;
    while (first <= last && !found){
        mid = (first + last) / 2;
        if (list[mid] == searchItem)
            found = true;
        else if (list[mid] > searchItem)
            last = mid - 1;
        else
            first = mid + 1;
    }
    if (found)
        return mid;
    else
        return -1;
}//end binarySearch

template <class var>
void emptyList(vector<var> *list){
    int len = list->size();
    for (int i=0;i<len;i++){
        *list->erase(list->begin()+0);
    }
}


bool isSpace(string line){
    int len = line.length();
    for (int i=0;i<len;i++){
        if (line[i] != ' '){
            return false;
        }
    }
    return true;
}

int main(){

    int list[10] = {3,4,8,3,7,6,6,0,1,2};

    insertionSort(list, 10);

	return 0;
}
