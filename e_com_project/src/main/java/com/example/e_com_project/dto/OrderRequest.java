
package com.example.e_com_project.dto;

import java.util.Map;
import java.util.stream.Collectors;

public class OrderRequest {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Map<Long, Integer> getItemsAsLongMap() {
        return items.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Long.parseLong(e.getKey()),
                        Map.Entry::getValue
                ));
    }
}
