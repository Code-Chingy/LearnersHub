package com.techupstudio.Application;

import java.util.ArrayList;

import static com.techupstudio.Utils.MyFuncs.toDouble;

public class Calculator {

    public Double calculate(String operation){

        ArrayList<String> splitOperation = new ArrayList<>();
        String number = "";

        for (int i=0;i<operation.length();i++){
            Character c = operation.charAt(i);
            if (c.toString().equals("=")){
                calculate(splitOperation);
            }
            else if (isOperator(c.toString())){
                splitOperation.add(number.trim());
                splitOperation.add(c.toString());
                number = "";
            }
            else{
                number += c;
            }
        }

        return calculate(splitOperation);
    }

    private Double calculate(ArrayList<String> operation) {

        Double temp = 0.0;
        String operator = null;

        for (String val : operation){
            if (isOperator(val)){ operator = val; }
            else{
                if (operator == null){
                    temp = toDouble(val);
                }
                else{
                    switch (operator){
                        case "+":
                            temp = temp + toDouble(val);
                        case "-":
                            temp = temp - toDouble(val);
                        case "/":
                            temp = temp / toDouble(val);
                        case "*":
                            temp = temp * toDouble(val);
                    }
                }
            }
        }

        return temp;
    }

    private boolean isOperator(String c){
        String[] operators = {"+", "-", "/", "*"};

        for (String operator : operators){
            if (operator.equals(c)){
                return true;
            }
        }

        return false;

    }

}
