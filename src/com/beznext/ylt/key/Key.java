package com.beznext.ylt.key;

public class Key {	

	/**
	 * Process Id of the metric
	 */
	private String m_Id;

	/**
	 * Process user of the metric
	 */
	private String m_User;

	public String getId() {
		return m_Id;
	}

	public void setId(String m_Id) {
		this.m_Id = m_Id;
	}

	public String getUser() {
		return m_User;
	}

	public void setUser(String m_User) {
		this.m_User = m_User;
	}

	public Key(String Id, String user) {
		m_Id = Id;
		m_User = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (m_Id == null) {
			if (other.m_Id != null)
				return false;
		} else if (!m_Id.equals(other.m_Id))
			return false;
		if (m_User == null) {
			if (other.m_User != null)
				return false;
		} else if (!m_User.equals(other.m_User))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_Id == null) ? 0 : m_Id.hashCode());
		result = prime * result + ((m_User == null) ? 0 : m_User.hashCode());
		return result;
	}	

}
