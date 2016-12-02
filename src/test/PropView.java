package test;

import java.awt.TextField;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

public class PropView extends JPanel implements PropertyChangeListener{
	
	private PropModel propModel;
	private TextField textField;
	
	public PropView (PropModel propModel) {
		this.propModel = propModel;
		this.setSize(50, 50);
		this.textField = new TextField(50);
		this.add(textField);
		propModel.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		textField.setText((String) arg0.getNewValue());
		
	}

}
