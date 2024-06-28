package com.example.testfragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable {
    String subject;
    ArrayList<Theme> themes = new ArrayList<Theme>();

}

class Theme {
    String name;
    ArrayList<Question> questions = new ArrayList<Question>();
}

class Question {
    String text;
    String answer;
}
