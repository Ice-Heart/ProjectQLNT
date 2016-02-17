/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

/**
 *
 * @author lehoang
 */
public class RoomStatus {
    private String label, value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RoomStatus(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }
    
    
}
