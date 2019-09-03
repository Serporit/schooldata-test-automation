package bo;


public class ListCreationParametersBuilder {
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

    private ListCreationParametersBuilder() {
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
        return new ListCreationParametersBuilder().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withName(String name) {
            ListCreationParametersBuilder.this.name = name;
            return this;
        }

        public Builder withType(String type) {
            ListCreationParametersBuilder.this.type = type;
            return this;
        }

        public Builder withInstitutionOnly() {
            ListCreationParametersBuilder.this.institutionOnly = true;
            return this;
        }

        public Builder withInstitutionTypes(String institutionTypes) {
            ListCreationParametersBuilder.this.institutionTypes = institutionTypes;
            return this;
        }

        public Builder withGeography(String geography) {
            ListCreationParametersBuilder.this.geography = geography;
            return this;
        }

        public Builder withPersonnel(String personnel) {
            ListCreationParametersBuilder.this.personnel = personnel;
            return this;
        }

        public Builder withNames(String names) {
            ListCreationParametersBuilder.this.names = names;
            return this;
        }

        public Builder withParentPID() {
            ListCreationParametersBuilder.this.parentPid = true;
            return this;
        }

        public Builder withParentInst() {
            ListCreationParametersBuilder.this.parentInst = true;
            return this;
        }

        public Builder withUltParentPID() {
            ListCreationParametersBuilder.this.ultParentPid = true;
            return this;
        }

        public Builder withUltParentInst() {
            ListCreationParametersBuilder.this.ultParentInst = true;
            return this;
        }

        public ListCreationParametersBuilder build() {
            return ListCreationParametersBuilder.this;
        }
    }
}
