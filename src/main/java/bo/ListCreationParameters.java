package bo;


public class ListCreationParameters {
    private String name;
    private String type;
    private boolean institutionOnly;
    private String institutionTypes;
    private String geography;
    private String personnel;
    private String names;
    private boolean parentPid;
    private boolean parentInst;
    private boolean ultParentPid;
    private boolean ultParentInst;

    public ListCreationParameters() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInstitutionOnly() {
        return institutionOnly;
    }

    public void setInstitutionOnly(boolean institutionOnly) {
        this.institutionOnly = institutionOnly;
    }

    public String getInstitutionTypes() {
        return institutionTypes;
    }

    public void setInstitutionTypes(String institutionTypes) {
        this.institutionTypes = institutionTypes;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public boolean isParentPid() {
        return parentPid;
    }

    public void setParentPid(boolean parentPid) {
        this.parentPid = parentPid;
    }

    public boolean isParentInst() {
        return parentInst;
    }

    public void setParentInst(boolean parentInst) {
        this.parentInst = parentInst;
    }

    public boolean isUltParentPid() {
        return ultParentPid;
    }

    public void setUltParentPid(boolean ultParentPid) {
        this.ultParentPid = ultParentPid;
    }

    public boolean isUltParentInst() {
        return ultParentInst;
    }

    public void setUltParentInst(boolean ultParentInst) {
        this.ultParentInst = ultParentInst;
    }
}