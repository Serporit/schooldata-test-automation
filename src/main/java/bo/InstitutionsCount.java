package bo;

public class InstitutionsCount {
    private Integer districtsCount;
    private Integer schoolsCount;
    private Integer collegesCount;

    public InstitutionsCount(Integer districtsCount, Integer schoolsCount, Integer collegesCount) {
        this.districtsCount = districtsCount;
        this.schoolsCount = schoolsCount;
        this.collegesCount = collegesCount;
    }

    public boolean equals(InstitutionsCount obj) {
        return
                this.districtsCount.equals(obj.districtsCount) &&
                        this.schoolsCount.equals(obj.schoolsCount) &&
                        this.collegesCount.equals(obj.collegesCount);
    }
}
