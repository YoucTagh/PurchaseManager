package com.youctagh.purchasemanager.frontend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Ticket extends BaseEntity {
    private Store store;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Berlin")
    private Date date;
    private Set<Item> items;

    public String getStoreName() {
        if (store != null) {
            return store.getName();
        }
        return "";
    }

    public Integer getItemNumber() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }
}
