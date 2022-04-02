package com.youctagh.purchasemanager.frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseEntity {

    private String name;

    private Category category;


    public String getCategoryName() {
        if (category == null) {
            return "";
        }
        return category.getName();
    }

    @Override
    public String toString() {
        return name;
    }
}
