package test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropModel {
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private String message;
	
	
	public void updateText (String text) {
		changes.firePropertyChange(message, message, message+=text);
	}
	
	public void addPropertyChangeListener (PropertyChangeListener p) {
		changes.addPropertyChangeListener(p);
	}
	
	public void removePropertyChangeListener (PropertyChangeListener p) {
		changes.removePropertyChangeListener(p);
	}

}
