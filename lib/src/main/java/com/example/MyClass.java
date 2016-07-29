package com.example;


public class MyClass {
    public static void main(String[] args) {
        System.out.print("11");
        set();

    }


    static class Point<T> {

        private T x;

        public void setX(T x) {
            this.x = x;
        }


        public T getX() {
            return x;
        }
    }


    public static void set() {
        Point<String> p = new Point<>();
        p.setX("123");
        System.out.print(p.getX());
    }


}
