package com.gitlab.pedrioko.ats.vaadin.demo;

public class Test {
    private String test;
    private String test2;


    public Test() {

    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test='" + test + '\'' +
                ", test2='" + test2 + '\'' +
                '}';
    }
}
