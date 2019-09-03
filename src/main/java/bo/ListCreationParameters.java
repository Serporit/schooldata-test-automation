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

    private ListCreationParameters() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isInstitutionOnly() {
        return institutionOnly;
    }

    public String getInstitutionTypes() {
        return institutionTypes;
    }

    public String getGeography() {
        return geography;
    }

    public String getPersonnel() {
        return personnel;
    }

    public String getNames() {
        return names;
    }

    public boolean isParentPid() {
        return parentPid;
    }

    public boolean isParentInst() {
        return parentInst;
    }

    public boolean isUltParentPid() {
        return ultParentPid;
    }

    public boolean isUltParentInst() {
        return ultParentInst;
    }

    public static Builder builder() {
        return new ListCreationParameters().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withName(String name) {
            ListCreationParameters.this.name = name;
            return this;
        }

        public Builder withType(String type) {
            ListCreationParameters.this.type = type;
            return this;
        }

        public Builder withInstitutionOnly() {
            ListCreationParameters.this.institutionOnly = true;
            return this;
        }

        public Builder withInstitutionTypes(String institutionTypes) {
            ListCreationParameters.this.institutionTypes = institutionTypes;
            return this;
        }

        public Builder withGeography(String geography) {
            ListCreationParameters.this.geography = geography;
            return this;
        }

        public Builder withPersonnel(String personnel) {
            ListCreationParameters.this.personnel = personnel;
            return this;
        }

        public Builder withNames(String names) {
            ListCreationParameters.this.names = names;
            return this;
        }

        public Builder withParentPID() {
            ListCreationParameters.this.parentPid = true;
            return this;
        }

        public Builder withParentInst() {
            ListCreationParameters.this.parentInst = true;
            return this;
        }

        public Builder withUltParentPID() {
            ListCreationParameters.this.ultParentPid = true;
            return this;
        }

        public Builder withUltParentInst() {
            ListCreationParameters.this.ultParentInst = true;
            return this;
        }

        public ListCreationParameters build() {
            return ListCreationParameters.this;
        }
    }
}
