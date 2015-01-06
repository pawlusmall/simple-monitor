package com.pawlusmall.sm.model;

/**
 *
 * @author pawlusmall
 */
public enum Method {

    PING("Ping"),
    RESPONSE_REGEX("Resp. REGEX");
    
    private final String label;
    
    Method(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
    
}
