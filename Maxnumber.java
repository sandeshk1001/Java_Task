package com.company;

import java.util.*;

public class day4 {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(40);
        integerList.add(20);
        integerList.add(30);
        integerList.add(60);
        System.out.println("First Max"+findMax(integerList));
        System.out.println("Second max:"+findsecondMax(integerList));
        System.out.println("Third K max"+findkMax(integerList,3));
    }
    public static int findMax(List<Integer> l){
        Collections.sort(l);
        System.out.println(l);
        return l.get(l.size()-1);
    }
    public static int findsecondMax(List<Integer> l){
        Collections.sort(l);
        //int a=l.size()-2;
        return l.get(l.size()-2);
    }
    public static List<Integer> findkMax(List<Integer> l,int num){
        Collections.sort(l);
        List<Integer> k =new ArrayList<>();
        for (int i=l.size()-1;i>=(l.size()-num);i--){
            k.add(l.get(i));
        }
        return k;
    }
}
