/*
 * eID PKI RA Project.
 * Copyright (C) 2010-2014 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see
 * http://www.gnu.org/licenses/.
 */
package be.fedict.eid.pkira.reports;

import java.math.BigInteger;

import be.fedict.eid.pkira.generated.reports.ObjectFactory;
import be.fedict.eid.pkira.generated.reports.SuccessFailureCountType;


public class BuilderUtil {

	public static SuccessFailureCountType createSuccessFailureCountType(int successes, int failures) {
		SuccessFailureCountType result = new ObjectFactory().createSuccessFailureCountType();
		result.setSuccess(BigInteger.valueOf(successes));
		result.setFailure(BigInteger.valueOf(failures));
		result.setTotal(BigInteger.valueOf(successes+failures));
		
		return result;
	}
}
