package org.mspring.mlog.api.kuaipan.json;

public interface JSONErrorListener {
    void start(String text);
    void error(String message, int column);
    void end();
}