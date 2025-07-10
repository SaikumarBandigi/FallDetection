package com.sb.FallDetection.config;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Worker {
    int id;
    String name;

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Worker worker)
            return id == worker.id && name.equals(worker.name);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}


public class Test {
    public static void main(String[] args) {





    }
}