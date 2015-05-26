package br.com.sistema.siefcotacao.beans;

/**
 * Created by root on 05/05/15.
 */
public class RowItem_ListView_Main {
    private int imageId;
    private String title;

    public RowItem_ListView_Main(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
