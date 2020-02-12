package com.example.loginproject.Models;

public class SliderAds {
    int imagageSlide ;
    String headingSlide ;


    public SliderAds(int imagageSlide, String headingSlide) {
        this.imagageSlide = imagageSlide;
        this.headingSlide = headingSlide;
    }

    public int getImagageSlide() {
        return imagageSlide;
    }

    public void setImagageSlide(int imagageSlide) {
        this.imagageSlide = imagageSlide;
    }

    public String getHeadingSlide() {
        return headingSlide;
    }

    public void setHeadingSlide(String headingSlide) {
        this.headingSlide = headingSlide;
    }
}
