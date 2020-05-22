public class Value {

    private enum Type {
        INTEGER, DOUBLE, BOOLEAN, STRING
    }


    private Type type;
    private Object value;
    private String ident;
    private boolean const_;


    public Object getValue() {
        switch (type) {
            case INTEGER:
                return new Integer(value.toString());
            case DOUBLE:
                return new Double(value.toString());
            case BOOLEAN:
                return new Boolean(value.toString());
            case STRING:
                return new String(value.toString());
        }
        return null;
    }

    public void setValue(Object value) {
        if (!this.const_) this.value = value;
    }

    public String getType() {
        return type.toString();
    }

    public Value(String ident) {
        this.ident = ident;
        this.const_ = false;
    }

    public Value(String ident, String type, Object value, Boolean const_) {
        this.ident = ident;
        this.type = SetType(type);
        this.value = value;
        this.const_ = const_;
    }

    public String getIdent() {
        return this.ident;
    }

    public Boolean isConst() {
        return this.const_;
    }

    private Type SetType(String type) {
        switch (type) {
            case "INTEGER":
                return Type.INTEGER;
            case "DOUBLE":
                return Type.DOUBLE;
            case "BOOLEAN":
                return Type.BOOLEAN;
            case "STRING":
                return Type.STRING;
        }
        return null;
    }

    @Override
    public String toString() {
        if (this.value != null) {
            return this.value.toString();
        }
        else {
            return null;
        }
    }
}