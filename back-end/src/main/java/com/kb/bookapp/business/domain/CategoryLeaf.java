package com.kb.bookapp.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("LEAF")
public class CategoryLeaf extends Category {
}
