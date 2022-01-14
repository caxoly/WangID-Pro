package po;

import java.util.List;

public class AllType {
    private Integer all_id;
    private String all_name;
    private Integer type_id;
    private Integer af_id;
    List<AllType> allList;

    public List<AllType> getAllList() {
        return allList;
    }

    public void setAllList(List<AllType> allList) {
        this.allList = allList;
    }

    public Integer getAll_id() {
        return all_id;
    }

    public void setAll_id(Integer all_id) {
        this.all_id = all_id;
    }

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getAf_id() {
        return af_id;
    }

    public void setAf_id(Integer af_id) {
        this.af_id = af_id;
    }
}




