package com.user.micro.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME" , length = 20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name="ABOUT")
    private String about;

    //other properties u can add that

    //@Transient  means this entity never saved in database
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
