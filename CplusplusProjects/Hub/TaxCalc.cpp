#include <iostream>

using namespace std;

/**
    takes in the current_tax_value and returns the tax rate
**/
double getTaxRate(double amount);

int main(){
    //variables to hold salary,tax,taxValues
    double current_salary_value = 0.0;
    double tax = 0.0;
    double given_salary = 0.0 ;
    int taxValues[] = {261, 70, 100, 2810, 3241};

    //get salary
    cout << "Enter salary: ";
    cin >> given_salary;
    
    //duplicate salary value
    current_salary_value = given_salary;


    //calculate tax
    int i = 0;
    //check if there nothing to deduct as salary
    while(current_salary_value != 0){
        
        //check if the current_salary_value is less 
        //than the current_taxValue {70}  
        if(current_salary_value < taxValues[i] ){
            //IF TRUE 
            //THEN tax = current_salary_value * rate {0.05}
            //AND there is nothing to tax again 
            tax += current_salary_value * getTaxRate(taxValues[i]);
            current_salary_value = 0;
        } else if((current_salary_value-taxValues[i]) >= 0) {
            //ELSE IF current_salary_value > current_taxValue
            //THEN tax = current_taxValue * rate {0.05}
            //AND current_salary_value is less current_taxValue
            tax += taxValues[i] * getTaxRate(taxValues[i]);
            current_salary_value -= taxValues[i];
        }
        i++;
    }

    //display tax results
    cout << "The tax on a GHC " << given_salary << " is GHC " << tax << "\n";

    return 0;
}

double getTaxRate(double amount){
    double taxRate = 0.0;

    if(amount == 261){
        taxRate = 0.0;
    } else if(amount == 70 ){
        taxRate = 0.05;
    } else if(amount == 100){
        taxRate = 0.1;
    } else if(amount == 2810){
        taxRate = 0.175;
    } else if(amount == 3241 ){
        taxRate = 0.25;
    }

    return taxRate;
}

/* 
DEBUGGING 
IF: 
    cout << "the amount is "<<salary<<" tax is "<<tax <<
         " and i = "<<i<<" tax amount is "<<taxValues[i]<<"\n"; 
ELSE : 
    cout << "the amount is "<<taxValues[i]<<" tax is "<<tax <<
     " and i = "<<i<<" tax amount is "<<taxValues[i]<<" rem salary is "<< salary <<"\n"; 
*/