package com.example.hajozatuser.Model;

public class comments {

    public String Name;
    public String Comment;

    public comments(String name, String comment) {
        Name = name;
        Comment = comment;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
