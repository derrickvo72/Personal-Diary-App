package android.btth.notemanagementsystem.Controller;

import android.widget.TextView;

import java.util.List;

public abstract class Manage {
    public abstract String add(Object object);

    public abstract String edit(Object id, Object object);

    public abstract String delete(Object id);

    public abstract List<Object> getAll();

}
