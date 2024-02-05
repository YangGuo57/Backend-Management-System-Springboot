package com.yguo57.pojo;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;//Operation Status Code 0-Success 1-Failure
    private String message;
    private T data;// response data

    // return results of successful operation response (with response data)
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Successful", data);
    }

    // return operation success response
    public static Result success() {
        return new Result(0, "Successful", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
