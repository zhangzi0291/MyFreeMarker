package my.freemarker.util;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class JavaTypeNameTranslator {
    private static Map<Integer, String> typeToName;
    private static Map<String, Integer> nameToType;
    
    static {
        typeToName = new HashMap<Integer, String>();
        typeToName.put(Types.ARRAY, "Object"); //$NON-NLS-1$
        typeToName.put(Types.BIGINT, "Long"); //$NON-NLS-1$
        typeToName.put(Types.BINARY, "byte[]"); //$NON-NLS-1$
        typeToName.put(Types.BIT, "Boolean"); //$NON-NLS-1$
        typeToName.put(Types.BLOB, "byte[]"); //$NON-NLS-1$
        typeToName.put(Types.BOOLEAN, "Boolean"); //$NON-NLS-1$
        typeToName.put(Types.CHAR, "String"); //$NON-NLS-1$
        typeToName.put(Types.CLOB, "String"); //$NON-NLS-1$
        typeToName.put(Types.DATALINK, "Object"); //$NON-NLS-1$
        typeToName.put(Types.DATE, "Date"); //$NON-NLS-1$
        typeToName.put(Types.DECIMAL, "BigDecimal"); //$NON-NLS-1$
        typeToName.put(Types.DISTINCT, "Object"); //$NON-NLS-1$
        typeToName.put(Types.DOUBLE, "Double"); //$NON-NLS-1$
        typeToName.put(Types.FLOAT, "Double"); //$NON-NLS-1$
        typeToName.put(Types.INTEGER, "Integer"); //$NON-NLS-1$
        typeToName.put(Types.JAVA_OBJECT, "Object"); //$NON-NLS-1$
        typeToName.put(Types.LONGVARBINARY, "byte[]"); //$NON-NLS-1$
        typeToName.put(Types.LONGVARCHAR, "String"); //$NON-NLS-1$
        typeToName.put(Types.NCHAR, "String"); //$NON-NLS-1$
        typeToName.put(Types.NCLOB, "String"); //$NON-NLS-1$
        typeToName.put(Types.NVARCHAR, "String"); //$NON-NLS-1$
        typeToName.put(Types.LONGNVARCHAR, "String"); //$NON-NLS-1$
        typeToName.put(Types.NULL, "Object"); //$NON-NLS-1$
        typeToName.put(Types.NUMERIC, "Double"); //$NON-NLS-1$
        typeToName.put(Types.OTHER, "Object"); //$NON-NLS-1$
        typeToName.put(Types.REAL, "Float"); //$NON-NLS-1$
        typeToName.put(Types.REF, "Object"); //$NON-NLS-1$
        typeToName.put(Types.SMALLINT, "Short"); //$NON-NLS-1$
        typeToName.put(Types.STRUCT, "Object"); //$NON-NLS-1$
        typeToName.put(Types.TIME, "Date"); //$NON-NLS-1$
        typeToName.put(Types.TIMESTAMP, "Date"); //$NON-NLS-1$
        typeToName.put(Types.TINYINT, "Byte"); //$NON-NLS-1$
        typeToName.put(Types.VARBINARY, "byte[]"); //$NON-NLS-1$
        typeToName.put(Types.VARCHAR, "String"); //$NON-NLS-1$

        nameToType = new HashMap<String, Integer>();
        nameToType.put("Object", Types.ARRAY); //$NON-NLS-1$
        nameToType.put("Long", Types.BIGINT); //$NON-NLS-1$
        nameToType.put("byte[]", Types.BINARY); //$NON-NLS-1$
        nameToType.put("Boolean", Types.BIT ); //$NON-NLS-1$
        nameToType.put("byte[]" , Types.BLOB ); //$NON-NLS-1$
        nameToType.put("Boolean", Types.BOOLEAN) ; //$NON-NLS-1$
        nameToType.put("String",Types.CHAR); //$NON-NLS-1$
        nameToType.put("String",Types.CLOB); //$NON-NLS-1$
        nameToType.put("Object", Types.DATALINK); //$NON-NLS-1$
        nameToType.put("Date", Types.DATE ); //$NON-NLS-1$
        nameToType.put("BigDecimal",Types.DECIMAL ); //$NON-NLS-1$
        nameToType.put("Object", Types.DISTINCT ); //$NON-NLS-1$
        nameToType.put("Double", Types.DOUBLE ); //$NON-NLS-1$
        nameToType.put("Double", Types.FLOAT ); //$NON-NLS-1$
        nameToType.put("Integer", Types.INTEGER ); //$NON-NLS-1$
        nameToType.put("Object",Types.JAVA_OBJECT ); //$NON-NLS-1$
        nameToType.put("byte[]" ,Types.LONGVARBINARY); //$NON-NLS-1$
        nameToType.put("String", Types.LONGVARCHAR ); //$NON-NLS-1$
        nameToType.put("String", Types.NCHAR); //$NON-NLS-1$
        nameToType.put("String", Types.NCLOB); //$NON-NLS-1$
        nameToType.put("String", Types.NVARCHAR); //$NON-NLS-1$
        nameToType.put("String", Types.LONGNVARCHAR); //$NON-NLS-1$
        nameToType.put("Object", Types.NULL ); //$NON-NLS-1$
        nameToType.put("Double", Types.NUMERIC ); //$NON-NLS-1$
        nameToType.put("Object", Types.OTHER); //$NON-NLS-1$
        nameToType.put("Float", Types.REAL ); //$NON-NLS-1$
        nameToType.put("Object", Types.REF); //$NON-NLS-1$
        nameToType.put("Short", Types.SMALLINT ); //$NON-NLS-1$
        nameToType.put("Object", Types.STRUCT); //$NON-NLS-1$
        nameToType.put("Date", Types.TIME ); //$NON-NLS-1$
        nameToType.put("Date", Types.TIMESTAMP ); //$NON-NLS-1$
        nameToType.put("Byte", Types.TINYINT ); //$NON-NLS-1$
        nameToType.put("byte[]", Types.VARBINARY ); //$NON-NLS-1$
        nameToType.put("String", Types.VARCHAR ); //$NON-NLS-1$

    }
    public static String getJavaTypeName(int jdbcType) {
        String answer = typeToName.get(jdbcType);
        if (answer == null) {
            answer = "OTHER"; //$NON-NLS-1$
        }

        return answer;
    }
    public static int getJdbcType(String javaTypeName) {
        Integer answer = nameToType.get(javaTypeName);
        if (answer == null) {
            answer = Types.OTHER;
        }
        return answer;
    }
}
