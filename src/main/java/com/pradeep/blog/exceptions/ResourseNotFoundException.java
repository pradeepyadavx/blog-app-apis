package com.pradeep.blog.exceptions;

public class ResourseNotFoundException extends RuntimeException {

    private String resourceName;
    private String fielddName;
    private long fiedlValue;


    public ResourseNotFoundException(String resourceName, String fielddName, long fiedlValue) {
        super(String.format("%s not found with %s : %d", resourceName, fielddName, fiedlValue));
        this.resourceName = resourceName;
        this.fielddName = fielddName;
        this.fiedlValue = fiedlValue;
    }


}
