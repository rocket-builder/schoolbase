package com.rocketbuilder.schoolbase.models;

public class Response {

    private String text;
    private boolean isError;
    private Object content;

    public Response(String text, boolean isError) {
        this.text = text;
        this.isError = isError;
    }

    public Response(String text, boolean isError, Object content) {
        this.text = text;
        this.isError = isError;
        this.content = content;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isError() { return isError; }
    public void setError(boolean error) { isError = error; }

    public Object getContent() { return content; }
    public void setContent(Object content) { this.content = content; }
}
