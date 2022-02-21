package com.company.Taskwork.javaassignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Q5_TopElement {
    public static void main(String[] args) {
        System.out.println(topelement(Arrays.asList(10,30,20,40)));

    }
    static int topelement(List<Integer> list){
        int max=list.get(0);
        for(int i=1;i<list.size();i++){
            if(max < list.get(i)){
                max= list.get(i);
            }
        }
       return max;
    }
}
