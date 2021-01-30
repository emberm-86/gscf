package com.interview.gscf.pojo;

public class Room {

    public int l;
    public int w;
    public int h;

    public Room(int l, int w, int h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Room{ l=" + l + ", w=" + w + ", h=" + h + '}';
    }
}