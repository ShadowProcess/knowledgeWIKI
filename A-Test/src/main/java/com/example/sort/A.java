package com.example.sort;

import java.math.BigDecimal;

public class A  implements Comparable<A>{
    private Long id;
    private BigDecimal b;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    @Override
    public int compareTo(A o) {
        return b.compareTo(o.getB());
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", b=" + b +
                '}';
    }
}
