package com.adobe.aem.guides.wknd.core.classes;

public class ImageGrid {

    private String title;
    private String fileReference; //immagine
    private String link;

    public ImageGrid() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
