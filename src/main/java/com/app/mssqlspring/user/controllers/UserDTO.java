package com.app.mssqlspring.user.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String name;
    private int age;
}
