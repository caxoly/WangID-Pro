package po;

import java.util.List;

public class TypeTwo {
    private Integer typet_id;
    private String typet_name;
    private Integer pren_id;
    List<TypeTwo> tlist;

    public Integer getTypet_id() {
        return typet_id;
    }

    public void setTypet_id(Integer typet_id) {
        this.typet_id = typet_id;
    }

    public String getTypet_name() {
        return typet_name;
    }

    public void setTypet_name(String typet_name) {
        this.typet_name = typet_name;
    }

    public Integer getPren_id() {
        return pren_id;
    }

    public void setPren_id(Integer pren_id) {
        this.pren_id = pren_id;
    }

    public List<TypeTwo> getTlist() {
        return tlist;
    }

    public void setTlist(List<TypeTwo> tlist) {
        this.tlist = tlist;
    }
}
