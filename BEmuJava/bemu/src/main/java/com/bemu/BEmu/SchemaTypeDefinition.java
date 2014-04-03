package com.bemu.BEmu;

public class SchemaTypeDefinition
{
	public SchemaTypeDefinition(Schema.Datatype datatype) throws Exception
	{
	    this._datatype = datatype;
	    this._name = new Name(this._datatype.toString());
	}
	
	public SchemaTypeDefinition(Schema.Datatype datatype, Name name)
	{
	    this._datatype = datatype;
	    this._name = name;
	}
	
	private final Schema.Datatype _datatype;
	private final Name _name;
	
	public Name name()
	{
		return this._name;
	}
	
	public String ToString()
	{
	    return String.format("Type %s{1}", this._datatype.toString(), System.getProperty("line.separator"));
	}
}
