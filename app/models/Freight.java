package models;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by isatimur on 8/9/16.
 */
public class Freight implements Serializable {
    private static final AtomicLong GENERATED_ID = new AtomicLong();

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getEtsng() {
        return etsng;
    }

    public void setEtsng(String etsng) {
        this.etsng = etsng;
    }

    public String getGng() {
        return gng;
    }

    public void setGng(String gng) {
        this.gng = gng;
    }

    public String getEtsng6() {
        return etsng6;
    }

    public void setEtsng6(String etsng6) {
        this.etsng6 = etsng6;
    }

    @QuerySqlField(index = true)
    private Long key;

    @QuerySqlField
    private String name;

    @QuerySqlField(name = "class")
    private String _class;

    @QuerySqlField(name = "groupp")
    private Long group;

    @QuerySqlField
    private String etsng;

    @QuerySqlField
    private String gng;

    @QuerySqlField
    private String etsng6;

    public Freight(String name, String _class, Long group, String etsng, String gng, String etsng6) {
        this.key = GENERATED_ID.incrementAndGet();
        this.name = name;
        this._class = _class;
        this.group = group;
        this.etsng = etsng;
        this.gng = gng;
        this.etsng6 = etsng6;
    }

    @Override
    public String toString() {
        return "Freight{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", _class='" + _class + '\'' +
                ", group=" + group +
                ", etsng='" + etsng + '\'' +
                ", gng='" + gng + '\'' +
                ", etsng6='" + etsng6 + '\'' +
                '}';
    }

}
