package com.kb.bookapp.business.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class SearchApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long searchApiIdx;
    private int orders;
    private String fullClassName;
    private String description;
}
