package gridlock;

import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class GridLockRules implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		// TODO Auto-generated method stub
		return null;
	}

}
