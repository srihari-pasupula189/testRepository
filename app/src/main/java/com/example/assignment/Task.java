package com.example.assignment;

public interface Task<T> {
    T onExecute();
    void onComplete(T t);
}
