package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.ArrayList;

public class Pager<T> implements Serializable {

    public long pageNum;
    public long pageSize;
    public long size;
    public long startRow;
    public long endRow;
    public long total;
    public long pages;
    public long firstPage;
    public long prePage;
    public long nextPage;
    public long lastPage;
    public boolean isFirstPage;
    public boolean isLastPage;
    public ArrayList<T> list;

    public Pager() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}