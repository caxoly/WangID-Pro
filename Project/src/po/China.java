package po;

public class China {
    private Integer Id;
    private String Name;
    private Integer Pid;
    private Integer level;

    /**
     * 获取地址编号
     * @return
     */
    public Integer getId() {
        return Id;
    }

    /**
     * 设置地址编号
     * @param id
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * 获取省/市/区或县名称
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     * 设置省/市/区或县名称
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * 获取对应上一级的ID
     * @return
     */
    public Integer getPid() {
        return Pid;
    }

    /**
     *设置对应上一级的ID
     * @param pid
     */
    public void setPid(Integer pid) {
        Pid = pid;
    }

    /**
     * 获取地址等级
     * @return
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *设置地址等级
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}
