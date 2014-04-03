//------------------------------------------------------------------------------
// <copyright project="BEmuJava" file="BEmu.ReferenceDataRequest.ElementReferenceArrayChainTickers.java" company="Jordan Robinson">
//     Copyright (c) 2013 Jordan Robinson. All rights reserved.
//
//     The use of this software is governed by the Microsoft Public License
//     which is included with this distribution.
// </copyright>
//------------------------------------------------------------------------------

package com.bemu.BEmu.ReferenceDataRequest;

import java.util.ArrayList;
import java.util.Calendar;
import com.bemu.BEmu.Datetime;
import com.bemu.BEmu.Name;
import com.bemu.BEmu.SchemaTypeDefinition;
import com.bemu.BEmu.types.RandomDataGenerator;

public class ElementReferenceArrayChainTickers extends ElementReferenceArray
{
	public ElementReferenceArrayChainTickers(String underlier, int numPoints, String strDtExp, OptionalityEnum optionality) throws Exception
	{
		super("CHAIN_TICKERS", new ArrayList<ElementParent>());
		
		int indexSpace = underlier.indexOf(' ');
		String ticker = underlier.substring(0, indexSpace);
		
		Datetime dtExp;
		if(strDtExp == null)
		{
			dtExp = new Datetime();
			dtExp.calendar().add(Calendar.MONTH, 1);
			dtExp.calendar().set(Calendar.DAY_OF_MONTH, 20); //assume the 20th of the month
		}
		else if(strDtExp.length() == 8)
		{
			dtExp = com.bemu.BEmu.types.DisplayFormats.HistRef_TryParseInput(strDtExp);
		}
		else if(strDtExp.length() == 6)
		{
			dtExp = com.bemu.BEmu.types.DisplayFormats.HistRef_TryParseInput(strDtExp + "20");
		}
		else if (strDtExp.endsWith("F"))
		{
			String strNumMonths = strDtExp.substring(0, strDtExp.length() - 2);
			int numMonths = Integer.parseInt(strNumMonths);
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.add(Calendar.MONTH, numMonths);
			c.set(Calendar.DAY_OF_MONTH, 20);
			dtExp = new Datetime(c);
		}
		else
		{
			throw new Exception("BEmu.ReferenceDataRequest.ElementReferenceArrayChainTickers: unable to determine the dtExp");
		}

        int strike = RandomDataGenerator.strike();
        for (int count = 0; count < numPoints; count++, strike += 5)
        {
        	ElementParent elm = new ElementReferenceArrayChainTickersItem(ticker, dtExp, optionality, strike);        	
        	super._values.add(elm);
        }
	}
    
    public SchemaTypeDefinition typeDefinition() throws Exception
    {
    	return new SchemaTypeDefinition(this.datatype(), new Name("CHAIN_TICKERS"));
    }
}
