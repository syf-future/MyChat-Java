package com.syf.chat.common.utils;

public class SequenceTool {
    public static IdWork idWork;

    public static String nextId(){
        if(idWork == null){
            synchronized (SequenceTool.class){
                if(idWork == null){
                    idWork = new IdWork(0, 0);
                }
            }
        }
        return String.valueOf(idWork.nextId());
    }
}
