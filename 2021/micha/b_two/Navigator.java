package com.company.micha.b_two;

import java.math.BigInteger;

public class Navigator {

  String direction;
  BigInteger unit;

  public Navigator(String direction, BigInteger unit) {
    this.direction = direction;
    this.unit = unit;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public BigInteger getUnit() {
    return unit;
  }

  public void setUnit(BigInteger unit) {
    this.unit = unit;
  }
}
