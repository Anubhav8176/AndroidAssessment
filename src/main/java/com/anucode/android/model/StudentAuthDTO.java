package com.anucode.android.model;

import com.anucode.android.entities.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class StudentAuthDTO {
    private String name;
    private String password;
    private Set<Roles> roles;
}
