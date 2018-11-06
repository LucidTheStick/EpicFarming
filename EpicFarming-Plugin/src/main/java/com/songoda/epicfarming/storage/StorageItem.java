package com.songoda.epicfarming.storage;

import com.songoda.epicfarming.utils.Serializers;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class StorageItem {

    private String key = null;

    private Object object;

    public StorageItem(Object object) {
        this.object = object;
    }

    public StorageItem(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    public StorageItem(String key, List<ItemStack> material) {
        if (material == null || material.isEmpty()) return;
        StringBuilder object = new StringBuilder();
        for (ItemStack m : material) {
            if (m == null) continue;
            object.append(Serializers.serialize(m));
            object.append(";;");
        }
        this.key = key;
        this.object = object.toString();
    }

    public String getKey() {
        return key;
    }

    public String asString() {
        if (object == null) return null;
        return (String)object;
    }

    public boolean asBoolean() {
        if (object == null) return false;
        return (boolean)object;
    }

    public int asInt() {
        if (object == null) return 0;
        return (int)object;
    }

    public Object asObject() {
        return object;
    }

    public List<ItemStack> asItemStackList() {
        List<ItemStack> list = new ArrayList<>();
        if (object == null) return list;
        String obj = (String) object;
        if (obj.equals("[]"))return list;
        List<String> sers = new ArrayList<>(Arrays.asList(obj.split(";;")));
        for (String ser : sers) {
            list.add(Serializers.deserialize(ser));
        }
        return list;
    }
}