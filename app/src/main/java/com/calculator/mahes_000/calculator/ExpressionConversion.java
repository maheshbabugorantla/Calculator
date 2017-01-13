package com.calculator.mahes_000.calculator;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Mahesh Babu Gorantla on 1/11/2017.
 *
 * This Class contains the helper methods to process the Expression from the Calculator
 */

class ExpressionConversion {

    private static final String operators = "-+/*";

    public static String infix_to_Postfix(String infix_Str) {

        infix_Str = infix_Str.split(";")[0];
        infix_Str = infix_Str.replaceAll("[\\s]", ""); // Removing all the spaces in the expression
        String[] elements = infix_Str.split("(?<=[-+*/()])|(?=[-+*/()])"); // Splitting the Infix String into individual Tokens

        StringBuilder output_Str = new StringBuilder(); // This Stores the PostFix Conversion String

        Stack<String> opStack = new Stack<>(); // This Stack is used to store the Operators

        HashMap<String, Integer> opPrecedence = new HashMap<>(); // This HashMap Stores the Operator Precedence

        opPrecedence.put("+", 1);
        opPrecedence.put("-", 1);
        opPrecedence.put("*", 2);
        opPrecedence.put("/", 2);

        // Traversing through all the symbols
        for (String element: elements) {

            //    System.out.println(element);
            try {
                if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/") || element.equals("(") || element.equals(")")) {

                    if (element.equals(")")) {

                        while (!opStack.peek().equals("(")) {
                            String popped_op = opStack.pop();
                            output_Str.append(popped_op + " ");
                        }

                        opStack.pop();
                    } else if (element.equals("(")) {
                        opStack.push("(");
                    } else {

                        int currentPrecedence = 3;

                        if (opPrecedence.containsKey(element)) {
                            currentPrecedence = opPrecedence.get(element);
                        }

                        while (!opStack.empty() && opPrecedence.get(opStack.peek()) != null && currentPrecedence <= opPrecedence.get(opStack.peek())) {
                            String popped_op = opStack.pop();
                            output_Str.append(popped_op + " ");
                        }

                        opStack.push(element);
                    }
                } else {
                    output_Str.append(element + " ");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                //System.out.println("Parse Error 1");
                return null; // Which means a bad expression
            }
        }

        // Emptying the Operator Stack when we run out of Operands
        // At this stage of the program the Stack should contain only operators
        while (!opStack.empty()) {
            if (opStack.peek().equals("+") || opStack.peek().equals("-") || opStack.peek().equals("*") || opStack.peek().equals("/")) {
                output_Str.append(opStack.pop() + " ");
            }

            else { // This step is used to check for the presence of any unbalanced parantheses
                //System.out.println("Parse Error2");
                return null;
            }
        }

        return output_Str.toString();
    }

    public String evaluatePostfix(String postfixExpr) {

        String[] chars = postfixExpr.trim().split(" ");
        Stack<String> stack = new Stack<>();
        for (String c : chars) {

            try {
                if (isOperand(c)) {
                    //System.out.println("Pushing into Stack");
                    stack.push(c);
                } else if (isOperator(c)) {
                    String op2 = stack.pop();
                    String op1 = stack.pop();
                    float result;
                    switch (c) {
                        case "*":
                            result = Float.parseFloat(op1) * Float.parseFloat(op2);
                            //System.out.println("Mul: " + result);
                            stack.push(Float.toString(result));
                            break;

                        case "/":
                            result = Float.parseFloat(op1) / Float.parseFloat(op2);
                            //System.out.println("Div: " + result);
                            stack.push(Float.toString(result));
                            break;

                        case "+":
                            result = Float.parseFloat(op1) + Float.parseFloat(op2);
                            //System.out.println("Add: " + result);
                            stack.push(Float.toString(result));
                            break;

                        case "-":
                            result = Float.parseFloat(op1) - Float.parseFloat(op2);
                            //System.out.println("Sub: " + result);
                            stack.push(Float.toString(result));
                            break;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                //System.out.println("Eval Error");
                return null;
            }
        }

        String result = stack.pop();

        // This is to make sure that there is only one item in the Stack after the evaluation the
        // whole expression
        if(!stack.isEmpty()) {
            return null;
        }

        return result;
    }

    private boolean isOperator(String val) {
        return operators.indexOf(val) >= 0;
    }

    private boolean isOperand(String val) {
        try {
            Float.parseFloat(val);
            System.out.println("Parsed Integer");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}