package com.andre.core.model;

/**
 * 基础数据对象。
 * 
 * @author Andre
 * @version $Revision: 1.19 $ $Author: hyb $ $Date: 2011/06/01 20:51:36 $
 */
public abstract class DataObject extends BaseObject {

	private static final long serialVersionUID = -8474348103484447819L;

	private static final long defaultID = -9999;

	private String objKey;

	public String getObjKey() {
		return objKey;
	}

	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}

	public Long getId() {
		return defaultID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DataObject)) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		final DataObject other = (DataObject) o;

		final Long id = getId();
		if (id != null && id != defaultID) {
			return id.equals(other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = getId() == null ? 0 : getId();
		result = prime * result;
		return (int) result;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}

	public String simpleClassName() {
		String className = getClass().getSimpleName();
		int index = className.indexOf("_$");
		if (index != -1) {
			return className.substring(0, index);
		}
		return className;
	}

}
