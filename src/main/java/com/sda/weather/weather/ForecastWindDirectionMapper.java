package com.sda.weather.weather;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForecastWindDirectionMapper {

    List<QuadrantItem> quadrants;
    String[] QuadrantNames = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    ForecastWindDirectionMapper() {
        double leftValue = 360 - 22.5;
        double rightValue = 22.5;
        double step = 45;
        quadrants = new ArrayList<QuadrantItem>();
        for (String qname : QuadrantNames) {
            QuadrantItem quadrantItem = null;
            if (qname == "N")
                quadrantItem = new QuadrantItem(qname, leftValue, rightValue, "or");
            else {
                leftValue = rightValue;
                rightValue = leftValue + step;
                quadrantItem = new QuadrantItem(qname, leftValue, rightValue, "and");
            }
            quadrants.add(quadrantItem);
        }
    }

    String getQuadrantName(double degree) {
        for (QuadrantItem quadrantItem : quadrants)
            if (quadrantItem.isIn(degree))
                return quadrantItem.m_quadrantName;
        return "";
    }

    public static class QuadrantItem {
        String m_quadrantName;
        String m_operation; //or , and
        double m_left;
        double m_right;
        HashMap<String, QuadrantItem> quadrants;

        QuadrantItem(String name, double left, double right, String operation) {
            m_quadrantName = name;
            m_operation = operation;
            m_left = left;
            m_right = right;
        }

        boolean isIn(double degree) {
            if (m_operation == "and")
                return degree > m_left && degree < m_right;
            else
                return degree > m_left || degree < m_right;
        }
    }

}

