package vn.edu.tdtu.bai2_lab5;

public class DataItem {
    private String name;
    private String place;
    private String date;
    private String time;
    private boolean isChecked; // Biến để lưu trạng thái check

    public DataItem(String name, String place, String date, String time) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.isChecked = true; // Ban đầu, mục chưa được check
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

