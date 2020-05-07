package com.caopan.TrainSys.model;



public class Selection {
    private long selectionId;
    private String selectionContent;
    private Integer isRight;
    private long quesId;

    public long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(long selectionId) {
        this.selectionId = selectionId;
    }

    public String getSelectionContent() {
        return selectionContent;
    }

    public void setSelectionContent(String selectionContent) {
        this.selectionContent = selectionContent;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }

    public long getQuesId() {
        return quesId;
    }

    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }
}
