package sds.crystalfileparser.domain.core;

public class PropertyValue
{
    private String Name;
    private String Value;
    
    public PropertyValue(String name, String value){
        Name = name;
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public String getValue() {
        return Value;
    } 
    
}
