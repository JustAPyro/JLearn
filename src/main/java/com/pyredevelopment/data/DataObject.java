package com.pyredevelopment.data;

public interface DataObject {

    public abstract void drop(int rowOrCol, int ... labels);

    public abstract void dropRows(int ... labels);

    public abstract void dropColumns(int ... labels);

    public abstract void dropColumns(String ... labels);

    public abstract void concat(DataObject data, int rowOrCol);

    public abstract void concatRows(DataObject data);

    public abstract void concatColumns(DataObject data);

    public abstract String shape();

    public abstract String info();

    public abstract String head();

    public abstract String head(int numberRows);

    public abstract String tail();

    public abstract String tail(int numberRows);

}
