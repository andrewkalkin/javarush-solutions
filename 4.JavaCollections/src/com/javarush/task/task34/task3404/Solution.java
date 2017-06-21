package com.javarush.task.task34.task3404;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6

    }

    public void recursion(final String expression, int countOperation) {
        //implement

        Pattern[] levels = new Pattern[9];
        levels[0] = Pattern.compile("(\\(-|--|\\+-|\\*-|\\/-|\\^-)[0-9]+\\.?[0-9]*[^0-9\\.\\^]");
        levels[1] = Pattern.compile("(\\(|^)\\-\\(");
        levels[2] = Pattern.compile("^-[0-9]+\\.?[0-9]*[^0-9\\.\\^]");
        levels[3] = Pattern.compile("^-[0-9]+\\.?[0-9]*$");
        levels[4] = Pattern.compile("(sin|cos|tan)(\\(s?[0-9]+\\.?[0-9]*\\))");
        levels[5] = Pattern.compile("\\([^)(]*(s?[0-9]+\\.?[0-9]*)(\\^|\\*|\\/|\\+|\\-)(s?[0-9]+\\.?[0-9]*)[^)(]*\\)");
        levels[6] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)\\^(s?[0-9]+\\.?[0-9]*)");
        levels[7] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)(\\*|\\/)(s?[0-9]+\\.?[0-9]*)");
        levels[8] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)(\\+|\\-)(s?[0-9]+\\.?[0-9]*)");



        Pattern[] operations = new Pattern[7];
        operations[0] = Pattern.compile("sin(\\(s?[0-9]+\\.?[0-9]*\\))");
        operations[1] = Pattern.compile("cos(\\(s?[0-9]+\\.?[0-9]*\\))");
        operations[2] = Pattern.compile("tan(\\(s?[0-9]+\\.?[0-9]*\\))");

        operations[3] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)\\*(s?[0-9]+\\.?[0-9]*)");
        operations[4] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)\\/(s?[0-9]+\\.?[0-9]*)");

        operations[5] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)\\+(s?[0-9]+\\.?[0-9]*)");
        operations[6] = Pattern.compile("(s?[0-9]+\\.?[0-9]*)\\-(s?[0-9]+\\.?[0-9]*)");


        String normalExpression = expression.replaceAll("\\s+", "");



        Pattern brNumPattern = Pattern.compile("\\(s?[0-9]+\\.?[0-9]*\\)");
        Pattern numberPattern = Pattern.compile("s?[0-9]+\\.?[0-9]*");
        Matcher matcher, numberMatcher, brNumberMatcher, opMatcher;
        String opExpression, numStr="";
        double a = 0, b = 0;
        int beginSearch = 0;


        brNumberMatcher = brNumPattern.matcher(normalExpression);
        boolean flag = false;

        while (brNumberMatcher.find() && ((brNumberMatcher.start() < 3)|(brNumberMatcher.start() - 3 >= 0 && !(flag = (opMatcher = levels[4].matcher(normalExpression.substring(brNumberMatcher.start()-3, brNumberMatcher.end()))).find())))) {
            normalExpression = normalExpression.replaceFirst(normalExpression.substring(brNumberMatcher.start(), brNumberMatcher.end()).replace("(","\\(").replace(")","\\)"),normalExpression.substring(brNumberMatcher.start()+1,brNumberMatcher.end()-1));
            brNumberMatcher = brNumPattern.matcher(normalExpression);
        }

        normalExpression = normalExpression.replace("ss","");




        loop:for (int i = 0; i < levels.length; i++) {
            matcher = levels[i].matcher(normalExpression.substring(beginSearch));


            if (matcher.find()) {
                opExpression = normalExpression.substring(beginSearch + matcher.start(), beginSearch + matcher.end());


                switch (i) {
                    case 0:
                        numStr = normalExpression.substring(beginSearch + matcher.start()+1, beginSearch + matcher.end() - 1);
                        normalExpression = normalExpression.substring(0, beginSearch + matcher.start()+1) + normalExpression.substring(beginSearch + matcher.start()+1).replaceFirst(numStr, numStr.replace("-","s"));
                        recursion(normalExpression, countOperation + 1);
                        return;
                    case 1:
                        if (Pattern.compile("^\\-\\(").matcher(opExpression).matches()) normalExpression = normalExpression.replaceFirst("^\\-\\(", "s\\("); else
                        normalExpression = normalExpression.replaceFirst("\\(\\-\\(", "\\(s\\(");
                        recursion(normalExpression, countOperation + 1);
                        return;
                    case 2:
                        numStr = normalExpression.substring(beginSearch + matcher.start(), beginSearch + matcher.end() - 1);
                        normalExpression = normalExpression.substring(0, beginSearch + matcher.start()) + normalExpression.substring(beginSearch + matcher.start()).replaceFirst(numStr, numStr.replace("-","s"));
                        recursion(normalExpression, countOperation + 1);
                        return;
                    case 3:
                        recursion(normalExpression.replace("-","s"), countOperation + 1);
                        return;
                    case 4:
                        numberMatcher = numberPattern.matcher(opExpression);
                        numberMatcher.find();
                        a = Double.parseDouble(opExpression.substring(numberMatcher.start(), numberMatcher.end()).replace("s","-"));
                        break;
                    case 5:
                        beginSearch = matcher.start() + beginSearch;
                        continue loop;
                    case 6:
                    case 7:
                    case 8:
                        numberMatcher = numberPattern.matcher(opExpression);
                        numberMatcher.find();
                        a = Double.parseDouble(opExpression.substring(numberMatcher.start(), numberMatcher.end()).replace("s","-"));

                        numberMatcher.find();
                        b = Double.parseDouble(opExpression.substring(numberMatcher.start(), numberMatcher.end()).replace("s","-"));
                        break;
                }

                switch (i) {
                    case 4:
                        for (int j = 0; j < 3; j++) {
                            opMatcher = operations[j].matcher(opExpression);
                            if (opMatcher.find()) {
                                switch (j) {
                                    case 0:
                                        numStr = String.valueOf(Math.round(Math.sin(Math.toRadians(a))*100d)/100d);
                                        break;
                                    case 1:
                                        numStr = String.valueOf(Math.round(Math.cos(Math.toRadians(a))*100d)/100d);
                                        break;
                                    case 2:
                                        numStr = String.valueOf(Math.round(Math.tan(Math.toRadians(a))*100d)/100d);
                                        break;
                                }
                                opExpression = opExpression.replace("(","\\(").replace(")","\\)");

                            }
                        }
                        break;
                    case 6:
                        numStr = String.valueOf(Math.pow(a,b));
                        opExpression = opExpression.replace("^","\\^");
                        break;
                    case 7:
                        for (int j = 3; j < 5; j++) {
                            opMatcher = operations[j].matcher(opExpression);
                            if (opMatcher.find()) {
                                switch (j) {
                                    case 3:
                                        numStr = String.valueOf(a*b);
                                        opExpression = opExpression.replace("*","\\*");
                                        break;
                                    case 4:
                                        numStr = String.valueOf(a/b);
                                        opExpression = opExpression.replace("/","\\/");
                                        break;
                                }

                            }
                        }
                        break;
                    case 8:
                        for (int j = 5; j < 7; j++) {
                            opMatcher = operations[j].matcher(opExpression);
                            if (opMatcher.find()) {
                                switch (j) {
                                    case 5:
                                        numStr = String.valueOf(a+b);
                                        opExpression = opExpression.replace("+","\\+");
                                        break;
                                    case 6:
                                        opExpression = opExpression.replace("-","\\-");
                                        numStr = String.valueOf(a-b);
                                        break;
                                }

                            }
                        }
                        break;

                }


                numStr = numStr.replace("-","s");
                normalExpression = normalExpression.substring(0, beginSearch) + normalExpression.substring(beginSearch).replaceFirst(opExpression,numStr);
                recursion(normalExpression, countOperation + 1);
                return;
            }
        }


        numStr = normalExpression.replace("s","-");
        numStr = String.valueOf(Math.round(Double.parseDouble(numStr) * 100d)/100d);
        numStr = numStr.replaceAll(".0$","");
        System.out.println(numStr + " " + countOperation);

    }

    public Solution() {
        //don't delete
    }
}
