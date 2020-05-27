public class Value {

    private enum Type {
        INTEGER, FLOAT, BOOLEAN, STRING
    }


    private Type type;
    private Object value;
    private String ident;


    public Object getValue() {
        switch (type) {
            case INTEGER:
                return new Integer(value.toString());
            case FLOAT:
                return new Float(value.toString());
            case BOOLEAN:
                return new Boolean(value.toString());
            case STRING:
                return new String(value.toString());
        }
        return null;
    }

    public void setValue(Object value) {
         this.value = value;
    }

    public String getType() {
        return type.toString();
    }

    public Value(String ident) {
        this.ident = ident;
    }

    public Value(String ident, String type, Object value) {
        this.ident = ident;
        this.type = SetType(type);
        this.value = value;
    }

    public String getIdent() {
        return this.ident;
    }


    public void setIdent(String ident) {
        this.ident = ident;
    }

    private Type SetType(String type) {
        switch (type) {
            case "INTEGER":
                return Type.INTEGER;
            case "FLOAT":
                return Type.FLOAT;
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