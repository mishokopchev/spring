package com.example.service;

/**
 * Created by mihailkopchev on 3/7/18.
 */
public interface BackendService {

    public default <T> T required(T value, String name) {
        if (value == null) {
            throw new IllegalArgumentException("required [" + name + "]");
        }
        return value;
    }
}
